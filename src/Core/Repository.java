package Core;

import Repositories.PromotionRepositoryImpl;
import Repositories.ReservationRepositoryImpl;
import Repositories.RoomRepositoryImpl;

import Repositories.UserRepositoryImpl;
import Services.AuthService;
import Services.PromotionService;
import Services.ReservationService;
import Services.RoomService;

import java.sql.Connection;

public class Repository {
    private final ReservationService ReservationService;
    private final RoomService RoomService;
    private final AuthService AuthService;
    private final PromotionService PromotionService;

    public Repository() {
        Connection connectionInstance = DB_Connection.getConnection();

        UserRepositoryImpl UserRepository = new UserRepositoryImpl(connectionInstance);
        ReservationRepositoryImpl ReservationRepository = new ReservationRepositoryImpl(connectionInstance);
        RoomRepositoryImpl RoomRepository = new RoomRepositoryImpl(connectionInstance);
        PromotionRepositoryImpl PromotionRepository = new PromotionRepositoryImpl(connectionInstance);

        this.AuthService = new AuthService(UserRepository);
        this.ReservationService = new ReservationService(ReservationRepository);
        this.RoomService = new RoomService(RoomRepository);
        this.PromotionService = new PromotionService(PromotionRepository);

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

    public PromotionService getPromotionService() {
        return this.PromotionService;
    }
}
