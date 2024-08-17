package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class useTransform {
    public int getNumberFromString(String value) {
        // Sample Case: 04800abcd
        // Sample Case2: 0000000000000400abcd
        String regex = "\\d+(?=\\D*$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(value);
        
        return Integer.parseInt((match.find() ? match.group() : value));
    }
}
