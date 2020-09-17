package org.gokasama.datart.manager.repository;

import org.gokasama.datart.manager.model.AppConf;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ka wujia@chinamobile.com
 */
public interface AppConfRepository extends MongoRepository<AppConf, String> {

    /**
     * @param key key
     * @return AppConf
     */
    AppConf findByKey(String key);

    /**
     * @param key key
     * @return boolean
     */
    boolean existsByKey(String key);

}
