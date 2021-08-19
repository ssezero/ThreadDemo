package com.sunzheng.functionProgram.lambda;

/**
 * @ClassName Demo4Runnable
 * @Description
 * 函数式接口作为参数进行传递
 * @Author Neal
 * @Date 2021/8/17 13:58
 * @Version 1.0
 **/
public class Demo4Runnable {
    //定义一个方法,参数使用函数式接口
    static void startThread(Runnable runnable){
        new Thread(runnable).start();
    }

    public static void main(String[] args) {
        //1.调用startThread方法，方法的参数是个接口，我们可以传递这个接口的匿名内部类
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类创建..."+Thread.currentThread().getName());
            }
        });

        //2.方法的参数是一个函数式接口，还可以使用lambda表达式
        startThread(()->{
            System.out.println("Lambda表达式创建..."+Thread.currentThread().getName());
        });
    }

}
