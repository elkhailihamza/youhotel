package Model;

import Enums.Room.Amenities_enum;

public class Amenities {
    private Amenities_enum amenities;

    public Amenities(Amenities_enum amenities) {
        this.amenities = amenities;
    }

    public Amenities_enum getAmenities() {
        return this.amenities;
    }

    public void setAmenities(Amenities_enum amenities) {
        this.amenities = amenities;
    }
}
