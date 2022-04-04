package ru.otus.springSemesterBackend.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.otus.springSemesterBackend.domain.user.Role;
import ru.otus.springSemesterBackend.domain.user.User;
import ru.otus.springSemesterBackend.domain.user.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserDetailsService userDetailsService;
    private final DefaultUserService userService;

    @Autowired
    public UserController(UserDetailsService userDetailsService, DefaultUserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userDetailsService.loadUserByUsername(user.getUsername()).getPassword().equals(user.getPassword());
//        return
//                user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @PostMapping("/user")
    public void register(@RequestBody UserDto userDto) {
        userService.insert(new User(userDto.getUsername(), userDto.getPassword(), List.of(new Role("User"))));
    }
}
