package com.example.LibraryManagement.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("within(com.example.LibraryManagement.Service..*) || within(com.example.LibraryManagement.Controller..*)")
    public void appPointCut() {}

    @Around("appPointCut()")
    public Object logMethodExecution (ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[ENTER] {}.{}() with arguments : {}", className, methodName, Arrays.toString(args));

        long startingTime = System.currentTimeMillis();

        try {
            Object proceed = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startingTime;

            log.info("[EXIT] {}.{}() executed in {} ms", className, methodName, executionTime);

            return proceed;

        } catch (Throwable ex) {
           long executionTime = System.currentTimeMillis() - startingTime;
           log.error("[ERROR] {}.{}() failed after {} ms with message {} ", className, methodName, executionTime, ex.getMessage());
           throw ex;
        }
    }
}
