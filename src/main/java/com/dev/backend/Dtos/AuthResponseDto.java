package com.dev.backend.Dtos;

import lombok.Getter;

import java.util.List;

@Getter
public class AuthResponseDto {
    private final String token;
    private final List<String> permissions;

    public AuthResponseDto(String token, List<String> permissions) {
        this.token = token;
        this.permissions = permissions;
    }
}
