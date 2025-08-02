package com.gm.study.designPattern.decorator.set;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * @Author: xexgm
 */
public class Main {

    public static void main(String[] args) {
        HistorySet<Integer> historySet = new HistorySet<>(new HashSet<Integer>());
        HistorySet<Integer> set = new HistorySet<>(historySet);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.remove(1);
        set.remove(4);
        set.remove(5);

        System.out.println(set);

        Collection<Object> objects = Collections.synchronizedCollection(new HashSet<>());

        if (objects.isEmpty()) {
            objects.add(1);
        }

    }
}
