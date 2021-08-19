package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo6
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/15 14:23
 * @Version 1.0
 **/
@Slf4j(topic = "c6.Demo6")
public class Demo6 {
    public static void main(String[] args) {
        String par="1";
        method1(par);
    }

    private static void method1(String par) {
        String par2="2";
        method2(par,par2);
    }

    private static void method2(String par, String par2) {
        log.debug("参数一{}参数2{}",par,par2);
    }
}
