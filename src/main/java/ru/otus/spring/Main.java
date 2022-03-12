package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.user.Role;
import ru.otus.spring.domain.user.User;
import ru.otus.spring.userService.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private final BookService bookService;
    private final UserService userService;

    public Main(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {

        bookService.insert("Thinking in Java", "Bruce Eckel", "Java");
        bookService.insert("Spring in Action", "Craig Walls", "Spring");

        userService.insert(new User("login", "password", Arrays.asList(new Role("ADMIN"))));
    }
}
