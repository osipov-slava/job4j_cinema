package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmDto;

import java.util.Map;
import java.util.Optional;

public interface FilmService {

    Map<Integer, FilmDto> findAll();

    Optional<FilmDto> findById(int id);
}
