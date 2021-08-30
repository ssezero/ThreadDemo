package com.sunzheng.threadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockQueue
 * @Description
 * 自定义线程池
 * @Author Neal
 * @Date 2021/8/26 11:19
 * @Version 1.0
 **/
public class TestPool { }


//线城池阻塞队列
class BlockQueue<T>{
    private  int capacity;
    private Deque<T> queue=new ArrayDeque<>();
    private ReentrantLock lock=new ReentrantLock();
    private Condition emptyCondition=lock.newCondition();
    private Condition fullCondition=lock.newCondition();
    //构造函数初始化大小
    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }


    //带超时的awati,就是即便是空也别永久的等待
    public T poll(long timeOut){
        lock.lock();


        long nanos= TimeUnit.SECONDS.toNanos(timeOut);
        try{
            while (queue.isEmpty()){
                try {
//                    emptyCondition.await();
                    nanos = emptyCondition.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.remove();
            fullCondition.signal();
            return task;
        }finally {
            lock.unlock();
        }
    }

    //获取任务
    public T getTask(){
        lock.lock();
        try{
            while (queue.isEmpty()){
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.remove();
            fullCondition.signal();
            return task;
        }finally {
            lock.unlock();
        }
    }

    //添加任务
    public void addTask(T task){
        lock.lock();
        try{
            while (queue.size()==capacity){
                try {
                    fullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addFirst(task);
            emptyCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    //返回一队列的大小
    public int getSize (){
        lock.lock();
        try{
            return queue.size();
        }finally {
            lock.unlock();
        }
    }
}
