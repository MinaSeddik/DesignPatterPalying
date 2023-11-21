package javamaps;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;


/*
https://stackoverflow.com/questions/683518/java-class-that-implements-map-and-keeps-insertion-order

I suggest a LinkedHashMap or a TreeMap. A LinkedHashMap keeps the keys in the order they were inserted,
while a TreeMap is kept sorted via a Comparator or the natural Comparable ordering of the keys.

 */
public class MapTest {

    public static void main(String[] args) {

        NavigableMap<String, Integer> nm = new TreeMap<String, Integer>();
//        NavigableMap<String, Integer> nm = new TreeMap<String, Integer>();
//        Map<String, Integer> nm = new TreeMap<String, Integer>();

        // Add elements using put() method
        nm.put("C", 888);
        nm.put("Y", 999);
        nm.put("A", 444);
        nm.put("T", 555);
        nm.put("B", 666);
        nm.put("A", 555);

        // Print the contents on the console
        System.out.println("Mappings of NavigableMap : " + nm);

        System.out.printf("First Entry  : %s%n", nm.firstEntry());
        System.out.printf("Last Key : %s%n", nm.lastKey());

        SortedMap<String, Integer> sub = nm.headMap("C");

        // Print the contents on the console
        System.out.println("Mappings of NavigableMap : " + sub);
    }
}
