package com.example.lab5_20221203.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserId;

    @Column(name = "total_regalos")
    private Integer total;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "usuario_id")
    private Usuario usuario;

}
