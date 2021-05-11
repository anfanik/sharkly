package me.anfanik.sharkly.utility;

import java.util.Arrays;
import java.util.Iterator;

public class Formatter {

    public static String format(String text, Object... arguments) {
        String[] parts = text.split("\\{}");

        StringBuilder builder = new StringBuilder();
        Iterator<Object> argumentsIterator = Arrays.asList(arguments).iterator();

        for (String part : parts) {
            builder.append(part);
            if (argumentsIterator.hasNext()) {
                builder.append(argumentsIterator.next());
            } else {
                builder.append("{}");
            }
        }

        return builder.substring(0, builder.length() - 2);
    }

}
