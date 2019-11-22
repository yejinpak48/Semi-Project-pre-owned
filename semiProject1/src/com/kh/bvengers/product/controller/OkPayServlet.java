package com.kh.bvengers.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.Payment;
import com.kh.bvengers.user.member.model.vo.Member;

/**
 * Servlet implementation class OkPayServlet
 */
@WebServlet("/okPay.pa")
public class OkPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OkPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNo =  ((Member) (request.getSession().getAttribute("loginUser"))).getMemberNo();
		String paymentPrice = request.getParameter("paymentPrice");
		paymentPrice = paymentPrice.substring(0, paymentPrice.length()-1);
		String userName = request.getParameter("userName");
		int count = Integer.parseInt(request.getParameter("count"));
		
		String productCode = request.getParameter("productCode");
		String[] codeList = productCode.split(",");
		
		String priceSplit = request.getParameter("priceSplit");
		String[] priceList = priceSplit.split(",");
		
		String receiptId = request.getParameter("receiptId");
		
		String addNum = request.getParameter("addNum");
		String address = request.getParameter("address");
		String subAddress = request.getParameter("subAddress");
		String subAdd = addNum + "$" + address + "$";
		String add = addNum + "$" + address + "$" + subAddress;
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		
		String mail = request.getParameter("mail");
		
		Payment pay = new Payment();
		
		//pay.setProductCode(productCode);
		pay.setMemberNo(memberNo);
		//pay.setPayMoney(Integer.parseInt(paymentPrice));
		pay.setRecieverName(userName);
		pay.setSubDeliverySite(subAdd);
		pay.setDeliverySite(add);
		pay.setRecieverPhone(phone);
		pay.setMail(mail);
		pay.setReceipt(receiptId);
		
		int result = new ProductService().okPay(pay, count, codeList, priceList);
		
		if(result >0) {
			response.sendRedirect("index.jsp");
		}else {
			request.setAttribute("msg", "구매 실패!");
			request.getRequestDispatcher("views/common/errorPagePrompt.jsp").forward(request, response);
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
