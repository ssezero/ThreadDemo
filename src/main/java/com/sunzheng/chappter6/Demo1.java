package com.sunzheng.chappter6;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/10 10:57
 * @Version 1.0
 **/
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
//        Account account = new AccountUnsafe();
        Account account = new AccountCas(10000);
//        Account account2 = new AccountSys();
        Account.demo(account);
//        Account.demo(account2);
    }
}

class AccountCas implements Account {

    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        //获取余额
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {

        balance.addAndGet(amount*-1);
//        while (true) {
//            //当前余额
//            int curr=this.balance.get();
//            //未来金额
//            int next=curr-amount;
//            //写入到banance
//            if (balance.compareAndSet(curr,next)) {
//                break;
//            }
//        }
    }
}

class AccountUnsafe implements Account {

    private Integer balance = 10000;

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

class AccountSys implements Account {

    private Integer balance = 10000;

    @Override
    public Integer getBalance() {
        synchronized (this)
        {
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this)
        {
            this.balance -= amount;
        }
    }
}

interface Account {
    //获取余额
    Integer getBalance();

    //取钱
    void withdraw(Integer amount);

    static void demo(Account account) {
        List<Thread> tl = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            tl.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }
        tl.forEach(Thread::start);
        tl.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost: " + (end - start) / 1000_000 + " ms");


    }
}
