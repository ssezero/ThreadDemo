package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName FiexThreadPool
 * @Description
 * 固定线程池大小的测试
 * @Author Neal
 * @Date 2021/9/3 10:21
 * @Version 1.0
 */
@Slf4j(topic = "Neal.")
public class FiexThreadPool {
    public static void main(String[] args) {
        defineName();

    }
    //自定义线程名称使用ThreadFatory
    private static void defineName() {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger count=new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"我的线程名称"+count.getAndIncrement());
            }
        });
        pool.execute(()->{
            log.debug("1");
        });
        pool.execute(()->{
            log.debug("2");
        });
        pool.execute(()->{
            log.debug("3");
        });
    }

    //没有指定线程名称的固定线程池
    private static void noDefineName() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(()->{
            log.debug("1");
        });
        pool.execute(()->{
            log.debug("2");
        });
        pool.execute(()->{
            log.debug("3");
        });
    }
}
