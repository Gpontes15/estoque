package com.estoque.kafka;

import com.estoque.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEvento(UserEvent evento) {
        kafkaTemplate.send("topico-users", evento);
        System.out.println("📤 Evento enviado ao Kafka: " + evento.getTipo());
    }
}
