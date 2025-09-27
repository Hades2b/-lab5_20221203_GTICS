package com.example.lab5_20221203.repository;

import com.example.lab5_20221203.entity.Ranking;
import com.example.lab5_20221203.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

    List<Ranking> findAllByOrderByTotalRegalosDesc();
}