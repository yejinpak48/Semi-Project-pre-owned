package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.service.MemberService;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.member.model.vo.MemberPageInfo;

@WebServlet("/searchMember.me")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchMemberServlet() {
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

		String send = (String) request.getParameter("send");

		int idx = send.indexOf("$");

		String selecthowsearch = send.substring(0,idx);
		String searchValue = send.substring(idx+1);


		ArrayList<Member> list = new MemberService().searchMember(currentPage,limit,selecthowsearch,searchValue);
		String page = "";
		//PrintWriter out = response.getWriter();

		if(list!=null) {
			request.setAttribute("list1", list);
			request.setAttribute("pi1", pi);
			page = "views/manager/member/membermanagement.jsp";
		}else {
			request.setAttribute("msg", "목록조회실패!");
			page="/views/common/errorPagePrompt.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
		/*out.flush();
		out.close();*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
