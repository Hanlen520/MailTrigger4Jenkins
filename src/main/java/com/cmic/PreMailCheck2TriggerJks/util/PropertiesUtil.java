package com.cmic.PreMailCheck2TriggerJks.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties load(String path) {
		try {
			FileInputStream in = new FileInputStream(path);
			InputStreamReader iReader = new InputStreamReader(in, "UTF-8");
			Properties p = new Properties();
			p.load(iReader);
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getProperty(String fileName, String value) {
		return load(fileName).getProperty(value);
	}
}
