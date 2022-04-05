package ru.otus.taskChecker.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Set;

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

        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.GET)
                .path("/_ping")
                .build();

        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);

        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            System.out.println("BUNS "+response.getStatusCode());
            System.out.println("BUNS "+response.getBody());
        }

//        Object response = dockerClient.buildImageCmd(new File(dockerfile))
//                .withTags(Set.of("buns:0.0.1"))
//                .start().awaitImageId();

//        dockerClient.logContainerCmd("").exec(new Ob);

//        System.out.println("BUNSBUNS"+response);
        return null;
    }
}
