package com.estoque.cosumer;

import com.estoque.model.Item;
import com.estoque.repository.ItemRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemConsumer {

    private final ItemRepository itemRepository;

    public ItemConsumer(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @KafkaListener(topics = "topico-itens", groupId = "grupo-estoque")
    public void consumirItem(Item item) {
        itemRepository.save(item);
        System.out.println("✅ Item salvo no banco via Kafka: " + item);
    }
}
