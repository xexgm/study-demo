package com.gm.study.designPattern.chain.handler;

import com.gm.study.designPattern.chain.ValidateHandlerContext;
import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 */
public class LengthValidateHandler implements ValidateHandler{

    private int length;

    public LengthValidateHandler(int length) {
        this.length = length;
    }

    @Override
    public void validate(ValidateHandlerContext context) {
        if (context.getValue() instanceof String stringValue) {
            if (stringValue.length() != length) {
                // 可以将错误信息装进 context，走完一整个链，而不是到某个处理器就停下
                //throw new ValidateException("你的值是: " + stringValue.length() + ", 需要的值是:" + this.length);
                context.addErrorMessage("name的值是: " + stringValue.length() + ", 需要的值是: " + this.length);
            }
        }
        context.doNext();
    }
}
