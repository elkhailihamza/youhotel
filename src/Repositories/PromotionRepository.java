package Repositories;

import Models.Promotions;

import java.sql.Timestamp;

public interface PromotionRepository {
    Promotions fetchPromotionDay(Timestamp date);
}
