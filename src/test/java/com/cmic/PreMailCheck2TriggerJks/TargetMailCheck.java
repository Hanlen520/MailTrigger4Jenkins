package com.cmic.PreMailCheck2TriggerJks;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class TargetMailCheck {

	@Tips(description = "用于进行初始化的测试")
	@Test
	public void initCheck() throws IOException {
		Properties pro = App.pro;
		try {
			System.err.println(pro.getProperty("MAIL_HOST_TYPE", "本地caonima"));
			System.err.println(pro.getProperty("SENDER_GRANT_CODE", "本地caonima"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
