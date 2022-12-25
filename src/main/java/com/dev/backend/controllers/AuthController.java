package com.dev.backend.controllers;

import com.dev.backend.models.ErrorResponse;
import com.dev.backend.models.UserClientRegister;
import com.dev.backend.models.UserLogin;
import com.dev.backend.models.UserAccountAuth;
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

import java.time.LocalDateTime;

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
        System.out.println("UserClient - register");
        if (!userRegister.getPassword().equals(userRegister.getPasswordConfirm())) {
            ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, "Passwords do not match");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        if (userClientService.findByEmail(userRegister.getEmail()) != null) {
            ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, "Email already registered");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        userClientService.register(userRegister);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogin userLogin) {
        System.out.println("hello");
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLogin.getEmail(),
                                userLogin.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAccountAuth userAuth = (UserAccountAuth) authentication.getPrincipal();
        String token = jwtUtils.token(userAuth);
        return ResponseEntity.ok("Bearer "+token);
    }

}
