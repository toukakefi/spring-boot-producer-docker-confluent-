package com.example.producer.aspect;


import com.example.producer.entity.Enseignant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Aspect
@Slf4j
@Service
public class GeneralInterceptorAspect {

    //@Pointcut("execution(* com.example.project.controller.*.*(..))")
    //@Pointcut("within(com.example.project..*)")
    //@Pointcut("this(com.example.project.service.EnseignantCommandService.*)")
    @Pointcut("@annotation(com.example.project.Annotation.CustomAnnotation)")
    public void loggingPointCut(){}

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){
        log.info("before method invoked:"+joinPoint.getSignature());
    }

    @AfterReturning(value="execution(* com.example.project.controller.*.*(..))",
            returning="enseignant")
    public void after(JoinPoint joinPoint, Enseignant enseignant){
        log.info("After method invoked:"+enseignant);

    }

    @AfterThrowing(value = "execution(* com.example.project.controller.*.*(..))",
            throwing="e")
    public void after(JoinPoint joinPoint, Exception e ){
        log.info("After method invoked:"+e.getMessage());

    }

    @Around("loggingPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        log.info("before method invoked"+joinPoint.getArgs()[0]);
        Object object=joinPoint.proceed();

        if(object instanceof  Enseignant)
        {
            log.info("after method invoked ..."+joinPoint.getArgs()[0]);
        }
        return object;
    }
}
