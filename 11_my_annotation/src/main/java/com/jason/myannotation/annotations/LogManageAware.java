package com.jason.myannotation.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author：stanzeng
 * @Description：日志管理注解增强
 * @Date ：Created in 2020/7/30 2:11 下午
 * @Modified By：
 */
@Aspect
@Component
public class LogManageAware{

    @Pointcut("@annotation(lggg)")
    public void log(LogManage lggg){

    }

    @Around("log(lggg)")
    public Object round(ProceedingJoinPoint pjp, LogManage lggg) throws Throwable {

        Object result = pjp.proceed(pjp.getArgs());

        System.out.println("LogManageAware日志切面，val=" + lggg.value());
        System.out.println("LogManageAware日志切面，desc=" + lggg.desc());

        return result;
    }
}
