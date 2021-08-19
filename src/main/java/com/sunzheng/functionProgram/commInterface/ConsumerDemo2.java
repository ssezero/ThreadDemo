package com.sunzheng.functionProgram.commInterface;

import java.util.function.Consumer;

/**
 * @ClassName ConsumerDemo2
 * @Description 使用andThen联合Consumer接口
 * @Author Neal
 * @Date 2021/8/18 16:22
 * @Version 1.0
 **/
public class ConsumerDemo2 {
    static void demo(String name, Consumer<String> con1, Consumer<String> con2) {
        //使用andThen消费2次
        con1.andThen(con2).accept(name);
    }

    public static void main(String[] args) {
        //分别实现2个Consumer接口
        demo("HellOWorld", (s) -> {
            System.out.println(s.toUpperCase());
        }, (k) -> {
            System.out.println(k.toLowerCase());
        });
    }
}
