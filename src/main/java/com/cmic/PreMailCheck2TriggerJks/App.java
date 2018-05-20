package com.cmic.PreMailCheck2TriggerJks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.PropertyConfigurator;

import com.cmic.PreMailCheck2TriggerJks.util.LogUtil;
import com.cmic.PreMailCheck2TriggerJks.util.SendMailUtil;

@Tips(description = "用于进行一些初始化的操作")
public class App {

	public static Properties pro;
	// 全局参数
	public static String SENDER_MAIL;
	public static String SENDER_GRANT_CODE;
	public static String RECEIVE_GRANT_CODE;

	public static String ATTACHMENT_SAVE_DIR;

	static {
		try {
			pro = new Properties();
			// ClassLoader.class.getResourceAsStream的意义参见tip.md，使用和不使用/开头将会产生不同结果，注意
			InputStream in = ClassLoader.class.getResourceAsStream("/res/ini/mailinfo.properties");
			pro.load(in);
			in.close();
			if (pro.getProperty("MAIL_HOST_TYPE").equals("139")) {
				SENDER_MAIL = pro.getProperty("SENDER_MAIL", "18814127364@qq.com");
				SENDER_GRANT_CODE = pro.getProperty("SENDER_GRANT_CODE");//
				RECEIVE_GRANT_CODE = pro.getProperty("RECEIVE_GRANT_CODE");
			} else {// 其他类型的邮箱
				System.err.println("暂时不支持");
			}
			ATTACHMENT_SAVE_DIR = pro.getProperty("ATTACH_SAVEPATH");
			LogUtil.i("附件保存位置{}", ATTACHMENT_SAVE_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// System.out.println("Hello World!");
		try {
			SendMailUtil.sendMailBy139("kiwi188141@163.com");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
