package com.sunzheng.shareModel;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/20 13:49
 * @Version 1.0
 **/
@Slf4j(topic = "c.demo1")
public class Demo1 {
    public static void main(String[] args) {
        DateTimeFormatter st=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            TemporalAccessor parse = st.parse("1995-12-21");
            log.debug("{}",parse);
        }

    }

    private static void testDemo() {
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-mm-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    Date parse = sd.parse("2021-08-20");
                    log.debug("{}",parse);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
