package com.sunzheng.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockQueue
 * @Description 自定义线程池
 * @Author Neal
 * @Date 2021/8/26 11:19
 * @Version 1.0
 **/
@Slf4j(topic = "c.TestPost")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 2, 1, TimeUnit.MILLISECONDS,((queue, task) -> {
//            queue.addTask(task);
            queue.offer(task,500,TimeUnit.MILLISECONDS);
        }));
        for (int i = 0; i < 15; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });
        }
    }
}


/*
 * @Author Neal
 * @Description //
 * 接口用于
 * @Date 13:45 2021/9/1
 * @Param
 * @return
 **/
interface TacticsRule<T>{
    void tasic(BlockQueue<T> queue,T task);
}
@Slf4j(topic = "c.")
//创建线程池
class ThreadPool {
    //任务队列
    private BlockQueue<Runnable> taskQueue;

    //线程的集合
    HashSet<Worker> workers = new HashSet<>();

    //任务获取超时时间
    private long timeOut;

    //核心线程数量
    private int coreSize;

    //时间单位
    private TimeUnit timeUnit;
    //cel
    private  TacticsRule<Runnable> tacticsRule;

    public ThreadPool(int queueCapcity, long timeOut, int coreSize, TimeUnit timeUnit,TacticsRule<Runnable> tacticsRule) {
        this.taskQueue = new BlockQueue<>(queueCapcity);
        this.timeOut = timeOut;
        this.coreSize = coreSize;
        this.timeUnit = timeUnit;
        this.tacticsRule=tacticsRule;
    }

    //执行任务
    public void execute(Runnable task) {

        //因为works不是线程安全的。所以增加syno锁
        synchronized (workers) {
            //1.判断线程池的数量是否大于核心线程数
            if (workers.size() < coreSize) {
                //1-1：交给核心线程取执行任务
                Worker worker = new Worker(task);
                //1-2 ：把线程添加到线程池
                log.debug("任务进入到线程池:{}", task);
                workers.add(worker);
                //1-3 : 执行任务
                worker.start();
            } else {
                //2. 如果线程池里面的任务已经大于核心线程数，则进入队列
//                taskQueue.addTask(task);
                //采用策略
                taskQueue.celui(tacticsRule,task);
            }
        }
    }

    //线程池里面的线程
    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            // 1: 当task不为空，执行任务
            // 2: 当task执行完毕，再接着从任务对立获取任务执行
            //因为是循环利用线程池里面的线程所以用个循环
            while (task != null || (task = taskQueue.poll(timeOut)) != null) {
                try {
                    log.debug("正在执行的任务....{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            //没有这个线程能再执行的任务了，从线程池移除
            synchronized (workers) {
                log.debug("woker 被移除了{}", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j(topic = "Queue.")
//线城池阻塞队列
class BlockQueue<T> {
    private int capacity;
    private Deque<T> queue = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyCondition = lock.newCondition();
    private Condition fullCondition = lock.newCondition();

    //构造函数初始化大小
    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }


    //带超时的awati,就是即便是空也别永久的等待
    public T poll(long timeOut) {
        lock.lock();
        long nanos = TimeUnit.SECONDS.toNanos(timeOut);
        try {
            while (queue.isEmpty()) {
                try {
                    nanos = emptyCondition.awaitNanos(nanos);
                    if(nanos<=0) return null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.remove();
            fullCondition.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    //获取任务
    public T getTask() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.remove();
            fullCondition.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }


    //添加任务
    public void addTask(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列{}....",task);
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            log.debug("任务进入到等待队列:{}", task);
            emptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时的添加
    public boolean offer(T task,long timeOut,TimeUnit timeUnit){
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeOut);
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列{}....",task);
                    if(nanos<=0){
                        return false;
                    }
                    nanos = fullCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            log.debug("任务进入到等待队列:{}", task);
            emptyCondition.signal();
            return  true;
        } finally {
            lock.unlock();
        }
    }
    //返回一队列的大小
    public int getSize() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void celui(TacticsRule<T> tacticsRule, T task) {
        lock.lock();
        try{
            if(queue.size()==capacity){
                tacticsRule.tasic(this,task);
            }
            else
            {
                queue.addLast(task);
                log.debug("任务进入到等待队列:{}", task);
                emptyCondition.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}
