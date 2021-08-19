package com.sunzheng.functionProgram;

/*函数式接口：有且又只有抽象方法得接口
* FunctionalInterface
* 作用检查接口是否一个函数式接口
* 是：编译成功
* 否，编译失败（接口中灭有抽象方法抽象方法个数多余1个）
* */
@FunctionalInterface
public interface MyFuncitonInterface {
    //定义一个抽象方法
    void method();
}
