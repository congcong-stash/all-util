package org.demo;

import org.object.array.ArrayUtil;
import org.object.bean.BeanUtil;
import org.object.collection.CollectionUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yangCHong
 */
public class Demo {
    public static void main(String[] args) {
        Map<String, Object> map = BeanUtil.map(new User());
        System.out.println(map);
        System.out.println(BeanUtil.<Map<String, Object>>copy(map));

        Object[] arrays = new Object[]{1, "4", 5, "51", 5, 2, "4241237"};
        System.out.println(Arrays.toString(arrays));
        ArrayUtil.reverse(arrays);
        System.out.println(Arrays.toString(arrays));
        List<Object> list = Arrays.asList(arrays);
        CollectionUtil.reverse(list);
        System.out.println(list);
    }
}
