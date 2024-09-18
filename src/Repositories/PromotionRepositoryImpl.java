package Repositories;

import Models.Promotions;

import java.sql.*;

public class PromotionRepositoryImpl implements PromotionRepository {
    private final Connection connectionInstance;

    public PromotionRepositoryImpl(Connection connectionInstance) {
        this.connectionInstance = connectionInstance;
    }

    @Override
    public Promotions fetchPromotionDay(Timestamp date) {
        Promotions promotion = null;
        String sql = "SELECT * FROM promotions WHERE promotions.promotion_start_date >= ? AND promotions.promotion_end_date <= ?";
        try (PreparedStatement stmt = connectionInstance.prepareStatement(sql)) {
            stmt.setTimestamp(1, date);
            stmt.setTimestamp(2, date);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long promotion_id = rs.getLong("promotion_id");
                    String promotion_name = rs.getString("promotion_name");
                    int promotion_discount_percentage = rs.getInt("promotion_discount_percentage");
                    Timestamp promotion_start_date = rs.getTimestamp("promotion_start_date");
                    Timestamp promotion_end_date = rs.getTimestamp("promotion_end_date");
                    promotion = new Promotions(promotion_id, promotion_name, promotion_discount_percentage, promotion_start_date, promotion_end_date);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection err: " + e);
        }
        return promotion;
    }
}
