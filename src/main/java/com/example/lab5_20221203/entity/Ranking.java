package com.example.lab5_20221203.entity;

import jakarta.persistence.*;

@Entity
public class Ranking {


    private Integer total;

    @
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
