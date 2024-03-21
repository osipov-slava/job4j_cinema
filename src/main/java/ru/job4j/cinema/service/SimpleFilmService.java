package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;

    private final GenreService genreService;

    public SimpleFilmService(FilmRepository filmRepository, GenreService genreService) {
        this.filmRepository = filmRepository;
        this.genreService = genreService;
    }

    @Override
    public Map<Integer, FilmDto> findAll() {
        var genres = genreService.findAll();
        var films = filmRepository.findAll();
        Map<Integer, FilmDto> filmsDto = new ConcurrentHashMap<>();
        for (Film film : films) {
            var filmDto = new FilmDto(
                    film.getId(), film.getName(), film.getDescription(), film.getYear(), genres.get(film.getGenreId()).getName(),
                    film.getMinimalAge(), film.getDuration(), film.getFileId());
            filmsDto.put(film.getId(), filmDto);
        }
        return filmsDto;
    }

    @Override
    public Optional<FilmDto> findById(int id) {
        var filmsMap = findAll();
        if (filmsMap.containsKey(id)) {
            return Optional.of(filmsMap.get(id));
        }
        return Optional.empty();
    }
}
