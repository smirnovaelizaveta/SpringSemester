package ru.otus.spring.bookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.bookService.repository.BookRepository;
import ru.otus.spring.bookService.repository.CommentRepository;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.Comment;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultCommentService implements CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public DefaultCommentService(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public String comment(long bookId, String text) {
        Comment comment = new Comment(text);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.addComment(comment);
        bookRepository.save(book);
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).toString();
    }

    @Override
    @Transactional
    public String deleteComment(long bookId, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with commentId " + commentId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId));
        book.removeComment(comment);
        bookRepository.save(book);
        commentRepository.deleteById(commentId);
        return comment.toString();
    }

    @Override
    @Transactional
    public Optional<Comment> updateComment(Comment comment) {
    commentRepository.save(comment);
        return commentRepository.findById(comment.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getComment(long id) {
        return commentRepository.findById(id);
    }


    @Override
    @Transactional
    public List<Comment> findAll(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("No such book with bookId " + bookId)).getComments();
    }
}
