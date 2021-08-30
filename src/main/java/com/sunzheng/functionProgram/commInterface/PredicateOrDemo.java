package com.sunzheng.functionProgram.commInterface;

import java.util.function.Predicate;

/**
 * @ClassName Demo6Predicate
 * @Description
 * 测试Predicate 的默认or方法,多个条件满足一个条件就返回true
 * @Author Neal
 * @Date 2021/8/19 17:05
 * @Version 1.0
 **/
public class PredicateOrDemo {
    public static void main(String[] args) {
        String s = "abc";
        //判断字符串长度是否大于5获取是否包含a
        boolean b = demoAnd(s, (s1) -> {
            return s1.length() > 5;
        }, (s2) -> {
            return s2.contains("a");
        });

        System.out.println(b);
    }

    //同时判断2个条件
    public static boolean demoAnd(String s, Predicate<String> pre1, Predicate<String> pre2) {
        //return pre1.test(s) || pre2.test(s);
        //使用 or 方法跟上面&&效果一样
        return pre1.or(pre2).test(s);
    }
}
