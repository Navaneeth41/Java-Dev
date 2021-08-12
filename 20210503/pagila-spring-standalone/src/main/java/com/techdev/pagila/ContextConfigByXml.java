package com.techdev.pagila;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techdev.pagila.dao.PagilaDao;

public class ContextConfigByXml {
	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

	public PagilaDao getDao() {
		return (PagilaDao) context.getBean("dao");
	}
}
