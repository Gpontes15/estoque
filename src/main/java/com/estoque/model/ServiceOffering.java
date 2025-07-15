package com.estoque.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_offerings")
@Getter
@Setter
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer duracaoEmMinutos;
}
