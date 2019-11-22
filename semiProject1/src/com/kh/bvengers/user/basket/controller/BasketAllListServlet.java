package com.kh.bvengers.user.basket.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.basket.model.service.BasketService;
import com.kh.bvengers.user.basket.model.vo.Basket;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class BasketAllListServlet
 */
@WebServlet("/basketAllList.bk")
public class BasketAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketAllListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Member m = (Member) session.getAttribute("loginUser");
		String page = "";	
		if(m!=null) {
			String userNo = m.getMemberNo();
			ArrayList<Basket>list = new BasketService().basketAllList(userNo);				
			page="views/user/basket/basket.jsp";
			request.setAttribute("list", list);
		}else {
			page="views/common/errorPagePrompt.jsp";
			request.setAttribute("msg","로그인후 이용해주세요");			
		}
		request.getRequestDispatcher(page).forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
