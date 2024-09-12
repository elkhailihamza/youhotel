package Repositories;

import Models.Rooms;

import java.util.HashMap;

public interface RoomRepository {
    Rooms findById(long id);
    HashMap<Long, Rooms> fetchAll();
}
