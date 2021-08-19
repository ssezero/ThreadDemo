package com.sunzheng.day5.jmm;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.MonitorInfo;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/4 15:05
 * @Version 1.0
 **/
@Slf4j(topic = "c.dmo2")
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        MointiorThread mointiorThread = new MointiorThread();

        log.debug("开启监控");
        new Thread(() -> {
            mointiorThread.start();
        }, "t1").start();
        new Thread(() -> {
            mointiorThread.start();
        }, "t2").start();
        new Thread(() -> {
            mointiorThread.start();
        }, "t3").start();
        new Thread(() -> {
            mointiorThread.start();
        }, "t4").start();

        TimeUnit.MILLISECONDS.sleep(14100);
        log.debug("关闭监控");
        mointiorThread.stop();
    }
}

@Slf4j(topic = "c.demo2")
class MointiorThread {
    private boolean stop = false;
    private Thread monitorThread;
    private boolean istart = false;

    public void start() {
        synchronized (this) {
            //如果已经启动了
            if (istart) {
                return;
            }
            istart = true;
        }
        new Thread(() -> {
            while (true) {
                if (stop) {
                    log.debug("开始料理后事....");
                    break;
                }
                //直接退出
                //开始监控逻辑
                log.debug("监控线程在执行....");

                //休眠1秒继续监控
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }

            }

        }, "monitor").start();
    }

    public void stop() {
        stop = true;
        monitorThread.interrupt();
    }
}
