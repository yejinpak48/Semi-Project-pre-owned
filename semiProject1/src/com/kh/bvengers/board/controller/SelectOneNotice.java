package com.kh.bvengers.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.board.model.service.BoardService;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.board.model.vo.Attachment;


@WebServlet("/son.no")
public class SelectOneNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectOneNotice() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		HashMap<String, Object> hmap = new BoardService().selectOneNotice(num);

		Board b = (Board)hmap.get("board");
		Attachment fileList = (Attachment)hmap.get("attachment");


		String page = "";
		
		if(hmap != null) {
			page = "views/user/board/boardDetail.jsp";
			request.setAttribute("b", b);
			request.setAttribute("fileList", fileList);
			System.out.println("b"+b);
		}else {
			page = "views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "사진 게시판 상세보기 실패!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}















