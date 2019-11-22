package com.kh.bvengers.manager.depot.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.manager.depot.model.servies.DepotService;
import com.kh.bvengers.manager.depot.model.vo.Depot;

@WebServlet("/requestCheck.dp")
public class RequestCheckStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RequestCheckStatusServlet() {
        super();
        
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productCode = request.getParameter("productCode");
		String depotCate = request.getParameter("root");
		String location =  request.getParameter("location");
		String session = request.getParameter("session");
		String room = request.getParameter("room");
		String checkStatus=request.getParameter("request");
		String completStatus=request.getParameter("status");
		String checkPassContent=request.getParameter("checkPassContent");
		int deliveryPrice =Integer.parseInt(request.getParameter("deliveryPrice")); 
		
		
		Depot d = new Depot();
		String locationCode = depotCate+"-"+location+"-"+session+"-"+room;
		if(completStatus==null&&checkStatus==null) {
			completStatus="2";
			checkStatus="1";
		}else if(checkStatus.equals("requestCheck")) {
			checkStatus="1";
			completStatus="2";
		}else if(checkStatus.equals("checking")) {
			checkStatus="2";
			completStatus="2";
		}else {
			checkStatus="3";
			if(completStatus.equals("pass")) {
				completStatus="1";
			}else if(completStatus.equals("fail")) {
				completStatus="2";
			}else {
				completStatus="3";
			}
		}
		d.setCheckStatus(checkStatus);
		d.setCompletStatus(completStatus);
		d.setCheckPassContent(checkPassContent);
		d.setProductCode(productCode);
		d.setLocationCode(locationCode);
		d.setDeliveryPrice(deliveryPrice);
		int result = new DepotService().requsetCheck(d);
		
		
		String page = "";
		
		if(result>0) {
			page=request.getContextPath()+"/list.dp";
			response.sendRedirect(page);
		}else {
			page="views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "작성완료");
			request.getRequestDispatcher(page).forward(request, response);			
		}
		
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
