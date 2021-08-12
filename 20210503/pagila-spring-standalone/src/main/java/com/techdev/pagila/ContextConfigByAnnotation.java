package com.techdev.pagila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.techdev.pagila.dao.PagilaDao;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.techdev.pagila.dao"})
public class ContextConfigByAnnotation {
    static ApplicationContext context= new AnnotationConfigApplicationContext(ContextConfigByAnnotation.class);
	
    @Autowired
    private PagilaDao dao;
    
    public PagilaDao getDao() {
    	return this.dao;
    }
    
    public static ContextConfigByAnnotation getInstance() {
        return context.getBean(ContextConfigByAnnotation.class);
    }
}

