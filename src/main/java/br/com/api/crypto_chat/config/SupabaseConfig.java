package br.com.api.crypto_chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SupabaseConfig {
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @Profile("prod")
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        // Validate URL format
        if (!url.contains("supabase.co")) {
            throw new IllegalStateException("Invalid Supabase URL format");
        }

        // Ensure SSL is enabled
        String finalUrl = url;
        if (!url.contains("sslmode=")) {
            finalUrl = url + "?sslmode=require";
        }

        // Configure connection pool
        dataSource.setJdbcUrl(finalUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setAutoCommit(true);
        dataSource.setConnectionTimeout(20000);
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        dataSource.setIdleTimeout(300000);
        dataSource.setMaxLifetime(1200000);
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setValidationTimeout(5000);

        // Set Supabase specific properties
        dataSource.addDataSourceProperty("reWriteBatchedInserts", "true");
        dataSource.addDataSourceProperty("applicationName", "CryptoChat");
        dataSource.addDataSourceProperty("prepareThreshold", "0");

        return dataSource;
    }
}
