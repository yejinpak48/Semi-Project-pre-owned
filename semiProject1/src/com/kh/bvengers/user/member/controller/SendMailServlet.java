package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/send.me")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendMailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String receiver="";
		String receiver = request.getParameter("email");
		String hc = request.getParameter("hc");
		Boolean ch = true;

		if(hc==null) {
		hc = receiver.charAt(0)+"";
		String test = "";
		if(hc.equals("2")) {
			for(int i=1; i<receiver.length(); i++) {
				test += receiver.charAt(i);
			}
			receiver=test;

		}
		}
		final String sender = "thddl7364@naver.com";//네이버아이디쓰삼(test@naver.com)
		final String password = "song080808";//네이버비밀번호
		//인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for(int i = 0; i < 10; i++) {
			int rIndex =rnd.nextInt(3);
			switch(rIndex) {
			case 0 :
				//a-z
				temp.append((char) ((int)(rnd.nextInt(26))+97));break;
			case 1 :
				//A-Z
				temp.append((char) ((int)(rnd.nextInt(26))+65));break;
			case 2 :
				//0-9
				temp.append((rnd.nextInt(10)));break;
			}

		}
		String key = temp.toString();
		String title="";
		String contents="";
		String host="smtp.naver.com";

		if(hc.equals("2")) {
		title = "중고애민족 회원가입 인증 메일입니다.";
		contents = "인증번호는 "+key;
		}else {
		title = "중고애민족 임시 비밀번호 메일입니다.";
		contents = "임시 비밀번호는 "+key;
		}

		String page="";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", 25);


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// sender Email Address
			message.setFrom(sender);

			// Subject
			message.setSubject("[TEST-Mail-Service] " + title);

			// Text
			message.setText(contents, "UTF-8", "html");

			// send the message
			Transport.send(message);

			HttpSession ss = request.getSession();
			ss.setAttribute("password", key);
			
			if(hc.equals("1")) {
				page="/sp/checkuser.me";
			}else{
				page="views/user/join/checkMail.jsp";
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(page).forward(request,response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
