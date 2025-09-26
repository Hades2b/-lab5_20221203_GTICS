package com.example.lab5_20221203.repository;

import com.example.lab5_20221203.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}
