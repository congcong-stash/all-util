package org.object.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * @author yangchong
 * @version 1.0
 * @date 2024/3/13 23:38
 */
public class BeanUtil {
    private static final Gson GSON = new Gson();
    public static <K, V> Map<K, V> map(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        String dataJson = GSON.toJson(object);
        Map<K, V> map = GSON.fromJson(dataJson, new TypeToken<Map<K, V>>() {}.getType());
        Set<K> keys = map.keySet();
        for (K key : keys) {
            V value = map.get(key);
            map.put(key, value);
        }
        return map;
    }

    public static <K> K copy(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        String dataJson = GSON.toJson(object);
        return GSON.fromJson(dataJson, new TypeToken<K>() {}.getType());
    }
}
