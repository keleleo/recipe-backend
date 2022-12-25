package com.dev.backend.models;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserLogin {
    @Email(message = "Email format error")
    private String email;
    @Length(min = 8, message = "Min length: 8")
    private String password;
}
