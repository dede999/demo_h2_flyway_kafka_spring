package com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.repositories;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
