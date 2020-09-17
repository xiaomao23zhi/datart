package org.gokasama.datart.manager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author ka wujia@chinamobile.com
 */
@Configuration
@EnableMongoAuditing
public class MongoConfiguration extends AbstractMongoClientConfiguration  {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
