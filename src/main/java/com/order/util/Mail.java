package com.order.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	/**
	 * 发送邮件
	 * @param receiver 接收的邮箱
	 * @param content 邮件验证码
	 */
	public static void send(String receiver, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.sina.com.cn"); // 指定smtp服务器
		props.put("mail.smtp.auth", true); // 指定是否需要smtp验证

		Session session = Session.getDefaultInstance(props);

		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress("parknshopcheck@sina.com")); // 设置发件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject("parknshopcheck"); // 设置主题
			message.setContent("Your check code is " + content, "text/html;charset=utf-8");
			Transport transport = session.getTransport("smtp"); // 以smtp
																// 协议实例化Transport对象
			transport.connect("smtp.sina.com.cn", "parknshopcheck@sina.com", "parknshopcheck"); // 邮箱连接
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	public static void main(String[] args) {
//		send("1621250794@qq.com","23456");
//	}
}
