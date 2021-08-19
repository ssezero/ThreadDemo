package com.sunzheng.functionProgram.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;

/**
 * @ClassName Demo03Comparator
 * @Description 函数式接口作为返回值类型
 * @Author Neal
 * @Date 2021/8/17 13:56
 * @Version 1.0
 **/
public class Demo03Comparator {
    //方法的返回值是一个接口，那么我们可以返回这个接口的匿名内部类
    static Comparator<String> getComparator() {
        //1.可以使用匿名内部类
        /*return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //按照字符串的降序排序
                return o2.length()-o1.length();
            }
        };*/

        //2.使用lambda方式返回，注意有2个参数String o1, String o2
        return ((String o1, String o2) -> {
            //按照字符串的降序排序
            return o2.length() - o1.length();
        });
    }

    public static void main(String[] args) {
        //创建字符串数组
        String [] array={"a","aaaaa","bbb","ccdd","aa"};
        System.out.println(Arrays.toString(array));
        //调用Arrays.sort 方法，第二个参数是要传递一个Comparator<T> 类型的参数
        Arrays.sort(array,getComparator());
        System.out.println(Arrays.toString(array));


    }


}
