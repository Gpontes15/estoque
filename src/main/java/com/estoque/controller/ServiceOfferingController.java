package com.estoque.controller;

import com.estoque.model.ServiceOffering;
import com.estoque.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServiceOfferingController {

    @Autowired
    private ServiceOfferingService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ServiceOffering s) {
        return ResponseEntity.status(201).body(service.salvar(s));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOffering>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ServiceOffering s) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.status(404).body("Serviço não encontrado");
        }
        s.setId(id);
        return ResponseEntity.ok(service.salvar(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.status(404).body("Serviço não encontrado");
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
