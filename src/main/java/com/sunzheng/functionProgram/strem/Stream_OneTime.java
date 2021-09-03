package com.sunzheng.functionProgram.strem;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @ClassName Stream_OneTime
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/3 16:17
 * @Version 1.0
 */
public class Stream_OneTime {
    public static void main(String[] args) throws InterruptedException {
        Stream<String> stream = Stream.of("张三", "张无忌", "赵敏", "周芷若");

        stream.filter(name->name.length()==3);
        TimeUnit.SECONDS.sleep(1);
        stream.forEach(name-> System.out.println(name));
        TimeUnit.SECONDS.sleep(2);
        stream.forEach(name-> System.out.println(name));

    }
}
