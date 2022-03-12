package ru.otus.spring.bookService;

import ru.otus.spring.domain.book.Book;
import ru.otus.spring.rest.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    String insert(String bookTitle, String bookAuthor, String bookGenreName);

    Optional<Book> getById(long id);

    Book update(BookDto book);

    String delete(long id);
}
