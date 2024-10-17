package ru.films.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* ru.films.controller.FilmController.*(..))")
    public void anyFilmControllerMethod() {
    }

    @Pointcut("execution(* ru.films.service.FilmServiceImpl.addFilms(..))")
    public void addFilmsFilmServiceMethod() {
    }

    @Before("anyFilmControllerMethod() || addFilmsFilmServiceMethod()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        log.info("film-api: вызов метода {} с аргументами: {}", methodName, Arrays.toString(methodArgs));
    }

    @AfterReturning(pointcut = "anyFilmControllerMethod() || addFilmsFilmServiceMethod()", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        if (!methodName.equals("getAll")) {
            log.info("film-api: метод {} выполнен, результат: {}", methodName, result);
        } else {
            log.info("film-api: метод {} выполнен", methodName);
        }
    }
}
