package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo9SleepInterrupt
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 15:01
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo9")
public class Demo9SleepInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("t1开始睡眠20秒...");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    log.debug("t1没睡醒，被打断了....");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        log.debug("main 去骚扰t1线程.....");
        t1.interrupt();

    }
}
