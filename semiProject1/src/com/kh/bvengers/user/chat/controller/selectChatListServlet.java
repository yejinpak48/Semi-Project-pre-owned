package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.chat.model.service.ChatService;
import com.kh.bvengers.user.chat.model.vo.Chat;

@WebServlet("/chatList.ch")
public class selectChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public selectChatListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Chat> chList = new ChatService().selectChatList();
		String page = "";

		if(chList!=null) {
			page = "views/manager/chat/chatList.jsp";
			request.setAttribute("chList", chList);
			request.getRequestDispatcher(page).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
