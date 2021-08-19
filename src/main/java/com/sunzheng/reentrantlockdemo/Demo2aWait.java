package com.sunzheng.reentrantlockdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Demo2aWait
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/2 14:07
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo2")
public class Demo2aWait {
    static final ReentrantLock ROOM = new ReentrantLock();
    private static boolean hasCirgate = false;
    private static boolean hasOutfood = false;
    static Condition cirateRoom = ROOM.newCondition();
    static Condition outFoodRoom = ROOM.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Condition condition = ROOM.newCondition();
        //小楠线程，有烟就去
        new Thread(() -> {
            //先获取锁才能等待
            ROOM.lock();
            log.debug("小楠进入工作间间.....");
            try {
                //判断是否有烟
                while (!hasCirgate) {
                    log.debug("没有烟，先去休息室休息一下...");
                    try {
                        cirateRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果有烟就干活
                log.debug("开始干活....");
            } finally {
                ROOM.unlock();
            }
        }, "小楠").start();


        //小女线程，有烟就去
        new Thread(() -> {
            //先获取锁才能等待
            ROOM.lock();
            log.debug("小女进入工作间间.....");
            try {
                //判断是否有外卖
                while (!hasOutfood) {
                    log.debug("没有外卖，先去休息室休息一下...");
                    try {
                        outFoodRoom.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果有外卖就干活
                log.debug("拿到外卖开始干活....");
            } finally {
                ROOM.unlock();
            }
        }, "小女").start();

        //送外卖的线程5秒后抵达
        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> {
            ROOM.lock();
            try {
                 log.debug("送外卖的到了");
                hasOutfood = true;
                outFoodRoom.signal();
            } finally {
                ROOM.unlock();
            }
        }, "外卖").start();
    }
}
