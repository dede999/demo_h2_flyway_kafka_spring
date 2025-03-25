package com.andre_luiz_dev.demo_h2_flway_kafka.services.auth;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.TokenServiceException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.secret.jwt_key}")
    private String jwtKey;

    public String generateToken(UserModel userModel) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        try {
            return JWT.create()
                .withIssuer("my-kafka-spring-api")
                .withClaim("userID", userModel.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenServiceException(e.getMessage());
        }
    }

    public String verifyToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(jwtKey))
                .build()
                .verify(token)
                .getClaim("userID")
                .asString();
        } catch (JWTVerificationException e) {
            throw new TokenServiceException(e.getMessage());
        }
    }

    public Instant getExpirationDate() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
