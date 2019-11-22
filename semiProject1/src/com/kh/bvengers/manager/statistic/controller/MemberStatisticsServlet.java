package com.kh.bvengers.manager.statistic.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.bvengers.manager.statistic.model.service.StatisticService;
import com.kh.bvengers.manager.statistic.model.vo.AdjustmentPay;

/**
 * Servlet implementation class MemberStatisticsServlet
 */
@WebServlet("/memberStatistics")
public class MemberStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberStatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat ( "yyMMdd");
		Date dTime = new Date();
		String time = format.format(dTime);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		Calendar calender = Calendar.getInstance();
		
		calender.setTime(dTime);
		list = new StatisticService().memberStatistic(calender);
		
		
		
		String page = null;
		if(list != null) {
			page = "/views/manager/statistics/memberStatistics.jsp";
			request.setAttribute("dataList", list);
		}else {
			page="/views/common/errorPagePrompt.jsp";
			request.setAttribute("msg", "상품 등록 실패!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
