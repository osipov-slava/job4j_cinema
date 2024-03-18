package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleGenreService implements GenreService {

    private static Map<Integer, Genre> genresMap;

    public SimpleGenreService(GenreRepository genreRepository) {
        genresMap = new ConcurrentHashMap<>();
        var genres = genreRepository.findAll();
        if (!genres.isEmpty()) {
            for (Genre genre : genres) {
                genresMap.put(genre.getId(), genre);
            }
        }
    }

    @Override
    public Map<Integer, Genre> findAll() {
        return genresMap;
    }
}
