package com.gm.study.designPattern.chain;

import java.util.ArrayList;
import java.util.List;

import com.gm.study.designPattern.chain.handler.ValidateHandler;

/**
 * @Author: xexgm
 */
public class ValidateChain {

    private List<ValidateHandler> chain = new ArrayList<>();

    public void addLastHandler(ValidateHandler handler) {
        this.chain.add(handler);
    }

    public void validate(Object object, ValidateContext validateContext) throws Exception{
        // 每个字段的校验链发起校验的时候，可以创建一个上下文，用来管理
        ValidateHandlerContext context = new ValidateHandlerContext(object);

        while (true) {
            // 获取当前在责任链中的位置
            int index = context.currentIndex();
            // 如果责任链走到头，则退出
            if (index == chain.size()) {
                break;
            }
            // 拿到处理器
            ValidateHandler handler = chain.get(index);
            handler.validate(context);

            // 如果处理器中没有调用 doNext，也就是不往下走，则退出
            if (context.currentIndex() == index) {
                break;
            }
        }
        context.throwExceptionIfNecessary(validateContext);
    }
}
