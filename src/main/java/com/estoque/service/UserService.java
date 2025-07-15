package com.estoque.service;

import com.estoque.model.User;
import com.estoque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User salvarUsuario(User user) {
        // Só criptografa se ainda não estiver criptografada
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public Optional<User> buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> buscarPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public void deletarUsuario(Long id) {
        userRepository.deleteById(id);
    }
}
