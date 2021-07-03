package me.anfanik.sharkly.utility;

import java.lang.reflect.Array;

public class ArrayUtility {

    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(Class<?> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    public static <T> T[] skipFirst(T[] array, int skip) {
        T[] result = createArray(array.getClass().getComponentType(), array.length - skip);
        System.arraycopy(array, skip, result, 0, array.length - skip);
        return result;
    }

}
