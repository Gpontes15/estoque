package com.estoque.repository;

import java.util.Optional;
import com.estoque.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByCodigo(String codigo);
}
