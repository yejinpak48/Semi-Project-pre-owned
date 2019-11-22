package com.kh.bvengers.user.myPage.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Service.MyPageService;
import com.kh.bvengers.user.myPage.model.vo.myPage;

@WebServlet("/orderDetail.mp")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String memberNo = loginUser.getMemberNo();
		String ono = request.getParameter("ono");

		ArrayList<myPage> odList = new MyPageService().selectOrderDetailList(memberNo, ono);
		String page = "";
		

		if(odList != null) {
			
			for(myPage m : odList) {
				
				if(m.getRefundStatus() == null){
					if(m.getDstatus() != null) {
						if(m.getPayStatus().equals("2")) {
							m.setPstatus("결제 취소");
						}else if(m.getDstatus().equals("1")) {
							m.setPstatus("배송 준비중");
						}else if(m.getDstatus().equals("2")) {
							m.setPstatus("배송 중");
						}else if(m.getDstatus().equals("3")){
							m.setPstatus("배송 완료");
						}
					}
			}else {

				if(m.getRefundStatus().equals("1")) {
					m.setPstatus("환불 대기");
				}else if(m.getRefundStatus().equals("2")) {
					m.setPstatus("환불 완료");
				}else if(m.getRefundStatus().equals("3")) {
					m.setPstatus("환불 취소");
				}
			}

	}
				page = "views/user/mypage/orderDetails.jsp";
				request.setAttribute("odList", odList);

			}else {

				page="views/common/errorPagePrompt.jsp";
				request.setAttribute("msg", "실패!");
			}


			request.getRequestDispatcher(page).forward(request, response);

		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

	}
