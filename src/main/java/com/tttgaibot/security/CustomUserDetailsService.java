package com.tttgaibot.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.tttgaibot.model.User;
import com.tttgaibot.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            return withUsername(email)
                    .password(userOptional.get().getPassword())
                    .roles(userOptional.get().getRole().name())
                    .build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
