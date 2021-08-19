package com.sunzheng.functionProgram;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/16 14:32
 * @Version 1.0
 **/
public class Demo {
    //函数式表达式作为参数传递
    public static void show(MyFuncitonInterface myIner) {
        myIner.method();
    }

    public static void main(String[] args) {
        //调用show方法，方法的参数是一个接口，可以传递接口的实现对象
        show(new MyFunctioninterImpl());

        //调用show方法，方法的参数是一个接口，可以传递接口的匿名内部类
        show(new MyFuncitonInterface() {
            @Override
            public void method() {
                System.out.println("匿名内部类实现的方法");
            }
        });

        //调用show方法，方法的参数是一个函数式表达式接口，我们可以lambda表达式
        show(() -> {
            System.out.println("lambda表达式实现的方法");
        });
        show(() -> System.out.println("lambda再精简"));
    }
}
