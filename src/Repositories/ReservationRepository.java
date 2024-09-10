package Repositories;

import Models.Reservations;

import java.util.List;

public interface ReservationRepository {
    Reservations findById(int id);
    List<Reservations> fetchAll();
    void addReservation(Reservations reservation);
    void updateReservation(Reservations reservation);
    void deleteReservation(int id);
}
