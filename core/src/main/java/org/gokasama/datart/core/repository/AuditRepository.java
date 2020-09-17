package org.gokasama.datart.core.repository;

import org.gokasama.datart.core.model.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ka wujia@chinamobile.com
 */
public interface AuditRepository extends MongoRepository<Audit, String> {
}
