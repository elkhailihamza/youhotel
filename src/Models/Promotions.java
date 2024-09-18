package Models;

import java.sql.Timestamp;

public class Promotions {
    private final long promotion_id;
    private String promotion_name;
    private int promotion_discount_percentage;
    private Timestamp promotion_start_date;
    private Timestamp promotion_end_date;

    public Promotions(long promotionId, String promotionName, int promotionDiscountPercentage, Timestamp promotionStartDate, Timestamp promotionEndDate) {
        this.promotion_id = promotionId;
        this.promotion_name = promotionName;
        this.promotion_discount_percentage = promotionDiscountPercentage;
        this.promotion_start_date = promotionStartDate;
        this.promotion_end_date = promotionEndDate;
    }

    public long getPromotionId() {
        return this.promotion_id;
    }

    public void setPromotionName(String promotionName) {
        this.promotion_name = promotionName;
    }

    public String getPromotionName() {
        return this.promotion_name;
    }

    public void setPromotionDiscountPercentage(int promotionDiscountPercentage) {
        this.promotion_discount_percentage = promotionDiscountPercentage;
    }

    public int getPromotionDiscountPercentage() {
        return this.promotion_discount_percentage;
    }

    public void setPromotionStartDate(Timestamp promotionStartDate) {
        this.promotion_start_date = promotionStartDate;
    }

    public Timestamp getPromotionStartDate() {
        return this.promotion_start_date;
    }

    public void setPromotionEndDate(Timestamp promotionEndDate) {
        this.promotion_end_date = promotionEndDate;
    }

    public Timestamp getPromotionEndDate() {
        return this.promotion_end_date;
    }
}
