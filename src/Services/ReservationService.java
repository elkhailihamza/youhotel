package Services;

import Models.Reservations;
import Models.Rooms;
import Repositories.ReservationRepository;

import java.util.List;
import java.util.Objects;

public class ReservationService {
    private final ReservationRepository ReservationRepository;
    public ReservationService(ReservationRepository ReservationRepository) {
        this.ReservationRepository = ReservationRepository;
    }

    public Reservations findById(long id) {
        return ReservationRepository.findById(id);
    }

    public List<Reservations> fetchAll() {
        return ReservationRepository.fetchAll();
    }

    public void addReservation(Reservations reservation) {
        ReservationRepository.addReservation(reservation);
    }

    public void updateReservation(Reservations reservation) {
        ReservationRepository.updateReservation(reservation);
    }

    public void deleteRoom(long id) {
        ReservationRepository.deleteReservation(id);
    }

    public boolean checkIfExists(long id) {
        Reservations reservation = this.findById(id);
        return !Objects.equals(reservation, null);
    }

    //public boolean checkIfAlreadyReserved(long user_id, long room_id) {
    //    Reservations reservations
    //}
}
