package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {


    public static void main(String[] args) {
        String urlStr = "http://www.microsoft.com/some/other/url/path   dummy " +
                "dummy String " +
                "http://www.google.com/some/other/url/yanas" +
                " dummy";


//        Pattern simpleUrlPattern = Pattern.compile("[^:]+://(?:[.a-z]+/?)+");
        Pattern simpleUrlPattern = Pattern.compile("[^: \n\r]+://([.a-z]+/?)+");

        Matcher matcher = simpleUrlPattern.matcher(urlStr);

//        System.out.println(urlStr + " : " + matcher.find());
        System.out.println(matcher.groupCount());

        String resultStringzzz = matcher.replaceAll ("<a href=\"$0\"> $1 link</a>");
        System.out.println("zzz " + resultStringzzz);



    }


}
