package com.example.demo.anime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query("SELECT s FROM Anime s WHERE s.name = ?1")
    Optional<Anime> findAnimeByName(String name);
}
