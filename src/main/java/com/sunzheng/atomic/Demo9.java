package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo9
 * @Description 使用atomic实现CAS锁
 * @Author Neal
 * @Date 2021/8/18 10:59
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo9 ")
public class Demo9 {
    public static void main(String[] args) {
        TestLock lock=new TestLock();
        new Thread(()->{
            lock.lock();
            //如果能锁上就输出下面的话
            log.debug("t1 已经拿到锁 5秒后释放锁");
            try {
                TimeUnit.SECONDS.sleep(5);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            lock.lock();
            //如果能锁上就输出下面的话
            log.debug("t2 已经拿到锁 1秒后释放锁");
            try {
                TimeUnit.SECONDS.sleep(1);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t2").start();
    }
}
class TestLock{
    private AtomicInteger flag=new AtomicInteger(0);
    public void lock(){
        //如果能加上锁就是不用进入死循环
        while (true){
            if(flag.compareAndSet(0,1)){
                break;
            }
        }
    }
    public void unlock(){

        flag.set(0);
    }
}
