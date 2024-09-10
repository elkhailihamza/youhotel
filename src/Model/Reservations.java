package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Reservation {

    private int id;
    private String note;
    private static List<Reservation> reservationList;
    private static Scanner scanner = Program.getScannerInstance();

    static {
        reservationList = new ArrayList<>();
    }

    public Reservation(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public void setReservationId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return this.id;
    }

    public int getReservationNote() {
        return this.id;
    }

    public static int getReservations() {
        if (reservationList.isEmpty()) {
            System.out.println("No reservations found!");
        } else {
            System.out.println("Found: " + reservationList.size());
            for (Reservation r : reservationList)
                System.out.println(r.id + ". " + r.note);
        }
        return 0;
    }

    public static int create(HashMap<String, Hotel> hotelRooms) {
        int hotelRoomId;
        String userNote;

        while (true) {
            System.out.println(" Type Room id: ");
            System.out.println("0. Back to main menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid room id. Please input a valid room id:");
                scanner.next();
                continue;
            }

            hotelRoomId = scanner.nextInt();
            if (hotelRoomId == 0) return 0;
            if (Hotel.checkHotelId(hotelRoomId, hotelRooms) && !Hotel.checkIfAlreadyReservedRoom(hotelRooms.get(hotelRoomId - 1)))
                break;

            System.out.println("Model.Hotel room doesn't exist, or already reserved!");
        }

        Program.clearInputBuffer(scanner);

        do {
            System.out.println("Include a note: ");
            userNote = scanner.nextLine();
        } while (userNote.isEmpty());

        int newId = reservationList.isEmpty() ? 1 : reservationList.getLast().id + 1;
        reservationList.add(new Reservation(newId, userNote));

        System.out.println("Created! " + reservationList.getLast().id + ". " + reservationList.getLast().note);

        return 0;
    }

    public static int update() {
        int reservationId;
        Reservation reservation;

        while (true) {
            System.out.println(" Type reservation id: ");
            System.out.println("0. Back to main menu ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid reservation id. Please input a valid reservation id:");
                scanner.next();
                continue;
            }

            reservationId = scanner.nextInt();
            Program.clearInputBuffer(scanner);

            if (reservationId == 0) return 0;
            if (Reservation.checkReservationId(reservationId, reservationList)) {
                reservation = reservationList.get(reservationId - 1);
                System.out.println("Change reservation Note " + reservation.id + ". Note: " + reservation.note);

                reservation.note = scanner.nextLine();
                System.out.println("New note: " + reservation.note);
                return 0;
            }

            System.out.println("Invalid room id. Try again");
        }
    }

    public static int delete() {
        int reservationId;
        String userConfirmation;
        Reservation reservation;

        while (true) {
            System.out.println("Select reservation id: ");
            System.out.println("0. Back to main menu");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid reservation id. Please input a valid reservation id:");
                scanner.next();
                continue;
            }

            reservationId = scanner.nextInt();
            System.out.println(reservationId);
            Program.clearInputBuffer(scanner);

            if (reservationId == 0) return 0;
            if (Reservation.checkReservationId(reservationId, reservationList)) {
                reservation = reservationList.get(reservationId-1);
                do {
                    System.out.println(reservation.id + ". Note: " + reservation.note);
                    System.out.println(" Are you sure? (y,n)");
                    userConfirmation = scanner.nextLine().trim();
                } while (!userConfirmation.equalsIgnoreCase("Y") && !userConfirmation.equalsIgnoreCase("N"));

                if (userConfirmation.equalsIgnoreCase("Y"))
                    reservationList.remove(reservationId-1);
                break;
            } else {
                System.out.println("Model.Reservation doesn't exist!");
            }
        }
        return 0;
    }

    public static boolean checkReservationId(int id, List<Reservation> reservationList) {
        for (Reservation n : reservationList)
            if (n.id == id)
                return true;
        return false;
    }
}
