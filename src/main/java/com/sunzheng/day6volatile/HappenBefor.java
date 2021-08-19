package com.sunzheng.day6volatile;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName HappenBefor
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/9 9:51
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class HappenBefor {
    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

    static  int x;
    private static void demo1() throws InterruptedException {
        Thread t1=new Thread(()->{
            x=10;
        },"t1");
        t1.start();
        t1.join(); // 等待t1结束
        new Thread(()->{
            log.debug("{}",x); // t2 输出10
        },"t2").start();
    }
}
