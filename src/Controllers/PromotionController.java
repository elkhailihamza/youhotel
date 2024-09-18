package Controllers;

import Core.Repository;
import Models.Promotions;
import Models.Rooms;
import Services.PromotionService;
import Services.RoomService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class PromotionController {
    private final PromotionService promotionService;
    private final RoomService roomService;

    public PromotionController(Repository repository) {
        this.promotionService = repository.getPromotionService();
        this.roomService = repository.getRoomService();
    }

    public void reducePricesDependingOnPromoDay() {
        Promotions promotion = null;
        Instant instant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Timestamp date = Timestamp.from(instant);
        HashMap<Long, Rooms> rooms = this.roomService.fetchAll();

        if (this.promotionService.checkIfPromoDay(date)) {
            System.out.println("Today is not a special day :(");
            for (Rooms r : rooms.values()) {
                r.setPrice(r.getBasePrice());
                this.roomService.updateRoomPrice(r);
            }
        } else {
            promotion = this.promotionService.fetchPromotionDate(date);
            for (Rooms r : rooms.values()) {
                double updatedPrice = r.getPrice() * (1 - (promotion.getPromotionDiscountPercentage() / 100.0));
                r.setPrice(updatedPrice);
                this.roomService.updateRoomPrice(r);
            }
        }
    }
}
