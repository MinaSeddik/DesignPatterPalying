package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpliteratorMain {

    public static void main(String[] args) {

//        Stream.generate(() -> new Article("Java"))
        List<Article> list = Stream.generate(new Random()::nextInt)
                .limit(35000)
                .map(String::valueOf)
                .map(Article::new)
                .collect(Collectors.toList());

        tryAdvance(list);

        trySplit(list);
    }

    public static String tryAdvance(List<Article> list) {
        int current = 0;

        Iterator<Article> iterator = list.iterator();
        Spliterator<Article> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);

        while (spliterator.tryAdvance(a -> a.setName(a.getName().concat("- published by Monmon")))) {
            current++;
        }

        String str = Thread.currentThread().getName() + ":" + current;
        System.out.println(str);
        return str;
    }


    public static String tryAdvance(Spliterator<Article> spliterator) {
        int current = 0;

        while (spliterator.tryAdvance(a -> a.setName(a.getName().concat("- published by Monmon")))) {
            current++;
        }

        String str = Thread.currentThread().getName() + ":" + current;
        System.out.println(str);
        return str;
    }

    public static void trySplit(List<Article> list) {
        int current = 0;

//        Iterator<Article> iterator = list.iterator();
//        Spliterator<Article> spliterator1 = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
        Spliterator<Article> spliterator1 = Spliterators.spliterator(list, Spliterator.SIZED);
        Spliterator<Article> spliterator2 = spliterator1.trySplit();

        System.out.println("spliterator1 Size: " + spliterator1.estimateSize());
        System.out.println("spliterator1Characteristics: " + spliterator1.characteristics());


        System.out.println("spliterator2 Size: " + spliterator2.estimateSize());
        System.out.println("spliterator2 Characteristics: " + spliterator2.characteristics());

        System.out.println("call tryAdvance on spliterator1");
        tryAdvance(spliterator1);

        System.out.println("call tryAdvance on spliterator2");
        tryAdvance(spliterator2);
    }

}
