package com.example.gymgenius.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Before("execution(* com.example.gymgenius.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method called: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.gymgenius.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method threw exception: " + exception.getMessage());
    }
}
