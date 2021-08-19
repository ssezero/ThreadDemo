package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @ClassName Demo7
 * @Description 使用 Atomic 原子类保证类的属性的安全
 * @Author Neal
 * @Date 2021/8/17 10:29
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class Demo7 {
    public static void main(String[] args) {
        //创建对象
        Student student = new Student();
        //定义一个字段更新器,更新引用类型的
        AtomicReferenceFieldUpdater fieldUpdater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        //定义一个更新器，更新整形的
        AtomicIntegerFieldUpdater ageUpdate = AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");
        //使用compareAndSet进行字段的更新，跟之前一样，先比较预期，再更新
        fieldUpdater.compareAndSet(student, null, "张三");
        ageUpdate.compareAndSet(student, 0, 39);
        System.out.println(student);
    }
}

class Student {
    volatile String name;
    volatile int age;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
