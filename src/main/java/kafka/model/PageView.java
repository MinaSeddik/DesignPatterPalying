package kafka.model;

public class PageView {
    int userID;
    String page;

    public PageView(int userID, String page) {
        this.userID = userID;
        this.page = page;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getPage() {
        return this.page;
    }
}
