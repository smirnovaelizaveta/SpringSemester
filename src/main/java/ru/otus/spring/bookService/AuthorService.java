package ru.otus.spring.bookService;

import ru.otus.spring.domain.book.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
}
