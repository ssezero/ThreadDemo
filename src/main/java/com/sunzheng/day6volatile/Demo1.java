package com.sunzheng.day6volatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/6 10:08
 * @Version 1.0
 **/
@Slf4j(topic = "c")
public class Demo1 {
    static int i=0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        },"t2").start();
        TimeUnit.SECONDS.sleep(5);
    log.debug("{}",i);
    }


}
