package com.kh.bvengers.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class reportServlet
 */
@WebServlet("/ss.re")
public class reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String array = request.getParameter("array");

		String[] reportarr = array.split("/");

		String dustId = reportarr[0];
		String post_id = reportarr[1];

		String page = "";

		String content = request.getParameter("content");

		String reporter = String.valueOf(((Member) request.getSession().getAttribute("loginUser")).getMemberNo());

		int result = new BoardService().insertReport(dustId,post_id,content,reporter);

		if(result>0) {
			page = "views/user/board/board.jsp";
		}else {
			request.setAttribute("msg","신고 실패하셨습니다.");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);

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
