package com.example.lab5_20221203.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "regalo_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private String regalo;

    @Column(name = "regalo_color")
    private String color;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha_envio")
    private Timestamp fechaEnvio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

}
