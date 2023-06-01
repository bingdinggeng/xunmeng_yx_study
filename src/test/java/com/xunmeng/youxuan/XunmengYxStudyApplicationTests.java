package com.xunmeng.youxuan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootTest
public class XunmengYxStudyApplicationTests {

    @Test
    void test1() {
        int [] arr = {1,2,3,5,7,8};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
        for(int i: arr){
            System.out.print(i);
        }
        System.out.println();

        HashSet<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");

        for(String item: set){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Test
    void test2(){
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("小昭");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");

        List<String> listA = new ArrayList<>();
        for ( String s  : list) {
            if (s.startsWith("张"))
                listA.add(s);
        }

        List<String> listB = new ArrayList<>();
        for (String s: listA) {
            if (s.length() == 3)
                listB.add(s);
        }

        for (String s: listB) {
            System.out.println(s);
        }
        System.out.println("----------------");

        list.stream().filter(item -> item.length() > 2).forEach(System.out::println);
    }

    @Test
    void test3(){
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("小昭");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");

        //List<String> listA = list.stream().filter(item -> item.startsWith("张"))
        //        .filter(item -> item.length() == 3).collect(Collectors.toList());

        List<String> listA = list.stream().filter(item -> item.startsWith("张") && item.length() == 3)
                .collect(Collectors.toList());
        for(String s: listA){
            System.out.println(s);
        }

    }


    @Test
    void test4(){
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
    }

    @Test
    void test5(){
        List<Integer> numbers = Arrays.asList(1, -1, 3, 4, 5);

        // 将每个数字映射为其平方值
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("原集合元素个数: " + numbers.size());
        System.out.println("映射后集合元素个数: " + squares.size());
        System.out.println(squares);
    }


    @Test
    void test6(){
        List<Integer> number = Arrays.asList(1,2,3,4,5,6,7,8,9);

        number.stream().limit(3).skip(2).forEach(System.out::println);
    }
}
