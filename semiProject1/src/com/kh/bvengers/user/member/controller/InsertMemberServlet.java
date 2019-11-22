package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Member;

@WebServlet("/insertMember.me")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String address4 = request.getParameter("address4");
		String address = address1 +" "+ address2 +" "+ address3 +" "+ address4;
		String phone = request.getParameter("phone");
		String emailstatus = (String) request.getSession().getAttribute("emailstatus");


		Member m = new Member();
			m.setMemberId(memberId);
			m.setMemberPassword(memberPwd);
			m.setMemberName(memberName);
			m.setEmail(email);
			m.setAddress(address);
			m.setPhone(phone);

			//if(!emailstatus.equals("fail")) {
			int result = new MemberService().insertMember(m);

			if(result>0) {
				response.sendRedirect("/sp/index.jsp");
			}else {
				request.setAttribute("msg","이메일 인증에 실패하셨습니다.");
				request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
			}

			//}else {
				//request.setAttribute("msg","실패하셨습니다.");
				//request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		//	}
			}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

