package enums;

public class EnumMain {


    public static void main(String[] args) {

        System.out.println("Winter: " + Season.WINTER.ordinal());
        System.out.println("Summer: " + Season.SUMMER.ordinal());
        System.out.println("Spring: " + Season.SPRING.ordinal());
        System.out.println("Fall: " + Season.FALL.ordinal());

        System.out.println("----------------------------------------");

        for(Season s: Season.values()){
            System.out.println(s + ": " + s.ordinal());
        }


        System.out.println("----------------------------------------");

        System.out.println("Winter: " + Season2.WINTER.ordinal());
        System.out.println("Summer: " + Season2.SUMMER.ordinal());
        System.out.println("Spring: " + Season2.SPRING.ordinal());
        System.out.println("Fall: " + Season2.FALL.ordinal());


        System.out.println("----------------------------------------");

        System.out.println(Season.WINTER.equals(Season2.WINTER) );

    }












}
