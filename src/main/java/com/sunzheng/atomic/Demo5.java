package com.sunzheng.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @ClassName Demo5
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/13 11:16
 * @Version 1.0
 **/
@Slf4j(topic = "c.")
public class Demo5 {
    public static void main(String[] args) throws InterruptedException {
        Garabge bag = new Garabge("垃圾袋满了");
        log.debug("start.....");
        AtomicMarkableReference<Garabge> garabge = new AtomicMarkableReference<>(bag, true);
        Garabge prev = garabge.getReference();
        log.debug(prev.toString());
        //保洁上场
        new Thread(() -> {
            bag.setDesc("垃圾整理了！");
            garabge.compareAndSet(bag, bag,true,false );
        }, "保洁大姐").start();


        TimeUnit.SECONDS.sleep(1);
        boolean change = garabge.compareAndSet(prev, new Garabge("新的垃圾袋"), true, false);
        log.debug("是否换了新的垃圾袋:{}", change);

        log.debug(garabge.getReference().toString());
    }
}

class Garabge {
    private String desc;

    public Garabge(String desc) {
        this.desc = desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Garabge{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
