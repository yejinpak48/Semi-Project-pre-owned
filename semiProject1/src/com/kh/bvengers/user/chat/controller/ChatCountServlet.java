package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.bvengers.user.chat.model.service.ChatService;
import com.kh.bvengers.user.chat.model.vo.Chat;

/**
 * Servlet implementation class ChatCountServlet
 */
@WebServlet("/chatCount.ch")
public class ChatCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChatCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Chat> list = new ChatService().chatCount();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		int count = list.size();
		System.out.println(count);
		new Gson().toJson(count, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
