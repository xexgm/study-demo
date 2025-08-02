package com.gm.study.designPattern.chain.handler;

import com.gm.study.designPattern.chain.ValidateHandlerContext;
import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 */
public class MaxValidateHandler implements ValidateHandler{

    private int val;

    public MaxValidateHandler(int val) {
        this.val = val;
    }

    @Override
    public void validate(ValidateHandlerContext context) {
        if (context.getValue() instanceof Integer intValue) {
            if (intValue > val) {
                context.addErrorMessage("最大值不能超过: " + val + ", 你的值是: " + intValue);
            }
        }
        context.doNext();
    }
}
