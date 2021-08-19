package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo11yield
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 15:13
 * @Version 1.0
 **/
@Slf4j(topic = "c1.Demo11")
public class Demo11yield {
    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            int k=0;
            while (true){
                k++;
            }
        };

        Thread t2=new Thread(r1,"t2");
        Thread t3=new Thread(r1,"t3");
        Thread t4=new Thread(r1,"t4");
        Thread t5=new Thread(r1,"t5");
        Thread t6=new Thread(r1,"t6");
        Thread t7=new Thread(r1,"t7");


        //1. 创建线程1.然后执行输入1..50操作，当到20的时候让出CUP给main,多核心CUP不好实现
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if(i>20) Thread.yield();
                    log.debug("t1-"+i);
                }
            }
        };
        //t3-t7是为了占住cup的线程
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        t1.start();
        for (int i = 0; i < 50; i++) {
            log.debug("main-"+i);
        }
    }
}
