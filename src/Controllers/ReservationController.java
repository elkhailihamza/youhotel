package Controllers;

import Core.Program;
import Core.Repository;
import Models.Reservations;
import Services.ReservationService;
import Services.RoomService;

import java.util.Date;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private final ReservationService reservationService;
    private final Scanner scanner;
    private final RoomService roomService;

    public ReservationController(Repository repository) {
        this.reservationService = repository.getReservationService();
        this.roomService = repository.getRoomService();
        this.scanner = Program.getScannerInstance();
    }

    private void printReservationDetails(Reservations reservation, int iterations) {
        System.out.println(iterations + "). Id: (" + reservation.getId() + ") - " + reservation.getNote() + " / On:" + reservation.getStartDate() + " Ends:" + reservation.getEndDate() + " / Room id:" + reservation.getRoomId());
    }

    public int showById(long id) {
        Reservations reservation = this.reservationService.findById(id);
        if (reservation == null) {
            System.out.println("Reservation not found!");
        } else {
            this.printReservationDetails(reservation, 1);
        }
        return 0;
    }

    public int showReservations() {
        List<Reservations> reservationsList = this.reservationService.fetchAll();
        int i = 1;
        if (reservationsList.isEmpty()) {
            System.out.println("No Reservations can be found!");
        } else {
            for (Reservations r : reservationsList) {
                this.printReservationDetails(r, i);
                i++;
            }
        }
        return 0;
    }

    public int addReservation() {
        long roomId;
        String reservationNote;
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fullDateFormat.setLenient(false);
        Date startDate, endDate;
        LocalDate today = java.time.LocalDate.now();

        while (true) {
            System.out.println("Type Room id: ");
            System.out.println("0). back to main menu ");
            if (!this.scanner.hasNextLong()) {
                System.out.println("***WARNING*** : Invalid room id. Please input a valid room id:");
                this.scanner.next();
                continue;
            }
            roomId = this.scanner.nextLong();
            if (roomId == 0) return 0;
            if (!this.roomService.checkIfExists(roomId) || this.roomService.checkStatus(roomId, "Occupied")) {
                System.out.println("***WARNING*** : Room doesn't exist or is already reserved!");
                continue;
            }
            break;
        }
        Program.clearInputBuffer(this.scanner);

        do {
            System.out.println("Include a note (3 characters or more): ");
            reservationNote = this.scanner.nextLine();
            if (reservationNote.length() < 3)
                System.out.println("***WARNING*** : Note needs to be at least 3 characters long!");
        } while (reservationNote.length() < 3);

        startDate = promptForDate("Enter a starting date (yyyy-MM-dd): ", "Today is:", fullDateFormat, today);
        endDate = promptForDate("Enter an end date (yyyy-MM-dd): ", "Starting date: ", fullDateFormat, startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        Reservations reservation = new Reservations(1, reservationNote, startDate, endDate, roomId);
        this.reservationService.addReservation(reservation);

        return 0;
    }

    private Date promptForDate(String firstPrompt, String secondPrompt, SimpleDateFormat dateFormat, LocalDate dateToCompareTo) {
        Date date = null;
        String input, confirmation;

        do {
            System.out.println(secondPrompt + " " + dateToCompareTo);
            System.out.println(firstPrompt);
            input = this.scanner.nextLine();
            try {
                date = dateFormat.parse(input);
                if (date.before(Date.from(dateToCompareTo.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    date = null;
                    System.out.println("***WARNING*** : Chosen date can't be before, " + dateToCompareTo);
                    continue;
                }
                System.out.println();
                System.out.println("Date: " + date);
                System.out.println("Are you sure? (y/n):");
                confirmation = this.scanner.nextLine();
                if (confirmation.equalsIgnoreCase("n")) date = null;
            } catch (ParseException e) {
                System.out.println("***WARNING*** : Invalid date format. Please try again.");
            }
        } while (date == null);

        return date;
    }

    public int updateReservation() {
        int reservationId;
        Reservations reservation;
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fullDateFormat.setLenient(false);
        Date startDate, endDate;
        LocalDate today = java.time.LocalDate.now();

        while (true) {
            System.out.println(" Type reservation id: ");
            System.out.println("0. Back to main menu ");

            if (!this.scanner.hasNextLong()) {
                System.out.println("***WARNING*** : Invalid reservation id. Please input a valid reservation id:");
                this.scanner.next();
                continue;
            }

            reservationId = this.scanner.nextInt();
            Program.clearInputBuffer(this.scanner);

            if (reservationId == 0) return 0;
            if (reservationService.checkIfExists(reservationId)) {
                reservation = reservationService.findById(reservationId);
                String result;
                System.out.println("Change reservation Details : " + reservation.getId());
                System.out.println("Input 1 to skip or 0 to exit");
                do {
                    System.out.println("Old Note: " + reservation.getNote());
                    System.out.println("New Note: ");

                    result = this.scanner.nextLine();
                    if (result.equals("1"))
                        break;
                    else if (result.equals("0"))
                        return 0;
                    reservation.setNote(result);

                    System.out.println("New Note: " + reservation.getNote());
                } while (true);

                System.out.println("Old start date: " + reservation.getStartDate());
                System.out.println("Old end date: " + reservation.getEndDate());

                startDate = promptForDate("Change starting date (yyyy-MM-dd): ", "Today is:", fullDateFormat, today);
                endDate = promptForDate("Change an end date (yyyy-MM-dd): ", "Starting date: ", fullDateFormat, startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                reservation = new Reservations(1, reservation.getNote(), startDate, endDate, reservation.getRoomId());
                this.reservationService.updateReservation(reservation);
                return 0;
            }

            System.out.println("***WARNING*** : Invalid room id. Try again");
        }
    }

    public int cancelReservation() {
        long reservationId;
        String userConfirmation;
        Reservations reservation;

        while (true) {
            System.out.println("Select reservation id: ");
            System.out.println("0. Back to main menu");

            if (!this.scanner.hasNextLong()) {
                System.out.println("***WARNING*** : Invalid reservation id. Please input a valid reservation id:");
                Program.clearInputBuffer(this.scanner);
                continue;
            }

            reservationId = this.scanner.nextLong();
            Program.clearInputBuffer(this.scanner);

            if (reservationId == 0) return 0;
            if (this.reservationService.checkIfExists(reservationId)) {
                reservation = this.reservationService.findById(reservationId);
                do {
                    this.printReservationDetails(reservation, 1);
                    System.out.println(" Are you sure? (y,n)");
                    userConfirmation = this.scanner.nextLine().trim();
                } while (!userConfirmation.equalsIgnoreCase("Y") && !userConfirmation.equalsIgnoreCase("N"));

                if (userConfirmation.equalsIgnoreCase("Y"))
                    this.reservationService.deleteRoom(reservation.getId());
                break;
            } else {
                System.out.println("***WARNING*** : Selected Reservation doesn't exist!");
            }
        }
        return 0;
    }
}
