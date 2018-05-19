package com.cmic.PreMailCheck2TriggerJks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Tips(description = "用于进行一些初始化的操作")
public class App {

	static Properties pro;
	static {
		try {
			pro = new Properties();
			// ClassLoader.class.getResourceAsStream的意义参见tip.md，使用和不使用/开头将会产生不同结果，注意
			InputStream in = ClassLoader.class.getResourceAsStream("/res/ini/mailinfo.properties");
			pro.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
