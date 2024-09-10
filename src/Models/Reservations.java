package Models;

import Core.Program;
import java.sql.Date;
import java.util.*;

public class Reservations {

    private final long id;
    private String note;
    private Date start_date;
    private Date end_date;
    private long room_id;

    private static final Scanner scanner = Program.getScannerInstance();

    public Reservations(long id, String note, Date start_date, Date end_date, long room_id) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.note = note;
        this.room_id = room_id;
    }

    public long getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartDate() {
        return this.start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEndDate() {
        return this.start_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    public long getRoomId() {
        return this.room_id;
    }

    public void getRoomId(long room_id) {
        this.room_id = room_id;
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

            System.out.println("Hotel room doesn't exist, or already reserved!");
        }

        Program.clearInputBuffer(scanner);

        do {
            System.out.println("Include a note: ");
            userNote = scanner.nextLine();
        } while (userNote.isEmpty());

        int newId = reservationList.isEmpty() ? 1 : reservationList.getLast().id + 1;
        reservationList.add(new Reservations(newId, userNote));

        System.out.println("Created! " + reservationList.getLast().id + ". " + reservationList.getLast().note);

        return 0;
    }

    public static int update() {
        int reservationId;
        Reservations reservation;

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
            if (Reservations.checkReservationId(reservationId, reservationList)) {
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
        Reservations reservation;

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
            if (Reservations.checkReservationId(reservationId, reservationList)) {
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

    public static boolean checkReservationId(int id, List<Reservations> reservationList) {
        for (Reservations n : reservationList)
            if (n.id == id)
                return true;
        return false;
    }
}
