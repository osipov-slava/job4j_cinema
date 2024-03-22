package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final HallService hallService;

    public FilmSessionController(FilmSessionService filmSessionService, FilmService filmService, HallService hallService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmSessions", filmSessionService.findAll());
        model.addAttribute("films", filmService.findAll());
        model.addAttribute("halls", hallService.findAll());
        return "filmSessions/list";
    }

}
