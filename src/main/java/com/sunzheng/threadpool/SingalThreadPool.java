package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SingalThreadPool
 * @Description
 * 单线程实例
 * 单线程池子的好处就是其中一个失败其他的可以继续操作
 * @Author Neal
 * @Date 2021/9/3 14:57
 * @Version 1.0
 */
@Slf4j(topic = "neal.")
public class SingalThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            log.debug("1");
            int k=1/0;  //出现错误后不影响其他的操作
        });

        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("2");
        });
        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("3");
        });
    }
}
