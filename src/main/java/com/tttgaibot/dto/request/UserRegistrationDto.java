package com.tttgaibot.dto.request;

import com.tttgaibot.lib.ValidEmail;
import com.tttgaibot.lib.ValidPassword;
import lombok.Data;

@Data
@ValidPassword
public class UserRegistrationDto {
    @ValidEmail
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String repeatPassword;
}
