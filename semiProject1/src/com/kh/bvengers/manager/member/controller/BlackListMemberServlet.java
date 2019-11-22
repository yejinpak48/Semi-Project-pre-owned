package com.kh.bvengers.manager.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.manager.member.model.service.ManagerMemberService;

@WebServlet("/blacklist.me")
public class BlackListMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlackListMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = (String) request.getParameter("memberId");
		String reason = (String) request.getParameter("reason");
		
		int result = new ManagerMemberService().blackmember(memberId,reason);
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.append("success");
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
