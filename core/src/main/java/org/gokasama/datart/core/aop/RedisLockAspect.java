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

/**
 * @author ka wujia@chinamobile.com
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 5)
@Slf4j
public class RedisLockAspect {

    private static final String LOCK_KEY_PREFIX = "redisLock_";
    @Resource
    private Redisson redisson;

    @Pointcut("@annotation(org.gokasama.datart.core.annotation.RedisLock)")
    public void redisLockPointcut() {

    }

    @Around("redisLockPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        Object object;

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();

        RedisLock annotation = method.getAnnotation(RedisLock.class);
        String name;
        String key;
        RLock rLock = null;

        try {
            if (annotation == null) {
                //No annotation
                log.info("No annotation");
                object = proceedingJoinPoint.proceed();
                return object;
            }

            name = annotation.name();
            key = LOCK_KEY_PREFIX + annotation.key();

            //Get lock
            log.info("GetLock, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                    name, key, annotation.waitTime(), annotation.leaseTime());
            rLock = redisson.getLock(key);

            try {
                //Try lock
                boolean isLock = rLock.tryLock(annotation.waitTime(), annotation.leaseTime(), annotation.timeUnit());
                //If failed then return null(no proceed)
                if (!isLock) {
                    log.info("Failed tryLock, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                            name, key, annotation.waitTime(), annotation.leaseTime());
                    return null;
                }

                //proceed
                object = proceedingJoinPoint.proceed();
                log.info("Success, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                        name, key, annotation.waitTime(), annotation.leaseTime());

            } catch (Exception exception) {
                //Error
                log.error("Error", exception);
                throw new RedisLockException("<RedisLock> - error: " + exception.getMessage());
            } finally {
                //Release
                log.info("Release, name:{}, key:{}, waitTime:{}, leaseTime:{}",
                        name, key, annotation.waitTime(), annotation.leaseTime());
                if (rLock != null) {
                    rLock.unlock();
                }
            }

        } catch (Throwable throwable) {
            log.error("Error", throwable);
            throw new RedisLockException("Error: " + throwable.getMessage());
        }

        return object;
    }
}
