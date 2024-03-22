package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/new")
    public String getCreationPage(@RequestParam(value = "sessionId") Integer sessionId,
                                  @RequestParam(value = "title") String title,
                                  @RequestParam(value = "startDateTime") String startDateTime,
                                  @RequestParam(value = "hall") String hall,
                                  @RequestParam(value = "rows") Integer rows,
                                  @RequestParam(value = "places") Integer places,
                                  @RequestParam(value = "price") Integer price,
                                  @RequestParam(value = "fileId") Integer fileId,
                                  Model model, HttpServletRequest request) {

        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ISO_DATE_TIME);
        model.addAttribute("startDateTime", dateTime);

        model.addAttribute("title", title);
        model.addAttribute("hall", hall);
        model.addAttribute("rows", rows);
        model.addAttribute("places", places);
        model.addAttribute("price", price);
        model.addAttribute("fileId", fileId);
        var session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("ticket", new Ticket(0, sessionId, 0, 0, user.getId()));
        return "tickets/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Ticket ticket, Model model, HttpServletRequest request) {
        try {
            var optional = ticketService.save(ticket);
            if (optional.isEmpty()) {
                model.addAttribute(ticket);
                model.addAttribute("message", "These row or place are busy. Try another");
                return "tickets/unsuccessful";
            }
            model.addAttribute("message", "Ticket purchased successfully");
            model.addAttribute(optional.get());
            return "tickets/successful";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }
}
