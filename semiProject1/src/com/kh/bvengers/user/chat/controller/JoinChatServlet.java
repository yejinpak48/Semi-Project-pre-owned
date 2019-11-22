package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.chat.model.service.ChatService;
import com.kh.bvengers.user.chat.model.vo.Chat;

@WebServlet("/joinChat.ch")
public class JoinChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JoinChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));

		Chat ch = new ChatService().joinChat(no);

		String page = "";

		if(ch!=null) {
			page = "views/user/mypage/chat.jsp";
			request.setAttribute("ch", ch);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
