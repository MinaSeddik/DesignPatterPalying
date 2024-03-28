package kafka.model;

public class Trade {
    String type;
    String ticker;
    double price;
    int size;

    public Trade(String type, String ticker, double price, int size) {
        this.type = type;
        this.ticker = ticker;
        this.price = price;
        this.size = size;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return "Trade{type='" + this.type + "', ticker='" + this.ticker + "', price=" + this.price + ", size=" + this.size + "}";
    }
}
