package ru.otus.spring.bookService;

import ru.otus.spring.domain.book.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    String comment(long bookId, String text);

    String deleteComment(long bookId, long commentId);

    Optional<Comment> updateComment(Comment comment);

    Optional<Comment> getComment(long id);

    List<Comment> findAll(long bookId);
}
