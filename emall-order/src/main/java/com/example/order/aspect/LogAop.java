package com.example.order.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author tianchangqing
 * @date 2022/8/23 14:10
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut("execution(public * com.example.order.controller.*.*(..)))")
    public void logPointcut(){
    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取类名
        String className = point.getTarget().getClass().getName();

        // 获取方法名
        String methodName = point.getSignature().getName();

        // 获取参数
        Object[] args = point.getArgs();
        if (args.length == 0) {
            log.info("{}.{}()方法请求参数为===>：{}", className, methodName, 0);
        } else {
//            for (Object arg : args) {
//                String params = JSON.toJSONString(arg);
//                log.info("{}.{}()【方法请求参数为】：{}", className, methodName, params);
//            }
            String params = JSON.toJSONString(args);
            log.info("{}.{}()方法请求参数为===>：{}", className, methodName, params);
        }


        Object result = point.proceed();

        String s = JSON.toJSONString(result);
        log.info("{}.{}()方法返回结果为===>：{}", className, methodName, s);

        // 获取执行完的时间
        long time = System.currentTimeMillis() - startTime;
        log.info("{}.{}()方法执行时长为===>：{}{}", className, methodName, time, " ms");

        return result;
    }
}
