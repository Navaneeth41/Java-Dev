package com.teckdev.pagila;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minIdle;
	@Value("${spring.datasource.hikari.max-life-time}")
	private long maxLifeTime;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maxIdle;
	

    @Bean
    public DataSource dataSource() 
    {
    	HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setMaximumPoolSize(maxIdle);
        dataSource.setMaxLifetime(maxLifeTime);
        return dataSource;
    }
}
