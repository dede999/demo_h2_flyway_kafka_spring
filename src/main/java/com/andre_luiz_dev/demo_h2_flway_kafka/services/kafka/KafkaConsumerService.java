package com.andre_luiz_dev.demo_h2_flway_kafka.services.kafka;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.EmailSendingDto;
import com.andre_luiz_dev.demo_h2_flway_kafka.services.EmailSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {
    private EmailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "user_topic", groupId = "group-1")
    public void consume(String message) {
        logger.info("MESSAGE RECEIVED AT CONSUMER END -> {}", message);
        EmailSendingDto emailSendingDto = EmailSendingDto.fromString(message);
        emailSender.sendEmail(emailSendingDto.email(), emailSendingDto.message(), emailSendingDto.subject());
    }
}
