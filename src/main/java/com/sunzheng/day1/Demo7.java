package com.sunzheng.day1;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Demo7
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/16 14:43
 * @Version 1.0
 **/
@Slf4j
public class Demo7 {
    public static void main(String[] args) {
        Thread t1=new Thread(){
          @Override
          public void run() {
              log.debug("runnin......");
          }
      };
        log.debug(t1.getState().toString());
        t1.start();
        //t1.start(); //IllegalThreadStateException
        log.debug(t1.getState().toString());
    }
}
