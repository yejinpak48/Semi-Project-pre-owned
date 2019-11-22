package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;

@WebServlet("/cancelApply.mp")
public class CancelApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelApplyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();
		String ono = request.getParameter("ono");
		int result = new MyPageService().cancelApply(ono, memberNo);
		PrintWriter out = response.getWriter();
		
		String msg = "1";
		
		if(result > 0) {
			/*request.setAttribute("msg", msg);
			request.getRequestDispatcher("views/user/mypage/orderDetails.jsp").forward(request, response);*/
			
			out.println("<script> history.go(-1); alert('취소 신청이 되었습니다.'); location.href='views/user/mypage/orderDetails.jsp';</script>");

		}else {
			request.setAttribute("msg", "주문 취소 실패!");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
