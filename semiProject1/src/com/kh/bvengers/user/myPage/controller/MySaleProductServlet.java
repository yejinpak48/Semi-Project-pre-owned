package com.kh.bvengers.user.myPage.controller;

import static com.kh.bvengers.common.JDBCTemplate.close;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


@WebServlet("/mySaleProduct.mp")
public class MySaleProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MySaleProductServlet() {
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
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit = 10;
		
		int listCount = new MyPageService().getSaleListCount(memberNo);
	
		maxPage = (int)((double)listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		MyPagePageInfo pi =
				new MyPagePageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		String page = "";
		
		ArrayList<myPage> sList = new MyPageService().selectSaleList(memberNo, currentPage, limit);
		
		
		if(sList != null) {
			
			for(myPage m : sList) {
				
				if(m.getaStatus() == null) {
					if(m.getPayStatus() != null && m.getPayStatus().equals("1")) {
						m.setPayStatus("Y");
						m.setaStatus("N");
					}else {
						m.setPayStatus("N");
						m.setaStatus("N");
					}
					
				}else {
					
					if(m.getaStatus().equals("2")) {
						m.setaStatus("Y");
						m.setPayStatus("Y");
					}else {
						m.setaStatus("N");
						m.setPayStatus("Y");
						
					
					}
				}
				
			}
			
			page = "views/user/mypage/mySaleProduct.jsp";
			request.setAttribute("sList", sList);
			request.setAttribute("pi", pi);
			
		}else {
			
			page = "views/common/errorPrompt.jsp";
			request.setAttribute("msg", "조회 실패");
			
		}
		request.getRequestDispatcher(page).forward(request, response);
		
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
