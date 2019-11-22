package com.kh.bvengers.manager.statistic.model.dao;

import static com.kh.bvengers.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import com.kh.bvengers.product.model.dao.ProductDao;
public class StatisticDao {
	private Properties prop = new Properties();
	
	public StatisticDao() {
		String fileName = ProductDao.class.getResource("/sql/manager/statistic/statistic-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> countDate(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> dateList = null;
		
		String query = prop.getProperty("selectDate");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			int i = 0;
			dateList = new ArrayList<String>();
			while(rset.next()) {
				dateList.add(i, rset.getString("DA"));
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return dateList;
	}
	
	public ArrayList<HashMap<String, Object>> memberStatistic(Connection con, ArrayList<String> dateList, Calendar calender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>>  list = null;
		HashMap<String, Object> hmap = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd"); 
		
		

		
		int count = 0;
		
		String query = prop.getProperty("memberStatistic");
		try {
			list = new ArrayList<HashMap<String, Object>>();
			int dateLength = 7;
			
			for(int i = 0; i < dateLength; i++) {
				Date date = calender.getTime();
				String time = format.format(date);
				
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, time);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					hmap = new HashMap<String, Object>();
					
					String yy = time.substring(0,2);
					String MM = time.substring(2,4);
					String dd = time.substring(4);
					hmap.put("payDate", MM+"월"+dd+"일");
					hmap.put("row", rset.getString("COUNT(*)"));
					
					int dpay = 0;
					if(rset.getString("SUM(PAY_MONEY)") != null) {
						dpay = Integer.parseInt(rset.getString("SUM(PAY_MONEY)"))/10000;
					}
					hmap.put("allPay", dpay+"");
					list.add(hmap);
					
				}
				calender.add(Calendar.DATE, -1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<String> salesCountDate(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> dateList = null;
		
		String query = prop.getProperty("salesCountDate");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			int i = 0;
			dateList = new ArrayList<String>();
			while(rset.next()) {
				dateList.add(i, rset.getString("DA"));
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return dateList;
	}

	public ArrayList<HashMap<String, Object>> allDatalist(Connection con, ArrayList<String> dateList) {
		SimpleDateFormat format = new SimpleDateFormat ( "yyMMdd");
		Date dTime = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(dTime);
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>>  list = null;
		HashMap<String, Object> hmap = null;
		
		int count = 0;
		
		String query = prop.getProperty("salesStatisticsAll");
		try {
			
			list = new ArrayList<HashMap<String, Object>>();
			int dateLength = 7;
			
			
			for(int i = 0; i < dateLength; i++) {
				Date date = calender.getTime();
				String time = format.format(date);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, time);
				
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					hmap = new HashMap<String, Object>();
					
					String yy = time.substring(0,2);
					String MM = time.substring(2,4);
					String dd = time.substring(4);
					hmap.put("payDate", MM+"월"+dd+"일");
					
					int allPrice = 0;
					if(rset.getString("SUM(PRICE)") != null) {
						allPrice = Integer.parseInt(rset.getString("SUM(PRICE)"))/10000;
					}
					
					hmap.put("allPrice", allPrice+"");
					list.add(hmap);
				}
				calender.add(Calendar.DATE, -1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<HashMap<String, Object>> calculateDatalist(Connection con, ArrayList<String> dateList) {
		SimpleDateFormat format = new SimpleDateFormat ( "yyMMdd");
		Date dTime = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(dTime);
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>>  list = null;
		HashMap<String, Object> hmap = null;
		
		int count = 0;
		
		String query = prop.getProperty("salesStatistics");
		try {
			list = new ArrayList<HashMap<String, Object>>();
			int dateLength = 7;
			
			for(int i = 0; i < dateLength; i++) {
				Date date = calender.getTime();
				String time = format.format(date);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, time);
				pstmt.setString(2, "2");
				
				rset = pstmt.executeQuery();				
				while(rset.next()) {
					hmap = new HashMap<String, Object>();
					
					String yy = time.substring(0,2);
					String MM = time.substring(2,4);
					String dd = time.substring(4);
					hmap.put("payDate", MM+"월"+dd+"일");
					
					int calParice = 0;
					if(rset.getString("SUM(PRICE)") != null) {
						calParice = Integer.parseInt(rset.getString("SUM(PRICE)"))/10000;
					}
					
					hmap.put("calParice", calParice+"");
					list.add(hmap);
				}
				calender.add(Calendar.DATE, -1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<HashMap<String, Object>> refundDatalist(Connection con, ArrayList<String> dateList) {
		SimpleDateFormat format = new SimpleDateFormat ( "yyMMdd");
		Date dTime = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(dTime);
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>>  list = null;
		HashMap<String, Object> hmap = null;
		
		int count = 0;
		
		String query = prop.getProperty("salesStatistics");
		try {
			list = new ArrayList<HashMap<String, Object>>();
			int dateLength = 7;
			
			for(int i = 0; i < dateLength; i++) {
				Date date = calender.getTime();
				String time = format.format(date);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, time);
				pstmt.setString(2, "3");
				
				rset = pstmt.executeQuery();				
				while(rset.next()) {
					hmap = new HashMap<String, Object>();
					
					String yy = time.substring(0,2);
					String MM = time.substring(2,4);
					String dd = time.substring(4);
					hmap.put("payDate", MM+"월"+dd+"일");
					
					int refundParice = 0;
					if(rset.getString("SUM(PRICE)") != null) {
						refundParice = Integer.parseInt(rset.getString("SUM(PRICE)"))/10000;
					}
					
					hmap.put("refundParice", refundParice+"");
					list.add(hmap);
				}
				calender.add(Calendar.DATE, -1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
}





















