package Models;

import java.util.List;

public class Rooms {

    private final Long id;
    private String roomNumber;
    private double price;
    private long status_id;
    private long category_id;
    private List<Amenity> amenities;

    public Rooms(Long id, String roomNumber, double price, long status, long category, List<Amenity> amenities) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.status_id = status;
        this.category_id = category;
        this.amenities = amenities;
    }

    public Long getId() {
        return this.id;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCategory() {
        return this.category_id;
    }

    public void setCategory(long category_id) {
        this.category_id = category_id;
    }

    public long getStatus() {
        return this.status_id;
    }

    public void setStatus(long status_id) {
        this.status_id = status_id;
    }

    public List<Amenity> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}

