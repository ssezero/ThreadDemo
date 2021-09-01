package com.sunzheng.functionProgram.commInterface;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @ClassName FunctionDemo
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/1 14:44
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class FunctionDemo {
    public static void main(String[] args) {
        String str="123";
        Demo(str,(strs)->{
            return  Integer.parseInt(strs);
        });
    }
    public static void Demo(String s, Function<String,Integer> fun){
        Integer in = fun.apply(s);
        System.out.println(in);
    }
}
