package com.estoque.controller;

import com.estoque.model.Item;
import com.estoque.kafka.ItemProducer;
import com.estoque.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemProducer itemProducer;

    @Autowired
    private ItemService itemService;

    // Criar novo item (Envia para o Kafka)
    @PostMapping
    public void criarItem(@RequestBody Item item) {
        itemProducer.enviarItem(item);
    }

    // Listar todos os itens (Novo)
    @GetMapping
    public List<Item> listarItens() {
        return itemService.listarItens();
    }

    // Atualizar um item existente (Novo)
    @PutMapping("/{id}")
    public Item atualizarItem(@PathVariable Long id, @RequestBody Item itemAtualizado) {
        Optional<Item> itemExistente = itemService.buscarPorId(id);

        if (itemExistente.isPresent()) {
            Item item = itemExistente.get();
            item.setNome(itemAtualizado.getNome());
            item.setCodigo(itemAtualizado.getCodigo());
            item.setDescricao(itemAtualizado.getDescricao());
            item.setPreco(itemAtualizado.getPreco());

            return itemService.save(item);
        } else {
            throw new RuntimeException("❌ Item não encontrado com ID: " + id);
        }
    }

    // Excluir um item pelo ID (Novo)
    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
    }

    @GetMapping("/todos")
    public List<Item> listarTodos() {
        return itemService.listarItens();
    }


}
