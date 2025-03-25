package com.andre_luiz_dev.demo_h2_flway_kafka.services;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.repositories.IUserRepository;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private IUserRepository userService;

    public UserModel getUserByEmail(String email) throws UserNotFoundException {
        return userService.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(email));
    }

    public boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }
}
