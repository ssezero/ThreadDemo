package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName DemoSyno
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/19 10:14
 * @Version 1.0
 **/
@Slf4j(topic = "synoDemo")
public class DemoSyno2 {
    static int i = 0;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        Room room=new Room();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i1 = 0; i1 < 5000; i1++) {
                    room.add();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i1 = 0; i1 < 5000; i1++) {
                    room.sub();
                }
            }
        }, "t2");

        room.getCount();
    }
}

class Room {
    private int count = 0;
    public void add() {
        synchronized (this) {
            count++;
        }
    }
    public void sub(){
        synchronized (this){
            count--;
        }
    }

    public int getCount(){
        synchronized (this){
            return count;
        }
    }
}
