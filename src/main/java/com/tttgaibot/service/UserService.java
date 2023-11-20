package com.tttgaibot.service;

import com.tttgaibot.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User getById(Long userId);

    Optional<User> getByEmail(String email);

    boolean isPresentByEmail(String email);

    User update(User user);
}
