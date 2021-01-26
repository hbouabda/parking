package com.example.parking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuration
@EnableJpaRepositories("fr.edf.argos.backend.repository")
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware", modifyOnCreate = false)
@EnableTransactionManagement
public class DatabaseConfiguration extends AbstractDatabaseConfiguration {

    @Override
    protected String getMasterXml() {
        return "classpath:db/master.xml";
    }

}*/
