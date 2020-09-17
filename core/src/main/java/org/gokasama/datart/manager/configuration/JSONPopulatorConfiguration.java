package org.gokasama.datart.manager.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ka wujia@chinamobile.com
 */
@Configuration
@Slf4j
public class JSONPopulatorConfiguration {

    final MongoTemplate mongoTemplate;

    public JSONPopulatorConfiguration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator() {

        /* factory */
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

        try {
            /* data json*/
            Resource[] resources = resourceResolver.getResources("classpath:data/*.json");
            List<Resource> resourceList = new ArrayList<>();

            for (Resource resource : resources) {
                String collection = resource.getFilename().substring(0, resource.getFilename().length() - 5);
                if (!mongoTemplate.collectionExists(collection)) {
                    log.info("[JSONPopulatorConfig] - Adding data for collection: {}", resource.getFilename());
                    resourceList.add(resource);
                } else {
                    log.info("[JSONPopulatorConfig] - Skipping data for collection: {}", resource.getFilename());
                }
            }

            /* Set resources */
            resources = new Resource[resourceList.size()];
            for (int i = 0; i < resources.length; i++) {
                resources[i] = resourceList.get(i);
            }
            factory.setResources(resources);
        } catch (IOException exception) {
            log.error("[JSONPopulatorConfig] - Cannot read datac");
        }
        return factory;
    }
}
