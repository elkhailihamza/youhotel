package Models;

public class Users {
    private final long user_id;
    private String user_name;
    private String user_pass;

    public Users(long user_id, String user_name, String user_pass) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
    }

    public long getUserId() {
        return this.user_id;
    }

    public String getUserName() {
        return this.user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserPass() {
        return this.user_pass;
    }

    public void setUserPass(String user_pass) {
        this.user_pass = user_pass;
    }
}
