package com.andre_luiz_dev.demo_h2_flway_kafka.controllers;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.repositories.IUserRepository;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.RegisteredUserMailException;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.UserNotFoundException;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.WrongPasswordException;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.TokenService;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.AuthResponseDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.UserLoginDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.auth.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;
    private IUserRepository userServiceRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRegistrationDto auth) {
        if (userServiceRepository.existsByEmail(auth.email())) {
            throw new RegisteredUserMailException(auth.email());
        }

        String encryptedPassword = passwordEncoder.encode(auth.password());
        UserModel user = new UserModel(auth.email(), encryptedPassword, auth.role());
        userServiceRepository.save(user);

        return ResponseEntity.ok(new AuthResponseDto(
                "The user has been successfully registered",
                tokenService.generateToken(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto userData) {
        UserModel user = userServiceRepository.findByEmail(userData.email()).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with email %s not found", userData.email()))
        );

        if (!passwordEncoder.matches(userData.password(), user.getPassword())) {
            throw new WrongPasswordException("The password is incorrect for this user. Please try again with a correct password.");
        }

        return ResponseEntity.ok()
                .body(new AuthResponseDto(
                        "The user has been authenticated successfully!",
                        tokenService.generateToken(user)));
    }
}
