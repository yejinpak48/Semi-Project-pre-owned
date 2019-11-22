package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;

@WebServlet("/changePwd.me")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePwdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberPwd = (String) request.getParameter("password");
		String memberId = (String) request.getSession().getAttribute("sessionId");

		System.out.println(memberId);
		System.out.println(memberPwd);
		
		int result = new MemberService().changePwd(memberId,memberPwd);
		String page ="";
		if(result>0) {
			request.getSession().invalidate();
			page = "/index.jsp";
		}else {
			request.setAttribute("msg", "비밀번호 바꾸기 실패");
			page="views/common/errorPagePrompt.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
