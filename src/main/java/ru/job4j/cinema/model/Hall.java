package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Hall {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "row_count", "description",
            "place_count", "creationDate",
            "description", "visible"
    );

    private final int id;

    private final String name;

    private final int rowCount;

    private final int placeCount;

    private final String description;

    public Hall(int id, String name, int rowCount, int placeCount, String description) {
        this.id = id;
        this.name = name;
        this.rowCount = rowCount;
        this.placeCount = placeCount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}