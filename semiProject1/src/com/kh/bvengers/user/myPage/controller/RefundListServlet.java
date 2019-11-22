package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;
import com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo;
import com.kh.bvengers.user.myPage.model.vo.myPage;

@WebServlet("/refundList.mp")
public class RefundListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefundListServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;

		int currentPage1;
		int limit1;
		int maxPage1;
		int startPage1;
		int endPage1;

		int num = 1;

		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		currentPage1 = 1;
		if(request.getParameter("currentPage1") != null) {
			currentPage1 = Integer.parseInt(request.getParameter("currentPage1"));
		}

		limit = 5;
		limit1 = 5;
		int[] array = new int[2];
		//전체 목록 개수 리턴
		array = new MyPageService().getrcListCount(memberNo);

			int listCountR = array[0];
			int listCountC = array[1];

		maxPage = (int)((double)listCountR/ limit + 0.8);
		maxPage1 = (int)((double)listCountC/ limit1 + 0.8);

		startPage = (((int)((double)currentPage / limit + 0.8)) - 1) * 10 + 1;
		startPage1 = (((int)((double)currentPage1 / limit1 + 0.8)) -1) * 10 + 1;

		endPage = startPage + 10 -1 ;
		endPage1 = startPage1 + 10 -1 ;

		if(maxPage < endPage) {
			endPage = maxPage;
		}

		if(maxPage1 < endPage1) {
			endPage1 = maxPage1;
		}

		MyPagePageInfo pi = new MyPagePageInfo(currentPage, listCountR, limit, maxPage, startPage, endPage);
		MyPagePageInfo pi1 = new MyPagePageInfo(currentPage1, listCountC, limit1, maxPage1, startPage1, endPage1);

		ArrayList<myPage> rList = new MyPageService().selectRefundList(memberNo, currentPage, limit);
		ArrayList<myPage> cList = new MyPageService().selectCalculateList(memberNo, currentPage1, limit1);

		/*Collections.reverse(rList);
		Collections.reverse(cList);*/

		String page = "";
		String page1 = "";


		if(rList != null) {
			
			for(myPage m : rList) {
				
				if(m.getRefundStatus().equals("1")) {
					m.setRefundStatus("환불 대기");
				}else if(m.getRefundStatus().equals("2")) {
					m.setRefundStatus("환불 완료");
				}else if(m.getRefundStatus().equals("3")) {
					m.setRefundStatus("환불 취소");
				}
			}
			
			page = "views/user/mypage/refund.jsp";
			request.setAttribute("rList", rList);
			request.setAttribute("pi", pi);
		}else {
			page = "views/user/mypage/refund.jsp";
			request.setAttribute("msg", "게시판 조회 실패");
		}


		if(cList != null) {
			
			for(myPage m : cList) {
				
				if(m.getaStatus().equals("1")) {
					m.setaStatus("정산 대기");
				}else if(m.getaStatus().equals("2")) {
					m.setaStatus("정산 완료");
				}
			}
			
			page1 = "views/user/mypage/refund.jsp";
			request.setAttribute("cList", cList);
			request.setAttribute("pi1", pi1);
		}else {
			page1 = "views/user/mypage/refund.jsp";
			request.setAttribute("msg", "조회 실패");
		}

		request.getRequestDispatcher(page1).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
