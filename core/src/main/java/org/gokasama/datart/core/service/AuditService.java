package org.gokasama.datart.core.service;

import lombok.extern.slf4j.Slf4j;
import org.gokasama.datart.core.model.Audit;
import org.gokasama.datart.core.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author ka wujia@chinamobile.com
 */
@Component
@Slf4j
public class AuditService {

    final AuditRepository auditRepository;

    @Autowired
    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    /**
     * Log audit entry
     *
     * @param audit audit
     */
    @Async
    public void log(Audit audit) {

        log.debug("Log audit: {}", audit);

        auditRepository.save(audit);
    }
}
