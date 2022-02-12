package pl.tomaszbuga.utils;

import java.util.List;

public class StringUtils {

    public static String parseListIntoString(List<String> listOfElements) {
        return String.join(", ", listOfElements);
    }

}
