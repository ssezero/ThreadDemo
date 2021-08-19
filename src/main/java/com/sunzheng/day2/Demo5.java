package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @ClassName Demo5
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/20 11:26
 * @Version 1.0
 **/
@Slf4j(topic = "demo6")
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket=new Ticket(1000);
        Random random = new Random();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    ticket.sellTicket(random.nextInt(20));
                }
            }
        }, "t1");

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    ticket.sellTicket(random.nextInt(20));
                }
            }
        },"t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("剩余票数:{}",ticket.getTickets());

    }
}
class Ticket{
    private  Integer tickets;
    public Ticket(Integer tickets){
        this.tickets=tickets;
    }

    public Integer getTickets(){
        return this.tickets;
    }

    public Integer sellTicket(int amount){

        if(this.tickets>amount){
            this.tickets-=amount;
            return amount;
        }else
        {
            return 0;
        }
    }


}
