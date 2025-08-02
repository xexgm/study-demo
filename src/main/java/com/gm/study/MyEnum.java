package com.gm.study;

import java.util.function.IntPredicate;

/**
 * @Author: xexgm
 */
public enum MyEnum {

    NORMAL(num -> 1 < num && num < 10),
    SMALL(num -> 10 < num && num < 100),
    BIG(num -> 100 < num && num < 1000);

    private final IntPredicate support;

    MyEnum(IntPredicate intPredicate) {
        this.support = intPredicate;
    }

    public static MyEnum typeOf(int num) {
        for (MyEnum value : values()) {
            if (value.support.test(num)) {
                return value;
            }
        }
        return null;
    }
}
