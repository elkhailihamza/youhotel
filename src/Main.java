import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean start = true;
        int option = 0;
        List<Hotel> hotelRooms = new ArrayList<>();
        hotelRooms.add(new Hotel(Hotel.Status.Reserved, 1, "hotel1", 12.12));
        hotelRooms.add(new Hotel(Hotel.Status.Empty,2, "hotel2", 20.1));
        hotelRooms.add(new Hotel(Hotel.Status.Empty, 3, "hotel3", 102.2));

        Program.start(start, option, hotelRooms);
    }
}