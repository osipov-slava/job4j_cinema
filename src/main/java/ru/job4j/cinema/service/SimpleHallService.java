package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleHallService implements HallService {

    private static Map<Integer, Hall> hallsMap = new ConcurrentHashMap<>();

    private HallRepository hallRepository;

    public SimpleHallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Map<Integer, Hall> findAll() {
        if (hallsMap.isEmpty()) {
            var halls = hallRepository.findAll();
            if (!halls.isEmpty()) {
                for (Hall hall : halls) {
                    hallsMap.put(hall.getId(), hall);
                }
            }
        }
        return hallsMap;
    }
}
