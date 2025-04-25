package com.estoque.controller;

import com.estoque.UserEvent;
import com.estoque.kafka.UserProducer;
import com.estoque.service.UserService;
import com.estoque.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserProducer userProducer;

    @Autowired
    private UserService userService;

    @PostMapping
    public void criarUsuario(@RequestBody User user) {
        userProducer.enviarEvento(new UserEvent("CREATE", user));
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable Long id, @RequestBody User user) {
        user.setId(id); // força o ID
        userProducer.enviarEvento(new UserEvent("UPDATE", user));
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        User user = new User();
        user.setId(id); // apenas o ID já é suficiente
        userProducer.enviarEvento(new UserEvent("DELETE", user));
    }

    @GetMapping
    public List<User> listarUsuarios() {
        return userService.listarUsuarios();
    }

    @GetMapping("/{username}")
    public User buscarPorUsername(@PathVariable String username) {
        return userService.buscarPorUsername(username).orElse(null);
    }
}

