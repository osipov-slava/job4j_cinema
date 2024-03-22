package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleFilmSessionServiceTest {

    private HallService hallService;

    private FilmService filmService;

    private FilmSessionRepository filmSessionRepository;

    private FilmSessionService filmSessionService;

    @BeforeEach
    public void initServicesAndRepositories() {
        hallService = mock(HallService.class);
        filmService = mock(FilmService.class);
        filmSessionRepository = mock(FilmSessionRepository.class);
        filmSessionService = new SimpleFilmSessionService(hallService, filmService, filmSessionRepository);
    }

    @Test
    public void whenFindAllThenGetAllOrEmpty() {
        when(filmSessionRepository.findAll()).thenReturn(Collections.emptyList());
        var actual = filmSessionRepository.findAll();
        assertThat(actual.size()).isEqualTo(0);

        var hallsMap = Map.of(1, new Hall(1, "hall1", 10, 10, "description"));
        when(hallService.findAll()).thenReturn(hallsMap);

        var expectedFilmDtos = Map.of(2, new FilmDto(2, "Dune: Part Two", "description",
                2024, "Sci Fi", 13, 166, 1));
        when(filmService.findAll()).thenReturn(expectedFilmDtos);

        var localDateTime = LocalDateTime.now();
        var filmSession1 = new FilmSession(1, 2, 1, localDateTime, localDateTime.plusHours(2), 10);
        var filmSession2 = new FilmSession(2, 2, 1, localDateTime.plusHours(3), localDateTime.plusHours(5), 10);
        var expected = List.of(filmSession1, filmSession2);
        when(filmSessionRepository.findAll()).thenReturn(expected);

//        var filmSessionDto1 = new FilmSessionDto(1, "Dune: Part Two", "hall1", localDateTime, localDateTime.plusHours(2), 10);
//        var filmSessionDto2 = new FilmSessionDto(2, "Dune: Part Two", "hall1", localDateTime.plusHours(3), localDateTime.plusHours(5), 10);
//        var expectedDtos = List.of(filmSessionDto1, filmSessionDto2);
        actual = filmSessionService.findAll();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}
