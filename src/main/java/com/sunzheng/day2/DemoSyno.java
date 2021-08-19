package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DemoSyno
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/19 10:14
 * @Version 1.0
 **/
@Slf4j(topic = "synoDemo")
public class DemoSyno {
    static int i=0;
    static Object lock=new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i1 = 0; i1 < 5000; i1++) {
                    synchronized (lock){
                        i++;
                    }
                }
            }
        }, "t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i1 = 0; i1 < 5000; i1++) {
                    synchronized (lock){
                        i--;
                    }
                }
            }
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",i);
    }
}
