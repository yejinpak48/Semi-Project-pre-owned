package com.kh.bvengers.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.Calcul;
import com.kh.bvengers.product.model.vo.CalculPageInfo;

/**
 * Servlet implementation class SearchCalculateServlet
 */
@WebServlet("/search.cal")
public class SearchCalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCalculateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("curr"));
		int limit = Integer.parseInt(request.getParameter("limited"));
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
			listCount = new ProductService().getListCountall(selOption, selectDate);
		}else if(selOption != null) {
			listCount = new ProductService().getListCountSeachOp(selOption);
		}else if(selectDate != null){
			listCount = new ProductService().getListCountSeachDt(selectDate);
		}else {
			listCount = new ProductService().getListCount();
		}
		
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9))-1)*10+1;
		
		endPage = startPage + 5 -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		CalculPageInfo ci = new CalculPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Calcul> list = null;
		
		if(selOption != null && selectDate != null) {
			list = new ProductService().selectCalculSearch(currentPage, limit, selOption, selectDate);
		}else if(selOption != null) {
			list = new ProductService().selectCalculSearchOp(currentPage, limit, selOption);
		}else if(selectDate != null){
			list = new ProductService().selectCalculSearchDt(currentPage, limit, selectDate);
		}else {
			list = new ProductService().selectCalcul(currentPage, limit);	
		}
		
		String page = "";
		
		if(list != null) {
			page = "views/manager/product/calculate.jsp";
			request.setAttribute("limit", limit);
			request.setAttribute("list", list);
			request.setAttribute("ci", ci);
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
