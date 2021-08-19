package com.sunzheng.day4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName Demo1
 * @Description 实现必须先打印2再打印1
 * @Author Neal
 * @Date 2021/8/3 9:40
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo1")
public class Demo1 {
    final static private Object object = new Object();
    private static boolean isbegin = false;

    public static void main(String[] args) throws InterruptedException {
//        demo1();
        demo2();
    }

    private static void demo2() throws InterruptedException {
          Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        Thread t2 = new Thread(() -> {
            LockSupport.unpark(t1);
            log.debug("2");
        }, "t2");

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();

    }

    private static void demo1() {
        Thread t1 = new Thread(() -> {
            synchronized (object) {
                while (!isbegin) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (object) {
                isbegin = true;
                object.notify();
            }
            log.debug("2");
        }, "t2");

        t1.start();
        t2.start();
    }
}
