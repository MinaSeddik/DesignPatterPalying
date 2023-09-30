package slf4j;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

        String data = "val";

        log.info("here is a sample log: " + data);
        log.info("here is a sample log: {}", data);
    }
}
