package com.gm.study.designPattern.chain;

import java.util.ArrayList;
import java.util.List;

import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 * desc: 不同字段的校验链的总上下文，用于管理不同的校验链
 */
public class ValidateContext {

    List<List<String>> contextErrorMessage = new ArrayList<>();

    public void addChainErrorMessage(List<String> list) {
        this.contextErrorMessage.add(list);
    }

    public void throwExceptionIfNecessary() {
        if (contextErrorMessage.isEmpty()) {
            return;
        }
        throw new ValidateException(this.toString());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<String> strings : this.contextErrorMessage) {
            sb.append(strings.toString());
        }
        return sb.toString();
    }

}
