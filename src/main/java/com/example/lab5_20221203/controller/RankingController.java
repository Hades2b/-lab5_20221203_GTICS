package com.example.lab5_20221203.controller;

import com.example.lab5_20221203.entity.Ranking;
import com.example.lab5_20221203.repository.RankingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {

    final RankingRepository rankingRepository;

    public RankingController(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @GetMapping("/ranking")
    public String mostrarRanking(Model model) {

        List<Ranking> rankings = rankingRepository.findAllByOrderByTotalRegalosDesc();
        model.addAttribute("rankings", rankings);

        return "ranking";

    }

}
