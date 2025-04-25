package com.estoque;

import com.estoque.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // ⬅️ ESTE É O CONSTRUTOR QUE ESTÁ FALTANDO
public class UserEvent {
    private String tipo; // CREATE, UPDATE, DELETE
    private User user;
}
