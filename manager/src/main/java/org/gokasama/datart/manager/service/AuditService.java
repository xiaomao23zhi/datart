package org.gokasama.datart.manager.service;

import lombok.extern.slf4j.Slf4j;
import org.gokasama.datart.manager.model.Audit;
import org.gokasama.datart.manager.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void log(Audit audit) {

        log.debug("Log audit: {}", audit);

        auditRepository.save(audit);
    }
}
