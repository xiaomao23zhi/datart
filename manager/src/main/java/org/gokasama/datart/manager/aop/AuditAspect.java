package org.gokasama.datart.manager.aop;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.gokasama.datart.manager.annotation.Auditing;
import org.gokasama.datart.manager.model.Audit;
import org.gokasama.datart.manager.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ka wujia@chinamobile.com
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 5)
@Slf4j
public class AuditAspect {

    final AuditService auditService;

    @Autowired
    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @Pointcut("@annotation(org.gokasama.datart.manager.annotation.Auditing)")
    public void auditPointcut() {

    }

    @AfterReturning("auditPointcut()")
    public void audit(JoinPoint joinPoint) {

        Audit audit = new Audit();
        Object[] args = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Auditing auditing = method.getAnnotation(Auditing.class);
        if (auditing != null) {
            //get operation
            audit.setOperation(auditing.operation());
        }

        //get args
        audit.setArgs(JSON.toJSONString(args));

        //log to db
        log.debug("[AuditAspect] - log to db: {}", audit);
        auditService.log(audit);
    }
}
