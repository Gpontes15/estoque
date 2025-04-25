package com.estoque.consumer;

import com.estoque.model.Item;
import com.estoque.service.ItemService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemConsumer {

    private final ItemService itemService;

    public ItemConsumer(ItemService itemService) {
        this.itemService = itemService;
    }

    @KafkaListener(topics = "topico-itens", groupId = "grupo-estoque")
    public void consumirItem(Item item) {
        itemService.save(item);
        System.out.println("✅ Item salvo no banco via Kafka: " + item);
    }
}
