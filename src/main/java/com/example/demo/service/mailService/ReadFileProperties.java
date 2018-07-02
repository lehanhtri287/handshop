package com.example.demo.service.mailService;

import java.util.Properties;

public class ReadFileProperties {
	public Properties readFileProperties() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			properties.load(classLoader.getResourceAsStream("application.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return properties;
	}

}
