package CopyOnWriteArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListMain {


    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList();
//        List<String> list = new ArrayList<>();

        list.add("111");
        list.add("222");
        list.add("333");

        Iterator<String> itr1 = list.iterator();

        list.add("444");
        list.add("555");


        Iterator<String> itr2 = list.iterator();

        list.add("****");
        list.remove(0);
        list.add("----");

        Iterator<String> itr3 = list.iterator();

        iterateOn(itr1);
        System.out.println("---------------------------");
        iterateOn(itr2);
        System.out.println("---------------------------");
        iterateOn(itr3);



    }

    private static void iterateOn(Iterator<String> itr) {

        while (itr.hasNext())
            System.out.println(itr.next());

    }
}
