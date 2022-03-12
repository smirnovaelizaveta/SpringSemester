package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookService.repository.AuthorRepository;
import ru.otus.spring.bookService.repository.BookGenreRepository;
import ru.otus.spring.bookService.repository.BookRepository;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.BookGenre;
import ru.otus.spring.rest.dto.BookDto;
import ru.otus.spring.rest.NotFoundException;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookGenreRepository bookGenreRepository;

    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository, BookGenreRepository bookGenreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookGenreRepository = bookGenreRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String insert(String bookTitle, String bookAuthor, String bookGenreName) {
        Author author = new Author(bookAuthor);
        BookGenre bookGenre = new BookGenre(bookGenreName);
        return bookRepository.save(new Book(bookTitle, author, bookGenre)).toString();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book update(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(NotFoundException::new);
        BookGenre bookGenre = bookGenreRepository.findById(bookDto.getBookGenreId()).orElseThrow(NotFoundException::new);
        Book book = bookRepository.findById(bookDto.getId()).orElse(new Book());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        book.setBookGenre(bookGenre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public String delete(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + id));
        bookRepository.deleteById(id);
        return book.toString();
    }

}
