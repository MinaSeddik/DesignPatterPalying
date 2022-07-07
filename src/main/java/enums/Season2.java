package enums;

public enum Season2 {

    WINTER(1),
    SUMMER(3),
    SPRING(0),
    FALL(2);

    private final int index;

    Season2(int index) {
        this.index = index;
    }
}
