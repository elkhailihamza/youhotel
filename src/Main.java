import Controllers.AuthController;
import Controllers.ReservationController;
import Controllers.RoomController;
import Controllers.StatisticsController;
import Core.Program;
import Core.Repository;
import Models.Users;
import Services.AuthService;
import Views.Menu;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        boolean loggedIn = false;
        boolean start = true;
        int option = 0;

        Repository repository = new Repository();
        AuthController authController = new AuthController(repository);
        ReservationController reservationController = new ReservationController(repository);
        RoomController roomController = new RoomController(repository);
        StatisticsController statisticsController = new StatisticsController(repository);

        loggedIn = authController.showMenu();

        if (loggedIn) {
            System.out.println();
            System.out.println("Welcome!");
            while (start) {
                switch (option) {
                    case 0 -> option = Menu.mainMenu();
                    case 1 -> option = roomController.showAll();
                    case 2 -> option = reservationController.addReservation();
                    case 3 -> option = reservationController.updateReservation();
                    case 4 -> option = reservationController.cancelReservation();
                    case 5 -> option = reservationController.showReservations();
                    case 6 -> option = statisticsController.showStatistics();
                    case 7 -> start = Program.exit();
                    default -> option = 0;
                }
                System.out.println();
            }
        }
    }
}