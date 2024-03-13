package ru.job4j.cinema.repository;

import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_session");
            return query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeAndFetch(FilmSession.class);
        }
    }
}
