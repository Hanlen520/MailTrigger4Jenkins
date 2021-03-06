package com.cmic.PreMailCheck2TriggerJks.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SearchTerm;

import com.cmic.PreMailCheck2TriggerJks.App;
import com.cmic.PreMailCheck2TriggerJks.Tips;
import com.cmic.PreMailCheck2TriggerJks.bean.Device;
import com.cmic.PreMailCheck2TriggerJks.bean.TestInfo;

@Tips(description = "接收邮件的工具类")
public class ReceiveMailUtil {

	public static void receiveInImapWithFilterBy139(SearchTerm st) throws Exception {
		Properties properties = new Properties();

		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.transport.protocol", "imap"); // 使用的协议
		properties.setProperty("mail.imap.port", "993");
		properties.setProperty("mail.imap.socketFactory.port", "993");

		Session session = Session.getDefaultInstance(properties);
		// 获取连接
		session.setDebug(false);
		Store store = session.getStore("imap");
		// 登陆认证
		store.connect("imap.139.com", App.SENDER_MAIL, App.RECEIVE_GRANT_CODE);
		// 获得收件箱
		Folder folder = store.getFolder("INBOX");
		// Folder.READ_ONLY：只读权限 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
		folder.open(Folder.READ_WRITE); // 打开收件箱

		// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
		System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

		// 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
		System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
		System.out.println("新邮件: " + folder.getNewMessageCount());

		// 获得收件箱中的邮件总数
		System.out.println("邮件总数: " + folder.getMessageCount());

		// 得到收件箱中的所有邮件,并解析
		Message[] messages = folder.search(st);
		int mailCounts = messages.length;
		LogUtil.i("初步过滤到{}封目标主题邮件!", mailCounts);
		// 解析
		parseMessage(messages);
		// 释放资源
		folder.close(true);
		store.close();
	}

	public static void receiveInImapWithFilterByQQ(SearchTerm st) throws Exception {
		Properties properties = new Properties();

		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.transport.protocol", "imap"); // 使用的协议
		properties.setProperty("mail.imap.port", "993");
		properties.setProperty("mail.imap.socketFactory.port", "993");

		Session session = Session.getDefaultInstance(properties);
		// 获取连接
		session.setDebug(false);
		Store store = session.getStore("imap");
		// 登陆认证
		store.connect("imap.qq.com", App.SENDER_MAIL, App.RECEIVE_GRANT_CODE);
		// 获得收件箱
		Folder folder = store.getFolder("INBOX");
		// Folder.READ_ONLY：只读权限 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
		folder.open(Folder.READ_WRITE); // 打开收件箱

		// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
		System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

		// 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
		System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
		System.out.println("新邮件: " + folder.getNewMessageCount());

		// 获得收件箱中的邮件总数
		System.out.println("邮件总数: " + folder.getMessageCount());

		// 得到收件箱中的所有邮件,并解析
		Message[] messages = folder.search(st);
		int mailCounts = messages.length;
		System.out.println("搜索过滤到" + mailCounts + " 封符合条件的邮件！");
		// 解析
		parseMessage(messages);
		// 释放资源
		folder.close(true);
		store.close();
	}

	/**
	 * 接收邮件通过IMap协议
	 */
	public static void receiveInIMap() throws Exception {

		Properties properties = new Properties();

		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.transport.protocol", "imap"); // 使用的协议
		properties.setProperty("mail.imap.port", "993");
		properties.setProperty("mail.imap.socketFactory.port", "993");

		Session session = Session.getDefaultInstance(properties);
		// 获取连接
		session.setDebug(false);
		Store store = session.getStore("imap");
		// 登陆认证
		store.connect("imap.qq.com", App.SENDER_MAIL, App.RECEIVE_GRANT_CODE);

		// 获得收件箱
		Folder folder = store.getFolder("INBOX");
		/*
		 * Folder.READ_ONLY：只读权限 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
		 */
		folder.open(Folder.READ_WRITE); // 打开收件箱

		// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
		System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

		// 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
		System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
		System.out.println("新邮件: " + folder.getNewMessageCount());

		// 获得收件箱中的邮件总数
		System.out.println("邮件总数: " + folder.getMessageCount());

		// 得到收件箱中的所有邮件,并解析
		Message[] messages = folder.getMessages();
		parseMessage(messages);

		// 得到收件箱中的所有邮件并且删除邮件
		// deleteMessage(messages);

		// 释放资源
		folder.close(true);
		store.close();
	}

	/**
	 * 接收邮件通过Pop3协议
	 */
	public static void receiveInPop3() throws Exception {

		Properties properties = new Properties();
		properties.setProperty("mail.pop3.host", "pop.qq.com"); // 按需要更改
		properties.setProperty("mail.pop3.port", "995");
		// SSL安全连接参数
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "true");
		properties.setProperty("mail.pop3.socketFactory.port", "995");

		Session session = Session.getDefaultInstance(properties);
		Store store = session.getStore("pop3");
		store.connect("pop.qq.com", App.SENDER_MAIL, App.RECEIVE_GRANT_CODE);

		// 获得收件箱
		Folder folder = store.getFolder("INBOX");
		/*
		 * Folder.READ_ONLY：只读权限 Folder.READ_WRITE：可读可写（可以修改邮件的状态）
		 */
		folder.open(Folder.READ_WRITE); // 打开收件箱

		// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
		System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

		// 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
		System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
		System.out.println("新邮件: " + folder.getNewMessageCount());

		// 获得收件箱中的邮件总数
		System.out.println("邮件总数: " + folder.getMessageCount());

		// 得到收件箱中的所有邮件,并解析
		Message[] messages = folder.getMessages();
		parseMessage(messages);

		// 得到收件箱中的所有邮件并且删除邮件
		// deleteMessage(messages);

		// 释放资源
		folder.close(true);
		store.close();
	}

	/**
	 * 解析邮件
	 * 
	 * @param messages
	 *            要解析的邮件列表
	 * @throws Exception
	 */
	public static void parseMessage(Message... messages) throws Exception {
		if (messages == null || messages.length < 1)
			LogUtil.e("未找到要解析的邮件!");
		Message[] resultMsg = sentDateFilter(messages);
		// 解析所有邮件
		if (resultMsg.length <= 0) {
			LogUtil.e("未找到要符合发送期限内的邮件!");
			// 只是为了关闭Jenkins的工作流构建，抛出了异常
			throw new RuntimeException("未找到要符合发送期限内的邮件!");
		} else {
			LogUtil.w("实际找到{}封符合条件的邮件", resultMsg.length);
		}
		Properties proSave = new Properties();
		for (int i = 0; i < resultMsg.length; i++) {
			// 初始化保存的文件
			proSave.clear();
			// 读取邮件
			MimeMessage msg = (MimeMessage) resultMsg[i];
			System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
			String mailSubject = getSubject(msg);
			System.out.println("主题: " + mailSubject);
			proSave.setProperty("TESTMAIL_SUBJECT", mailSubject);
			String mailSender = getFrom(msg);
			// 发送者串格式化
			String formatSender = mailSender.substring(mailSender.indexOf("<") + 1, mailSender.indexOf(">"));
			System.out.println("发件人: " + formatSender);
			proSave.setProperty("TESTMAIL_SENDER", formatSender);
			System.out.println("收件人：" + getReceiveAddress(msg, null));
			String sentTime = getSentDate(msg, null);
			proSave.setProperty("TESTMAIL_SENTTIME", sentTime);
			//
			System.out.println("发送时间：" + sentTime);
			System.out.println("是否已读：" + isSeen(msg));
			System.out.println("邮件优先级：" + getPriority(msg));
			System.out.println("是否需要回执：" + isReplySign(msg));
			System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");
			boolean isContainerAttachment = isContainAttachment(msg);
			System.out.println("是否包含附件：" + isContainerAttachment);
			// 暂存并解压
			if (isContainerAttachment) {// 保存附件
				if (App.ATTACHMENT_SAVE_DIR.isEmpty()) {// 默认路径
					saveAttachment(msg, "D:\\Jenkins\\TestAutomation\\Parpare\\mailResultSave\\");
				} else {
					if (!App.ATTACHMENT_SAVE_DIR.endsWith(File.separator)) {
						saveAttachment(msg, App.ATTACHMENT_SAVE_DIR + File.separator);
					} else {
						saveAttachment(msg, App.ATTACHMENT_SAVE_DIR);
					}
				}
			}
			StringBuilder content = new StringBuilder();
			getMailTextContent(msg, content);
			System.out.println("邮件正文如下:");
			int indexStart = content.indexOf("#ConfigStartFromHere");
			int indexEnd = content.indexOf("#ConfigEndInHere");
			if (indexEnd > 0 && indexStart >= 0) {
				String yamlString = content.substring(indexStart, indexEnd);
				LogUtil.i(yamlString);
				// 得到配置邮件
				TestInfo testInfo = YamlUtil.yaml2Bean(yamlString, TestInfo.class);// 目前只支持单个设备测试
				Device deviceInfo = testInfo.getTestDeviceList()[0];
				LogUtil.w("测试目标设备为{},型号为{}", deviceInfo.getDeviceName(), deviceInfo.getDeviceModelName());
				// 目前只保存以下两个属性的值
				proSave.setProperty("DEVICENAME", deviceInfo.getDeviceName());
				proSave.setProperty("DEVICEMODEL", deviceInfo.getDeviceModelName());

				StringBuilder subscribeList = new StringBuilder();
				for (int j = 0; j < testInfo.getTestSubscriber().length; j++) {
					subscribeList.append(testInfo.getTestSubscriber()[j]);
					subscribeList.append(",");
				}
				proSave.setProperty("REPORTSUBSCRIBER",
						subscribeList.deleteCharAt(subscribeList.length() - 1).toString());

			} else {
				LogUtil.w("该自动化测试配置邮件模板有误{},{}", indexStart, indexEnd);
			}
			// 保存流
			if (new File(App.SHAREPROPERTYPATH).isFile()) {
				if (!new File(App.SHAREPROPERTYPATH).exists()) {
					new File(App.SHAREPROPERTYPATH).mkdirs();
					new File(App.SHAREPROPERTYPATH).createNewFile();
				}
				FileOutputStream out = new FileOutputStream(App.SHAREPROPERTYPATH);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
				proSave.store(bw, "Commit4Jenkins");
				out.close();
			}
			System.out.println();
			System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");
			System.out.println();
		}

	}

	private static Message[] sentDateFilter(Message[] messages) throws MessagingException {
		List<Message> filterResult = new ArrayList<Message>();
		for (int i = 0; i < messages.length; i++) {
			Date receivedDate = messages[i].getSentDate();
			if (receivedDate == null) {
				LogUtil.e("获取第{}封目标邮件{}失败", messages[i].getMessageNumber(), messages[i].getSubject());
			} else if (receivedDate.after(MailFilterFactory.mDateRange)) {
				filterResult.add(messages[i]);
			}
		}
		Message[] msgArray = new Message[filterResult.size()];
		filterResult.toArray(msgArray);
		return msgArray;
	}

	@Tips(description = "由于无法存在SentDate过滤精度只能到达日期，折衷的方案", riskPoint = "不确定性")
	private static boolean go2FilterBySentTime(MimeMessage msg) throws MessagingException {
		Date receivedDate = msg.getSentDate();
		if (receivedDate == null) {
			System.err.println("获取邮件发送时间失败");
			return false;
		} else {
			return receivedDate.after(MailFilterFactory.mDateRange);
		}
	}

	/**
	 * 解析邮件
	 * 
	 * @param messages
	 *            要解析的邮件列表
	 */
	public static void deleteMessage(Message... messages) throws MessagingException, IOException {
		if (messages == null || messages.length < 1)
			throw new MessagingException("未找到要解析的邮件!");

		// 解析所有邮件
		for (int i = 0, count = messages.length; i < count; i++) {

			/**
			 * 邮件删除
			 */
			Message message = messages[i];
			String subject = message.getSubject();
			// set the DELETE flag to true
			message.setFlag(Flags.Flag.DELETED, true);
			System.out.println("Marked DELETE for message: " + subject);

		}
	}

	/**
	 * 获得邮件主题
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 解码后的邮件主题
	 */
	public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
		return MimeUtility.decodeText(msg.getSubject());
	}

	/**
	 * 获得邮件发件人
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 姓名 <Email地址>
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
		String from = "";
		Address[] froms = msg.getFrom();
		if (froms.length < 1)
			throw new MessagingException("没有发件人!");

		InternetAddress address = (InternetAddress) froms[0];
		String person = address.getPersonal();
		if (person != null) {
			person = MimeUtility.decodeText(person) + " ";
		} else {
			person = "";
		}
		from = person + "<" + address.getAddress() + ">";

		return from;
	}

	/**
	 * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
	 * <p>
	 * Message.RecipientType.TO 收件人
	 * </p>
	 * <p>
	 * Message.RecipientType.CC 抄送
	 * </p>
	 * <p>
	 * Message.RecipientType.BCC 密送
	 * </p>
	 * 
	 * @param msg
	 *            邮件内容
	 * @param type
	 *            收件人类型
	 * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
	 * @throws MessagingException
	 */
	public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
		StringBuffer receiveAddress = new StringBuffer();
		Address[] addresss = null;
		if (type == null) {
			addresss = msg.getAllRecipients();
		} else {
			addresss = msg.getRecipients(type);
		}

		if (addresss == null || addresss.length < 1)
			throw new MessagingException("没有收件人!");
		for (Address address : addresss) {
			InternetAddress internetAddress = (InternetAddress) address;
			receiveAddress.append(internetAddress.toUnicodeString()).append(",");
		}

		receiveAddress.deleteCharAt(receiveAddress.length() - 1); // 删除最后一个逗号

		return receiveAddress.toString();
	}

	/**
	 * 获得邮件发送时间
	 * 
	 * @param msg
	 *            邮件内容
	 * @return yyyy年mm月dd日 星期X HH:mm
	 * @throws MessagingException
	 */
	public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
		Date receivedDate = msg.getSentDate();
		if (receivedDate == null)
			return "";

		if (pattern == null || "".equals(pattern))
			pattern = "yyyy年MM月dd日HH时mm分";

		return new SimpleDateFormat(pattern).format(receivedDate);
	}

	/**
	 * 判断邮件中是否包含附件
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 邮件中存在附件返回true，不存在返回false
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
		boolean flag = false;
		if (part.isMimeType("multipart/*")) {
			MimeMultipart multipart = (MimeMultipart) part.getContent();
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					flag = true;
				} else if (bodyPart.isMimeType("multipart/*")) {
					flag = isContainAttachment(bodyPart);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("application") != -1) {
						flag = true;
					}

					if (contentType.indexOf("name") != -1) {
						flag = true;
					}
				}

				if (flag)
					break;
			}
		} else if (part.isMimeType("message/rfc822")) {
			flag = isContainAttachment((Part) part.getContent());
		}
		return flag;
	}

	/**
	 * 判断邮件是否已读
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 如果邮件已读返回true,否则返回false
	 * @throws MessagingException
	 */
	public static boolean isSeen(MimeMessage msg) throws MessagingException {
		return msg.getFlags().contains(Flags.Flag.SEEN);
	}

	/**
	 * 判断邮件是否需要阅读回执
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 需要回执返回true,否则返回false
	 * @throws MessagingException
	 */
	public static boolean isReplySign(MimeMessage msg) throws MessagingException {
		boolean replySign = false;
		String[] headers = msg.getHeader("Disposition-Notification-To");
		if (headers != null)
			replySign = true;
		return replySign;
	}

	/**
	 * 获得邮件的优先级
	 * 
	 * @param msg
	 *            邮件内容
	 * @return 1(High):紧急 3:普通(Normal) 5:低(Low)
	 * @throws MessagingException
	 */
	public static String getPriority(MimeMessage msg) throws MessagingException {
		String priority = "普通";
		String[] headers = msg.getHeader("X-Priority");
		if (headers != null) {
			String headerPriority = headers[0];
			if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)
				priority = "紧急";
			else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)
				priority = "低";
			else
				priority = "普通";
		}
		return priority;
	}

	/**
	 * 获得邮件文本内容
	 * 
	 * @param part
	 *            邮件体
	 * @param content
	 *            存储邮件文本内容的字符串
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void getMailTextContent(Part part, StringBuilder content) throws MessagingException, IOException {
		// 如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
		if (part.isMimeType("text/*") && !isContainTextAttach) {
			content.append(part.getContent().toString());
		} else if (part.isMimeType("message/rfc822")) {
			getMailTextContent((Part) part.getContent(), content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				getMailTextContent(bodyPart, content);
			}
		}
	}

	/**
	 * 保存附件
	 * 
	 * @param part
	 *            邮件中多个组合体中的其中一个组合体
	 * @param destDir
	 *            附件保存目录
	 * @throws Exception
	 */
	public static void saveAttachment(Part part, String destDir) throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent(); // 复杂体邮件
			// 复杂体邮件包含多个邮件体
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				// 获得复杂体邮件中其中一个邮件体
				BodyPart bodyPart = multipart.getBodyPart(i);
				// 某一个邮件体也有可能是由多个邮件体组成的复杂体
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					InputStream is = bodyPart.getInputStream();
					saveFile(is, destDir, decodeText(bodyPart.getFileName()));
					String saveDir = destDir + File.separator + decodeText(bodyPart.getFileName());
					// 另存为构建物体
					if (!new File("apps").exists()) {// 另存路径
						new File("apps").mkdirs();
					}
					File archiveDir = new File("apps" + File.separator + decodeText(bodyPart.getFileName()));
					if (archiveDir.isFile()) {
						archiveDir.delete();
					}
					LogUtil.i("构建物另存为{}", archiveDir.getAbsolutePath());
					Files.copy(new File(saveDir).toPath(), new FileOutputStream(archiveDir.getAbsolutePath()));
					// 解压
					DeCompressUtil.unzip(destDir + File.separator + decodeText(bodyPart.getFileName()),
							destDir + File.separator + "RawAttachment", false);
				} else if (bodyPart.isMimeType("multipart/*")) {
					saveAttachment(bodyPart, destDir);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
						saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachment((Part) part.getContent(), destDir);
		}
	}

	/**
	 * 读取输入流中的数据保存至指定目录
	 * 
	 * @param is
	 *            输入流
	 * @param fileName
	 *            文件名
	 * @param destDir
	 *            文件存储目录
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void saveFile(InputStream is, String destDir, String fileName) {
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destDir + fileName)));
			int len = -1;
			while ((len = bis.read()) != -1) {
				bos.write(len);
				bos.flush();
			}
			bos.close();
			bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文本解码
	 * 
	 * @param encodeText
	 *            解码MimeUtility.encodeText(String text)方法编码后的文本
	 * @return 解码后的文本
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeText(String encodeText) throws UnsupportedEncodingException {
		if (encodeText == null || "".equals(encodeText)) {
			return "";
		} else {
			return MimeUtility.decodeText(encodeText);
		}
	}

}
