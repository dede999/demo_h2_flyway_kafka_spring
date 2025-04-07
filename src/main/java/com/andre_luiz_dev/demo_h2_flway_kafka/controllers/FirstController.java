package com.andre_luiz_dev.demo_h2_flway_kafka.controllers;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.EmailSendingDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.SentEmailDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.exceptions.JsonParsingException;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
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
    private KafkaTemplate<String, EmailSendingDto> kafkaTemplate;

    @RequestMapping("/send")
    public ResponseEntity<SentEmailDto> emailSender(@RequestBody EmailSendingDto emailSendingDto) {
        System.out.println(emailSendingDto);
        String emailID = emailSender.sendEmail(
            emailSendingDto.email(),
            emailSendingDto.message(),
            emailSendingDto.subject()
        );

        return ResponseEntity.ok(new SentEmailDto(
                emailSendingDto.email(), "The email has been successfully sent", emailID
        ));
    }

    @PostMapping("/apache-msg")
    public ResponseEntity<ResponseEntity<String>> emailSenderApache(@RequestBody EmailSendingDto emailSendingDto) {
        System.out.println(emailSendingDto);
//        kafkaTemplate.send("email-order-topic", emailSendingDto);
        return ResponseEntity.ok(ResponseEntity.ok("The message has been successfully sent to Kafka"));
    }
}
