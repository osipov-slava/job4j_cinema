package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.util.ArrayList;
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
    public Collection<FilmSessionDto> findAll() {
        var filmSessions = filmSessionRepository.findAll();
        Collection<FilmSessionDto> filmSessionDtos = new ArrayList<>();
        if (!filmSessions.isEmpty()) {
            var halls = hallService.findAll();
            var filmDtos = filmService.findAll();
            for (FilmSession fs : filmSessions) {
                filmSessionDtos.add(new FilmSessionDto(
                        fs.getId(), filmDtos.get(fs.getFilmId()).getName(), halls.get(fs.getHallId()).getName(),
                        fs.getStartTime(), fs.getEndTime(), fs.getPrice()));
            }
        }
        return filmSessionDtos;
    }

}
