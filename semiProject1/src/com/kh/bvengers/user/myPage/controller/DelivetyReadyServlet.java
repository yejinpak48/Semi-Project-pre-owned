package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;
import com.kh.bvengers.user.myPage.model.vo.myPage;

@WebServlet("/deliReady.mp")
public class DelivetyReadyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelivetyReadyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dr1 = request.getParameter("dr1");

		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();

		ArrayList<myPage> drlist = new MyPageService().selectDeliReadyList(memberNo);

		JSONArray result = new JSONArray();
		JSONObject dr1list = null;

		for(myPage m : drlist) {
			dr1list = new JSONObject();

			if(m.getDstatus().equals(dr1)) {

				dr1list.put("Ono", m.getOno());
				dr1list.put("pName", URLEncoder.encode(m.getPname(), "UTF-8"));
				dr1list.put("dstatus", m.getDstatus());
				dr1list.put("oDate", m.getoDate());

			}
			result.add(dr1list);
		}

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(result.toJSONString());
		out.flush();
		out.close();




	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
