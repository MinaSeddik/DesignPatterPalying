package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex2 {


    public static void main(String[] args) {
        String text = "Seddik, Mina and Sabry, Jasmin also we have Imam, Adel";


        Pattern simpleUrlPattern = Pattern.compile("(?:[a-zA-Z-']+),\\s(?:[a-zA-Z-']+)");

        Matcher matcher = simpleUrlPattern.matcher(text);

        String result = matcher.replaceAll ("$2 $1");


        System.out.println("Input text: " + text);
        System.out.println("Result: " + result);

    }


}
