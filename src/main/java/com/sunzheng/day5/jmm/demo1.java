package com.sunzheng.day5.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/4 10:20
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo1")
public class demo1 {
     volatile static boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (run) {

            }
        }, "t1").start();

        Thread.sleep(1000);
        log.debug("设置run为true");
        run=false;
    }
}
