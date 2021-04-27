package edu.omur.msworld.corelib.aspect;

import edu.omur.msworld.corelib.annotation.Loggable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(edu.omur.msworld.corelib.annotation.Loggable)")
    public Object loggable(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        Method method = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
        Object[] methodArguments = joinPoint.getArgs();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        Loggable loggable = method.getAnnotation(Loggable.class);
        if (loggable != null) {
            if (loggable.logArguments() && methodArguments != null && methodArguments.length > 0) {
                for (Object argumentObject : methodArguments) {
                    logger.info(" --> [{}] {}", className, argumentObject.toString());
                }
            } else {
                logger.info(" --> [{}] no parameter", className);
            }
        }

        long startTime = System.currentTimeMillis();
        proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        if (loggable != null) {
            if (loggable.logArguments()) {
                logger.info("<-- [{}] {}", className, proceed);
            } else {
                logger.info("<-- [{}] Method ends.", className);
            }

            if (loggable.logExecutionTime()) {
                long executionTime = endTime - startTime;
                logger.info("<-- [{}] executionTime: {}", className, executionTime);
            }
        }

        return proceed;
    }
}
