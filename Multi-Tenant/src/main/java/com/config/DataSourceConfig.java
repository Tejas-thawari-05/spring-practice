package com.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
    @Value("${spring.datasource.url}")
    private String defaultUrl;

    @Value("${spring.datasource.username}")
    private String defaultUsername;

    @Value("${spring.datasource.password}")
    private String defaultPassword;

    @Bean
    DataSource dataSource() {
        Map<Object, Object> tenantDataSources = new HashMap<>();

        // Load tenants dynamically from a database or config
        tenantDataSources.put("tenant1", createDataSource("tenant1_db"));
        tenantDataSources.put("tenant2", createDataSource("tenant2_db"));

        TenantDataSource tenantRoutingDataSource = new TenantDataSource();
        tenantRoutingDataSource.setTargetDataSources(tenantDataSources);
        tenantRoutingDataSource.setDefaultTargetDataSource(createDataSource("default_db"));
        tenantRoutingDataSource.afterPropertiesSet();
        return tenantRoutingDataSource;
    }

    private DataSource createDataSource(String dbName) {
        return DataSourceBuilder.create()
                .url(defaultUrl.replace("default_db", dbName))
                .username(defaultUsername)
                .password(defaultPassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}

