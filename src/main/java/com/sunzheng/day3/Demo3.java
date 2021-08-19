package com.sunzheng.day3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo3
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/28 10:46
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo3")
public class Demo3 {
    final  static  Object object=new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    log.debug("t1拿到索引");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    log.debug("t1后续代码执行");

                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    log.debug("t2拿到索引");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("t2后续代码执行");
                }
            }
        },"t2").start();

        Thread t3=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("T3");
                Thread.sleep(10000);
                log.debug("结束睡眠");
            }
        },"T3");

        log.debug("MAIN");
        t3.start();
        t3.join();
        TimeUnit.SECONDS.sleep(10);
        synchronized (object){
            object.notifyAll();
        }
    }
}
