package com.sunzheng.shareModel;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/20 14:40
 * @Version 1.0
 **/
public class Demo2 {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(10);
        System.out.println(atomicIntegerArray.get(1));
    }
}
