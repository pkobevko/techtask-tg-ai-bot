package com.tttgaibot.security;

import com.tttgaibot.dto.request.UserRegistrationDto;
import com.tttgaibot.exception.AuthenticationException;
import com.tttgaibot.model.User;

public interface AuthenticationService {
    User register(UserRegistrationDto user);

    User login(String login, String password) throws AuthenticationException;
}

