package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo12Join
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 16:06
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo12")
public class Demo12Join {
    static int r = 0;
    public static void main(String[] args) throws Exception {
        method1();
    }
    private static void method1() throws InterruptedException {
        log.debug("开始");
        Thread t1=new Thread() {
            @Override
            public void run() {
                log.debug("开始");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r=100;
                log.debug("结束");
            }
        };
        t1.start();
        //谁调用谁优先
        t1.join();
        log.debug("R的值是{}",r);
        log.debug("结束了");
    }
}
