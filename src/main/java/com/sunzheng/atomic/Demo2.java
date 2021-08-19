package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Demo2
 * @Description
 * 原子引用
 * @Author Neal
 * @Date 2021/8/13 9:21
 * @Version 1.0
 **/
public class Demo2 {
    public static void main(String[] args) {
        DecimalAccount decimalAccount=new DecimalAccountImpl(new BigDecimal(10000));
        DecimalAccount.demo(decimalAccount);

    }
}
class DecimalAccountImpl implements DecimalAccount{

    private AtomicReference<BigDecimal>  balance;

    public DecimalAccountImpl(BigDecimal balance){
        this.balance=new AtomicReference<>(balance);

    }
    @Override
    public BigDecimal getBalance() {
        return this.balance.get();
    }

    @Override
    public void withdraw(BigDecimal amout) {
        while(true){
            BigDecimal prev=balance.get();
            BigDecimal next=prev.subtract(amout);
            if(balance.compareAndSet(prev,next)) break;
        }
    }
}
interface DecimalAccount{
    BigDecimal getBalance();
    void withdraw(BigDecimal amout);

    static void demo(DecimalAccount account){
        List<Thread> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(()->{
                account.withdraw(new BigDecimal(10));
            }));
        }

        list.forEach(Thread::start);
        list.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(String.format("剩余金额：%s",account.getBalance()));
    }
}
