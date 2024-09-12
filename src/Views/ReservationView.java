//package Views;
//
//import Core.Program;
//import Core.Repository;
//import Models.Reservations;
//import Services.ReservationService;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
//public class ReservationView {
//    private static final Scanner scanner = Program.getScannerInstance();
//
//    Repository repository = new Repository();
//
//
//
//
//
//    public static int delete() {
//        int reservationId;
//        String userConfirmation;
//        Reservations reservation;
//
//        while (true) {
//            System.out.println("Select reservation id: ");
//            System.out.println("0. Back to main menu");
//
//            if (!scanner.hasNextInt()) {
//                System.out.println("Invalid reservation id. Please input a valid reservation id:");
//                scanner.next();
//                continue;
//            }
//
//            reservationId = scanner.nextInt();
//            System.out.println(reservationId);
//            Program.clearInputBuffer(scanner);
//
//            if (reservationId == 0) return 0;
//            if (Reservations.checkReservationId(reservationId, reservationList)) {
//                reservation = reservationList.get(reservationId-1);
//                do {
//                    System.out.println(reservation.id + ". Note: " + reservation.note);
//                    System.out.println(" Are you sure? (y,n)");
//                    userConfirmation = scanner.nextLine().trim();
//                } while (!userConfirmation.equalsIgnoreCase("Y") && !userConfirmation.equalsIgnoreCase("N"));
//
//                if (userConfirmation.equalsIgnoreCase("Y"))
//                    reservationList.remove(reservationId-1);
//                break;
//            } else {
//                System.out.println("Model.Reservation doesn't exist!");
//            }
//        }
//        return 0;
//    }
//
//    public static boolean checkReservationId(int id, List<Reservations> reservationList) {
//        for (Reservations n : reservationList)
//            if (n.id == id)
//                return true;
//        return false;
//    }
//}
