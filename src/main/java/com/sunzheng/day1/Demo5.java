package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo5
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/15 10:31
 * @Version 1.0
 **/
@Slf4j(topic = "c5.Out")
public class Demo5 {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.debug("T1.....");
            }
        }).start();
        new Thread(() -> {
            while (true){

                log.debug("T2.....");
            }
        }).start();
    }
}
