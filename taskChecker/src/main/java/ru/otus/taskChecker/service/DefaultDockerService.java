package ru.otus.taskChecker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.WaitContainerResultCallback;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultDockerService implements DockerService {
    @Override
    public String runAndGetLog(File projectRoot) {
        String dockerfile = projectRoot.getPath()+"/Dockerfile";
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);

        String imageId = dockerClient.buildImageCmd(new File(dockerfile))
                .start().awaitImageId();

        CreateContainerResponse container
                = dockerClient.createContainerCmd(imageId)
                .exec();

        dockerClient.startContainerCmd(container.getId()).exec();

        WaitContainerResultCallback resultCallback = new WaitContainerResultCallback();
        dockerClient.waitContainerCmd(container.getId()).exec(resultCallback);
        try {
            resultCallback.awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        final List<String> logs = new ArrayList<>();

        try {
            dockerClient.logContainerCmd(container.getId()).withStdOut(true).withStdErr(true).exec(new ResultCallback.Adapter<Frame>() {
                @Override
                public void onNext(Frame item) {
                    logs.add(item.toString());
                }
            }).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        dockerClient.removeContainerCmd(container.getId()).exec();
        return logs.stream()
                .map(line -> line.replaceAll("STDOUT: |STDERR: ", ""))
                .collect(Collectors.joining("\n"));

    }
}
