package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex3 {

    public static void main(String[] args) {
        String text = "Here are 12 and 54 and 1 and 65 this is good";


        Pattern regex = Pattern.compile("(\\d{1,2})");
        Matcher matcher = regex.matcher(text);
        StringBuffer resultString = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(resultString, String.valueOf(3 * Integer.parseInt(matcher.group(1))));
        }
        matcher.appendTail(resultString);


        System.out.println("Input text: " + text);
        System.out.println("Result: " + resultString);

    }
}
