package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Hall;

import java.util.Map;

public interface HallService {

    Map<Integer, Hall> findAll();
}
