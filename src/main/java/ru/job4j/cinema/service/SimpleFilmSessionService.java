package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final Map<Integer, Hall> halls;

    private final FilmSessionRepository filmSessionRepository;

    private final FilmService filmService;


    public SimpleFilmSessionService(HallService hallService, FilmService filmService, FilmSessionRepository filmSessionRepository) {
        this.halls = hallService.findAll();
        this.filmService = filmService;
        this.filmSessionRepository = filmSessionRepository;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        Map<Integer, FilmDto> filmDtos = filmService.findAll();
        var filmSessions = filmSessionRepository.findAll();
        Collection<FilmSessionDto> filmSessionDtos = new ArrayList<>();
        for (FilmSession fs : filmSessions) {
            filmSessionDtos.add(new FilmSessionDto(
                    fs.getId(), filmDtos.get(fs.getFilmId()).getName(), this.halls.get(fs.getHallId()).getName(),
                    fs.getStartTime(), fs.getEndTime(), fs.getPrice()));
        }
        return filmSessionDtos;
    }

}
