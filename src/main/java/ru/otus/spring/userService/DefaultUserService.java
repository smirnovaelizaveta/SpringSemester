package ru.otus.spring.userService;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.book.Author;
import ru.otus.spring.domain.book.Book;
import ru.otus.spring.domain.book.BookGenre;
import ru.otus.spring.domain.user.User;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }
}
