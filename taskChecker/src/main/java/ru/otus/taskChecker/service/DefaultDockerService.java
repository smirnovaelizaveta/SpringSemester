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

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

//        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
//                .method(DockerHttpClient.Request.Method.GET)
//                .path("/_ping")
//                .build();
//
//
//        try (DockerHttpClient.Response response = httpClient.execute(request)) {
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//        }
        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);

        String imageId = dockerClient.buildImageCmd(new File(dockerfile))
                .withTags(Set.of("buns:0.0.1"))
                .start().awaitImageId();

        CreateContainerResponse container
                = dockerClient.createContainerCmd(imageId)
//                .withCmd("--bind_ip_all")
//                .withName("mongo")
//                .withHostName("baeldung")
//                .withEnv("MONGO_LATEST_VERSION=3.6")
//                .withPortBindings(PortBinding.parse("9999:27017"))
//                .withBinds(Bind.parse("/Users/baeldung/mongo/data/db:/data/db"))
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

//        System.out.println("BUNSBUNS"+response);
        return logs.stream()
                .collect(Collectors.joining("\n"));

    }
}