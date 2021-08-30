package com.sunzheng.functionProgram.lambda;

import java.util.function.Predicate;

/**
 * @ClassName Demo6Predicate
 * @Description 测试Predicate 的默认and方法 同时判断多个条件
 * @Author Neal
 * @Date 2021/8/19 17:05
 * @Version 1.0
 **/
public class Demo6Predicate {
    public static void main(String[] args) {
        String s = "abcdef";
        //判断字符串长度是否大于5并且是否包含a
        boolean b = demoAnd(s, (s1) -> {
            return s1.length() > 5;
        }, (s2) -> {
            return s2.contains("f");
        });

        System.out.println(b);
    }

    //同时判断2个条件
    public static boolean demoAnd(String s, Predicate<String> pre1, Predicate<String> pre2) {
        //return pre1.test(s) && pre2.test(s);
        //使用 and 方法跟上面&&效果一样
        return pre1.and(pre2).test(s);
    }
}
