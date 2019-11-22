package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;
import com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo;
import com.kh.bvengers.user.myPage.model.vo.myPage;

@WebServlet("/cancelLook.mp")
public class CancelLookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelLookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();
		String ono = request.getParameter("ono");
		
		currentPage = 1;
		
		if(request.getParameter("currenPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		}
		
		limit = 10;
		
		int listCount = new MyPageService().getCancelLookCount(memberNo);
		
		maxPage = (int)((double)listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		MyPagePageInfo pi =
				new MyPagePageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		String page = "";
		ArrayList<myPage> cList = new MyPageService().selectCancelLook(memberNo, ono, currentPage, limit);
		
		if(cList != null) {
			
			for(myPage m : cList) {
				
				if(m.getPayStatus().equals("2")) {
					m.setPayStatus("결제 취소");
				}
				
			}
			page = "views/user/mypage/cancelLook.jsp";
			request.setAttribute("cList", cList);
			request.setAttribute("pi", pi);
			
		}else {
			page = "views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "조회 실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
