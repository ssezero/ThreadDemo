package com.sunzheng.functionProgram.lambda;

import java.util.function.Consumer;

/**
 * @ClassName FormatPrintDemo
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/19 15:49
 * @Version 1.0
 **/
public class FormatPrintDemo {
    public static void main(String[] args) {
        String [] array={"迪丽热巴,女","萧敬腾,男","欧阳娜拉,女"};
        pringDemo((str)->{
            System.out.print(" 姓名："+str.split(",")[0]);
        },(str2)->{
            System.out.print(" 性别："+str2.split(",")[1]);
        },array);
    }

    public static void pringDemo(Consumer<String> con1,Consumer<String> con2,String [] arrs){
        for (int i = 0; i < arrs.length; i++) {
            con1.andThen(con2).accept(arrs[i].toString());
        }
    }
}
