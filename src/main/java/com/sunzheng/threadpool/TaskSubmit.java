package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName TaskSubmit
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/7 13:41
 * @Version 1.0
 */
@Slf4j(topic = "C.")
public class TaskSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        method3();
    }

    //invokeAny，当线程池中有一个有了计算结果的时候就返回了
    private static void method3() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        String result = pool.invokeAny(Arrays.asList(() -> {
                    log.debug("being -- 1");
                    Thread.sleep(1000);
                    log.debug("end --- 1 ");
                    return "第一个线程返回，我执行了1000毫秒";
                },
                () -> {
                    log.debug("being -- 2");
                    Thread.sleep(500);
                    log.debug("end --- 2 ");
                    return "第二个线程返回，我执行了500毫秒";
                },
                () -> {
                    log.debug("being -- 3");
                    Thread.sleep(100);
                    log.debug("end --- 3");
                    return "第三个线程返回，我执行了100毫秒";
                }
                )
        );
        log.debug("我是"+result);
    }

    //使用invokeAll 提交任务
    private static void method2() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("being1");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("being2");
                    Thread.sleep(1500);
                    return "2";
                }
                ,
                () -> {
                    log.debug("being3");
                    Thread.sleep(2000);
                    return "2";
                }
        ));


        new Thread(() -> {
            log.debug("hhh");
        }).start();

        futures.forEach(f -> {
            try {
                log.debug(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    //使用submit
    private static void method() throws InterruptedException, ExecutionException {
        //sumbmit 提交Callable参数进行线程的执行
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            final int k = i;
            //标准的callable调用
            Future<Integer> submit1 = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 200 + k;
                }
            });
            //lambda 简写
            Future<Integer> submit = pool.submit(() -> {
                return 200 + k;
            });

            Integer Integer = submit.get();
            System.out.println(Integer);
        }
    }
}
