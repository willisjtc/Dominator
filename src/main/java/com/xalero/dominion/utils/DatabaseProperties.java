package com.xalero.dominion.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DatabaseProperties {
	private static Map<String, String> properties = initMap();
	
	public static String getProperty(String property) {
		return properties.get(property);
	}
	
	private static Map<String, String> initMap() {
		Map<String, String> properties = new HashMap<>();
		ResourceBundle rb = ResourceBundle.getBundle("dominiondatabase");
		Enumeration<String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			properties.put(key, rb.getString(key));
		}
		return properties;
	}
}
