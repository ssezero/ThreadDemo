package com.sunzheng.functionProgram.strem;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Stream_Count
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/3 16:43
 * @Version 1.0
 */
public class Stream_Count {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        //应用Stream流的count方法
        long count = list.stream().count();
        System.out.println("List的个数是"+count);
    }
}
