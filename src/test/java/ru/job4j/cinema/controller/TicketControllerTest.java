package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SimpleTicketService;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    private TicketService ticketService;

    private TicketController ticketController;

    @Mock
    private MockHttpSession mockHttpSession;

    private HttpServletRequest httpServletRequest;

    @BeforeEach
    public void init() {
        ticketService = mock(SimpleTicketService.class);
        ticketController = new TicketController(ticketService);
        httpServletRequest = mock(HttpServletRequest.class);
    }

    @Test
    public void whenRequestNewTicketThenGetCreationPage() {
        when(httpServletRequest.getSession()).thenReturn(mockHttpSession);
        var sessionUser = new User(1, "john@gmail.com", "John", "password");
        when(mockHttpSession.getAttribute("user")).thenReturn(sessionUser);

        var model = new ConcurrentModel();
        var expectedTicket = new Ticket(0, 1, 0, 0, 1);
        var localDateTime = LocalDateTime.now();
        var view = ticketController.getCreationPage(1, "title", localDateTime.toString(), "hall",
                10, 10, 15, 1, model, httpServletRequest);

        var actualTicket = model.getAttribute("ticket");
        var actualStartDateTime = model.getAttribute("startDateTime");
        var actualTitle = model.getAttribute("title");
        var actualHall = model.getAttribute("hall");
        var actualRows = model.getAttribute("rows");
        var actualPlaces = model.getAttribute("places");
        var actualFileId = model.getAttribute("fileId");

        assertThat(view).isEqualTo("tickets/create");
        assertThat(actualTicket).isEqualTo(expectedTicket);
        assertThat(actualStartDateTime).isEqualTo(localDateTime);
        assertThat(actualTitle).isEqualTo("title");
        assertThat(actualHall).isEqualTo("hall");
        assertThat(actualRows).isEqualTo(10);
        assertThat(actualPlaces).isEqualTo(10);
        assertThat(actualFileId).isEqualTo(1);
    }

    @Test
    public void whenPostTicketThenSameDataRedirectSuccessfulPage() {
        var ticket = new Ticket(0, 1, 10, 10, 1);
        var expectedTicket = new Ticket(1, 1, 10, 10, 1);
        when(ticketService.save(any())).thenReturn(Optional.of(expectedTicket));

        var model = new ConcurrentModel();
        var view = ticketController.create(ticket, model, httpServletRequest);
        var actualMessage = model.getAttribute("message");
        var actualTicket = model.getAttribute("ticket");

        assertThat(view).isEqualTo("tickets/successful");
        assertThat(actualMessage).isEqualTo("Ticket purchased successfully");
        assertThat(actualTicket).isEqualTo(expectedTicket);
    }

    @Test
    public void whenPostBusyTicketThenSameDataRedirectUnsuccessfulPage() {
        var ticket = new Ticket(0, 1, 10, 10, 1);
        when(ticketService.save(any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.create(ticket, model, httpServletRequest);
        var actualMessage = model.getAttribute("message");
        var actualTicket = model.getAttribute("ticket");

        assertThat(view).isEqualTo("tickets/unsuccessful");
        assertThat(actualMessage).isEqualTo("These row or place are busy. Try another");
        assertThat(actualTicket).isEqualTo(ticket);
    }

    @Test
    public void whenPostTicketAndSomeExceptionThrownThenGetErrorPageWithMessage() {
        var ticket = new Ticket(0, 1, 10, 10, 1);
        var expectedException = new RuntimeException("Some Exception");
        when(ticketService.save(any())).thenThrow(expectedException);

        var model = new ConcurrentModel();
        var view = ticketController.create(ticket, model, httpServletRequest);
        var actualMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expectedException.getMessage());
    }
}
