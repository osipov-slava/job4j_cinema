package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleFilmServiceTest {

    private FilmRepository filmRepository;

    private GenreService genreService;

    private FilmService filmService;

    @BeforeEach
    public void initServicesAndRepositories() {
        filmRepository = mock(FilmRepository.class);
        genreService = mock(GenreService.class);
        filmService = new SimpleFilmService(filmRepository, genreService);
    }

    @Test
    public void whenFindAllFilmThenGetAllOrGetEmptyList() {
        var genre1 = new Genre(1, "Sci Fi");
        var genre2 = new Genre(5, "Drama");
        var genres = Map.of(1, genre1, 5, genre2);
        when(genreService.findAll()).thenReturn(genres);

        when(filmRepository.findAll()).thenReturn(Collections.emptyList());
        var actualFilmDtos = filmService.findAll();
        assertThat(actualFilmDtos.size()).isEqualTo(0);

        var film1 = new Film(1, "American Beauty", "description", 1999, 5, 17, 122, 1);
        var film2 = new Film(2, "Dune: Part Two", "description", 2024, 1, 13, 166, 2);
        var films = List.of(film1, film2);
        when(filmRepository.findAll()).thenReturn(films);

        var filmDto1 = new FilmDto(1, "American Beauty", "description", 1999, "Drama", 17, 122, 1);
        var filmDto2 = new FilmDto(2, "Dune: Part Two", "description", 2024, "Sci Fi", 13, 166, 1);
        var expectedFilmDtos = Map.of(1, filmDto1, 2, filmDto2);

        actualFilmDtos = filmService.findAll();
        assertThat(actualFilmDtos).usingRecursiveComparison().isEqualTo(expectedFilmDtos);
    }

    @Test
    public void whenFindByIdThenGetFilmOrGetEmpty() {
        var genre1 = new Genre(1, "Sci Fi");
        var genres = Map.of(1, genre1);
        when(genreService.findAll()).thenReturn(genres);

        var film2 = new Film(2, "Dune: Part Two", "description", 2024, 1, 13, 166, 2);
        var films = List.of(film2);
        when(filmRepository.findAll()).thenReturn(films);

        var filmDto2 = new FilmDto(2, "Dune: Part Two", "description", 2024, "Sci Fi", 13, 166, 1);
        var expectedOptional = Optional.of(filmDto2);

        var actualOptional = filmService.findById(2);
        assertThat(actualOptional.get()).usingRecursiveComparison().isEqualTo(expectedOptional.get());

        actualOptional = filmService.findById(3);
        assertThat(actualOptional.isEmpty()).isTrue();
    }

}
