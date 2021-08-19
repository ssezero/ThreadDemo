package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo10SleepTimeUnit
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 15:09
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo10")
public class Demo10SleepTimeUnit {
    public static void main(String[] args) throws InterruptedException {
        log.debug("开始睡");
        TimeUnit.SECONDS.sleep(1);
        log.debug("睡完了");

    }
}
