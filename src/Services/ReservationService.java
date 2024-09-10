package Services;

import Models.Reservations;
import Models.Rooms;
import Repositories.ReservationRepository;

import java.util.List;

public class ReservationService {
    private final ReservationRepository ReservationRepository;
    public ReservationService(ReservationRepository ReservationRepository) {
        this.ReservationRepository = ReservationRepository;
    }

    public Reservations findById(int id) {
        return ReservationRepository.findById(id);
    }

    public List<Reservations> fetchAll() {
        return ReservationRepository.fetchAll();
    }

    public void addRoom(Rooms room) {

    }

    public void updateRoom(Rooms room) {

    }

    public void deleteRoom(int id) {

    }
}
