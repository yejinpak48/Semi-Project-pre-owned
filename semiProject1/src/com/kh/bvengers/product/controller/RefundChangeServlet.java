package com.kh.bvengers.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.Refund;

/**
 * Servlet implementation class RefundChangeServlet
 */
@WebServlet("/refundChange.mp")
public class RefundChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefundChangeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = request.getParameter("pass");
		String pno = request.getParameter("pno");
		String pcode = request.getParameter("pcode");
		int result = 0;
		
		result = new ProductService().passRefund(pass, pno, pcode);
		
		
		
		if(result > 0) {
			/*response.sendRedirect("views/manager/product/refundManagement.jsp");*/
			//page = "views/manager/product/refundManagement.jsp";
			request.getRequestDispatcher("/refundProduct.mp").forward(request, response);
		}else {
			

			request.setAttribute("msg", "실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}
		


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
