package Core;

import Repositories.ReservationRepositoryImpl;
import Repositories.RoomRepositoryImpl;

import Repositories.UserRepository;
import Repositories.UserRepositoryImpl;
import Services.AuthService;
import Services.ReservationService;
import Services.RoomService;

import java.sql.Connection;

public class Repository {
    private final ReservationService ReservationService;
    private final RoomService RoomService;
    private final AuthService AuthService;

    public Repository() {
        Connection connectionInstance = DB_Connection.getConnection();

        UserRepositoryImpl UserRepository = new UserRepositoryImpl(connectionInstance);
        ReservationRepositoryImpl ReservationRepository = new ReservationRepositoryImpl(connectionInstance);
        RoomRepositoryImpl RoomRepository = new RoomRepositoryImpl(connectionInstance);

        this.AuthService = new AuthService(UserRepository);
        this.ReservationService = new ReservationService(ReservationRepository);
        this.RoomService = new RoomService(RoomRepository);

    }
    public AuthService getAuthService() {
        return this.AuthService;
    }

    public ReservationService getReservationService() {
        return this.ReservationService;
    }

    public RoomService getRoomService() {
        return this.RoomService;
    }
}
