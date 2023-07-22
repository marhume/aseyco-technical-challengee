package com.pe.aseyco.technical.challenge.core.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pe.aseyco.technical.challenge.common.util.JwtAuth;
import com.pe.aseyco.technical.challenge.core.controller.CompanyController;
import com.pe.aseyco.technical.challenge.core.controller.OauthTokenController;
import com.pe.aseyco.technical.challenge.core.service.impl.CompanyDBServiceImpl;
import com.pe.aseyco.technical.challenge.repository.impl.CompanyRepositoryImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Import({
	CompanyController.class,
	OauthTokenController.class,
    JwtAuth.class,
    CompanyDBServiceImpl.class,
    CompanyRepositoryImpl.class
})
@Slf4j
public class BeanConfig {
	
	@Value("${spring.data.mongodb.uri}")
	private String uri;

	@Value("${spring.data.mongodb.database}")
	private String db;
	
    @Bean
	public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), db);
        mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
        return mongoTemplate;
	}
    
    private MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(uri + "/" + db);        
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyToSocketSettings(builder -> {
                })
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> {
                    builder.minSize(10);
                    builder.maxSize(100);
                    builder.maxConnectionLifeTime(0, TimeUnit.MILLISECONDS);
                    builder.maxConnectionIdleTime(0, TimeUnit.MILLISECONDS);
                })
                .build();
        return MongoClients.create(mongoClientSettings);
    }
    
}
