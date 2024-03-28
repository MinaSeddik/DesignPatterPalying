package kafka.model;

public class UserProfile {
    int userID;
    String userName;
    String zipcode;
    String[] interests;

    public UserProfile(int userID, String userName, String zipcode, String[] interests) {
        this.userID = userID;
        this.userName = userName;
        this.zipcode = zipcode;
        this.interests = interests;
    }

    public int getUserID() {
        return this.userID;
    }

    public UserProfile update(String zipcode, String[] interests) {
        this.zipcode = zipcode;
        this.interests = interests;
        return this;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String[] getInterests() {
        return this.interests;
    }
}
