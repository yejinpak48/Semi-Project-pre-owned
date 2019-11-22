package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Member;

@WebServlet("/deleteMember.mp")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberId = loginUser.getMemberId();



		int result = new MemberService().deleteMember(memberId);

		if(result > 0) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			request.setAttribute("msg", "회원가입이 불가능합니다.");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
