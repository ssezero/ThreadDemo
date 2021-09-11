package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName ThreadModel
 * @Description 模拟点菜，做饭
 * 主体思想是线程池内部的线程不能再调用该线程池的线程
 * @Author Neal
 * @Date 2021/9/10 14:25
 * @Version 1.0
 */
@Slf4j(topic = "C.")
public class ThreadModel {
    public static  String [] Menum={"宫保鸡丁","鱼香肉丝","麻婆豆腐","水煮肉"};
    static Random random=new Random();
    public static String getCook(){
        return Menum[random.nextInt(Menum.length)];
    }
    public static void main(String[] args) {
        Mehtod2();
    }

    //如果是不同的线程池做不同的事情就不会造成线程饿了的问题。
    public static void Mehtod2(){
        //服务员线程池
        ExecutorService waiter = Executors.newFixedThreadPool(1);
        //厨子线程池
        ExecutorService cooker = Executors.newFixedThreadPool(1);
        //来了一个客人
        waiter.execute(()->{
            log.debug("来客人了开始点菜");
            Future<String> cookCai = cooker.submit(() -> {
                log.debug("厨子做菜");
                String cai = getCook();
                return cai;
            });
            try {
                String s = cookCai.get();
                log.debug("菜做好了给客人上菜{}",s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
        //又来一个
        waiter.execute(()->{
            log.debug("来客人了开始点菜");
            Future<String> cookCai = cooker.submit(() -> {
                log.debug("厨子做菜");
                String cai = getCook();
                return cai;
            });
            try {
                String s = cookCai.get();
                log.debug("菜做好了给客人上菜{}",s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    //如果就2个线程干的都是相同的事情，造成线程饥饿
    private static void Method() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(()->{
            log.debug("客人来了点菜");
            //让厨子做饭
            Future<String> submit = pool.submit(() -> {
                log.debug("大厨做饭了");
                String cook = getCook();
                return cook;
            });
            try {
                String cai = submit.get();
                log.debug("上菜了,{}",cai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.execute(()->{
            log.debug("客人来了点菜");
            //让厨子做饭
            Future<String> submit = pool.submit(() -> {
                log.debug("大厨做饭了");
                String cook = getCook();
                return cook;
            });
            try {
                String cai = submit.get();
                log.debug("上菜了,{}",cai);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
