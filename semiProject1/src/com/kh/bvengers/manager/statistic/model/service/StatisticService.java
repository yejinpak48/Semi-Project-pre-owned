package com.kh.bvengers.manager.statistic.model.service;

import static com.kh.bvengers.common.JDBCTemplate.close;
import static com.kh.bvengers.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.kh.bvengers.manager.statistic.model.dao.StatisticDao;

public class StatisticService {

	public ArrayList<HashMap<String, Object>> memberStatistic(Calendar calender) {
		Connection con = getConnection();
		ArrayList<HashMap<String, Object>>  datalist = null;

		ArrayList<String> dateList = new StatisticDao().countDate(con);
		
		datalist = new StatisticDao().memberStatistic(con, dateList, calender);

		close(con);
		return datalist;
	}

	public ArrayList<ArrayList<HashMap<String, Object>>> salesStatistics() {
	      Connection con = getConnection();
	      ArrayList<ArrayList<HashMap<String, Object>>> allList = new ArrayList<ArrayList<HashMap<String, Object>>>();
	      
	      ArrayList<HashMap<String, Object>>  allDatalist = null;
	      ArrayList<HashMap<String, Object>>  calculateDatalist = null;
	      ArrayList<HashMap<String, Object>>  refundDatalist = null;
	      
	      ArrayList<String> dateList = new StatisticDao().salesCountDate(con);

	      allDatalist = new StatisticDao().allDatalist(con, dateList);
	      calculateDatalist = new StatisticDao().calculateDatalist(con, dateList);
	      refundDatalist = new StatisticDao().refundDatalist(con, dateList);
	      
	      allList.add(allDatalist);
	      allList.add(calculateDatalist);
	      allList.add(refundDatalist);
	      
	      close(con);
	      return allList;
	   }

}
