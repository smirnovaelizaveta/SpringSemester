package ru.otus.spring;

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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.Tasks.FileStorageProperties;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableKafka
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
