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
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/recommend.cm")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecommendServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		String writer = request.getParameter("writer");
		String loginUser = ((Member) request.getSession().getAttribute("loginUser")).getMemberId();

		response.setCharacterEncoding("utf-8");

		if (writer.equals(loginUser)) {
			response.getWriter().println("자신의 글은 추천하실 수 없습니다.");
			System.out.println("자신의 글은 추천하실 수 없습니다.");
		} else {
			int result = new BoardService().recommendComment(num);
			if (result > 0) {
				response.getWriter().println("추천에 성공했습니다");
			} else {
				response.getWriter().println("추천에 실패했습니다");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
