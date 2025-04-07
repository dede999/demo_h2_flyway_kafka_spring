package com.andre_luiz_dev.demo_h2_flway_kafka.configuration.kafka;

import com.andre_luiz_dev.demo_h2_flway_kafka.domain.auth.records.EmailSendingDto;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, EmailSendingDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
            Map.of(
                JsonSerializer.ADD_TYPE_INFO_HEADERS, false,
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
            )
        );
    }

    @Bean
    public KafkaTemplate<String, EmailSendingDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
