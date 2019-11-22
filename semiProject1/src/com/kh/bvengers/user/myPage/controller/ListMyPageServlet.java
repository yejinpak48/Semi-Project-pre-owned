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
import com.kh.bvengers.user.myPage.model.vo.myPage;
import com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo;


@WebServlet("/listMyPage.mp")
public class ListMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListMyPageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int currentPage;		//현재 페이지를 표시할 변수
		int limit;				//한 페이지에 보여질 게시물 수
		int maxPage;			//전체 페이지에서 가장 마지막 페이지
		int startPage;			//한 번에 표시될 페이징 버튼이 시작할 번호
		int endPage;			//한 번에 표시될 페이징 버튼이 끝나는 번호

		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();

		//게시판은 1페이지부터 시작함
		currentPage = 1;

		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		//한 페이지에 보여질 목록 갯수
		limit = 10;

		//전체 목록 갯수를 리턴받음
		int listCount = new MyPageService().getListCount(memberNo);


		maxPage = (int)((double)listCount / limit + 0.9);

		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;

		endPage = startPage + 10 - 1;

		if(maxPage < endPage) {
			endPage = maxPage;
		}

		MyPagePageInfo pi =
				new MyPagePageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

		String page = "";

		ArrayList<myPage> mplist = new MyPageService().selectMyPageList(memberNo, currentPage, limit);

    	int ready = 0;
    	int start = 0;
    	int success = 0;

		if(mplist != null) {

			for(myPage m : mplist) {

				if(m.getRefundStatus() == null){
					if(m.getDstatus() != null) {
						if(m.getPayStatus().equals("2")) {
							m.setPstatus("결제 취소");
						}else if(m.getDstatus().equals("1")) {
							m.setPstatus("배송 준비중");
							ready += 1;
						}else if(m.getDstatus().equals("2")) {
							m.setPstatus("배송 중");
							start += 1;
						}else if(m.getDstatus().equals("3")){
							m.setPstatus("배송 완료");
							success += 1;
						}
					}
			}else {

				if(m.getRefundStatus().equals("1")) {
					m.setPstatus("환불 대기");
				}else if(m.getRefundStatus().equals("2")) {
					m.setPstatus("환불 완료");
				}else if(m.getRefundStatus().equals("3")) {
					m.setPstatus("환불 취소");
				}
			}


			}
			request.setAttribute("ready", ready+"");
			request.setAttribute("start", start+"");
			request.setAttribute("success", success+"");

			page = "views/user/mypage/myPage.jsp";//회원리스트나올페이지
			request.setAttribute("mplist", mplist);
			request.setAttribute("pi", pi);
		}else {
			page="views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
