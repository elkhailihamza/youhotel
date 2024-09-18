package Controllers;

import Core.Repository;
import Models.Rooms;
import Services.RoomService;

import java.util.HashMap;
import java.util.Objects;

public class RoomController {
    private final RoomService roomService;
    public RoomController(Repository repository) {
        this.roomService = repository.getRoomService();
    }

    private void printRoomDetails(Rooms room, int iteration) {
        System.out.println(iteration+"). ID: ("+room.getId()+")- Room N°:"+room.getRoomNumber()+" / Price-per-night:"+room.getPrice()+"$ / Category:"+room.getCategoryName()+"  Status:"+room.getStatusName());
    }

    public static boolean checkIfOccupied(Rooms room) {
        return Objects.equals(room.getCategoryName(), "Occupied");
    }

    public int showById(long id) {
        Rooms room = roomService.findById(id);
        if (room == null) {
            System.out.println("Room not found!");
        } else {
            printRoomDetails(room, 1);
        }
        return 0;
    }

    public int showAll() {
        System.out.println("Fetching hotel rooms..");
        HashMap<Long, Rooms> rooms = roomService.fetchAll();
        if (rooms.isEmpty()) {
            System.out.println("No Rooms can be found at the moment!");
        } else {
            System.out.println("Found: "+rooms.size());
            int i = 1;
            for (Rooms r:rooms.values()) {
                printRoomDetails(r, i);
                i++;
            }
        }
        return 0;
    }
}
