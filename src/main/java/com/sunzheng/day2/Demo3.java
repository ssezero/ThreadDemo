package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo3
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/19 14:33
 * @Version 1.0
 **/
@Slf4j(topic = "demo3")
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> threadUnsafe.method1(5),"T"+i).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(threadUnsafe.arrayList);
    }

}
@Slf4j
class ThreadUnsafe {
    ArrayList<Integer> arrayList = new ArrayList<>();

    public void method1(Integer rounds) {
        for (Integer integer = 0; integer < rounds; integer++) {
            method2();
//            method3();
        }
    }

    public void method2() {
//         log.debug("{},执行Add",Thread.currentThread().getName());
//         log.debug("{},执行Add");
        arrayList.add(1);
    }

    public void method3() {
//        log.debug("{},执行Remove",Thread.currentThread().getName());
        arrayList.remove(0);
    }
}
