package org.gokasama.datart.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.gokasama.datart.core.annotation.RedisLock;
import org.gokasama.datart.core.exception.RedisLockException;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author ka wujia@chinamobile.com
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 5)
@Slf4j
public class RedisLockAspect {

    @Resource
    private Redisson redisson;

    private static final String LOCK_KEY_PREFIX = "redisLock-";

    @Pointcut("@annotation(org.gokasama.datart.core.annotation.RedisLock)")
    public void redisLockPointcut() {

    }

    @Around("redisLockPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        Object object = null;


        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();

        RedisLock annotation = method.getAnnotation(RedisLock.class);
        RLock rLock = null;

        try {
            if (annotation == null) {
                //No annotation
                log.info("<RedisLock> - No annotation");
                object = proceedingJoinPoint.proceed();
                return object;
            }

            //Get lock
            log.info("<RedisLock> - getLock with name:{}, key:{}, waitTime:{}, leaseTime:{}",
                    annotation.name(), annotation.key(), annotation.waitTime(), annotation.leaseTime());
            rLock = redisson.getLock(annotation.key());

            //Try lock
            boolean isLock = rLock.tryLock(annotation.waitTime(), annotation.leaseTime(), annotation.timeUnit());
            //If failed then return null(no proceed)
            if (!isLock) {
                log.info("<RedisLock> - Failed tryLock, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                        annotation.name(), annotation.key(), annotation.waitTime(), annotation.leaseTime());
                return null;
            }
            object = proceedingJoinPoint.proceed();
            log.info("<RedisLock> - Success, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                    annotation.name(), annotation.key(), annotation.waitTime(), annotation.leaseTime());

        } catch (Throwable throwable) {
            log.error("<RedisLock> - error", throwable);
            throw new RedisLockException("<RedisLock> - error: " + throwable.getMessage());
        } finally {
            //Release
            log.info("<RedisLock> - Release, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                    annotation.name(), annotation.key(), annotation.waitTime(), annotation.leaseTime());
            if (rLock != null) {
                rLock.unlock();

            }
        }

        return object;
    }
}
