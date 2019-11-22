package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Member;

@WebServlet("/login.me")
public class LoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sessionId = (String) request.getSession().getAttribute("sessionId");
		String memberId = request.getParameter("loginId");
		String memberPwd = request.getParameter("password");
		String sessionPass = (String) request.getSession().getAttribute("pass");
		System.out.println(sessionPass);
		String sessionID="";
		Member loginUser = null;
		if(sessionId==null) {
			loginUser = new MemberService().loginCheck(memberId,memberPwd);
			
			
			
			
		}else {
			sessionID = request.getParameter("sessionId");
			loginUser = new MemberService().loginCheck(sessionID,memberPwd);
			
			
			
		}

		if(loginUser.getMemberId().equals(sessionID) &&loginUser.getMemberPassword().equals(memberPwd) &&!loginUser.getMemberId().equals("admin")) {

			request.getRequestDispatcher("views/user/join/searchPwdResult.jsp").forward(request, response);
		}else if(loginUser.getMemberPassword().equals(memberPwd)&&loginUser.getMemberId().equals(memberId) && !loginUser.getMemberId().equals("admin")) {

			HttpSession session = request.getSession();

			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(60*60); // 세션 시간 1시간 지정

			response.sendRedirect(request.getContextPath()+"/index.jsp");

		}else if(loginUser.getMemberPassword().equals(memberPwd)&&loginUser.getMemberId().equals(memberId) && loginUser.getMemberId().equals("admin")){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath()+"/smnl.mm");
		}else if(!loginUser.getMemberPassword().equals(memberPwd)){
			request.setAttribute("msg", "잘못입력하셨습니다.");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "정지회원입니다.");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
