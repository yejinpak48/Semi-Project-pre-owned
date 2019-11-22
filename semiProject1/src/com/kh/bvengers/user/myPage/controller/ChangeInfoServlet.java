package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Member;

@WebServlet("/changeInfo.me")
public class ChangeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String memberId = request.getParameter("memberId");
		String MemberPassword = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String add1 = request.getParameter("address1");
		String add2 = request.getParameter("address2");
		String add3 = request.getParameter("address3");
		String address = add1+"$"+add2+"$"+add3;

		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPassword(MemberPassword);
		m.setMemberName(memberName);
		m.setEmail(email);
		m.setAddress(address);
		m.setPhone(phone);
		
		int result = new MemberService().changeMember(m);
		
		String page = "";
		
		if(result > 0) {

			request.setAttribute("m", m);
			//response.sendRedirect("/sp/views/user/mypage/changeInfo2.jsp");
			
			page = "views/user/mypage/changeInfo2.jsp";
			
		}else {
			request.setAttribute("msg","회원정보변경 실패!");
			request.getRequestDispatcher("/views/common/errorPagePrompt.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
