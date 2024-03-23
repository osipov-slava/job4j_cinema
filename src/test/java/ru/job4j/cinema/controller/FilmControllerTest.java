package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmControllerTest {

    FilmService filmService;

    FilmController filmController;

    @BeforeEach
    public void init() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    public void whenRequestFilmListPageThenGetPageWithFilms() {
        var film1 = new FilmDto(1, "film1", "desc1", 1995, "comedy", 17, 200, 1);
        var film2 = new FilmDto(2, "film2", "desc2", 2000, "drama", 13, 100, 2);
        var expected = new ConcurrentHashMap<Integer, FilmDto>();
        expected.put(1, film1);
        expected.put(2, film2);
        when(filmService.findAll()).thenReturn(expected);

        var model = new ConcurrentModel();
        var view = filmController.getAll(model);
        var actual = model.getAttribute("films");

        assertThat(view).isEqualTo("films/list");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenRequestFilmByIdThenGetPageWithFilmDescription() {
        var expected = new FilmDto(1, "film1", "desc1", 1995, "comedy", 17, 200, 1);
        when(filmService.findById(1)).thenReturn(Optional.of(expected));

        var model = new ConcurrentModel();
        var view = filmController.getById(model, 1);
        var actual = model.getAttribute("film");

        assertThat(view).isEqualTo("films/one");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenRequestFilmByWrongIdThenGetPageWithError() {
        when(filmService.findById(anyInt())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = filmController.getById(model, 1);
        var actual = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(actual).isEqualTo("Film with this Id not found!");
    }
}

