package com.estoque.controller;

import com.estoque.model.User;
import com.estoque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST - Criar usuário
    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody User user) {
        User novo = userService.salvarUsuario(user);
        return ResponseEntity.status(201).body(novo); // 201 Created
    }

    // PUT - Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody User userAtualizado) {
        Optional<User> userExistente = userService.buscarPorId(id);

        if (userExistente.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        userAtualizado.setId(id);
        User atualizado = userService.salvarUsuario(userAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE - Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        Optional<User> userExistente = userService.buscarPorId(id);

        if (userExistente.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        return ResponseEntity.ok(userService.listarUsuarios());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> buscarPorUsername(@PathVariable String username) {
        Optional<User> user = userService.buscarPorUsername(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }


    // POST - Login (validação de credenciais)
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody User userLogin) {
        Optional<User> usuarioOpt = userService.buscarPorUsername(userLogin.getUsername());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuário não encontrado");
        }

        User usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(userLogin.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Senha inválida");
        }

        return ResponseEntity.ok("Login bem-sucedido");
    }
}
