package Model;

import java.util.List;

public class Rooms {

    private int id;
    private String roomNumber;
    private double price;
    private Category category;
    private List<Amenity> amenities;
    private Status status;

    public Rooms(int id, String roomNumber, double price, Category category, List<Amenity> amenities, Status status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.category = category;
        this.amenities = amenities;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Amenity> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

