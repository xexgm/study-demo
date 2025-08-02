package com.gm.study.designPattern.chain.handler;

import com.gm.study.designPattern.chain.ValidateHandlerContext;
import com.gm.study.designPattern.chain.exception.ValidateException;

/**
 * @Author: xexgm
 */
public interface ValidateHandler {

    void validate(ValidateHandlerContext context) throws ValidateException;
}
