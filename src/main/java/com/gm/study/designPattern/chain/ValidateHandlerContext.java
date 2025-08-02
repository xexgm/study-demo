package com.gm.study.designPattern.chain;

import java.util.ArrayList;
import java.util.List;

import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 * desc: 校验链中不同处理器的上下文
 */
public class ValidateHandlerContext {

    // 上下文可以来管理 value
    private Object value;

    private List<String> errorMessageList = new ArrayList<>();

    public ValidateHandlerContext(Object object) {
        this.value = object;
    }

    public Object getValue() {
        return this.value;
    }

    public void addErrorMessage(String s) {
        errorMessageList.add(s);
    }

    // 上下文可以来管理校验处理器
    private int index = 0; // 初始化为 0

    // 要获取当前的index, 来从校验链中获取处理器
    public int currentIndex() {
        return this.index;
    }

    // 如何递进校验器呢？写一个 doNext 方法
    public void doNext() {
        index ++;
    }


    public void throwExceptionIfNecessary(ValidateContext validateContext) {
        if (errorMessageList.isEmpty()) {
            return;
        }
        validateContext.addChainErrorMessage(errorMessageList);
    }

}
