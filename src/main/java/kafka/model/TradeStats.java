package kafka.model;

public class TradeStats {
    String type;
    String ticker;
    int countTrades;
    double sumPrice;
    double minPrice;
    double avgPrice;

    public TradeStats() {
    }

    public TradeStats add(Trade trade) {
        if (trade.type != null && trade.ticker != null) {
            if (this.type == null) {
                this.type = trade.type;
            }

            if (this.ticker == null) {
                this.ticker = trade.ticker;
            }

            if (this.type.equals(trade.type) && this.ticker.equals(trade.ticker)) {
                if (this.countTrades == 0) {
                    this.minPrice = trade.price;
                }

                ++this.countTrades;
                this.sumPrice += trade.price;
                this.minPrice = this.minPrice < trade.price ? this.minPrice : trade.price;
                return this;
            } else {
                throw new IllegalArgumentException("Aggregating stats for trade type " + this.type + " and ticker " + this.ticker + " but recieved trade of type " + trade.type + " and ticker " + trade.ticker);
            }
        } else {
            throw new IllegalArgumentException("Invalid trade to aggregate: " + trade.toString());
        }
    }

    public TradeStats computeAvgPrice() {
        this.avgPrice = this.sumPrice / (double)this.countTrades;
        return this;
    }
}
