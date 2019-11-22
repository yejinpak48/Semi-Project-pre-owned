package com.kh.bvengers.user.myPage.controller;

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
import com.kh.bvengers.wrapper.LoginWrapper;

@WebServlet("/memberInfo.me")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();

	HttpSession session = request.getSession();
	Member loginUser = (Member)session.getAttribute("loginUser");
	String memberNo = loginUser.getMemberNo();
	/*String password = new LoginWrapper(request).getParameter(request.getParameter("password"));*/
	String password = request.getParameter("password");
	Member checkPwd = new MemberService().checkPwd(memberNo, password);

	if(checkPwd != null) {
		session.setAttribute("checkPwd", checkPwd);
		response.sendRedirect("/sp/views/user/mypage/changeInfo.jsp");
	}else {

	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
