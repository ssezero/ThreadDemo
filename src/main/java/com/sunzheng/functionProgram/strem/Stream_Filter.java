package com.sunzheng.functionProgram.strem;

import java.util.stream.Stream;

/**
 * @ClassName Stream_Filter
 * @Description
 * filter方法的蚕食是一个predicate函数接口，而可以传递lambda表达式，对数据进行过滤
 * Predicate中的抽象方法
 *      boolean test(T t)
 * @Author Neal
 * @Date 2021/9/2 15:47
 * @Version 1.0
 */
public class Stream_Filter {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张三", "张无忌", "赵敏", "周芷若");
        //过滤姓张的人
        Stream<String> stream2 = stream.filter((String name) -> {
            return name.startsWith("张");
        });
        //遍历stream2流
        stream2.forEach(name-> System.out.println(name));
    }
}
