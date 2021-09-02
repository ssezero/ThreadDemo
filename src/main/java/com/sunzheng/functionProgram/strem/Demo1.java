package com.sunzheng.functionProgram.strem;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName Demo1
 * @Description 流逝操作
 * @Author Neal
 * @Date 2021/9/1 16:00
 * @Version 1.0
 **/
@Slf4j(topic = "Stream.")
public class Demo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三风");
        commMethod(list);
        log.debug("---------------------------");


        streamDemo1(list);
    }

    private static void streamDemo1(List<String> list) {
        //采用Stream流，从1.8开始顶层Collection接口开始提供stream方法
        //lambda 表达式的特点
        //1.参数可以不写类型名
        //2.参数是一个可以没有小括号
        //3.函数内容是一行可以没有{}
        //3.函数内容是一行可以省却return

//        Stream<String> hh = list.stream().filter((String name) -> {
//            return name.startsWith("张");
//        });
        //简化lambda
        list.stream()
                .filter(name -> name.startsWith("张")) //等于 .filter((String name)->{return name.startsWith("张");})
                .filter(name -> name.length() == 3)
                .forEach(name->log.debug(name));
    }

    private static void commMethod(List<String> list) {


        List<String> zhanglist = new ArrayList<>();
        //第一次过滤只要张开头
        for (String s : list) {
            if (s.startsWith("张")) {
                zhanglist.add(s);
            }
        }
        //第二次过滤就要名字长度是3的
        List<String> length3 = new ArrayList<>();
        for (String s : zhanglist) {
            if (s.length() == 3) {
                length3.add(s);
            }
        }
        //第三次遍历集合
        for (String s : length3) {
            System.out.println(s);
        }
    }
}
