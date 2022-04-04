package ru.otus.springSemesterBackend.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserDetailsService userDetailsService, DefaultUserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("api/login")
    public boolean login(@RequestBody UserDto userDto) {
        return passwordEncoder.matches(userDto.getPassword(), userDetailsService.loadUserByUsername(userDto.getUsername()).getPassword());
//        return userDetailsService.loadUserByUsername(userDto.getUsername()).getPassword().equals(passwordEncoder.encode(userDto.getPassword()));
//        return
//                user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("api/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @PostMapping("api/user")
    public void register(@RequestBody UserDto userDto) {
        userService.insert(new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), Role.USER));
    }
}
