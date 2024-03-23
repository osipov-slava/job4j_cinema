package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.util.Properties;

import static java.util.Optional.empty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var ticketname = properties.getProperty("datasource.ticketname");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, ticketname, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void clearTickets() {
        var tickets = sql2oTicketRepository.findAll();
        for (var ticket : tickets) {
            sql2oTicketRepository.deleteById(ticket.getId());
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var ticket = sql2oTicketRepository.save(new Ticket(1, 1, 1, 1, 1)).get();
        var savedTicket = sql2oTicketRepository.findById(ticket.getId()).get();
        assertThat(savedTicket).usingRecursiveComparison().isEqualTo(ticket);
    }

    @Test
    public void whenDontSaveThenNothingFound() {
        assertThat(sql2oTicketRepository.findById(1)).isEqualTo(empty());
    }

    @Test
    public void whenTryCreateExistingTicket() {
        var ticket = new Ticket(1, 1, 1, 1, 1);
        sql2oTicketRepository.save(ticket);
        assertThat(sql2oTicketRepository.save(ticket)).isEqualTo(empty());
    }

    @Test
    public void whenDeleteTicketByWrongId() {
        var result = sql2oTicketRepository.deleteById(2);
        assertThat(result).isFalse();
    }
}
