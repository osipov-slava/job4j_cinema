package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Hall;

import java.util.Collection;

public interface HallRepository {

    Collection<Hall> findAll();
}
