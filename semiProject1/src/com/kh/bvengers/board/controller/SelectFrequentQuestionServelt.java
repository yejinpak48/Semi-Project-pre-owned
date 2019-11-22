package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.board.model.vo.BoardPageInfo;
import com.kh.bvengers.user.member.model.vo.Member;


/**
 * Servlet implementation class SelectFrequentQuestionServelt
 */
@WebServlet("/sfqs.qo")
public class SelectFrequentQuestionServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFrequentQuestionServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int limit = 5;
		
		int currentPage1;
		int limit1;
		int maxPage1;
		int startPage1;
		int endPage1;
		
		int num = 5;

		String page = "";
		String uno = ((Member)(request.getSession().getAttribute("loginUser"))).getMemberNo();
		currentPage1 = 1;
		
		limit1 = 10;

		int listCount = new BoardService().getListQandACount(num,uno);
		
		if(request.getParameter("currentPage") != null) {
			currentPage1 = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		maxPage1 = (int)((double)listCount / limit + 0.9);
		
		startPage1 = (((int)((double)currentPage1 / limit + 0.9))-1) * 10 + 1;

		
		endPage1 = startPage1 + 10 - 1;	
		
		if(maxPage1 < endPage1) {
			endPage1 = maxPage1;
		}
		
		BoardPageInfo pi = new BoardPageInfo(currentPage1, listCount, limit
		, maxPage1, startPage1, endPage1);
	      ArrayList<Board> list = new BoardService().selectQandAList(currentPage1, limit, num,uno);
	      
	      
	      if (list != null) {
	         request.setAttribute("list", list);
	         request.setAttribute("pi", pi);
	      } else {
	         page= "views/common/errorPage.jsp";
	         request.setAttribute("msg", "게시판 조회 실패!");
	      }
	
		ArrayList<Board>List = new BoardService().selectQuestionList(limit);
	

			page = "views/user/serviceCenter/qna.jsp";
			request.setAttribute("List", List);
			request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
