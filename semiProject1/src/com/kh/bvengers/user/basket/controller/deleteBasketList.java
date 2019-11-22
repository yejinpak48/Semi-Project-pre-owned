package com.kh.bvengers.user.basket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.bvengers.user.basket.model.service.BasketService;
import com.kh.bvengers.user.basket.model.vo.Basket;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class deleteBasketList
 */
@WebServlet("/deleteList.bk")
public class deleteBasketList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBasketList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] code =request.getParameter("code").split(",");
		HttpSession session = request.getSession();		
		Member m = (Member) session.getAttribute("loginUser");
		String userNo = m.getMemberNo();
		ArrayList<Basket> list = new BasketService().deleteBasketList(code,userNo);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(list,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
