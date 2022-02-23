package com.example.demo.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Autowired
    public List<Anime> getAnimes() {
        return animeRepository.findAll();
    }

    public void addNewAnime(Anime anime) {
        Optional<Anime> animeOptional = animeRepository.findAnimeByName(anime.getName());
        if (animeOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        animeRepository.save(anime);
    }

    public void deleteAnime(Long animeId) {
        boolean exists = animeRepository.existsById(animeId);
        if (!exists) {
            throw new IllegalStateException("anime with id " + animeId + " does not exist");
        }
        animeRepository.deleteById(animeId);
    }

    @Transactional
    public void updateAnime(Long animeId, String name, String genre, Integer seasons) {
            Anime anime = animeRepository.findById(animeId).orElseThrow(() -> new IllegalStateException("anime with id " + animeId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(anime.getName(), name)) {
            Optional<Anime> animeOptional = animeRepository.findAnimeByName(name);
            if (animeOptional.isPresent()) {
                throw new IllegalStateException("name taken");
            }
            anime.setName(name);
        }

        if (genre != null && genre.length() > 0 && !Objects.equals(anime.getGenre(), genre)) {
            anime.setGenre(genre);
        }

        if (seasons != null && seasons > 0 && !Objects.equals(anime.getSeasons(), seasons)) {
            anime.setSeasons(seasons);
        }
    }
}
