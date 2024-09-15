import Controllers.AuthController;
import Controllers.ReservationController;
import Controllers.RoomController;
import Core.Program;
import Core.Repository;
import Views.Menu;

public class Main {
    public static void main(String[] args) {
        boolean loggedIn = false;
        boolean start = true;
        int option = 0;

        Repository repository = new Repository();
        ReservationController reservationController = new ReservationController(repository);
        RoomController roomController = new RoomController(repository);
        AuthController authController = new AuthController(repository);

        loggedIn = authController.showMenu();

        if (loggedIn) {
            System.out.println();
            System.out.println("Welcome!");
            while (start) {
                switch (option) {
                    case 0 -> option = Menu.mainMenu();
                    case 1 -> option = roomController.showAll();
                    case 2 -> option = reservationController.addReservation();
                    case 3 -> option = reservationController.update();
                    case 4 -> option = reservationController.delete();
                    case 5 -> option = reservationController.showReservations();
                    case 6 -> start = Program.exit();
                    default -> option = 0;
                }
                System.out.println();
            }
        }
    }
}