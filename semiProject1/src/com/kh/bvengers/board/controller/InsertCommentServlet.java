package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Comment;

@WebServlet("/insertComment.pd")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertCommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String postsId = request.getParameter("postsId");
		String content = request.getParameter("content");
		Comment b = new Comment();
		b.setCommentContents(content);
		b.setPostsId(postsId);
		b.setMemberId(writer);

		ArrayList<Comment> commentList = new BoardService().insertComment(b);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(commentList, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
