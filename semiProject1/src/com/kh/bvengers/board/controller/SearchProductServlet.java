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

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/search.pd")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		String value = request.getParameter("searchProduct");
		ArrayList<HashMap<String, Object>> list = null;
		if (search.equals("title")) {
			list = new BoardService().searchProductByTitle(value);
		} else if (search.equals("category")) {
			list = new BoardService().searchProductByCategory(value);
		} else if (search.equals("content")) {
			list = new BoardService().searchProductByContent(value);
		}
		String page = "";
		if (list != null) {
			page = "/views/user/product/productList.jsp";
			request.setAttribute("list", list);
		} else {
			page = "/views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "게시판 조회 실패!!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
