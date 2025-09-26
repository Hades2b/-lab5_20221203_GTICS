package com.example.lab5_20221203.repository;

import com.example.lab5_20221203.entity.Ranking;
import com.example.lab5_20221203.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Usuario> {
}
