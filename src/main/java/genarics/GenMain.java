package genarics;

import proxy.PersonService;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenMain {


    public static void main(String[] args) {


        Event<Double> x = new Event<>("test yanas", 56.236);
        Event<PersonService> y = new Event<>("test PersonService", new PersonService());

        System.out.println(x.getName());
        System.out.println(x.getData());

        System.out.println("----------------------------------------");

        System.out.println(y.getName());
        System.out.println(y.getData());

        System.out.println("----------------------------------------");

        double d = getData(12.36);
        double r = getData(12);
        PersonService t = getData(new PersonService());


        Object o = new PersonService();
        int yg = (int) o;

        System.out.println("----------------------------------------");

//        getStaticMethods(GenMain.class);


    }

    public static <T extends Comparable<T>> T getData(T data) {
        System.out.println("$$$$$$" + data.getClass().getName());
        return data;
    }

}


