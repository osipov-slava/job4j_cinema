package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Ticket {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "session_id", "sessionId",
            "row_number", "row",
            "place_number", "place",
            "user_id", "userId"
    );

    private int id;

    private int sessionId;

    private int row;

    private int place;

    private int userId;

    public Ticket(int id, int sessionId, int row, int place, int userId) {
        this.id = id;
        this.sessionId = sessionId;
        this.row = row;
        this.place = place;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
