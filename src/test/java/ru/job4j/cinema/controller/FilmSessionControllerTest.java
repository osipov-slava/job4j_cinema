package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilmSessionControllerTest {

    private FilmSessionController filmSessionController;

    private FilmSessionService filmSessionService;

    private FilmService filmService;

    private HallService hallService;

    @BeforeEach
    public void init() {
        filmSessionService = mock(SimpleFilmSessionService.class);
        filmService = mock(SimpleFilmService.class);
        hallService = mock(SimpleHallService.class);
        filmSessionController = new FilmSessionController(filmSessionService, filmService, hallService);
    }

    @Test
    public void whenRequestFilmSessionListPageThenGetPageWithFilmSessions() {
        var film = new FilmDto(1, "film1", "desc1", 1995, "comedy", 17, 200, 1);
        var films = new ConcurrentHashMap<Integer, FilmDto>();
        films.put(1, film);

        var hall = new Hall(1, "hall", 20, 20, "decr");
        var halls = new ConcurrentHashMap<Integer, Hall>();
        halls.put(1, hall);

        var localDateTime = LocalDateTime.now();
        var filmSession = new FilmSession(1, 1, 1, localDateTime, localDateTime.plusHours(2), 10);
        var filmSessions = List.of(filmSession);

        when(filmService.findAll()).thenReturn(films);
        when(filmSessionService.findAll()).thenReturn(filmSessions);
        when(hallService.findAll()).thenReturn(halls);

        var model = new ConcurrentModel();
        var view = filmSessionController.getAll(model);
        var actualFilms = model.getAttribute("films");
        var actualFilmSessions = model.getAttribute("filmSessions");
        var actualHalls = model.getAttribute("halls");

        assertThat(view).isEqualTo("filmSessions/list");
        assertThat(actualFilms).isEqualTo(films);
        assertThat(actualFilmSessions).isEqualTo(filmSessions);
        assertThat(actualHalls).isEqualTo(halls);
    }

}
