package Services;

import Models.Promotions;
import Repositories.PromotionRepository;

import java.sql.Timestamp;
import java.util.Objects;

public class PromotionService {
    private final PromotionRepository PromotionRepository;
    public PromotionService(PromotionRepository PromotionRepository) {
        this.PromotionRepository = PromotionRepository;
    }

    public Promotions fetchPromotionDate(Timestamp date) {
        return this.PromotionRepository.fetchPromotionDay(date);
    }

    public boolean checkIfPromoDay(Timestamp date) {
        Promotions promotion = this.fetchPromotionDate(date);
        return Objects.equals(promotion, null);
    }
}
