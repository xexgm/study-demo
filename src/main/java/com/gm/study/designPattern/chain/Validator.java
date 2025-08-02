package com.gm.study.designPattern.chain;

import java.lang.reflect.Field;

import com.gm.study.designPattern.chain.anotation.Length;
import com.gm.study.designPattern.chain.anotation.Max;
import com.gm.study.designPattern.chain.anotation.Min;
import com.gm.study.designPattern.chain.handler.LengthValidateHandler;
import com.gm.study.designPattern.chain.handler.MaxValidateHandler;
import com.gm.study.designPattern.chain.handler.MinValidateHandler;

/**
 * @Author: xexgm
 */
public class Validator {


    private ValidateContext validateContext = new ValidateContext();


    private ValidateChain buildChain(Field field) {
        ValidateChain chain = new ValidateChain();

        Length length = field.getAnnotation(Length.class);
        if (length != null) {
            //        注解标注的值   字段的值
            chain.addLastHandler(new LengthValidateHandler(length.value()));
        }
        Min min = field.getAnnotation(Min.class);
        if (min != null) {
            chain.addLastHandler(new MinValidateHandler(min.value()));
        }

        Max max = field.getAnnotation(Max.class);
        if (max != null) {
            chain.addLastHandler(new MaxValidateHandler(max.value()));
        }

        return chain;
    }


    public void validate(Object bean) throws Exception {
        Class<?> beanClass = bean.getClass();
        for (Field field : beanClass.getDeclaredFields()) {
            field.setAccessible(true);

            // build校验链
            ValidateChain chain = buildChain(field);

            chain.validate(field.get(bean), this.validateContext);
        }
        this.validateContext.throwExceptionIfNecessary();

    }
}
