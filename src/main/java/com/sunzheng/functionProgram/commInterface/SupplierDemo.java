package com.sunzheng.functionProgram.commInterface;

import java.util.function.Supplier;

/**
 * @ClassName SupplierDemo
 * @Description
 * Interface Supplier<T>
 *     指定泛型是什么类型，那么接口中的get方法就会返回什么类型数据
 * @Author Neal
 * @Date 2021/8/17 15:02
 * @Version 1.0
 **/
public class SupplierDemo {
    //定义一个方法，方法的参数传递Supplier<T>接口，泛型是String，返回就是String
    static String getString(Supplier<String> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        //调用getString方法，方法的参数supplier是一个函数接口，可以传递lambda表达式
        String s=getString(()->{
           //生成一个字符串
           return "胡歌";
        });
        System.out.println(s);
        //再简化，省去return
        String b = getString(() -> "刘亦菲");
        System.out.println(b);
    }
}
