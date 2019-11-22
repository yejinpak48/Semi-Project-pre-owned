package com.kh.bvengers.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class frequentQuestionServlet
 */
@WebServlet("/fqs.qb")
public class frequentQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public frequentQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String postCode = "4";
		String writer = String.valueOf(((Member) request.getSession().getAttribute("loginUser")).getMemberNo());

		Board b = new Board();
		b.setPostsTitle(title);
		b.setContents(content);
		b.setMemberNo(Integer.parseInt(writer));
		b.setPostsCode(postCode);

		int result = new BoardService().insertNotice(b);
		
		String page = "";
				
		if(result > 0) {
			page = "";
			response.sendRedirect(request.getContextPath()+"/SelectFrequentQuestionList.fq");
		}else {
			request.setAttribute("msg", "자주 찾는 질문 등록 실패삼");
			request.getRequestDispatcher("views/common/errorpage.jsp").forward(request, response);
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
