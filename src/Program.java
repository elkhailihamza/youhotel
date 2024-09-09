import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    private static Scanner scannerInstance;

    public static void start(boolean start, int option, List<Hotel> hotelRooms) {
        while (start) {
            switch (option) {
                case 0 -> option = Menu.mainMenu();
                case 1 -> option = Hotel.getHotelRooms(hotelRooms);
                case 2 -> option = Reservation.create(hotelRooms);
                case 3 -> option = Reservation.update();
                case 4 -> option = Reservation.delete();
                case 5 -> option = Reservation.getReservations();
                case 6 -> start = exit();
                default -> option = 0;
            }
        }
    }

    // Singleton pattern used here
    public static Scanner getScannerInstance() {
        if (scannerInstance == null)
            scannerInstance = new Scanner(System.in);
        return scannerInstance;
    }

    public static void clearInputBuffer(Scanner scanner) {
        if (scanner.hasNextLine())
            scanner.nextLine();
    }

    public static boolean exit() {
        System.out.println("Bye");
        return false;
    }
}