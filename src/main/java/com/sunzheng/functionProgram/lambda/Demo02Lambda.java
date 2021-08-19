package com.sunzheng.functionProgram.lambda;

/**
 * @ClassName Demo02Lambda
 * @Description 使用lambda优化日志案例
 * lambda的特点，延迟加载
 * lambda使用前提：必须存在函数式接口
 * @Author Neal
 * @Date 2021/8/16 14:59
 * @Version 1.0
 **/
public class Demo02Lambda {
    //定义显示日志的方法,日志等级和message
    public static void showLog(int level, MessageBuilder mb) {
        //对日志的等级判断,如果是一级，调用mb的builderMessage方法
        if (level == 1) {
            System.out.println(mb.builderMessage());
        }
    }

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = "World";
        String msg3 = "Java";
        //调用showLog方法，参数messagebuilder是一个函数式接口，可以传递lambda表达式
        showLog(1, () -> {
            //返回一个拼接号的字符串
            return msg1 + msg2 + msg3;
        });
 /*       使用Lambda表达式作为参数传递，仅仅当level等于1的时候才会
                执行MessageBuilder中的方法
                才会进行字符串的拼劲儿
          如果条件不满足level不等1，MessageBuilder中的方法的方法也不会执行
          所以拼接字符串的方法也不会执行，不会存在性能的浪费
                */
    }
}
