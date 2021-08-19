package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo8Sleep
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 14:54
 * @Version 1.0
 **/
@Slf4j
public class Demo8Sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        log.debug(t1.getState().toString());

        Thread.sleep(500);
        log.debug(t1.getState().toString());


    }
}
