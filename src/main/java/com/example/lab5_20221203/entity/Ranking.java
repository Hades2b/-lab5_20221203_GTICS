package com.example.lab5_20221203.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "total_regalos")
    private Integer totalRegalos;

    public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getTotalRegalos() {
        return totalRegalos;
    }
    public void setTotalRegalos(Integer totalRegalos) {
        this.totalRegalos = totalRegalos;
    }
}
