package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName PoolShutDown
 * @Description 尝试停止线程
 * @Author Neal
 * @Date 2021/9/8 14:38
 * @Version 1.0
 */
@Slf4j(topic = "C.")
public class PoolShutDown {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future1 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 1");
                Thread.sleep(1000);
                log.debug("end 1");
                return "1";

            }
        });
        Future<String> future2 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 2");
                Thread.sleep(1000);
                log.debug("end 2");
                return "1";

            }
        });
        Future<String> future3 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 3");
                Thread.sleep(1000);
                log.debug("end 3");
                return "1";
            }
        });
        //shutdownNow会立即暂停线程池里面没有执行完的任务。并且给把没有开始的任务任务与返回
        List<Runnable> runnables = pool.shutdownNow();
        runnables.forEach(p-> log.debug(p.toString()));

    }

    private static void submitShutdown() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future1 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 1");
                Thread.sleep(1000);
                log.debug("end 1");
                return "1";

            }
        });
        Future<String> future2 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 2");
                Thread.sleep(1000);
                log.debug("end 2");
                return "1";

            }
        });
        Future<String> future3 = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("being 3");
                Thread.sleep(1000);
                log.debug("end 3");
                return "1";
            }
        });
//        future.get()会组塞住MAIN线程
//        String s1 = future1.get();
//        String s2 = future2.get();
//        String s3 = future3.get();
//
        log.debug("shutdown");
        pool.shutdown();      //shutdown会执行已经提交的请求
        log.debug("others...."); //调用了shutdown后不会等待其他线程，这个地方会直接输出
        pool.awaitTermination(3, TimeUnit.SECONDS);//这个方法会让后面的代码等待线程池里面的线程都执行完毕
        log.debug("others22222");

        //之后再提交任务就不可以了 ,会报错，但是不影响之前的3个任务
//        Future<String> future4 = pool.submit(() -> {
//            log.debug("being 4");
//            Thread.sleep(1000);
//            log.debug("end 4");
//            return "4";
//        });
//        String s = future4.get();
    }

    //如果使用invoke的话即便shutdown也会先执行，队列中的任务
    private static void invokeShutdown() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(() -> {
            log.debug("begin 1");
            Thread.sleep(2000);
            log.debug("end 1");
            return "1";
        }, () -> {
            log.debug("begin 2");
            Thread.sleep(2000);
            log.debug("end 2");
            return "2";
        }, () -> {
            log.debug("begin 3");
            Thread.sleep(2000);
            log.debug("end 3");
            return "3";
        }));
        log.debug("showdon");
        pool.shutdown();
    }
}
