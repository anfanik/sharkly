package me.anfanik.sharkly.utility;

import java.util.Arrays;

public class ArrayUtility {

    @SafeVarargs
    public static <T> T[] createArray(int length, T... array) {
        if (array.length != 0) {
            throw new IllegalArgumentException("\"array\" vararg is used for array constructing and should be empty");
        }
        return Arrays.copyOf(array, length);
    }

    public static <T> T[] skipFirst(T[] array, int skip) {
        T[] result = createArray(array.length - skip);
        System.arraycopy(array, skip, result, 0, array.length - skip);
        return result;
    }

}
