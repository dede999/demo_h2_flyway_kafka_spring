package com.andre_luiz_dev.demo_h2_flway_kafka.controllers;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.EmailSendingDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.SentEmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.EmailSender;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/first")
public class FirstController {
    private EmailSender emailSender;

    @RequestMapping("/send")
    public ResponseEntity<SentEmailDto> emailSender(@RequestBody EmailSendingDto emailSendingDto) {
        String emailID = emailSender.sendEmail(
            emailSendingDto.email(),
            emailSendingDto.message(),
            emailSendingDto.subject()
        );

        return ResponseEntity.ok(new SentEmailDto(
                emailSendingDto.email(), "The email has been successfully sent", emailID
        ));
    }
}
