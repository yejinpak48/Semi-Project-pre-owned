package com.kh.bvengers.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;

/**
 * Servlet implementation class DeliSucessServlet
 */
@WebServlet("/deliSucess")
public class DeliSucessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliSucessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deliNo = request.getParameter("deliNo");
		
		int result = new BoardService().changeDeliStatus(deliNo); 
		
		String page = "";
		if(result > 0) {
			page = "/sp/productManagement";
			response.sendRedirect(page);
		}else {
			page = "views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "업데이트 실패!");
			request.getRequestDispatcher(page).forward(request, response);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
