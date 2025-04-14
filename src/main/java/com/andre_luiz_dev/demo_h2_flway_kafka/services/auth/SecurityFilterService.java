package com.andre_luiz_dev.demo_h2_flway_kafka.services.auth;

import com.andre_luiz_dev.demo_h2_flway_kafka.configuration.JsonUtil;
import com.andre_luiz_dev.demo_h2_flway_kafka.configuration.dtos.ExceptionResponseDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.models.UserModel;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.UserService;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Service
@AllArgsConstructor
public class SecurityFilterService extends OncePerRequestFilter {
    private TokenService tokenService;
    private UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException {
        try {
            String header = request.getHeader("Authorization");

            // Skip authentication if Authorization header is missing or empty
            if (header == null || header.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.replace("Bearer ", "");
            String email = tokenService.verifyToken(token);
            UserModel user = userService.getUserByEmail(email);
            var authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (TokenExpiredException ex) {
            handleException(ex, response, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            handleException(ex, response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void handleException(Exception ex, HttpServletResponse response, HttpStatus status) throws IOException {
        System.out.println(ex.getClass().getName());
        var errorObject = new ExceptionResponseDto(ex.getClass().getName(), ex.getMessage());
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(JsonUtil.convertToJson(errorObject));
        response.getWriter().flush();
    }
}
