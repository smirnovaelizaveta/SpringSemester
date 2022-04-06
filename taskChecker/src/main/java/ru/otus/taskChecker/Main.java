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
import ru.otus.taskChecker.service.DefaultDockerService;
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

        String logs = new DefaultDockerService().runAndGetLog(projectRoot);
        System.out.println("logs = " + logs);
    }
}
