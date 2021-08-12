package com.techdev.pagila;

import com.techdev.pagila.config.TomcatConfig;

public class WebApp {
	public static void main(String[] args) {
		TomcatConfig config = new TomcatConfig();
		config.startSever();
	}
}
