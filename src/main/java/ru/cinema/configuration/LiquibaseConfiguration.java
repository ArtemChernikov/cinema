package ru.cinema.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 27.02.2024
 */
@Configuration
public class LiquibaseConfiguration {
    private final DataSource dataSource;

    public LiquibaseConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
