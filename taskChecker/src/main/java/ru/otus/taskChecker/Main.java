package ru.otus.taskChecker;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import ru.otus.spring.bookService.BookService;
//import ru.otus.spring.domain.user.Role;
//import ru.otus.spring.domain.user.User;
//import ru.otus.spring.userService.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.Arrays;
//
//@SpringBootApplication
//public class Main {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Main.class);
//    }
//
//    private final BookService bookService;
//    private final UserService userService;
//
//    public Main(BookService bookService, UserService userService) {
//        this.bookService = bookService;
//        this.userService = userService;
//    }
//
//    @PostConstruct
//    public void init() {
//
//        bookService.insert("Thinking in Java", "Bruce Eckel", "Java");
//        bookService.insert("Spring in Action", "Craig Walls", "Spring");
//
//        userService.insert(new User("login", "password", Arrays.asList(new Role("ADMIN"))));
//    }
//}

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.util.ResourceUtils;
import ru.otus.taskChecker.model.CheckResult;
import ru.otus.taskChecker.service.DefaultZipExtractor;
import ru.otus.taskChecker.service.MavenInvokeService;
import ru.otus.taskChecker.service.SolutionProcessorFacade;

import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
//@EnableKafka
//@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {

        byte[] zip = new ClassPathResource("tasks/task1.zip").getInputStream().readAllBytes();

        File projectRoot = new DefaultZipExtractor().extract(zip);
        new MavenInvokeService().build(projectRoot);

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

        Object response = dockerClient.buildImageCmd(projectRoot)
                .withTags(Set.of("buns:0.0.1"))
                .start().awaitImageId();

//        dockerClient.logContainerCmd("").exec(new Ob);

        System.out.println("BUNSBUNS"+response);
    }
}
