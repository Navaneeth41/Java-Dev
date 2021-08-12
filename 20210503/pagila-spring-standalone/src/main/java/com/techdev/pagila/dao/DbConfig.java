package com.techdev.pagila.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {

	@Value("${db.userName}")
	String userName;
	@Value("${db.password}")
	String password;

	public DbConfig() {
	}

	public DbConfig(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		// jdbc:postgresql://host:port/database
		String url = "jdbc:postgresql://postgrestekdev.cd6oucy10xvk.us-east-2.rds.amazonaws.com:5432/postgrestekdev";
		Properties props = new Properties();
		
		props.setProperty("user", userName);
		props.setProperty("password", password);
		/*
		props.setProperty("user", "postgrestek");
		props.setProperty("password", "nbmax123");
		//props.setProperty("ssl", "true");
		 */
		return  DriverManager.getConnection(url, props);
	}
	
}
