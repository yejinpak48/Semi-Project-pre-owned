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

@WebServlet("/cancelDate.mp")
public class CancelDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelDateServlet() {
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
    	String start = request.getParameter("start");
    	String end = request.getParameter("end");
    	String currentPage1 = request.getParameter("currentPage");
    	
    	currentPage = 1;
    	
    	if(currentPage1 == null) {
    		currentPage = 1;
    	}else {
    		currentPage = Integer.parseInt(currentPage1);
    	}
    	
    	limit = 10;
    	
    	int listCount = new MyPageService().getCancelDateLookCount(memberNo, start, end, currentPage, limit);
    	
    	maxPage = (int)((double)listCount / limit + 0.9);
    	startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * 10 + 1;
    	endPage = startPage + 10 - 1;
    	
    	if(maxPage < endPage) {
    		endPage = maxPage;
    	}
    	
    	MyPagePageInfo pi =
    			new MyPagePageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
    	
    	String page = "";
    	
    	HashMap<String,Object> hmap = null;
    	
    	if(start != null && end != null) {
    		hmap = new HashMap<String,Object>();
    		ArrayList<myPage> dateList = new MyPageService().cancelDateList(memberNo, start, end, currentPage, limit);
    		
    		for(myPage m : dateList) {
				
    			if(m.getPayStatus() != null) {
    			if(m.getPayStatus().equals("2")) {
    				m.setPayStatus("결제 취소");
    			}
    			
    			}
    				
			}
    		
    		hmap.put("pi", pi);
			hmap.put("dateList", dateList);
    	}
    	
    	response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(hmap,response.getWriter());
    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
