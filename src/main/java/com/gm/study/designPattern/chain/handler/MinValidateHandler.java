package com.gm.study.designPattern.chain.handler;

import com.gm.study.designPattern.chain.ValidateHandlerContext;
import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 */
public class MinValidateHandler implements ValidateHandler{

    private int val;

    public MinValidateHandler(int val) {
        this.val = val;
    }

    @Override
    public void validate(ValidateHandlerContext context) {
        if (context.getValue() instanceof Integer intValue) {
            if (intValue < val) {
                context.addErrorMessage("最小值不能小于: " + val + ", 你的值为: " + intValue);
            }
        }
        context.doNext();
    }
}
