package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Stream_ForEach
 * @Description
 * forEach 终结方法
 * @Author Neal
 * @Date 2021/9/2 15:31
 * @Version 1.0
 */
public class Stream_ForEach {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("A","B","C","D","D");
/*        stringStream.forEach((String name)->{
            System.out.println(name);
        });*/
        stringStream.forEach(name-> System.out.println(name));
    }
}
