package ru.job4j.cinema.service;

import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleGenreServiceTest {

    @Test
    public void whenFindAllGenre() {
        GenreRepository genreRepository = mock(GenreRepository.class);

        var genre1 = new Genre(1, "Action");
        var genre2 = new Genre(2, "Comedy");
        var genres = List.of(genre1, genre2);
        var expectedGenres = Map.of(1, genre1, 2, genre2);
        when(genreRepository.findAll()).thenReturn(genres);

        GenreService genreService = new SimpleGenreService(genreRepository);

        var actualGenres = genreService.findAll();

        assertThat(actualGenres).isEqualTo(expectedGenres);
    }

    @Test
    public void whenFindAllWithoutData() {
        GenreRepository genreRepository = mock(GenreRepository.class);
        when(genreRepository.findAll()).thenReturn(Collections.emptyList());

        GenreService genreService = new SimpleGenreService(genreRepository);

        var actualGenres = genreService.findAll();

        assertThat(actualGenres.size()).isEqualTo(0);
    }
}
