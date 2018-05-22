package com.cmic.PreMailCheck2TriggerJks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.cmic.PreMailCheck2TriggerJks.util.LogUtil;
import com.cmic.PreMailCheck2TriggerJks.util.SendMailUtil;

@Tips(description = "用于进行一些初始化的操作", riskPoint = "不支持并发")
public class App {

	public static Properties pro;
	// 全局参数
	public static String SENDER_MAIL;
	public static String SENDER_GRANT_CODE;
	public static String RECEIVE_GRANT_CODE;

	public static String ATTACHMENT_SAVE_DIR;
	public static String LOOPFREQUENCY;// 检查频率
	public static String JENKINSHOME;// Jenkins目录，保存一些Job间共享的数据
	public static String SHAREPROPERTYPATH;// 共享Proprety目录

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
				LOOPFREQUENCY = pro.getProperty("LOOP_FREQUENCY", "180");// 检查频率
				JENKINSHOME = pro.getProperty("JENKINS_HOME");// 检查频率
			} else {// 其他类型的邮箱
				System.err.println("暂时不支持");
			}
			if (JENKINSHOME != null && !JENKINSHOME.isEmpty()) {
				File tempFile = new File(App.JENKINSHOME + File.separator + "tempProperty", "testInfo.properties");
				if (!tempFile.exists()) {
					// 先创建父级目录
					tempFile.getParentFile().mkdir();
					LogUtil.i(tempFile.getAbsolutePath());
					// 创建文件
					tempFile.createNewFile();
				}
				// 初始化保存的文件
			}
			SHAREPROPERTYPATH = App.JENKINSHOME + File.separator + "tempProperty" + File.separator
					+ "testInfo.properties";
			ATTACHMENT_SAVE_DIR = pro.getProperty("ATTACH_SAVEPATH");
			LogUtil.i("附件保存位置{}", ATTACHMENT_SAVE_DIR);
			// 检验
			LogUtil.e("检验1{}", JENKINSHOME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
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
