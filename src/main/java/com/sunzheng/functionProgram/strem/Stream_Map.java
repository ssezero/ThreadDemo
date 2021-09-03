package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Stream_Map
 * @Description
 * Stream map 可以把流中的数据组合成新的流
 * @Author Neal
 * @Date 2021/9/3 16:31
 * @Version 1.0
 */
public class Stream_Map {
    public static void main(String[] args) {
        //1 初始化stream流
        Stream<String> stringStream = Stream.of("1", "2", "3", "4");
        //2 使用map把String的流转换成Integer的流
        Stream<Integer> integerStream = stringStream.map((value) -> {
            return Integer.parseInt(value) + 20;
        });
        //3 输出流
        integerStream.forEach(value-> System.out.println(value));
    }
}
