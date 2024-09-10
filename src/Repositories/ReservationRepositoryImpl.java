package Repositories;

import Core.DB_Connection;
import Models.Reservations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final Connection connectionInstance;

    public ReservationRepositoryImpl(Connection connection) {
        this.connectionInstance = DB_Connection.getConnection();
    }

    @Override
    public Reservations findById(int id) {
        String sql = "SELECT * FROM reservations WHERE rooms.room_id = ?;";
        Reservations reservation = null;
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long reservation_id = rs.getLong("reservation_id");
                    String reservation_note = rs.getString("reservation_note");
                    Date reservation_start_date = rs.getDate("reservation_start_date");
                    Date reservation_end_date = rs.getDate("reservation_end_date");
                    long room_id = rs.getLong("room_id");
                    reservation = new Reservations(reservation_id, reservation_note, reservation_start_date, reservation_end_date, room_id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return reservation;
    }

    @Override
    public List<Reservations> fetchAll() {
        String sql = "SELECT * FROM reservations;";
        List<Reservations> reservations = new ArrayList<>();
        try (Statement stmt = connectionInstance.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                long reservation_id = rs.getLong("reservation_id");
                String reservation_note = rs.getString("reservation_note");
                Date reservation_start_date = rs.getDate("reservation_start_date");
                Date reservation_end_date = rs.getDate("reservation_end_date");
                long room_id = rs.getLong("room_id");

                reservations.add(new Reservations(reservation_id, reservation_note, reservation_start_date, reservation_end_date, room_id));
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return reservations;
    }

    @Override
    public void addReservation(Reservations reservation) {
        String sql = "INSERT INTO reservations (note, start_date, end_date, room_id) VALUES (?, ?, ?, ?)";

        try(PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setString(1, reservation.getNote());
            stmt.setDate(2, reservation.getStartDate());
            stmt.setDate(3, reservation.getEndDate());
            stmt.setLong(4, reservation.getRoomId());

            int result = stmt.executeUpdate();
            if (result > 0)
                System.out.println("Reservation Inserted Successfully!");
        } catch(SQLException e) {
            System.out.println("Connection err: "+e);
        }
    }

    @Override
    public void updateReservation(Reservations reservations) {

    }

    @Override
    public void deleteReservation(int id) {

    }
}
