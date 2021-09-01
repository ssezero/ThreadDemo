package com.sunzheng.functionProgram.commInterface;

import com.sunzheng.functionProgram.Func;

import java.util.function.Function;

/**
 * @ClassName FuncitonExec
 * @Description String str="赵丽颖,20"
 * 1.将字符串街区数字年龄部分，得到字符串
 * 2.将上一步的字符串转换成int类型的数字；
 * 3.将上一步的int数字类加到100 ，得到结果int数组
 * @Author Neal
 * @Date 2021/9/1 15:34
 * @Version 1.0
 **/
public class FuncitonExec {
    public static Integer demo(String str, Function<String, String> fun0, Function<String, Integer> fun1, Function<Integer, Integer> fun2) {
        Integer value = fun0.andThen(fun1).andThen(fun2).apply(str);
        return value;
    }

    public static void main(String[] args) {
        Integer value = demo("赵丽颖,20", (String str) -> {
            return str.split(",")[1];
        }, (String str2) -> {
            return Integer.parseInt(str2);
        }, (Integer num) -> {
            return num + 100;
        });
        System.out.println("最后的结果："+value);
    }
}
