package com.kh.bvengers.manager.member.model.dao;

import static com.kh.bvengers.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.bvengers.manager.member.model.vo.Report;
import com.kh.bvengers.manager.member.model.vo.SANCTION;
public class ManagerMemberDao {

	Properties prop = new Properties();
	public ManagerMemberDao() {
		String fileName = ManagerMemberDao.class.getResource("/sql/manager/member/managermember-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount=rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return listCount;
	}
	public ArrayList<Report> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Report> mlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			mlist = new ArrayList<Report>();
			while(rset.next()) {
				Report r = new Report();
				
				r.setReportNo(rset.getString("REPORT_NO"));
				r.setReporter(rset.getString("REPORTER"));
				r.setMemberDest(rset.getString("MEMBER_DEST"));
				r.setPostsId(rset.getString("POSTS_ID"));
				r.setReportDate(rset.getDate("REPORT_DATE"));
				r.setReportComments(rset.getString("REPORT_COMMENTS"));
				
				mlist.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return mlist;
	}
	public int memberban(Connection con, String memberId, int term, String reason) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("memberban");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, reason);
			pstmt.setInt(3, term);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int blackmember(Connection con, String memberId, String reason) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("blackmember");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, reason);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<SANCTION> badmanList(Connection con, int currentPage, int limit) {
		ArrayList<SANCTION> sanlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		String query = prop.getProperty("badmanList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			sanlist = new ArrayList<SANCTION>();
			while(rset.next()) {
				SANCTION s = new SANCTION();
				
				s.setSanctionNo(rset.getString("SANCTION_NO"));
				s.setMemberNo(rset.getString("MEMBER_NO"));
				s.setReason(rset.getString("REASON"));
				s.setSanctionDate(rset.getDate("SANCTION_DATE"));
				s.setSanctionDiv(rset.getString("SANCTION_DIV"));
				s.setSanctionStatus(rset.getString("SANCTION_STATUS"));
				s.setStopTerm(rset.getInt("STOP_TERM"));
				
				sanlist.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return sanlist;
	}
	public int ListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public ArrayList<SANCTION> searchstopbadman(Connection con, int currentPage, int limit, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SANCTION> list = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		String query = prop.getProperty("stopmanList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SANCTION>();
			if(rset.next()) {
				SANCTION s = new SANCTION();
				
				s.setSanctionNo(rset.getString("SANCTION_NO"));
				s.setMemberNo(rset.getString("MEMBER_NO"));
				s.setReason(rset.getString("REASON"));
				s.setSanctionDate(rset.getDate("SANCTION_DATE"));
				s.setSanctionDiv(rset.getString("SANCTION_DIV"));
				s.setSanctionStatus(rset.getString("SANCTION_STATUS"));
				s.setStopTerm(rset.getInt("STOP_TERM"));
				
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public ArrayList<SANCTION> searchbadblackman(Connection con, int currentPage, int limit, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SANCTION> list = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		String query = prop.getProperty("blackmanList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SANCTION>();
			if(rset.next()) {
				SANCTION s = new SANCTION();
				
				s.setSanctionNo(rset.getString("SANCTION_NO"));
				s.setMemberNo(rset.getString("MEMBER_NO"));
				s.setReason(rset.getString("REASON"));
				s.setSanctionDate(rset.getDate("SANCTION_DATE"));
				s.setSanctionDiv(rset.getString("SANCTION_DIV"));
				s.setSanctionStatus(rset.getString("SANCTION_STATUS"));
				s.setStopTerm(rset.getInt("STOP_TERM"));
				
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int getreListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		String query = prop.getProperty("getreListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount=rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return listCount;
	}
	public ArrayList<Report> searchbefore(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		ArrayList<Report> list = null;
		String query = prop.getProperty("searchbefore");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			list = new ArrayList<Report>();
			if(rset.next()) {
				Report r = new Report();
				
				r.setReportNo(rset.getString("REPORT_NO"));
				r.setReporter(rset.getString("REPORTER"));
				r.setMemberDest(rset.getString("POSTS_ID"));
				r.setReportDate(rset.getDate("REPORT_DATE"));
				r.setReportComments(rset.getString("REPORT_COMMENTS"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Report> searchafter(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Report> list = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		String query = prop.getProperty("searchafter");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			list = new ArrayList<Report>();
			if(rset.next()) {
				Report r = new Report();
				
				r.setReportNo(rset.getString("REPORT_NO"));
				r.setReporter(rset.getString("REPORTER"));
				r.setMemberDest(rset.getString("POSTS_ID"));
				r.setReportDate(rset.getDate("REPORT_DATE"));
				r.setReportComments(rset.getString("REPORT_COMMENTS"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
