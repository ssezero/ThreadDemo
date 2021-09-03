package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Stream_Limit
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/3 16:48
 * @Version 1.0
 */
public class Stream_Limit {
    public static void main(String[] args) {
        String [] arr={"懒洋洋","喜洋洋","美羊羊","大灰狼"};
        //1 初始Stream流
        Stream<String> stream = Stream.of(arr);
        //2 使用Limit截取
        Stream<String> strem2 = stream.limit(13);
        //3 输出
        strem2.forEach(value-> System.out.println(value));

    }
}
