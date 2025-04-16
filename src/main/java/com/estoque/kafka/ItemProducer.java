package com.estoque.kafka;

import com.estoque.model.Item;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemProducer {

    private final KafkaTemplate<String, Item> kafkaTemplate;

    public ItemProducer(KafkaTemplate<String, Item> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarItem(Item item) {
        kafkaTemplate.send("topico-itens", item);
        System.out.println("🔼 Item enviado para o Kafka: " + item);
    }
}
