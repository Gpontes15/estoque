package com.estoque.controller;

import com.estoque.model.Item;
import com.estoque.kafka.ItemProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemProducer itemProducer;

    @GetMapping
    public String status() {
        return "🟢 API de Estoque está no ar!";
    }

    @PostMapping
    public void criarItem(@RequestBody Item item) {
        itemProducer.enviarItem(item);  // envia pro Kafka (não salva direto no banco)
    }
}
