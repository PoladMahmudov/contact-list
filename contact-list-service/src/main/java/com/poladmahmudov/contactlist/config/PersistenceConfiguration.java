package com.poladmahmudov.contactlist.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.poladmahmudov.contactlist.persistence.domain")
@EnableJpaRepositories("com.poladmahmudov.contactlist.persistence.repository")
public class PersistenceConfiguration {
}
