package regex;

import org.apache.commons.math3.util.Pair;

import java.util.List;

public class RegexReplace1 {


    public static void main(String[] args) {
        String firmName = "GEORGE C. MCCLINTOCK, LLC";

        List<String> list = List.of(
                "GEORGE C. MCCLINTOCK, LLC",
                "GEORGE C. MCCLINTOCK, 123 LLC",
                "GEORGE C. MCCLINTOCK-LLC",
                "GEORGE C. MCCLINTOCK & Moha, LLC",
                "GEO76RGE C. MCCLINTOCK & Moha, LLC",
                "GEO76RGE C. MCCLINTOCK#@ & Moha, LLC",
                "GEO76RGE C. MCCLINTOCK#@ & Moha, LLC@yahoo.com",
                "GEO76RGE C. MCCLINTOCK#@ & Moha, LLC*",
                "GEO76RGE + MCCLINTOCK& Moha, LLC*"
        );

        /*
            the valid firm name should allow only:
            1. Alpha chars
            2. spaces
            3. Ampersand
            4. comma

            anything else should be removed

        */


        String validFirmName = firmName.replaceAll("[^a-zA-Z &,]", "");

        System.out.println("Original:       " + firmName);
        System.out.println("manipulated:    " + validFirmName);


        list.stream()
                .map(s -> new Pair<String, String>(s, s.replaceAll("[^a-zA-Z &,]", "")))
                .forEach(p -> System.out.println(String.format("%s -> %s", p.getFirst(), p.getSecond())));
    }
}
