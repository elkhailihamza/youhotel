package Models;

import java.util.Date;

public class Reservations {

    private final long id;
    private String note;
    private Date start_date;
    private Date end_date;
    private long room_id;
    private long user_id;


    public Reservations(long id, String note, Date start_date, Date end_date, long room_id, long user_id) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.note = note;
        this.room_id = room_id;
        this.user_id = user_id;
    }

    public long getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartDate() {
        return this.start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    public long getRoomId() {
        return this.room_id;
    }

    public void setRoomId(long room_id) {
        this.room_id = room_id;
    }

    public long getUserId() {
        return this.user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }
}
