package ru.otus.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.bookService.AuthorService;
import ru.otus.spring.bookService.BookGenreService;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.BookGenre;
import ru.otus.spring.rest.dto.BookDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(Book.class);


    private final BookService bookService;
    private final AuthorService authorService;
    private final BookGenreService bookGenreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, BookGenreService bookGenreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookGenreService = bookGenreService;
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/api/authors")
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/api/bookGenres")
    public List<BookGenre> getBookGenres() {
        return bookGenreService.findAll();
    }

    @PostMapping("/api/books")
    public Book addBook(@Valid @RequestBody BookDto bookDto) {
        log.info("Request to create book: {}", bookDto);
        return bookService.update(bookDto);
    }

    @PutMapping("/api/books/{id}")
    public Book addBook(@Valid @RequestBody BookDto bookDto, @PathVariable Long id) {
        bookDto.setId(id);
        log.info("Request to edit book: {}", bookDto);
        return bookService.update(bookDto);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Request to delete bookId: {}", id);
        bookService.delete(id);
    }

}
