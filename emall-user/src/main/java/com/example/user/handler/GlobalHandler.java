package com.example.user.handler;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.example.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Tian Changqing
 * @date 2022/11/14 17:31
 * @desc
 */
@Slf4j
@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler({Exception.class})
    public Result handlerRuntimeException(Exception e) {
        log.error("错误信息：{}, 异常e: {}", e.getMessage(), e);
        if (StringUtils.isBlank(e.getMessage()) && e.getCause() != null) {
            log.error("错误信息：{}", e.getCause().getMessage());
        }
        return Result.fail(e.getMessage());
    }
}
