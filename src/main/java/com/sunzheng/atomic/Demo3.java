package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Demo3
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/13 10:11
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class Demo3 {
    private static AtomicReference<String> str = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        String prev = str.get();
        log.debug("获取初始值:{}",prev);
        other();
        TimeUnit.SECONDS.sleep(1);
        //main 线程无法感知其他线程把str从A->B 又从B->A 的过程
        log.debug("从A到C:{}",str.compareAndSet(prev,"C"));
    }

    private static void other() {
        new Thread(()->{
            log.debug("从A到B:{}",str.compareAndSet(str.get(),"B"));
        },"T1").start();

        new Thread(()->{
            log.debug("从B到A:{}",str.compareAndSet(str.get(),"A"));
        },"T2").start();
    }
}
