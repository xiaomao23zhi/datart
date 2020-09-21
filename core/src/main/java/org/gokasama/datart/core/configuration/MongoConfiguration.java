package org.gokasama.datart.core.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.lang.NonNull;

/**
 * @author ka wujia@chinamobile.com
 */
@Configuration
@EnableMongoAuditing
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.auto-index-creation}")
    private boolean autoIndexCreation;

    @Override
    @NonNull
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @NonNull
    public MongoClient mongoClient() {

        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected boolean autoIndexCreation() {
//        return super.autoIndexCreation();
        return autoIndexCreation;
    }

//    @Autowired
//    void setAutoIndexCreation() {
//
//    }

}
