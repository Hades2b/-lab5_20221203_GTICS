package com.example.lab5_20221203.repository;

import com.example.lab5_20221203.entity.Mensaje;
import com.example.lab5_20221203.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    List<Mensaje> findByDestinatario_id(Integer idUsuario);
}
