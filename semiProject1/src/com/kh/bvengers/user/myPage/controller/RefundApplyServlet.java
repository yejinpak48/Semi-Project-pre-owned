package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;

@WebServlet("/refundApply.mp")
public class RefundApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefundApplyServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();
		String ono = request.getParameter("ono");
		int result = new MyPageService().refundApply(ono, memberNo);
		
		if(result > 0) {
			
			response.sendRedirect("views/user/mypage/returnAddress.jsp");
			
		}else {

			request.setAttribute("msg", "실패!");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
