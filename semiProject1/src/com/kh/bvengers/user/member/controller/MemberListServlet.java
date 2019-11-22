package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.*;

@WebServlet("/memberList.me")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit=10;
		
		int listCount = new MemberService().getListCount();
		
		
		maxPage = (int)((double)listCount / limit+0.9);
		
		startPage = (((int)((double)currentPage/limit+0.9))-1)*10+1;
		
		endPage = startPage + 10 -1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		
		MemberPageInfo pi = new MemberPageInfo(currentPage,listCount,limit,maxPage,startPage,endPage);
		
		ArrayList<Member> list = new MemberService().selectList(currentPage,limit);
		String page ="";
		if(list!=null) {
			page = "views/manager/member/listMember.jsp";//회원리스트나올페이지
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			page="";
			request.setAttribute("msg", "실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
