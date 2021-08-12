package com.techdev.pagila.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {

	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		// jdbc:postgresql://host:port/database
		String url = "jdbc:postgresql://postgrestekdev.cd6oucy10xvk.us-east-2.rds.amazonaws.com:5432/postgrestekdev";
		Properties props = new Properties();
		props.setProperty("user", "postgrestek");
		props.setProperty("password", "nbmax123");
		//props.setProperty("ssl", "true");
		return  DriverManager.getConnection(url, props);
	}
	
}
