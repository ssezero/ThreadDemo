package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/13 10:12
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo2")
public class Demo2 {
    public static void main(String[] args) {
//      先创建线程任务
        Runnable r= () -> log.debug("running");
//      创建线程
        Thread t1=new Thread(r,"T1");
//      启动
        t1.start();
    }
}
