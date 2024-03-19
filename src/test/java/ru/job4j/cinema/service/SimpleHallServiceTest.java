package ru.job4j.cinema.service;

import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleHallServiceTest {

    @Test
    public void whenFindAllHall() {
        HallRepository hallRepository = mock(HallRepository.class);
        var hall1 = new Hall(1, "hall1", 10, 10, "description");
        var hall2 = new Hall(2, "hall2", 20, 20, "description");
        var halls = List.of(hall1, hall2);
        var expectedHalls = Map.of(1, hall1, 2, hall2);
        when(hallRepository.findAll()).thenReturn(halls);
        HallService hallService = new SimpleHallService(hallRepository);

        var actualHalls = hallService.findAll();

        assertThat(actualHalls).isEqualTo(expectedHalls);
    }
}
