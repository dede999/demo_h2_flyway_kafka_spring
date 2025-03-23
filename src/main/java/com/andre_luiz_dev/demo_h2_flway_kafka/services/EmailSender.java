package com.andre_luiz_dev.demo_h2_flway_kafka.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Value("${api.secret.resend_key}")
    private String apiKey;

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
            e.printStackTrace();
            return null;
        }
    }
}
