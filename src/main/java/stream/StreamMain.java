package stream;

import java.util.ArrayList;
import java.util.List;

public class StreamMain {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        long count = list.stream().distinct().count();
    }
}
