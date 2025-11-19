package com.example.reservation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<?> addBooking(@RequestBody BookingDTO dto) {
        try{
            var booking = bookingService.addBooking(dto.name, dto.email, dto.date, dto.numberOfGuests, dto.status);
            return ResponseEntity.ok().body(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getBookings() {
        try{
            var bookings = bookingService.getBookings();
            return ResponseEntity.ok().body(bookings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingPerId(@PathVariable UUID id) {
        try {
            var booking = bookingService.getBookingPerId(id);
            if(booking.isPresent()) {
                return ResponseEntity.ok().body(booking.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> deleteBookingById(@PathVariable UUID id) {
        try {
            var booking = bookingService.deleteBookingById(id);
            return ResponseEntity.ok(booking + " Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<?> updateBookingById(@PathVariable UUID id, @RequestBody UpdateDTO dto) {
        try {
            var booking = bookingService.updateById(id, dto.name, dto.email, dto.date, dto.numberOfGuests, dto.status);
            return ResponseEntity.ok().body(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @Data
    public static class BookingDTO {
        private String name;
        private String email;
        private LocalDateTime date;
        private int numberOfGuests;
        private Status status;
    }

    @Data
    public static class UpdateDTO {
        private String name;
        private String email;
        private LocalDateTime date;
        private int numberOfGuests;
        private Status status;
    }

    @Data
    public static class IdDTO {
        private UUID id;
    }
}
