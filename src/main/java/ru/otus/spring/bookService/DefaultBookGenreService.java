package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.domain.book.BookGenre;

import java.util.List;

@Service
public class DefaultBookGenreService implements BookGenreService{

    private final BookGenreRepository bookGenreRepository;

    public DefaultBookGenreService(BookGenreRepository bookGenreRepository) {
        this.bookGenreRepository = bookGenreRepository;
    }

    @Override
    public List<BookGenre> findAll() {
        return bookGenreRepository.findAll();
    }
}
