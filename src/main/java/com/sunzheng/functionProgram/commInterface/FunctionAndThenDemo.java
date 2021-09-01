package com.sunzheng.functionProgram.commInterface;

import java.util.function.Function;

/**
 * @ClassName FunctionAndThenDemo
 * @Description
 * 支持多个函数组合调用,
 * 传入字符串先转换成Integer 然后增加10然后再传入
 * @Author Neal
 * @Date 2021/9/1 15:20
 * @Version 1.0
 **/
public class FunctionAndThenDemo {
    public static void main(String[] args) {
        String str="123";
        demo1(str,(String str1)->{
            Integer k=Integer.parseInt(str1);
            return k+10;
        },(Integer num)->{
            return num+"";
        });
    }
    static void demo1(String str, Function<String,Integer> fun1,Function<Integer,String >fun2){
        String apply = fun1.andThen(fun2).apply(str);
        System.out.println("返回的结果是"+apply);

    }
}
