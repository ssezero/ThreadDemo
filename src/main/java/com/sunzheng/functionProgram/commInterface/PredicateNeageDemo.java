package com.sunzheng.functionProgram.commInterface;

import java.util.function.Predicate;

/**
 * @ClassName PredicateNeageDemo
 * @Description
 * 条件结果取反操作
 * @Author Neal
 * @Date 2021/8/26 15:11
 * @Version 1.0
 **/
public class PredicateNeageDemo {
    public static void main(String[] args) {
        String s="abc";
        boolean back = demo(s, (String sk) -> {
            return sk.length()>5; //应该返回true，但是最后返回false
        });
        System.out.println(back);
    }
    //取反操作
    public static boolean demo(String s, Predicate<String> pred){
        return  pred.negate().test(s);
    }
}
