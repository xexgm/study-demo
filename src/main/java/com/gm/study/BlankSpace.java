package com.gm.study;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: xexgm
 */
public class BlankSpace {

    public static void main(String[] args) {
        List<Optional<String>> list = Arrays.asList(
            Optional.ofNullable("Hello"),
            Optional.empty(),
            Optional.ofNullable("World")
        );

        list.stream()
            .flatMap(Optional::stream)
            .forEach(str -> System.out.println(str));

        System.out.println("=============");


        List<String> list2 = Arrays.asList(
            "Hello World",
            "加 油"
        );

        List<String[]> ans1 = list2.stream()
            // map 把一个 元素，映射成其他元素
            .map(str -> str.split(" "))
            .toList();

        List<String> ans2 = list2.stream()
            // flatMap 接收一个函数，这个函数把一个元素转成流。flatMap再把这些流变成一个流，也就是扁平化
            .flatMap(str -> Arrays.stream(str.split(" ")))
            .toList();

        System.out.println("---------------------");
        Iterator<String[]> iterator = ans1.iterator();
        while (iterator.hasNext()) {
            String[] next = iterator.next();
            for (String str : next) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println("===========");
        System.out.println("ans2: " + ans2);

    }
}
