package com.example.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking addBooking(String name, String email, LocalDateTime date, int numberOfGuests, Status status) {
        Booking booking = new Booking(name, email, date, numberOfGuests, status);
        bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingPerId(UUID uuid) {
        return bookingRepository.findById(uuid);
    }

    public Booking deleteBookingById(UUID uuid) {
        var booking = bookingRepository.findById(uuid);
        if(booking.isPresent()) {
            bookingRepository.deleteById(uuid);
        } else {
            throw new NoSuchElementException();
        }
        return booking.get();
    }

    public Booking updateById(UUID uuid, String name, String email, LocalDateTime date, int numberOfGuests, Status status) {
        var booking = bookingRepository.findById(uuid);
        if(booking.isEmpty()) {
            throw new NoSuchElementException();
        }
        Booking item = booking.get();
        item.setName(name);
        item.setEmail(email);
        item.setDate(date);
        item.setNumberOfGuests(numberOfGuests);
        item.setStatus(status);
        return bookingRepository.save(item);
    }
}
