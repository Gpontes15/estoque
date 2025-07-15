package com.estoque.service;

import com.estoque.model.ServiceOffering;
import com.estoque.repository.ServiceOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfferingService {

    @Autowired
    private ServiceOfferingRepository repository;

    public ServiceOffering salvar(ServiceOffering s) {
        return repository.save(s);
    }

    public List<ServiceOffering> listarTodos() {
        return repository.findAll();
    }

    public Optional<ServiceOffering> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
