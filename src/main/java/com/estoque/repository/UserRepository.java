package com.estoque.repository;

import com.estoque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para encontrar um usuário pelo nome de usuário
    Optional<User> findByUsername(String username);

    // Método para encontrar um usuário pelo email
    Optional<User> findByEmail(String email);
}
