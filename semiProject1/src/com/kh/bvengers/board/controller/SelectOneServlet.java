package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Attachment;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.product.model.vo.Product;

/**
 * Servlet implementation class SelectOneServlet
 */
@WebServlet("/selectOne.pd")
public class SelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectOneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		HashMap<String, Object> hmap = new BoardService().selectOneProduct(num);
		Board b = (Board) hmap.get("board");
		Product p = (Product) hmap.get("product");
		ArrayList<Attachment> fileList = (ArrayList<Attachment>) hmap.get("attachment");
		String page = "";

		if(hmap != null) {
			page = "views/user/product/productDetails.jsp";
			request.setAttribute("b", b);
			request.setAttribute("p", p);
			request.setAttribute("fileList", fileList);
		}
		request.getRequestDispatcher(page).forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
