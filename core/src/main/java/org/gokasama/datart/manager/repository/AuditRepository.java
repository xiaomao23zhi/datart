package org.gokasama.datart.manager.repository;

import org.gokasama.datart.manager.model.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ka wujia@chinamobile.com
 */
public interface AuditRepository extends MongoRepository<Audit, String> {
}
