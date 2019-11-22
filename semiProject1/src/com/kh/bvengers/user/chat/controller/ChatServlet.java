package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.chat.model.service.ChatService;

@WebServlet("/chat.ch")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChatServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		int result = new ChatService().selectChat(no);

		if(result > 0) {
			request.setAttribute("no", no);
			request.getRequestDispatcher("joinChat.ch").forward(request, response);
		} else {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
