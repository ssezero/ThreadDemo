package com.sunzheng.functionProgram.commInterface;

import java.util.function.Consumer;

/**
 * @ClassName ConsumerDemo
 * @Description
 * Consumer 是一个消费型的接口，不带返回值
 * @Author Neal
 * @Date 2021/8/18 16:01
 * @Version 1.0
 **/

public class ConsumerDemo {
    static void demo(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    public static void main(String[] args) {
        //实现翻转输出
        demo("赵丽颖",(String nameInput)->{
            System.out.println(new StringBuilder(nameInput).reverse().toString());
        });
    }
}
