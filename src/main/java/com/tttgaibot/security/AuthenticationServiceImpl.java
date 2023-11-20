package com.tttgaibot.security;

import com.tttgaibot.dto.request.UserRegistrationDto;
import com.tttgaibot.exception.AuthenticationException;
import com.tttgaibot.exception.UserAlreadyExistsException;
import com.tttgaibot.model.User;
import com.tttgaibot.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationDto user) {
        if (userService.isPresentByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User are already exist with email: "
                    + user.getEmail());
        }
        User newUser = new User()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(User.Role.ADMIN);
        return userService.add(newUser);
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> user = userService.getByEmail(login);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AuthenticationException("Incorrect username or password!!!");
        }
        return user.get();
    }
}
