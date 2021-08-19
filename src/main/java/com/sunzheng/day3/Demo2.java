package com.sunzheng.day3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/27 11:18
 * @Version 1.0
 **/
@Slf4j
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("start....");
                Thread.sleep(1000);
                log.debug("park....");
                LockSupport.park();
                log.debug("重新启动...");
            }
        },"t1");
        t1.start();


        TimeUnit.SECONDS.sleep(5);
        log.debug("unpark.....");
        LockSupport.unpark(t1);
    }
}
