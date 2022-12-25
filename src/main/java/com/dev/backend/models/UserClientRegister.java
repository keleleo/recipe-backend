package com.dev.backend.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserClientRegister {
    @Length(min = 6, max = 20)
    @NotBlank
    private String userName;
    @Email
    @Length(min = 6, max = 100)
    @NotBlank
    private String email;
    @Length(min = 8, max = 100)
    @NotBlank
    private String password;
    @Length(min = 8, max = 100)
    @NotBlank()
    private String passwordConfirm;
}
