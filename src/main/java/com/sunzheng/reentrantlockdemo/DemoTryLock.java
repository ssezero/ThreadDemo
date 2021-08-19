package com.sunzheng.reentrantlockdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName DemoTryLock
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/2 10:17
 * @Version 1.0
 **/
@Slf4j(topic = "c.reentrantlock")
public class DemoTryLock {
    private  static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            //等待三秒如果还是获取不到返回false,带参数得可以
            try {
                if(!lock.tryLock(3, TimeUnit.SECONDS)){
                    log.debug("没有获取到锁");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                //有可能被打断
                return;
            }
            try{
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        //main 先获取到锁再启动t1
        lock.lock();
        log.debug("获取到锁");
        t1.start();
        Thread.sleep(2000);
        lock.unlock();
    }
}
