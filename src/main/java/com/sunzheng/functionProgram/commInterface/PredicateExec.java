package com.sunzheng.functionProgram.commInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName PredicateExec
 * @Description
 * 一个Predicate的练习
 * 返回性别是女名字是4个子的人
 * @Author Neal
 * @Date 2021/8/26 15:23
 * @Version 1.0
 **/
public class PredicateExec {
    public static void main(String[] args) {
        String [] array={"迪丽热巴,女","古力娜扎,女","马儿扎哈,男","赵丽颖，女"};
        List<String> list=demo(array,(s1)->{
            return s1.split(",")[0].length()==4;
        },(s2)->{
            return s2.split(",")[1].equals("女");
        });

        System.out.println(list);
    }
    public static List<String> demo(String [] array, Predicate<String> pre1,Predicate<String> pre2){
        List<String> list=new ArrayList<>();
        for (String s : array) {
            if(pre1.and(pre2).test(s)){
                list.add(s);
            }
        }
        return list;
    }
}
