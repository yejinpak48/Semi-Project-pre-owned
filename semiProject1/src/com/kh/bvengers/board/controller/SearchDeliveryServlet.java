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
 * Servlet implementation class SearchDeliveryServlet
 */
@WebServlet("/searchDeli.deli")
public class SearchDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDeliveryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("curr"));
		int limit = 10;
		int maxPage;
		int startPage;
		int endPage;
		
		String selOption = null;
		String selectDate = null;
		
		if(!request.getParameter("selOption").equals("select")) {
			if(request.getParameter("selOption").equals("wait")) {
				selOption = "1";
			}else if(request.getParameter("success").equals("wait")){
				selOption = "2";
			}else {
				selOption = "3";
			}
		}
		if(request.getParameter("selectDate") != "") {
			selectDate = request.getParameter("selectDate");
		}
		
		int listCount = 0;
		if(selOption != null && selectDate != null) {
			listCount = new BoardService().getListCountall(selOption, selectDate);
		}else if(selOption != null) {
			listCount = new BoardService().getListCountSeachOp(selOption);
		}else if(selectDate != null){
			listCount = new BoardService().getListCountSeachDt(selectDate);
		}else {
			listCount = new BoardService().getListCount();
		}
		
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9))-1)*10+1;
		
		endPage = startPage + 5 -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		BoardPageInfo bi = new BoardPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Calculate> list = null;
		
		if(selOption != null && selectDate != null) {
			list = new BoardService().selectBoardSearch(currentPage, limit, selOption, selectDate);
		}else if(selOption != null) {
			//list = new BoardService().selectBoardSearchOp(currentPage, limit, selOption);
		}else if(selectDate != null){
			//list = new BoardService().selectBoardSearchDt(currentPage, limit, selectDate);
		}else {
			//list = new BoardService().selectBoard(currentPage, limit);	
		}
		
		String page = "";
		
		if(list != null) {
			page = "views/manager/product/calculate.jsp";
			request.setAttribute("limit", limit);
			request.setAttribute("list", list);
			request.setAttribute("bi", bi);
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
