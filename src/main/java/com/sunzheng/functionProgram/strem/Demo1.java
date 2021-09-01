package com.sunzheng.functionProgram.strem;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Demo1
 * @Description 流逝操作
 * @Author Neal
 * @Date 2021/9/1 16:00
 * @Version 1.0
 **/

public class Demo1 {
    public static void main(String[] args) {
        commMethod();
    }

    private static void commMethod() {
        List<String> list=new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三风");

        List<String> zhanglist=new ArrayList<>();
        //第一次过滤只要张开头
        for (String s : list) {
            if(s.startsWith("张")){
                zhanglist.add(s);
            }
        }
        //第二次过滤就要名字长度是3的
        List<String> length3=new ArrayList<>();
        for (String s : zhanglist) {
            if(s.length()==3){
                length3.add(s);
            }
        }
        //第三次遍历集合
        for (String s : length3) {
            System.out.println(s);
        }
    }
}
