package com.dev.backend.controllers;

import com.dev.backend.Dtos.AuthResponseDto;
import com.dev.backend.models.*;
import com.dev.backend.security.JwtUtils;
import com.dev.backend.services.UserClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserClientService userClientService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserClientRegister userRegister) throws Exception {
        if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())) {
            ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, "Passwords do not match");
            return ResponseEntity.ok(error);
        }
        if (userClientService.findByEmail(userRegister.getEmail()) != null) {
            ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, "Email already registered");
            return ResponseEntity.ok(error);
        }
        userClientService.register(userRegister);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid UserLogin userLogin) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLogin.getEmail(),
                                userLogin.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAccountAuth userAuth = (UserAccountAuth) authentication.getPrincipal();
        List<String> permissionNameList = userAuth.getAuthorities().stream().map(m -> m.getAuthority()).toList();
        AuthResponseDto authResponseDto = new AuthResponseDto(jwtUtils.token(userAuth), permissionNameList);
        return ResponseEntity.ok(authResponseDto);
    }

}
