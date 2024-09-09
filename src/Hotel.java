import java.util.List;
import java.util.Scanner;

public class Hotel {

    public enum Status {
        Reserved, Empty;
    }

    private Status status;
    private int id;
    private String title;
    private double price;

    public Hotel(Status status, int id, String title, double price) {
        this.status = status;
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public static int getHotelRooms(List<Hotel> hotelRooms) {
        Scanner scanner = Program.getScannerInstance();
        String userConfirmation;
        boolean emptyRoomsDone = false;

        System.out.println();
        System.out.println(" Empty: ");
        for (Hotel h:hotelRooms) {
            if (h.status == Status.Empty) {
                emptyRoomsDone = true;
                System.out.println(h.id + ". " + h.title + " - " + h.price + "dh . " + h.status);
            }
        }

        System.out.println(" Reserved: ");
        if (emptyRoomsDone) {
            for (Hotel h : hotelRooms) {
                if (h.status == Status.Reserved)
                    System.out.println(h.id + ". " + h.title + " - " + h.price + "dh . " + h.status);
            }
        }
        System.out.println();

        do {
            System.out.println(" Would you like to reserve a spot (y,n)");
            userConfirmation = scanner.nextLine().trim();
        } while (!userConfirmation.equalsIgnoreCase("Y") && !userConfirmation.equalsIgnoreCase("N"));

        if (userConfirmation.equalsIgnoreCase("Y"))
            return Reservation.create(hotelRooms);
        return 0;
    }

    public static boolean checkHotelId(int id, List<Hotel> hotelRooms) {
        for (Hotel n : hotelRooms)
            if (n.id == id)
                return true;
        return false;
    }

    public static boolean checkIfAlreadyReservedRoom(Hotel room) {
        return room.status == Status.Reserved;
    }
}
