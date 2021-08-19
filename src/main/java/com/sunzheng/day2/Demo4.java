package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo4
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/19 15:10
 * @Version 1.0
 **/
@Slf4j(topic = "demo4")
public class Demo4 {
    static String str="";
    static StringBuffer stringBuffer=new StringBuffer();
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                str=str+"A";
                stringBuffer.append("A");
            }
        },"t1");

        Thread t2=new Thread(()->{
            for (int i = 0; i < 10; i++) {
                str=str+"b";
                stringBuffer.append("b");
            }
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug(str);
        log.debug("{}--{}",stringBuffer.toString(),stringBuffer.length());
    }
}
