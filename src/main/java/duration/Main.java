package duration;

import java.time.Duration;

public class Main {

    public static void main(String[] args) {

        Duration d = Duration.parse("100ms");

        System.out.println(d);
    }
}
