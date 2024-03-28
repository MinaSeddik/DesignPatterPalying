
package kafka.model;

public class Client {
    private int customerID;
    private String customerName;

    public Client(int ID, String name) {
        this.customerID = ID;
        this.customerName = name;
    }

    public int getID() {
        return this.customerID;
    }

    public String getName() {
        return this.customerName;
    }
}
