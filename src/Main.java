import DB.DB_Connection;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        boolean start = true;
        int option = 0;
        HashMap<String, Hotel> hotelRooms = new HashMap<>();
        hotelRooms.put("hotel1", new Hotel(Hotel.Category.DELUXE, Hotel.Status.Reserved, 1, Hotel.Equipements.Climatisation,12.12));
        hotelRooms.put("hotel2", new Hotel(Hotel.Category.STANDARD, Hotel.Status.Empty,2, Hotel.Equipements.Climatisation, 20.1));
        hotelRooms.put("hotel3", new Hotel(Hotel.Category.STANDARD, Hotel.Status.Empty, 3, Hotel.Equipements.Climatisation, 102.2));

        DB_Connection connection = new DB_Connection();
        connection.getConnection();
        Program.start(start, option, hotelRooms);
    }
}