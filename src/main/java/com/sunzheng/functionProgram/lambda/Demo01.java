package com.sunzheng.functionProgram.lambda;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/16 14:51
 * @Version 1.0
 **/
//日志案例
    /*
     问题，如果我们对showLog()传递的日志级别式1，字符串就白拼接了。有点浪费
     **/
public class Demo01 {
    //
    public static void showLog(int level,String message){
        if(level==1){
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        String msg1="Hello";
        String msg2="World";
        String msg3="Java";
        showLog(1,msg1+msg2+msg3);
    }
}
