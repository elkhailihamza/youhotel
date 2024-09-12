package Models;

public class Rooms {

    private final Long id;
    private String roomNumber;
    private double price;
    private long status_id;
    private String status_name;
    private long category_id;
    private String category_name;

    public Rooms(Long id, String roomNumber, double price, long status_id, String status_name, long category_id, String category_name) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.status_id = status_id;
        this.status_name = status_name;
        this.category_id = category_id;
        this.category_name = category_name;
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

    public long getCategoryId() {
        return this.category_id;
    }

    public void setCategoryId(long category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return this.category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public long getStatusId() {
        return this.status_id;
    }

    public void setStatusId(long status_id) {
        this.status_id = status_id;
    }

    public String getStatusName() {
        return this.status_name;
    }

    public void setStatusName(String status_name) {
        this.status_name = status_name;
    }
}

