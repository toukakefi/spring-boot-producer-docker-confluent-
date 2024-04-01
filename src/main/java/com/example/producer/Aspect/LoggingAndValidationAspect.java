package com.example.producer.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAndValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAndValidationAspect.class);

    @Before("execution(* com.example.producer.service.*.*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Before executing method '{}' with arguments: {}", methodName, args);
    }

    @AfterReturning(pointcut = "execution(* com.example.producer.service.*.*(..))", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("After executing method '{}'. Result: {}", methodName, result);
    }

    @AfterReturning("execution(* com.example.producer.service.*.*(..)) && args(..)")
    public void validateInput(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            Assert.notNull(arg, "Les données ne peuvent pas être nulles");
            // Vous pouvez ajouter d'autres validations selon vos besoins
        }
    }
}
