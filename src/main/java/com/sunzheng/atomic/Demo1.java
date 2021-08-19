package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/11 15:12
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger i=new AtomicInteger(0);
        log.debug("{}",i.getAndIncrement());//++i  // 0
        log.debug("{}",i.get());            //1
        log.debug("{}",i.getAndDecrement());//--i  //1
        log.debug("{}",i.get());            // 0
        log.debug("{}",i.incrementAndGet());//i++ // 1
        log.debug("{}",i.decrementAndGet());//i-- // 0

        log.debug("{}",i.getAndAdd(5)); //0
        log.debug("{}",i.get());              //5

        log.debug("{}",i.addAndGet(5)); //10
        log.debug("{}",i.get());              //10

        i.getAndUpdate(x->x*12);
        log.debug("{}",i.get());



    }
}
