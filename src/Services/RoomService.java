package Services;

import Models.Rooms;
import Repositories.RoomRepository;

import java.util.HashMap;
import java.util.Objects;

public class RoomService {
    private final RoomRepository RoomRepository;
    public RoomService(RoomRepository RoomRepository) {
        this.RoomRepository = RoomRepository;
    }

    public Rooms findById(long id) {
        return RoomRepository.findById(id);
    }

    public HashMap<Long, Rooms> fetchAll() {
        return RoomRepository.fetchAll();
    }

    public void updateRoomPrice(Rooms room) {
        RoomRepository.updateRoomPrice(room);
    }

    public boolean checkIfExists(long id) {
        Rooms room = this.findById(id);
        return !Objects.equals(room, null);
    }

    public boolean checkStatus(long id, String status) {
        Rooms room = this.findById(id);
        return Objects.equals(room.getStatusName(), status);
    }
}
