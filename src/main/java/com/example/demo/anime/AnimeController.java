package com.example.demo.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/anime")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) { this.animeService = animeService;}

    @GetMapping
    public List<Anime> getAnimes() {
        return animeService.getAnimes();
    }

    @PostMapping
    public void registerNewAnime(@RequestBody Anime anime) {
        animeService.addNewAnime(anime);
    }

    @DeleteMapping(path = "{animeId}")
    public void deleteAnime(@PathVariable("animeId") Long animeId) {
        animeService.deleteAnime(animeId);
    }

    @PutMapping(path = "{animeId}")
    public void updateAnime(@PathVariable("animeId") Long animeId, @RequestParam(required = false) String name,@RequestParam(required = false) String genre, @RequestParam(required = false) Integer seasons) {
        animeService.updateAnime(animeId, name, genre, seasons);
    }
}
