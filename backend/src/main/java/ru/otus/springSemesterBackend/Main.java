package ru.otus.springSemesterBackend;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import ru.otus.springSemesterBackend.mappers.TaskMapper;
import ru.otus.springSemesterBackend.services.UserService;

import javax.annotation.PostConstruct;

@Slf4j
@EnableKafka
@SpringBootApplication
public class Main {

        private final UserService userService;
    public Main(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

        @PostConstruct
    public void init() {

//        userService.insert(new User("login", "password", Arrays.asList(new Role("ADMIN"))));
    }
}
