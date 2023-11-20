package com.tttgaibot.controller;

import com.tttgaibot.dto.request.UserLoginDto;
import com.tttgaibot.dto.request.UserRegistrationDto;
import com.tttgaibot.dto.response.UserResponseDto;
import com.tttgaibot.exception.AuthenticationException;
import com.tttgaibot.mapper.UserMapper;
import com.tttgaibot.model.User;
import com.tttgaibot.security.AuthenticationService;
import com.tttgaibot.security.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto requestDto) {
        return userMapper.mapToDto(authService.register(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto)
            throws AuthenticationException {
        User user = authService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getEmail(),
                List.of(user.getRole().name()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
