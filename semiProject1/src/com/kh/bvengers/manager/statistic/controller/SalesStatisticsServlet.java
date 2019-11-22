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

/**
 * Servlet implementation class SalesStatisticsServlet
 */
@WebServlet("/salesStatistics.st")
public class SalesStatisticsServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesStatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      ArrayList<ArrayList<HashMap<String, Object>>> datalist = new StatisticService().salesStatistics();
      
      String page = null;
      if(datalist != null) {
         page = "views/manager/statistics/salesStatistics.jsp";
         request.setAttribute("dataList", datalist);
      }else {
         page = "views/common/errorPagePrompt.jsp";
         request.setAttribute("msg", "통계를 불러오는데 실패하였습니다.");
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













