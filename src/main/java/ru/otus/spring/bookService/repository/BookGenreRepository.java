package ru.otus.spring.bookService.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.book.BookGenre;

import java.util.List;

public interface BookGenreRepository extends CrudRepository<BookGenre, Long> {

    List<BookGenre> findAll();


}
