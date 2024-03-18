package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Genre;

import java.util.Map;

public interface GenreService {

    Map<Integer, Genre> findAll();
}
