package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.BoardPageInfo;
import com.kh.bvengers.board.model.vo.Calculate;

/**
 * Servlet implementation class ProductManagementServlet
 */
@WebServlet("/productManagement")
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArrayList<Calculate> list = new BoardService().paymentManagement();
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//작성 글 증가 시 5~10까지 추가
		limit = 10;
		
		int listCount = new BoardService().getListCount();
		
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9))-1)*10+1;
		
		endPage = startPage + 10 -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		BoardPageInfo bi = new BoardPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Calculate> list = new BoardService().paymentManagement(currentPage, limit);
		
		String page = "";
		limit = 10;
		if(list != null) {
			page = "views/manager/product/delivery.jsp";
			request.setAttribute("list", list);
			request.setAttribute("bi", bi);
			request.setAttribute("limit", limit);
		}else {
			page = "views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "조회 실패!");
		}
		
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
