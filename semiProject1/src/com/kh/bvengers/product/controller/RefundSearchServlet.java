package com.kh.bvengers.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.CalculPageInfo;
import com.kh.bvengers.product.model.vo.Refund;

@WebServlet("/refundSearch.mp")
public class RefundSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefundSearchServlet() {
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
		limit = 5;
		int listCount = new ProductService().getrListCount();
		maxPage = (int)((double)listCount / limit+0.9);
		startPage = (((int)((double)currentPage/limit+0.9))-1)*10+1;
		
		endPage = startPage + 10 -1;
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		
		CalculPageInfo pi = new CalculPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		String selectRefund[] = request.getParameterValues("selectRefund");
		String select = selectRefund[0];
		
		ArrayList<Refund> rList = null;
		
		if(select.equals("wait")) {
			rList = new ProductService().searchWait(currentPage, limit);
		}else if(select.equals("success")) {
			rList = new ProductService().searchSuccess(currentPage, limit);
		}else if(select.equals("cancel")) {
			rList = new ProductService().searchCancel(currentPage, limit);
		}
		
		String page = "";
		
		if(rList != null) {
			
			for(Refund m : rList) {
				
				if(m.getrStatus()!=null && m.getrStatus().equals("1")) {
					m.setrStatus("환불 대기");
				}else if(m.getrStatus()!=null && m.getrStatus().equals("2")) {
					m.setrStatus("환불 완료");
				}else if(m.getrStatus()!=null && m.getrStatus().equals("3")) {
					m.setrStatus("환불 취소");
				}

	}

			page = "views/manager/product/refundManagement.jsp";
			request.setAttribute("rList", rList);
			request.setAttribute("pi", pi);
			
		}else {
			page="views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
