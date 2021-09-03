package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Stream_Concat
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/3 16:59
 * @Version 1.0
 */
public class Stream_Concat {
    public static void main(String[] args) {
        String [] arr={"张三","李四","王五"};
        Stream<String> stream1 = Stream.of(arr);
        String [] arr2={"A","B","C"};
        Stream<String> stream2 = Stream.of(arr2);
        //合并2个Stream流
        Stream<String> concat = Stream.concat(stream1, stream2);
        concat.forEach(value-> System.out.println(value));
    }
}
