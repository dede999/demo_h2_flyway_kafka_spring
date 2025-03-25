package com.andre_luiz_dev.demo_h2_flway_kafka.controllers;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.repositories.IUserRepository;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.RegisteredUserMailException;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.TokenService;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.AuthResponseDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.UserLoginDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private TokenService tokenService;
    private IUserRepository userServiceRepository;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRegistrationDto auth) {
        if (userServiceRepository.existsByEmail(auth.email())) {
            throw new RegisteredUserMailException(auth.email());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(auth.password());
        UserModel user = new UserModel(auth.email(), encryptedPassword, auth.role());
        userServiceRepository.save(user);

        return ResponseEntity.ok(new AuthResponseDto(
                "The user has been successfully registered",
                tokenService.generateToken(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto userData) {
        var userPassword = new UsernamePasswordAuthenticationToken(userData.email(), userData.password());

        var auth = authenticationManager.authenticate(userPassword);
        String token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok()
                .body(new AuthResponseDto(token, "The user has been authenticated successfully!"));
    }
}
