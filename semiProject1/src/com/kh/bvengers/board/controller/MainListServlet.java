package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;

@WebServlet("/list.pd")
public class MainListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("value").toUpperCase();

		ArrayList<HashMap<String, Object>> list = new BoardService().mainList(value);

		String page = "";
		if (list != null) {
			page = "/views/user/product/productList.jsp";
			request.setAttribute("list", list);
		} else {
			page = "/views/common/errorPagePrompt.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
