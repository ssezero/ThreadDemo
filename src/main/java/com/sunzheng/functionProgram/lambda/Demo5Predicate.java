package com.sunzheng.functionProgram.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

/**
 * @ClassName Demo5Predicate
 * @Description
 * 使用Predicate 的test方法判断字符串信息
 * @Author Neal
 * @Date 2021/8/19 16:09
 * @Version 1.0
 **/
public class Demo5Predicate {
    //定义一个方法，用来判断字符串相关信息
    public static boolean demo(String s, Predicate<String> predicate){
        return  predicate.test(s);
    }

    public static void main(String[] args) {

        String s="Abcdefghigk";
        // 1. 可判断字符串是否长度为10个以上
        boolean b = demo(s, (String sk) -> {
        return sk.length()>10;
        });
        System.out.println("是否大于10"+b);

        // 2. 判断字符串里面是否是数字
        b=demo(s,(str)-> StringUtils.isNumeric(str));
        System.out.println("是否是纯数字:"+b);

    }

}
