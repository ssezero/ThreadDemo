package com.sunzheng.functionProgram.strem;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName ExecesOne
 * @Description TODO
 * @Author Neal
 * @Date 2021/9/6 17:09
 * @Version 1.0
 */
@Slf4j(topic = "c.")
public class ExecesOne {
    public static void main(String[] args) {
        ArrayList<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("古力娜扎");
        one.add("章子怡");
        one.add("汪峰");
        one.add("刘浩存");
        one.add("赵丽颖");
        one.add("冯绍峰");

        ArrayList<String> two = new ArrayList<>();
        two.add("张.罗纳呢多");
        two.add("李.C罗");
        two.add("张.梅西");
        two.add("李.马老多呢");
        two.add("张.乔丹");
        two.add("张.凤雏");
        //传统方式
        //method1(one, two);

//        List<String> filterone = one.stream().filter(name -> name.length() == 3).limit(3).collect(Collectors.toList
// ());
//        showList(filterone);
        //使用stream实现了传统方式的操作
        Stream.concat(one.stream().filter(name -> name.length() == 3).limit(3),
                two.stream().filter(name -> name.startsWith("张")).skip(2)).map(name -> new Person(name)).forEach(person -> log.debug(person.toString()));


    }

    private static void method1(ArrayList<String> one, ArrayList<String> two) {
        // 姓名长度等于3个，取前三个
        ArrayList<String> fileone = fileone(one);
        showList(fileone);
        // 姓名是张开头，跳过前2个
        ArrayList<String> filetwo = filetwo(two);
        showList(filetwo);
        //合并
        List<String> concat = concat(fileone, filetwo);
        showList(concat);

        //创建类
        List<Person> listPerons = new ArrayList<>();
        for (String s : concat) {
            listPerons.add(new Person(s));
        }
        //输出person类的list
        for (Person listPeron : listPerons) {
            System.out.println(listPeron);
        }
    }


    //合并集合
    private static <T> List<T> concat(List<T> one, List<T> two) {
        List<T> resultList = new ArrayList<>();
        for (T o : one) {
            resultList.add(o);
        }
        for (T o : two) {
            resultList.add(o);
        }
        return resultList;
    }

    private static ArrayList<String> filetwo(ArrayList<String> two) {
        ArrayList<String> fllter = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        for (String s : two) {
            if (s.startsWith("张")) {
                temp.add(s);
            }
        }

        for (int i = 0; i < temp.size(); i++) {
            if (i > 1) {
                fllter.add(temp.get(i));
            }
        }
        return fllter;
    }

    private static void showList(List<String> list) {
        log.debug("--------------");
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static ArrayList<String> fileone(ArrayList<String> one) {
        ArrayList<String> filterOne = new ArrayList<>();
        ArrayList<String> tempOne = new ArrayList<>();
        for (String s : one) {
            if (s.length() == 3) {
                tempOne.add(s);
            }
        }

        int k = 0;
        for (String s : tempOne) {
            k++;
            filterOne.add(s);
            if (k == 3) break;
        }
        return filterOne;
    }
}
