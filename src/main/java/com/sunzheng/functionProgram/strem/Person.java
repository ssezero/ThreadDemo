package com.sunzheng.functionProgram.strem;

/**
 * @ClassName Person
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/6 17:08
 * @Version 1.0
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
