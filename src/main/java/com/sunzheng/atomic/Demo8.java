package com.sunzheng.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName Demo8
 * @Description 原子累加器
 * LongAdder 在保证线程安全的前提下比AtomicLong更加的高效
 * @Author Neal
 * @Date 2021/8/17 11:24
 * @Version 1.0
 **/
public class Demo8 {
    public static void main(String[] args) {
        //使用Atomic类
        demo(() -> new AtomicLong(0),
                (array) -> array.incrementAndGet()
        );

        //使用更高效的loadAddr
        demo(()-> new LongAdder(),
                (array)->array.increment());


    }

    static <T> void demo(Supplier<T> supplier, Consumer<T> customer) {
        List<Thread> ts = new ArrayList<>();
        T adder = supplier.get();
        for (int i = 0; i < 10; i++) {
            ts.add(new Thread(() -> {
                for (int i1 = 0; i1 < 50000; i1++) {
                    customer.accept(adder);
                }
            }));
        }
        long begin = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();

        System.out.println(adder + " cost " + (end - begin) / 1000_000);


    }

}
