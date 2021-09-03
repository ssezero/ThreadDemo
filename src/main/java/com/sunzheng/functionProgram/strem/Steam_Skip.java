package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Steam_Skip
 * @Description
 * 使用skip跳过前几个值
 * @Author Neal
 * @Date 2021/9/3 16:54
 * @Version 1.0
 */
public class Steam_Skip {
    public static void main(String[] args) {
        String [] arr={"懒洋洋","喜洋洋","美羊羊","大灰狼","大力羊"};
        //1 初始Stream流
        Stream<String> stream = Stream.of(arr);
        //2 使用skip跳过前3个
        Stream<String> strem2 = stream.skip(3);
        //3 输出
        strem2.forEach(value-> System.out.println(value));
    }
}
