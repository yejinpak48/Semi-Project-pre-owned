package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.chat.model.service.ChatService;

/**
 * Servlet implementation class UpdateChatServlet
 */
@WebServlet("/updateChat.ch")
public class UpdateChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String content = request.getParameter("content");

		int result = new ChatService().updateChat(no, content);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
