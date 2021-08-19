package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Demo3
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/13 15:39
 * @Version 1.0
 **/
@Slf4j(topic = "c.Demo3")
public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//       使用 FutureTak来创建任务
        FutureTask<Integer> task=new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("test....");
                Thread.sleep(1000);
                return 100;
            }
        });
//        创建线程T1
        Thread t1=new Thread(task,"T1");
        t1.start();
//        把任务的返回结果取出
        log.debug("{}",task.get());
    }
}
