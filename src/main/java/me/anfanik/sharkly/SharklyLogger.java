package me.anfanik.sharkly;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SharklyLogger {

    private static final Logger logger = Logger.getLogger("Sharkly");

    public static void emptyLine() {
        info("");
    }

    public static void section(String section) {
        info("-=== Entering %s section ===-", section);
    }

    public static void info(String text) {
        logger.info(text);
    }

    public static void info(String text, Object... arguments) {
        info(format(text, arguments));
    }

    public static void warning(String text) {
        logger.warning(text);
    }

    public static void warning(String text, Object... arguments) {
        warning(format(text, arguments));
    }

    public static void warning(String text, Throwable throwable, Object... arguments) {
        warning(text, arguments);
        throwable.printStackTrace();
    }

    public static void error(String text) {
        logger.log(Level.SEVERE, text);
    }

    public static void error(String text, Object... arguments) {
        error(format(text, arguments));
    }

    public static void error(String text, Throwable throwable, Object... arguments) {
        error(text, arguments);
        throwable.printStackTrace();
    }

    private static String format(String text, Object... arguments) {
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

        return builder.toString();
    }

}
