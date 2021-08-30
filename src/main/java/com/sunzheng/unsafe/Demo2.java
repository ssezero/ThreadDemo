package com.sunzheng.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName Demo2
 * @Description TODO
 * 用底层的unsfae实现Atomic安全增加数字操作
 * @Author Neal
 * @Date 2021/8/19 14:52
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo2")
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        //测试安全的
        CuxAtomicint cuxAtomicint = new CuxAtomicint(10000);
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ts.add(new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    cuxAtomicint.addValueSafe(-1);
                }
            }));
        }
       ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.debug("剩余：{}", cuxAtomicint.getValue());

        //测试不安全的
        ts.clear();
        CuxUnsafe cuxUnsafe = new CuxUnsafe(10000);
        for (int i = 0; i < 10; i++) {
          ts.add(  new Thread(() -> {
              for (int i1 = 0; i1 < 1000; i1++) {
                  cuxUnsafe.addAmount(-1);
              }
          }));
        }
        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.debug("剩余：{}", cuxUnsafe.getValue());
    }
}

//非安全的
class CuxUnsafe {
    private int value;

    public CuxUnsafe(int value) {
        this.value = value;
    }

    public void addAmount(int amount) {
        this.value = this.value + amount;
    }

    public int getValue() {
        return this.value;
    }
}

//安全的
class CuxAtomicint {
    private volatile int value; //操作得值
    private static Unsafe UNSAFE;
    private static Long valueOffet; //偏移量

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            valueOffet = UNSAFE.objectFieldOffset(CuxAtomicint.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public CuxAtomicint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    //线程安全加减操作
    public void addValueSafe(int amount) {
        //使用CAS
        while (true) {
            int prev = value;
            int next = value + amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffet, prev, next)) {
                break;
            }
        }
    }
}
