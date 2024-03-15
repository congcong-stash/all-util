package org.object.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yangCHong
 */
public class CollectionUtil {
    public static <T> boolean isBank(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotBank(Collection<T> collection) {
        return !isBank(collection);
    }
    public static <K, V> boolean isBank(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> boolean isNotBank(Map<K, V> map) {
        return !isBank(map);
    }

    public static <T> void reverse(List<T> list) {
        if (isBank(list)) {
            return;
        }
        int length = list.size();
        for (int i = 0; i < length / 2; i ++) {
            T temp = list.get(i);
            list.set(i, list.get(length - 1 - i));
            list.set(length - 1 - i, temp);
        }
    }
}
