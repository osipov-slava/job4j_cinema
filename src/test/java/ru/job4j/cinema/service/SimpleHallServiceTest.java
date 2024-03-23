package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleHallServiceTest {

    HallRepository hallRepository;

    HallService hallService;

    @BeforeEach
    public void init() {
        hallRepository = mock(HallRepository.class);
        hallService = new SimpleHallService(hallRepository);
    }

    @Test
    public void whenFindAllHallThenReturnFromDBAndFromCache() {
        var hall1 = new Hall(1, "hall1", 10, 10, "description");
        var hall2 = new Hall(2, "hall2", 20, 20, "description");
        var halls = List.of(hall1, hall2);
        var expectedHalls = Map.of(1, hall1, 2, hall2);

        when(hallRepository.findAll()).thenReturn(halls);
        var actualHalls = hallService.findAll();
        assertThat(actualHalls).isEqualTo(expectedHalls);

        when(hallRepository.findAll()).thenReturn(Collections.emptyList());
        actualHalls = hallService.findAll();
        assertThat(actualHalls).isEqualTo(expectedHalls);
    }

    @Test
    public void whenFindAllHallThenReturnEmptyMap() {
        when(hallRepository.findAll()).thenReturn(Collections.emptyList());
        var actualHalls = hallService.findAll();
        assertThat(actualHalls).isEqualTo(new ConcurrentHashMap<Integer, Hall>());
    }
}
