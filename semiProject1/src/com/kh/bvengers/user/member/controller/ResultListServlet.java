package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.member.model.vo.MemberPageInfo;

@WebServlet("/resultlist.me")
public class ResultListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ResultListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
		MemberPageInfo pi = (MemberPageInfo)request.getAttribute("pi");
		request.getRequestDispatcher("views/manager/member/listMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
