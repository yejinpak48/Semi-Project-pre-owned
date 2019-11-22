package com.kh.bvengers.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.Calcul;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class DisposeSuccessServlet
 */
@WebServlet("/disposeSuccess.cal")
public class DisposeSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisposeSuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] code = request.getParameter("code").split(",");
		int limit = Integer.parseInt(request.getParameter("limit"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		ArrayList<Calcul> list = new ProductService().disposeSuccess(code, currentPage, limit);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
