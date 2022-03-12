package ru.otus.spring.bookService;

import ru.otus.spring.domain.book.BookGenre;

import java.util.List;

public interface BookGenreService {

    List<BookGenre> findAll();

}
