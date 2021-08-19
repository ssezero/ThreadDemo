package com.sunzheng.day3;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/27 10:17
 * @Version 1.0
 **/
@Slf4j(topic = "d3.demo1")

public class Demo1 {
    public static void main(String[] args) {
        //非线程
        MessageQueue messageQueue = new MessageQueue(2);

        //创建3个生成者
        for (int i = 0; i < 3; i++) {
            int d=i;
            new Thread(()->{
                messageQueue.putMessage(new Message(d,d+"号消息"));
            },"t"+i).start();
        }

        // 创建消费者

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = messageQueue.getMessage();
                log.debug("收到的信息{}",message);
            }
        },"消费这").start();

    }
}

@Slf4j(topic = "d3.demo1")
class MessageQueue {
    //消息储存的队列
    private LinkedList<Message> linkedList = new LinkedList<>();
    //队列的最大值
    private int MaxCaptice;

    public MessageQueue(int maxCaptice) {
        MaxCaptice = maxCaptice;
    }

    //消费消息
    public Message getMessage() {
        synchronized (linkedList) {
            while (linkedList.isEmpty()) {
                log.debug("队列为空");
                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message remove = linkedList.remove();
            linkedList.notifyAll();
            return remove;
        }

    }


    //存储消息
    public void putMessage(Message message) {
        synchronized (linkedList){
            while (linkedList.size() >= this.MaxCaptice) {
                log.debug("队列已经满了");
                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("存储数据");
            linkedList.add(message);
            linkedList.notifyAll();
        }

    }

}

final class Message {

    private int id;
    private Object object;

    public Message(int id, Object object) {
        this.id = id;
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", object=" + object +
                '}';
    }
}
