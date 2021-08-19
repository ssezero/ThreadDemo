package com.sunzheng.atomic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName Demo6
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/16 10:47
 * @Version 1.0
 **/
public class Demo6 {
    public static void main(String[] args) {

        //普通数组不安全
        demo(
                () -> new int[10],
                (array) -> array.length,
                (array, index) -> array[index]++,
                (array) -> System.out.println(Arrays.toString(array))
        );

        //采用atomic原子数组
        demo(
                () -> new AtomicIntegerArray(10),
                (array) -> array.length(),
                (array, index) -> array.getAndIncrement(index),
                array -> System.out.println(array)
        );
    }

    //传入数组，对数组每个索引上的数字进行加一操作
    private static <T> void demo(Supplier<T> arraySupplier,
                                 Function<T, Integer> lengthFun,
                                 BiConsumer<T, Integer> putConsumer,
                                 Consumer<T> pritConsumer) {
        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int lenght = lengthFun.apply(array);
        for (int i = 0; i < lenght; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array, j % lenght);
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
        pritConsumer.accept(array);
    }
}

