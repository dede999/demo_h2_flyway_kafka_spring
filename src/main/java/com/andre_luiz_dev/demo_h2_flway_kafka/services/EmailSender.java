package com.andre_luiz_dev.demo_h2_flway_kafka.services;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.EmailSendingDto;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Value("${api.secret.resend_key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public String sendEmail(String email, String message, String subject) {
        Resend instance = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
            .from("Andre Luiz - Dev <ceo@andre-luiz-dev.com>")
            .to(email)
            .subject(subject)
            .html(message)
            .build();

        try {
            CreateEmailResponse data = instance.emails().send(params);
            return data.getId();
        } catch (ResendException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    @KafkaListener(topics = "email-order-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listen(EmailSendingDto emailSendingDto) {
        logger.info("Email sent to {}", emailSendingDto.email());
    }
}
