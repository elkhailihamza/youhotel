package Repositories;

import Models.Reservations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {
    private final Connection connectionInstance;

    public ReservationRepositoryImpl(Connection connection) {
        this.connectionInstance = connection;
    }

    private void setResultsToReservation(PreparedStatement stmt, Reservations reservation) throws SQLException {
        stmt.setString(1, reservation.getNote());
        stmt.setDate(2, new java.sql.Date(reservation.getStartDate().getTime()));
        stmt.setDate(3, new java.sql.Date(reservation.getEndDate().getTime()));
        stmt.setLong(4, reservation.getRoomId());
        stmt.setLong(5, reservation.getId());
    }

    private Reservations insertResultsIntoReservation(ResultSet rs) throws SQLException {
        long reservation_id = rs.getLong("reservation_id");
        String reservation_note = rs.getString("reservation_note");
        Date reservation_start_date = rs.getDate("reservation_start_date");
        Date reservation_end_date = rs.getDate("reservation_end_date");
        long room_id = rs.getLong("room_id");
        long user_id = rs.getLong("user_id");
        return new Reservations(reservation_id, reservation_note, reservation_start_date, reservation_end_date, room_id, user_id);
    }

    @Override
    public Reservations findById(long id) {
        String sql = "SELECT * FROM reservations WHERE reservations.reservation_id = ?;";
        Reservations reservation = null;
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservation = this.insertResultsIntoReservation(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return reservation;
    }

    @Override
    public List<Reservations> fetchAll(long user_id) {
        String sql = "SELECT * FROM reservations WHERE reservations.user_id = ?;";
        List<Reservations> reservations = new ArrayList<>();
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, user_id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservations reservation = this.insertResultsIntoReservation(rs);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return reservations;
    }

    @Override
    public void addReservation(Reservations reservation) {
        String sql = "INSERT INTO reservations (reservation_note, reservation_start_date, reservation_end_date, room_id) VALUES (?, ?, ?, ?);";

        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            this.setResultsToReservation(stmt, reservation);
            int result = stmt.executeUpdate();
            if (result > 0)
                System.out.println("Reservation Inserted Successfully!");
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
    }

    @Override
    public void updateReservation(Reservations reservation) {
        try {
            String sql = "UPDATE reservations SET reservation_note = ?, reservation_start_date = ?, reservation_end_date = ?, room_id = ? WHERE reservations.reservation_id = ?;";
            try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
                this.setResultsToReservation(stmt, reservation);
                int rowUpdated = stmt.executeUpdate();
                if (rowUpdated > 0)
                    System.out.println(reservation.getNote() + " has been updated!");
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
    }

    @Override
    public void deleteReservation(long id) {
        try {
            String sql = "DELETE FROM reservations WHERE reservations.reservation_id = ?;";
            try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
                stmt.setLong(1, id);
                int rowUpdated = stmt.executeUpdate();
                if (rowUpdated > 0)
                    System.out.println("Successfully deleted reservation with id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
    }

    @Override
    public Reservations findByUserIdAndRoomId(long user_id, long room_id) {
        String sql = "SELECT * FROM reservations INNER JOIN users ON reservations.user_id = users.user_id INNER JOIN rooms ON reservations.room_id = rooms.room_id WHERE users.user_id = ? AND rooms.room_id = ?;";
        Reservations reservation = null;
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, user_id);
            stmt.setLong(2, room_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservation = this.insertResultsIntoReservation(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return reservation;
    }

    @Override
    public int userReservationsStatistics(long user_id) {
        String sql = "SELECT COUNT(*) AS reservation_count FROM reservations WHERE reservations.user_id = ?";
        int reservationCount = 0;

        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, user_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservationCount = rs.getInt("reservation_count");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user reservation statistics: " + e.getMessage());
        }

        return reservationCount;
    }
}
