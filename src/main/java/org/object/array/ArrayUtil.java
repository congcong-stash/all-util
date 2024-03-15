package org.object.array;

/**
 * @author yangCHong
 */
public class ArrayUtil {
    public static boolean isBlank(Object[] arrays) {
        return arrays == null || arrays.length == 0;
    }

    public static boolean isNotBank(Object[] arrays) {
        return !isBlank(arrays);
    }

    public static void reverse(Object[] arrays) {
        if (isBlank(arrays)) {
            return;
        }
        int length = arrays.length;
        for (int i = 0; i < length / 2; i ++) {
            Object temp = arrays[i];
            arrays[i] = arrays[length - 1 - i];
            arrays[length - 1 - i] = temp;
        }
    }
}
