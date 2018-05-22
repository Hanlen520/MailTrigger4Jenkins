package com.cmic.PreMailCheck2TriggerJks;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.DateTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SubjectTerm;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.cmic.PreMailCheck2TriggerJks.util.LogUtil;
import com.cmic.PreMailCheck2TriggerJks.util.MailFilterFactory;
import com.cmic.PreMailCheck2TriggerJks.util.ReceiveMailUtil;
import com.cmic.PreMailCheck2TriggerJks.util.SendMailUtil;

public class TargetMailCheck {

	@Tips(description = "用于进行初始化的测试")
	@Test
	public void initCheck() throws IOException {
		Properties pro = App.pro;
		try {
			System.err.println(pro.getProperty("MAIL_HOST_TYPE", "本地caonima"));
			System.err.println(pro.getProperty("SENDER_GRANT_CODE", "本地caonima"));
			System.err.println(pro.getProperty("RECEIVE_GRANT_CODE", "本地caonima"));
			System.err.println(pro.getProperty("SENDER_MAIL", "本地caonima"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Tips(description = "测试发送邮件功能正常")
	@Test(enabled = false)
	public void checkSendMailCanUse() {
		LogUtil.printCurrentMethodNameInLog4J();
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

	@Tips(description = "测试接收邮件功能正常")
	@Test
	public void checkReceiveMailCanUse() {
		LogUtil.printCurrentMethodNameInLog4J();
		try {
			Calendar cl = Calendar.getInstance();
			cl.set(Calendar.MINUTE, cl.get(Calendar.MINUTE) - Integer.parseInt(App.LOOPFREQUENCY));// 默认在60分钟之内
			LogUtil.w("设置的构建时间间隔为{}分钟", Integer.parseInt(App.LOOPFREQUENCY));
			ReceiveMailUtil.receiveInImapWithFilterBy139(new MailFilterFactory.Builder()//
					.setSubject("Jks2自动测试")//
					.setSender("李杰")//
					.setTimeRange2Now(cl)//
					.build());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError();
		}
	}

	@Tips(description = "进行一些数据存储 ")
	@BeforeSuite
	public void beforeSuit() {
		
	}
}
