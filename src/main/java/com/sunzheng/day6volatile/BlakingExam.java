package com.sunzheng.day6volatile;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName BlakingExam
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/9 10:45
 * @Version 1.0
 **/
@Slf4j
public class BlakingExam {
    public static void main(String[] args) {
        Test test=new Test();
    }
}

@Slf4j
class Test{
    void init(){
        log.debug("测试");
    }
}