package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/13 9:51
 * @Version 1.0
 **/
@Slf4j(topic = "c.Test")
public class Demo1 {
    public static void main(String[] args) {
//      使用匿名内部类方式直接创建Thread类
        Thread t=new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
//      指定线程名称
        t.setName("T1");
        t.start();
        log.debug("running");

    }
}
