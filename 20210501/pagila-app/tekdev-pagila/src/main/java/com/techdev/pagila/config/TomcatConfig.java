package com.techdev.pagila.config;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatConfig {
	private static final Logger logger = LoggerFactory.getLogger(TomcatConfig.class);

	private Integer getPort() {
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			return 8080;
		}
		return Integer.valueOf(webPort);
	}

	private String getWebAppFolderPath() {
		String webAppFolderPath = "src/main/webapp/";
		logger.info("configuring app with basedir: {}",  new File(webAppFolderPath).getAbsolutePath());
		//return webAppFolderPath;
		return new File(webAppFolderPath).getAbsolutePath();
	}

	private void setupClasses(StandardContext ctx) {
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);
	}

	public void startSever() {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(getPort());
		tomcat.getConnector();
		try {
			StandardContext ctx = (StandardContext) tomcat.addWebapp("/", getWebAppFolderPath());
			setupClasses(ctx);
			tomcat.start();
			tomcat.getServer().await();

		} catch (LifecycleException e1) {
			e1.printStackTrace();
		}
	}
}
