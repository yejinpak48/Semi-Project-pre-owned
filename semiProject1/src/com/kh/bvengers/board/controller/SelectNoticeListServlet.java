package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.board.model.vo.BoardPageInfo;


/**
 * Servlet implementation class SelectNoticeListServlet
 */
@WebServlet("/selectNotice.no")
public class SelectNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectNoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

		//1페이지부터 시작
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		currentPage1 = 1;
		if(request.getParameter("currentPage1") != null) {
			currentPage1 = Integer.parseInt(request.getParameter("currentPage1"));
		}

		limit = 5;
		limit1 = 10;
		int[] array = new int[2];
		//전체 목록 개수 리턴
		array = new BoardService().getListCount(num);

			int notice = array[0];
			int message = array[1];

		maxPage = (int)((double)notice/ limit+0.9);
		maxPage1 = (int)((double)message/ limit1+0.9);

		startPage = (((int)((double)currentPage / limit + 0.9))-1) * 10 + 1;
		startPage1 = (((int)((double)currentPage1 / limit1 + 0.9))-1) * 10 + 1;

		endPage = startPage + 10 -1 ;
		endPage1 = startPage1 + 10 -1 ;

		if(maxPage < endPage) {
			endPage = maxPage;
		}

		if(maxPage1 < endPage1) {
			endPage1 = maxPage1;
		}

		BoardPageInfo pi = new BoardPageInfo(currentPage, notice, limit, maxPage, startPage, endPage);
		BoardPageInfo pi1 = new BoardPageInfo(currentPage1, message, limit1, maxPage1, startPage1, endPage1);

		ArrayList<Board> list = new BoardService().selectList(currentPage, limit);
		ArrayList<Board> list1 = new BoardService().selectList1(currentPage1, limit1);

		String page = "";
		String page1 = "";


		if(list != null) {
			page = "views/user/board/board.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			page = "views/user/board/board.jsp";
			request.setAttribute("msg", "게시판 조회 실패");
		}


		if(list1 != null) {
			page1 = "views/user/board/board.jsp";
			request.setAttribute("list1", list1);
			request.setAttribute("pi1", pi1);
		}else {
			page1= "views/user/board/board.jsp";
			request.setAttribute("msg", "게시판 조회 실패");
		}


		request.getRequestDispatcher(page1).forward(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}











