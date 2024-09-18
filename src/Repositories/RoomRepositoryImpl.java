package Repositories;

import Models.Rooms;

import java.sql.*;
import java.util.HashMap;

public class RoomRepositoryImpl implements RoomRepository {
    private final Connection connectionInstance;

    public RoomRepositoryImpl(Connection connection) {
        this.connectionInstance = connection;
    }

    private Rooms insertResultsIntoRoom(ResultSet rs) throws SQLException {
        long room_id = rs.getLong("room_id");
        String room_number = rs.getString("room_number");
        double room_price = rs.getDouble("room_price");
        double room_base_price = rs.getDouble("room_base_price");
        long status_id = rs.getLong("status_id");
        String status_name = rs.getString("status_name");
        long category_id = rs.getLong("category_id");
        String category_name = rs.getString("category_name");

        return new Rooms(room_id, room_number, room_price, room_base_price, status_id, status_name, category_id, category_name);
    }

    @Override
    public Rooms findById(long id) {
        Rooms room = null;

        String sql = "SELECT rooms.room_id, rooms.room_number, rooms.room_price, rooms.room_base_price, rooms.category_id, rooms.status_id, " +
                "category.category_name, " +
                "status.status_name " +
                "FROM rooms " +
                "INNER JOIN public.category ON rooms.category_id = category.category_id " +
                "INNER JOIN public.status ON rooms.status_id = status.status_id " +
                "WHERE rooms.room_id = ?;";

        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    room = this.insertResultsIntoRoom(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Find By Id err: " + e);
        }
        return room;
    }

    @Override
    public HashMap<Long, Rooms> fetchAll() {
        HashMap<Long, Rooms> rooms = new HashMap<>();

        String sql = "SELECT rooms.room_id, rooms.room_number, rooms.room_price, rooms.room_base_price, rooms.category_id, rooms.status_id, " +
                "category.category_name, " +
                "status.status_name " +
                "FROM rooms " +
                "INNER JOIN public.category ON rooms.category_id = category.category_id " +
                "INNER JOIN public.status ON rooms.status_id = status.status_id;";

        try (Statement stmt = connectionInstance.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Rooms room = this.insertResultsIntoRoom(rs);
                rooms.put(room.getId(), room);
            }
        } catch (SQLException e) {
            System.out.println("Fetch all err: " + e);
        }
        return rooms;
    }

    @Override
    public void updateRoomPrice(Rooms room) {
        String sql = "UPDATE rooms SET room_price = ? WHERE room_id = ?;";
        try(PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setDouble(1, room.getPrice());
            stmt.setLong(2, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update room price err: "+e);
        }
    }
}
