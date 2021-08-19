package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName Demo4
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/13 10:53
 * @Version 1.0
 **/

@Slf4j(topic = "c.")
public class Demo4 {
    //使用AtomicStampedReference 指定版本号
    private static AtomicStampedReference<String> str = new AtomicStampedReference<>("A",0);

    public static void main(String[] args) throws InterruptedException {
        String prev = str.getReference();
        int stamp = str.getStamp();
        log.debug("获取初始值:{}",prev);
        log.debug("获取的版本号:{}",stamp);
        other();
        TimeUnit.SECONDS.sleep(1);
        //main 线程无法感知其他线程把str从A->B 又从B->A 的过程
        //需要指定老的版本号和新的版本号
        log.debug("从A到C:{}",str.compareAndSet(prev,"C",stamp,stamp+1));
    }

    private static void other() {
        new Thread(()->{
            //获取版本号
            int stamp = str.getStamp();
            log.debug("当前版本号{}",stamp);
            log.debug("从A到B:{}",str.compareAndSet(str.getReference(),"B",stamp,stamp+1));
        },"T1").start();

        new Thread(()->{
            int stamp = str.getStamp();
            log.debug("当前版本号{}",stamp);
            log.debug("从B到A:{}",str.compareAndSet(str.getReference(),"A",stamp,stamp+1));
        },"T2").start();
    }
}
