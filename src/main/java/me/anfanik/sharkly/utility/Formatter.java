package me.anfanik.sharkly.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Formatter {

    private static final List<Function<String, String>> formatters = new ArrayList<>();

    public static void addFormatter(Function<String, String> formatter) {
        formatters.add(formatter);
    }

    public static String format(String source) {
        String result = source;
        for (Function<String, String> formatter : formatters) {
            result = formatter.apply(result);
        }
        return result;
    }

    public static String replaceArguments(String source, Object... arguments) {
        String[] parts = source.split("\\{}");

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
