package Core;

import Repositories.ReservationRepositoryImpl;
import Services.ReservationService;

import java.sql.Connection;

public class Repository {
    private final Connection connectionInstance = DB_Connection.getConnection();

    ReservationRepositoryImpl ReservationsRepository = new ReservationRepositoryImpl(connectionInstance);
    ReservationService ReservationService = new ReservationService(ReservationsRepository);
}
