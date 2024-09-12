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

    private void printReservationDetails(Reservations reservation) {
        System.out.println(reservation.getId() + "). " + reservation.getNote() + " / On:" + reservation.getStartDate() + " Ends:" + reservation.getEndDate() + " / Room id:" + reservation.getRoomId());
    }

    private void checkUserChosenDates() {

    }

    public int showById(long id) {
        Reservations reservation = this.reservationService.findById(id);
        if (reservation == null) {
            System.out.println("Reservation not found!");
        } else {
            printReservationDetails(reservation);
        }
        return 0;
    }

    public int showReservations() {
        List<Reservations> reservationsList = this.reservationService.fetchAll();
        if (reservationsList.isEmpty()) {
            System.out.println("No Reservations can be found!");
        } else {
            for (Reservations r : reservationsList)
                this.printReservationDetails(r);
        }
        return 0;
    }

    public int addReservation() {
        long roomId;
        String reservationNote;
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fullDateFormat.setLenient(false);
        Date startDate = null, endDate = null;
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

        startDate = promptForDate("Enter a starting date (yyyy-MM-dd): ", fullDateFormat, today, "Today is:");

        while(true) {
            endDate = promptForDate("Enter an end date (yyyy-MM-dd): ", fullDateFormat, startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "Starting date: ");
            if (endDate.before(startDate)) {
                System.out.println();
                System.out.println("***WARNING*** : End date can't be before starting date!");
            } else {
                break;
            }
        }

        Reservations reservation = new Reservations(1, reservationNote, startDate, endDate, roomId);
        this.reservationService.addReservation(reservation);

        return 0;
    }

    private Date promptForDate(String prompt, SimpleDateFormat dateFormat, LocalDate dateToCompareTo, String showDateToCompareTo) {
        Date date = null;
        String input, confirmation;

        do {
            System.out.println(showDateToCompareTo+" "+dateToCompareTo);
            System.out.println(prompt);
            input = this.scanner.nextLine();
            try {
                date = dateFormat.parse(input);
                if (date.before(Date.from(dateToCompareTo.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    date = null;
                    System.out.println("***WARNING*** : Chosen date can't be before, " + dateToCompareTo);
                    continue;
                }
                System.out.println();
                System.out.println("Date: "+date);
                System.out.println("Are you sure? (y/n):");
                confirmation = this.scanner.nextLine();
                if (confirmation.equalsIgnoreCase("n")) date = null;
            } catch (ParseException e) {
                System.out.println("***WARNING*** : Invalid date format. Please try again.");
            }
        } while (date == null);

        return date;
    }

    public int update() {
        int reservationId;
        Reservations reservation;

        while (true) {
            System.out.println(" Type reservation id: ");
            System.out.println("0. Back to main menu ");

            if (!this.scanner.hasNextLong()) {
                System.out.println("Invalid reservation id. Please input a valid reservation id:");
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
                    else
                        reservation.setNote(result);

                    System.out.println("New Note: " + reservation.getNote());
                } while (true);

                do {
                    System.out.println("Old start date: " + reservation.getStartDate());
                    System.out.println("Old end date: " + reservation.getEndDate());
                    System.out.println("Change starting date: ");
                    break;
                } while (true);

                return 0;
            }

            System.out.println("Invalid room id. Try again");
        }
    }
}
