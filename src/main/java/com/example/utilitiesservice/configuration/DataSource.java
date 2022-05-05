package com.example.utilitiesservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataSource {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public javax.sql.DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public javax.sql.DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
