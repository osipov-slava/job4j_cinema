package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleTicketServiceTest {

    private TicketService ticketService;

    private TicketRepository ticketRepository;

    @BeforeEach
    public void initService() {
        ticketRepository = mock(TicketRepository.class);
        ticketService = new SimpleTicketService(ticketRepository);
    }

    @Test
    public void whenDeleteByIdWhenReturnTrue() {
        when(ticketRepository.deleteById(5)).thenReturn(true);
        when(ticketRepository.findById(5)).thenReturn(Optional.of(new Ticket()));
        var actual = ticketService.deleteById(5);
        assertThat(actual).isTrue();
    }

    @Test
    public void whenDeleteByInvalidIdWhenReturnFalse() {
        when(ticketRepository.deleteById(anyInt())).thenReturn(false);
        when(ticketRepository.findById(3)).thenReturn(Optional.empty());
        var actual = ticketService.deleteById(3);
        assertThat(actual).isFalse();
    }

}

