package com.sunzheng.day2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DemoHeader
 * @Description TODO
 * @Author Neal
 * @Date 2021/7/23 10:19
 * @Version 1.0
 **/
@Slf4j
public class DemoHeader {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Dog dog = new Dog();
            dog.setName("我是小狗" + i);
            dogs.add(dog);
        }

        for (Dog dog : dogs) {
           log.debug("{}",dog.getName());
        }


    }
}

class Dog {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}