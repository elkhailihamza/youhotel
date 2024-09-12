package Core;

import Repositories.ReservationRepositoryImpl;
import Repositories.RoomRepositoryImpl;

import Services.ReservationService;
import Services.RoomService;

import java.sql.Connection;

public class Repository {
    private final ReservationService ReservationService;
    private final RoomService RoomService;

    public Repository() {
        Connection connectionInstance = DB_Connection.getConnection();

        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl(connectionInstance);
        RoomRepositoryImpl roomRepository = new RoomRepositoryImpl(connectionInstance);
        this.ReservationService = new ReservationService(reservationRepository);
        this.RoomService = new RoomService(roomRepository);
    }

    public ReservationService getReservationService() {
        return this.ReservationService;
    }

    public RoomService getRoomService() {
        return this.RoomService;
    }
}
