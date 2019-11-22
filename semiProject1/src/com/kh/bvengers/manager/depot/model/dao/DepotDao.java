package com.kh.bvengers.manager.depot.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.bvengers.manager.depot.model.vo.Depot;

import static com.kh.bvengers.common.JDBCTemplate.*;
public class DepotDao {
	private Properties prop = new Properties();
	public DepotDao() {
		String fileName = DepotDao.class.getResource("/sql/manager/depot/Depot-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<Depot> selectCheckAll(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt  =null;
		ResultSet rset =null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("selectRequestList");
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Depot>();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setProductCate(rset.getString("PRODUCT_CATE"));
				d.setCheckDate(rset.getDate("CHECK_DATE"));
				d.setCheckStatus(rset.getString("CHECK_STATUS"));
				d.setFileName(rset.getString("NEW_FILE_NAME"));
				i++;
				list.add(d);
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
	public int insertLocation(Connection con, Depot d) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = prop.getProperty("insertLocation");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,d.getLocationCode());
			pstmt.setString(2,d.getProductCode());
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
		}finally {
			close(pstmt);
		}
		
				
		
		return result;
	}
	public int updateStatus(Connection con, Depot d) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query =prop.getProperty("updateStatus");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, d.getCheckStatus());
			pstmt.setString(2, d.getCompletStatus());
			pstmt.setString(3, d.getCheckPassContent());
			pstmt.setInt(4, d.getDeliveryPrice());
			pstmt.setString(5, d.getProductCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int updateLocation(Connection con, Depot d) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateLocation");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, d.getLocationCode());
			pstmt.setString(2, d.getProductCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Depot> selectAllList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList <Depot> list = null;
		Depot d = null;
		String query = prop.getProperty("selectAll");
		int i = 1;
		try {
			pstmt = con.prepareStatement(query);
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list= new ArrayList<Depot>();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				list.add(d);
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
	public ArrayList<Depot> selectBarcodeAllList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList <Depot> list = null;
		Depot d = null;
		String query = prop.getProperty("createBarcode");
		int i = 1;
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list= new ArrayList<Depot>();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("CHECK_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				list.add(d);
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
	public ArrayList<Depot> selectInList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i =1;
		String query = prop.getProperty("selectInList");
		try {
			pstmt =con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset =pstmt.executeQuery();
			list= new ArrayList<Depot>();
			while(rset.next()) {
				d=new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setSelerId(rset.getString("MEMBER_ID"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCompletStatus(rset.getString("COMPLETE_STATUS"));
				d.setCheckDate(rset.getDate("CHECK_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				d.setPayStatus(rset.getString("ORDER_NO"));				
				i++;
				list.add(d);
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
	public int updateRelesDate(Connection con, String productCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateRelesDate");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, productCode);
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Depot> outProductList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList <Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("outproductList");
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();			
			list = new ArrayList <Depot>();
			while(rset.next()) {
				d= new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setSelerId(rset.getString("MEMBER_ID"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCompletStatus(rset.getString("COMPLETE_STATUS"));
				d.setCheckDate(rset.getDate("CHECK_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));				
				i++;
				list.add(d);
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
	public int getAllListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("AllListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public int getOutProductListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("OutProductListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public int getBarcodeCreateListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("createBarcodeCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public int getInDepotCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("selectInListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public int getCheckListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = prop.getProperty("selectRequestListCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	public int serchL(Connection con, String location) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchL");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListL(Connection con, String location, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListL");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int serchId(Connection con, String insertDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchId");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, insertDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListId(Connection con, String insertDate, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListId");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, insertDate);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int serchRd(Connection con, String releaseDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchRd");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListRd(Connection con, String releaseDate, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListRd");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, releaseDate);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int serchP(Connection con, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListP(Connection con, String productCode, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListP");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setInt(2, startRow );
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int serchLId(Connection con, String location, String insertDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLId");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, insertDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLId(Connection con, String location, String insertDate, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLId");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, insertDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow );
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchLRd(Connection con, String location, String releaseDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLRd");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLRd(Connection con, String location, String releaseDate, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLRd");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, releaseDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchLP(Connection con, String location, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, productCode);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLP(Connection con, String location, String productCode, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLP");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, productCode);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchIdRd(Connection con, String insertDate, String releaseDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchIdRd");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, insertDate);
			pstmt.setString(2, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListIdRd(Connection con, String insertDate, String releaseDate, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListIdRd");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, insertDate);
			pstmt.setString(2, releaseDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchIdp(Connection con, String insertDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchIdP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListIdp(Connection con, String insertDate, String productCode, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListIdp");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchRdP(Connection con, String releaseDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchRdP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListRdP(Connection con, String releaseDate, String productCode, int currentPage,
			int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListIdp");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, releaseDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchLIdRd(Connection con, String location, String insertDate, String releaseDate) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLIdRd");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLIdRd(Connection con, String location, String insertDate, String releaseDate,
			int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLIdRd");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, releaseDate);
			pstmt.setString(3, insertDate);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchLIdP(Connection con, String location, String insertDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLIdP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, location);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, productCode);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLIdP(Connection con, String location, String insertDate, String productCode,
			int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLIdP");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, location);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchLRdP(Connection con, String location, String releaseDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchLRdP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, releaseDate);
			pstmt.setString(3, location);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListLRdP(Connection con, String location, String releaseDate, String productCode,
			int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListLRdP");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, releaseDate);
			pstmt.setString(3, location);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchIdRdP(Connection con, String insertDate, String releaseDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchIdRdP");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, releaseDate);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListIdRdP(Connection con, String insertDate, String releaseDate, String productCode,
			int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListIdRdP");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, releaseDate);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int searchTotal(Connection con, String location, String insertDate, String releaseDate, String productCode) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		int listCount =0;
		String query = prop.getProperty("searchTotal");
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, releaseDate);
			pstmt.setString(4, location);
			rset =pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<Depot> searchListTotal(Connection con, String location, String insertDate, String releaseDate,
			String productCode, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int i = 1;
		String query = prop.getProperty("searchListTotal");
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, insertDate);
			pstmt.setString(3, releaseDate);
			pstmt.setString(4, location);
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			rset = pstmt.executeQuery();
			list =new ArrayList<Depot> ();
			while(rset.next()) {
				d = new Depot();
				d.setProductNumber(i);
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				i++;
				
				list.add(d);				
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
	public int updateOpen(Connection con, String ProductCode) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = prop.getProperty("updateOpen");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ProductCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public ArrayList<Depot> excelPrint(Connection con, String[] productCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Depot> list = null;
		Depot d = null;
		int j = 1;
		String query = prop.getProperty("excelPrint");
		try {
			list = new ArrayList<Depot>();
			for(int i =0;i<productCode.length;i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, productCode[i]);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					d=new Depot();
					d.setProductNumber(j);
					d.setProductCode(rset.getString("PRODUCT_CODE"));
					d.setProductName(rset.getString("PRODUCT_NAME"));
					d.setLocationCode(rset.getString("LOCATION"));
					d.setCheckDate(rset.getDate("MAX_DEPOT_DATE"));
					d.setReleaseDate(rset.getDate("RELEASE_DATE"));
					j++;
					
					list.add(d);					
				}				
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
	public Depot productDetail(Connection con, String productCode) {
		PreparedStatement pstmt = null;
		ResultSet rset  =null;
		Depot d = null;
		String query = prop.getProperty("productDetail");
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, productCode);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				d = new Depot();
				d.setProductCode(rset.getString("PRODUCT_CODE"));
				d.setProductCate(rset.getString("PRODUCT_CATE"));
				d.setProductName(rset.getString("PRODUCT_NAME"));
				d.setLocationCode(rset.getString("LOCATION"));
				d.setCheckDate(rset.getDate("CHECK_DATE"));
				d.setCheckStatus(rset.getString("CHECK_STATUS"));
				d.setFileName(rset.getString("NEW_FILE_NAME"));
				d.setReleaseDate(rset.getDate("RELEASE_DATE"));
				d.setCompletStatus(rset.getString("COMPLETE_STATUS"));
				d.setCheckPassContent(rset.getString("REASON"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return d;
	}

}
