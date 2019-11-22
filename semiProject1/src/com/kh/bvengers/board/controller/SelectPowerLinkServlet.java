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
import com.kh.bvengers.board.model.vo.PowerLink;

@WebServlet("/power.pd")
public class SelectPowerLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectPowerLinkServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<PowerLink> list = new BoardService().powerLink();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		new Gson().toJson(list, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
