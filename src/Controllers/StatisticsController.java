package Controllers;

import Core.Repository;
import Services.AuthService;
import Services.ReservationService;

public class StatisticsController {
    private final ReservationService reservationService;

    public StatisticsController(Repository repository) {
        this.reservationService = repository.getReservationService();
    }

    private int getUserMadeReservationsNumber(long user_id) {
        return this.reservationService.userReservationsStatistics(user_id);
    }

    public int showStatistics() {
        long user_id = AuthService.getCurrentUserId();
        int reservations = this.getUserMadeReservationsNumber(user_id);
        System.out.println("Number of reservations made: "+reservations);
        return 0;
    }
}
