package com.kh.bvengers.manager.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;

@WebServlet("/selectProduct.mp")
public class SelectManagerProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectManagerProductListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<HashMap<String, Object>> list = new BoardService().selectProductList();
		String page = "";

		if(list != null) {
			page = "views/manager/product/productmanagement.jsp";
			request.setAttribute("list", list);
		} else {
		}
		request.getRequestDispatcher(page).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
