package com.cmic.PreMailCheck2TriggerJks.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.cmic.PreMailCheck2TriggerJks.App;

public class SendMailUtil {

	public static void sendMailBy139(String receiverMail) throws AddressException, MessagingException {
		if (!validateMailAccount(receiverMail)) {
			throw new RuntimeException("邮箱格式不合法");
		}
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp"); // 连接协议
		properties.put("mail.smtp.host", "smtp.139.com"); // 主机名
		properties.put("mail.smtp.port", 465); // 端口号
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true"); // 设置是否使用ssl安全连接 ---一般都使用
		properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
		// 得到回话对象
		Session session = Session.getInstance(properties);
		// 获取邮件对象
		Message message = new MimeMessage(session);
		// 设置发件人邮箱地址
		message.setFrom(new InternetAddress(App.SENDER_MAIL));// 本人的邮箱
		// 设置收件人地址
		message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(receiverMail) });
		// 设置邮件标题
		message.setSubject("由JavaMail发出的测试邮件");
		// 设置邮件内容
		message.setText("内容为： 这是第一封java发送来的邮件。");
		// 得到邮差对象
		Transport transport = session.getTransport();
		// 连接自己的邮箱账户
		transport.connect(App.SENDER_MAIL, App.SENDER_GRANT_CODE);// 密码为刚才得到的授权码
		// 发送邮件
		transport.sendMessage(message, message.getAllRecipients());
	}

	public static void sendMail(String receiverMail) throws AddressException, MessagingException {
		if (!validateMailAccount(receiverMail)) {
			throw new RuntimeException("邮箱格式不合法");
		}
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp"); // 连接协议
		properties.put("mail.smtp.host", "smtp.qq.com"); // 主机名
		properties.put("mail.smtp.port", 465); // 端口号
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true"); // 设置是否使用ssl安全连接 ---一般都使用
		properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
		// 得到回话对象
		Session session = Session.getInstance(properties);
		// 获取邮件对象
		Message message = new MimeMessage(session);
		// 设置发件人邮箱地址
		message.setFrom(new InternetAddress(App.SENDER_MAIL));// 本人的邮箱
		// 设置收件人地址
		message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(receiverMail) });
		// 设置邮件标题
		message.setSubject("由JavaMail发出的测试邮件");
		// 设置邮件内容
		message.setText("内容为： 这是第一封java发送来的邮件。");
		// 得到邮差对象
		Transport transport = session.getTransport();
		// 连接自己的邮箱账户
		transport.connect(App.SENDER_MAIL, App.SENDER_GRANT_CODE);// 密码为刚才得到的授权码
		// 发送邮件
		transport.sendMessage(message, message.getAllRecipients());
	}

	/**
	 * 检验邮箱的合法性
	 * 
	 * @return
	 */
	public static boolean validateMailAccount(String targetMailAccount) {
		return targetMailAccount.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
	}
}
