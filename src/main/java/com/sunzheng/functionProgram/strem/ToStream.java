package com.sunzheng.functionProgram.strem;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName ToStream
 * @Description
 * 把集合，MAP ，数组转换成Stream流
 * @Author Neal
 * @Date 2021/9/2 14:56
 * @Version 1.0
 */
public class ToStream {
    public static void main(String[] args) {
        getStream();

    }

    private static void getStream() {
        //1.把集合转换成Stream流
        List<String> list=new ArrayList<>();
        Stream<String> streamList = list.stream();

        Set<String> set=new HashSet<>();
        Stream<String> streamSet = set.stream();

        Map<String,String> map=new HashMap<>();
        //map 转stream流，也是必须先转成单列的集合
        //a. 得到所有的key的集合
        Set<String> keySet = map.keySet();
        Stream<String> streamKey = keySet.stream();
        //b. 得到所有的value的集合
        Collection<String> values = map.values();
        Stream<String> streamValue = values.stream();
        //获取entries集合
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> streamEntry = entries.stream();

        //2. 把数组转换成集合，使用Stream.Of(T...value)
        Stream<Integer> streamNum = Stream.of(1, 2, 3);
        Stream<String> StreamString = Stream.of("A", "B", "C");
        Integer [] arr={1,2,3,4};
        Stream<Integer> arr1 = Stream.of(arr);
    }
}
