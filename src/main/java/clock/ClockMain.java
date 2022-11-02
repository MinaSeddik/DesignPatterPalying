package clock;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class ClockMain {

    public static void main(String[] args) {

        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        System.out.println(ZoneId.systemDefault() + " :: " + clock);
        System.out.println(ZoneId.systemDefault() + " :: " + instant);
        System.out.println(ZoneId.systemDefault() + " :: " + instant.atZone(ZoneId.systemDefault()));

        Clock clock2 = Clock.systemUTC();
        System.out.println("UTC time :: " + clock2.instant());

        Clock clock3 = Clock.system(ZoneId.of("Asia/Calcutta"));
        System.out.println(clock3.instant());
        System.out.println(clock3.instant().atZone(ZoneId.of("Asia/Calcutta")));

        Clock clock4 = Clock.systemDefaultZone();
        System.out.println(clock4.millis());

        System.out.println("------------------------");

        Clock baseClock = Clock.systemDefaultZone();

        // result clock will be later than baseClock
        Clock clock5 = Clock.offset(baseClock, Duration.ofHours(72));
        System.out.println(clock5.instant());

        // result clock will be same as baseClock
        clock5 = Clock.offset(baseClock, Duration.ZERO);
        System.out.println(clock5.instant());

        // result clock will be earlier than baseClock
        clock5 = Clock.offset(baseClock, Duration.ofHours(-72));
        System.out.println(clock5.instant());

        System.out.println("------------------------");

        Clock clockDefaultZone = Clock.systemDefaultZone();
        Clock clocktick = Clock.tick(clockDefaultZone, Duration.ofSeconds(30));

        System.out.println("Clock Default Zone: " + clockDefaultZone.instant());
        System.out.println("Clock tick: " + clocktick.instant());

        System.out.println("------------------------");

        Clock fixedClock = Clock.fixed(Instant.parse("2018-04-29T10:15:30.00Z"),
                ZoneId.of("Asia/Calcutta"));
        System.out.println(fixedClock);


    }
}
