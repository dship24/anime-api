package com.example.demo.anime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class AnimeConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnimeRepository repository) {
        return args -> {
            Anime naruto = new Anime("Naruto", "Shonen", 18);
            Anime hxh = new Anime("HunterXHunter", "Action/Adventure", 6);
            Anime bb = new Anime("Black Butler", "Action", 3);
            repository.saveAll(
                    List.of(naruto, hxh, bb)
            );
        };
    }
}
