package Repositories;

import Models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements  UserRepository{
    private final Connection connectionInstance;

    public UserRepositoryImpl(Connection connection) {
        this.connectionInstance = connection;
    }

    @Override
    public void addUser(String user_name, String user_pass) {
        String sql = "INSERT INTO users (user_name, user_pass) VALUES (?, ?);";
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setString(1, user_name);
            stmt.setString(2, user_pass);
            int result = stmt.executeUpdate();
            if (result>0)
                System.out.println("User "+user_name+" Successfully Created!");

        } catch (SQLException e) {
            System.out.println("User err: "+e);
        }
    }

    @Override
    public Users getUserById(long id) {
        String sql = "SELECT users.user_id, users.user_name, users.user_pass FROM users WHERE users.user_id = ?;";
        Users user = null;
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                user = this.setResultsToUser(rs);
            }
        } catch (SQLException e) {
            System.out.println("User err: "+e);
        }
        return user;
    }

    @Override
    public Users getUserByUserName(String user_name) {
        String sql = "SELECT users.user_name, users.user_name, users.user_pass FROM users WHERE users.user_name = ?;";
        Users user = null;
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setString(1, user_name);
            try (ResultSet rs = stmt.executeQuery()) {
                user = this.setResultsToUser(rs);
            }
        } catch (SQLException e) {
            System.out.println("User err: "+e);
        }
        return user;
    }

    private Users setResultsToUser(ResultSet rs) throws SQLException {
        long user_id = rs.getLong("user_id");
        String user_name = rs.getString("user_name");
        String user_pass = rs.getString("user_pass");
        return new Users(user_id, user_name, user_pass);
    }
}
