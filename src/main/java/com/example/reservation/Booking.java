package com.example.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "booking")
public class Booking {

    @Id
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime date;
    private int numberOfGuests;
    private Status status;

    protected Booking() {}

    public Booking(String name, String email, LocalDateTime date, int numberOfGuests, Status status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
    }

    public Booking(UUID uuid, String name, String email, LocalDateTime date, int numberOfGuests, Status status) {
        this.id = uuid;
        this.name = name;
        this.email = email;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
    }
}
