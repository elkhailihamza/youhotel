package Repositories;

import Models.Reservations;

import java.util.List;

public interface ReservationRepository {
    Reservations findById(long id);
    List<Reservations> fetchAll(long user_id);
    void addReservation(Reservations reservation);
    void updateReservation(Reservations reservation);
    void deleteReservation(long id);
    Reservations findByUserIdAndRoomId(long user_id, long room_id);
    int userReservationsStatistics(long user_id);
}
