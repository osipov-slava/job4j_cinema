package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.util.Collection;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmService filmService;

    private final HallService hallService;

    public SimpleFilmSessionService(HallService hallService, FilmService filmService, FilmSessionRepository filmSessionRepository) {
        this.hallService = hallService;
        this.filmService = filmService;
        this.filmSessionRepository = filmSessionRepository;
    }

    @Override
    public Collection<FilmSession> findAll() {
        return filmSessionRepository.findAll();
    }

}
