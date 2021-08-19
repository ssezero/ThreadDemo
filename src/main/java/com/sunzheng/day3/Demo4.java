package com.sunzheng.day3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo4
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/29 16:07
 * @Version 1.0
 **/
@Slf4j(topic = "c2.demo4")
public class Demo4 {
    public static void main(String[] args) {
        Room studyroom=new Room();
        Room sleeproom=new Room();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (studyroom){
                    studyroom.Studys();
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (sleeproom){
                        sleeproom.Sleep();
                    }
                }

            }
        },"t1").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (sleeproom){
                    sleeproom.Sleep();
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (studyroom){
                        studyroom.Studys();
                    }
                }

            }
        },"t2").start();

    }
}
@Slf4j(topic = "c2.demo4")
class Room{
    public void Sleep(){
            log.debug("睡觉了");
    }
    public void Studys(){
            log.debug("学习了");
    }
}
