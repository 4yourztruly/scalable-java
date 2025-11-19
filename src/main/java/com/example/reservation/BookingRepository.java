package com.example.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAll();
    Optional<Booking> findById(UUID uuid);
    Booking save(Booking booking);
    void deleteById(UUID uuid);


}
