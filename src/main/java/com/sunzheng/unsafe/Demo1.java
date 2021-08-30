package com.sunzheng.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName Demo1
 * @Description unsafe对象提供更底层的对内存上的对象的进行线程安全的操作
 * @Author Neal
 * @Date 2021/8/19 10:41
 * @Version 1.0
 **/
public class Demo1 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 1.使用反射技术获取unsafe对象
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);

        //尝试使用unsafe对象对sutdent的属性进行cas操作
        Student student=new Student();
        // 1.首先获取Student类上属性的偏移量
        long idOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
        // 2.执行ca操作 compareAndSwapInt(对象名，属性偏移量，预期值，实际值)
        boolean b = unsafe.compareAndSwapInt(student, idOffset, 0, 1);
        unsafe.compareAndSwapObject(student,nameOffset,null,"张三");
        System.out.println(student);

    }
}

@Data
class Student {
    volatile int id;
    volatile String name;
}
