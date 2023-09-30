package hibernate.order_hier;

public enum OrderType {
    FBI(Values.FBI),
    CA(Values.CA),
    FDLE(Values.FDLE),
    P2C(Values.P2C);

    private String value;

    OrderType(String value) {
        // force equality between name of enum instance, and value of constant
        if (!this.name().equals(value))
            throw new IllegalArgumentException("Incorrect use of OrderType");

        this.value = value;
    }

    public static OrderType fromString(String value) {
        for (OrderType a : OrderType.values()) {
//            if (a.getActionText().equalsIgnoreCase(actionText)) {
//                return a;
//            }
        }
        return null;
    }

    public static class Values {
        public static final String FBI = "FBI";
        public static final String CA = "CA";
        public static final String P2C = "P2C";
        public static final String FDLE = "FDLE";
    }
}
