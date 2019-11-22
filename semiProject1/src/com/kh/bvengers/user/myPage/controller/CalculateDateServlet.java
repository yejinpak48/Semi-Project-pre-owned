package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;
import com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo;
import com.kh.bvengers.user.myPage.model.vo.myPage;

@WebServlet("/calculateDate.mp")
public class CalculateDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalculateDateServlet() {
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
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		currentPage = 1;

		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		limit = 5;

		int listCount = new MyPageService().getCalculateDateCount(memberNo, start, end);

		maxPage = (int)((double)listCount / limit + 0.8);

		startPage = (((int)((double) currentPage / limit + 0.8)) - 1) * 10 + 1;

		endPage = startPage + 10 - 1;

		if(maxPage < endPage) {
			endPage = maxPage;
		}

		MyPagePageInfo pi =
				new MyPagePageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

		String page = "";

		HashMap<String, Object> hmap = null;
		
		if(start != null && end != null) {
			hmap = new HashMap<String,Object>();
			ArrayList<myPage> cList = new MyPageService().calculateDateList(memberNo, start, end, currentPage, limit);
				for(myPage m : cList) {
				
				if(m.getaStatus().equals("1")) {
					m.setaStatus("정산 대기");
				}else if(m.getaStatus().equals("2")) {
					m.setaStatus("정산 완료");
				}else if(m.getaStatus().equals("3")) {
					m.setaStatus("환불 완료");
				}
			

		}

			hmap.put("pi", pi);
			hmap.put("cList", cList);

		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(hmap,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
