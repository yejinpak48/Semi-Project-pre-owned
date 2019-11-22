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

@WebServlet("/refundProduct.mp")
public class RefundProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefundProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;		//현재 페이지를 표시할 변수
		int limit;				//한 페이지에 보여질 게시물 수
		int maxPage;			//전체 페이지에서 가장 마지막 페이지
		int startPage;			//한 번에 표시될 페이징 버튼이 시작할 번호
		int endPage;			//한 번에 표시될 페이징 버튼이 끝나는 번호
		
		//게시판은 1페이지부터 시작함
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//한 페이지에 보여질 목록 갯수
		limit = 10;
		
		//전체 목록 갯수를 리턴받음
		int listCount = new ProductService().getRefundManagerListCount();
		
		
		maxPage = (int)((double)listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		CalculPageInfo pi = 
				new CalculPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		String page = "";
		
		ArrayList<Refund> rList = new ProductService().selectRefundManagerList(currentPage, limit);
		
		
		if(rList != null) {
			
			for(Refund m : rList) {
				
				if(m.getrStatus()!=null&&m.getrStatus().equals("1")) {
					m.setrStatus("환불 대기");
				}else if(m.getrStatus()!=null&&m.getrStatus().equals("2")) {
					m.setrStatus("환불 완료");
				}else if(m.getrStatus()!=null&&m.getrStatus().equals("3")) {
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
