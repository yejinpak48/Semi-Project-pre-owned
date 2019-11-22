package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.bvengers.user.member.model.service.MemberService;

@WebServlet("/checkuser.me")
public class CheckConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckConfirmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("userId");
		String memberName = request.getParameter("userName");
		String email = request.getParameter("email");
		
		String status = (String)request.getAttribute("status");
		PrintWriter out = response.getWriter();
		
		
		int result = new MemberService().checkuser(memberId,memberName,email);
		if(result>0) {
			
			request.setAttribute("hc", "1");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/send.me").forward(request, response);
			
			
		}else {
			
			out.append("fail");
		}
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
