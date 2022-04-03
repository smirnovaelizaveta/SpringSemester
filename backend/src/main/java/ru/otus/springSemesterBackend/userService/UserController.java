package ru.otus.springSemesterBackend.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.otus.springSemesterBackend.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
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
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
