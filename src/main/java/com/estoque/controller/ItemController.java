package com.estoque.controller;

import com.estoque.model.Item;
import com.estoque.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> listarItens() {
        return itemService.listarItens();
    }

    @GetMapping("/{codigo}")
    public Optional<Item> buscarPorCodigo(@PathVariable String codigo) {
        return itemService.buscarPorCodigo(codigo);
    }

    @PostMapping
    public Item criarItem(@RequestBody Item item) {
        return itemService.salvarItem(item);
    }

    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
    }
}
