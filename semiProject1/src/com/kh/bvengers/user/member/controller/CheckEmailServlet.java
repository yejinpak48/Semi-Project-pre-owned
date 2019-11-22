package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkmail.me")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String status = request.getParameter("mailchek");
			String checkuser= request.getParameter("checkuser");		
					
			PrintWriter out = response.getWriter();
			if(!status.equals(checkuser)) {// 실패시
				out.append("fail");
				HttpSession session = request.getSession();
				session.setAttribute("emailstatus", "fail");
			}else {//성공시
				out.append("Success");
				request.getSession().invalidate();
			}
			out.flush();
			out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
