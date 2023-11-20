package com.tttgaibot.service.impl;

import com.tttgaibot.model.User;
import com.tttgaibot.repository.UserRepository;
import com.tttgaibot.service.UserService;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public User add(User user) {
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists with email: "
                    + user.getEmail());
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Not found user by id: " + userId)
        );
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public boolean isPresentByEmail(String email) {
        return userRepository.getUserByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User userFromDb = userRepository.findById(user.getId()).orElseThrow(
                () -> new NoSuchElementException("Not found profile info for user: " + user));
        userFromDb.setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(encoder.encode(user.getPassword()));
        return userFromDb;
    }
}
