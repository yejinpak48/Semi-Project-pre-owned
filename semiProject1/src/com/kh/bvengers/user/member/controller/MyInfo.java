package com.kh.bvengers.user.member.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Seller;

@WebServlet("/myInfo.me")
public class MyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		Seller s = new MemberService().searchInfo(userId);

		String page="";

		if(s!= null) {
			page = "views/user/product/sellerPopup.jsp";
			request.setAttribute("s", s);
		} else {
		}

		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
