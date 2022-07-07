package qsort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QSortMain {

    private static int count = 0;
    public static void main(String[] args) {

        Random random = new Random();

        int endExclusive = 1000000;
        IntStream.range(1, endExclusive).forEach(i -> executeSortTest(random.nextInt(1000)));

    }

    private static void executeSortTest(int numberOfElements) {
        QuickSort<Integer> qSort = new QuickSort<>();

        if( numberOfElements <= 0 ){
            return;
        }

        count++;

        // generate 100 random number between 0 to 100
        List<Integer> list = IntStream.generate(() -> new Random().nextInt(numberOfElements)).limit(100).boxed().collect(Collectors.toList());

        System.out.println("Before :" + list);

        List<Integer> sorted = new ArrayList<>(list);
        Collections.sort(sorted);

        qSort.sort(list);

        System.out.println("My QSort: " + list);
        System.out.println("Java QSort: " + sorted);

        boolean ok = checkEquality(list, sorted);

        if(!ok){
            System.out.println("NOT OKay! *****************************************");
            System.exit(1);
        }

        System.out.println("Test #" + count + " ,Is Equal: " + ok);
    }

    private static boolean checkEquality(List<Integer> list, List<Integer> sorted) {

        if (list.size() != sorted.size()) return false;

        Iterator<Integer> itr1;
        Iterator<Integer> itr2;

        for(itr1 = list.iterator(), itr2 = sorted.iterator(); itr1.hasNext(); ){
            if (itr1.next().compareTo(itr2.next()) != 0) return false;
        }


        return true;
    }
}
