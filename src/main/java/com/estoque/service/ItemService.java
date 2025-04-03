package com.estoque.service;

import com.estoque.model.Item;
import com.estoque.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Optional<Item> buscarPorCodigo(String codigo) {
        return itemRepository.findByCodigo(codigo);
    }

    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    public void deletarItem(Long id) {
        itemRepository.deleteById(id);
    }
}
