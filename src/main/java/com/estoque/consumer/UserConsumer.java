package com.estoque.consumer;

import com.estoque.UserEvent;
import com.estoque.model.User;
import com.estoque.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private final UserService userService;

    public UserConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "topico-users", groupId = "grupo-estoque")
    public void consumirEvento(UserEvent evento) {
        User user = evento.getUser();

        switch (evento.getTipo()) {
            case "CREATE" -> {
                userService.salvarUsuario(user);
                System.out.println("✅ Usuário criado via Kafka: " + user);
            }
            case "UPDATE" -> {
                userService.salvarUsuario(user);
                System.out.println("🔄 Usuário atualizado via Kafka: " + user);
            }
            case "DELETE" -> {
                userService.deletarUsuario(user.getId());
                System.out.println("🗑️ Usuário deletado via Kafka: ID " + user.getId());
            }
        }
    }
}
