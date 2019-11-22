package com.kh.bvengers.user.basket.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.bvengers.user.basket.model.vo.Basket;
import static com.kh.bvengers.common.JDBCTemplate.*;

public class BasketDao {
	private Properties prop = new Properties();

	public BasketDao() {
		String fileName = BasketDao.class.getResource("/sql/user/basket/basket-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Basket> selectOneBasket(Connection con, String fileName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Basket> list = null;
		Basket bk = null;
		String query =prop.getProperty("selectOne");
		try {
			pstmt =con.prepareStatement(query);
			pstmt.setString(1, fileName);
			rset = pstmt.executeQuery();
			list = new ArrayList<Basket>();

			while(rset.next()) {
				bk = new Basket();

				bk.setProductCode(rset.getString("PRODUCT_CODE"));
				bk.setPrice(rset.getInt("PRODUCT_MONEY"));
				bk.setContent(rset.getString("CONTENTS"));
				bk.setProductName(rset.getString("PRODUCT_NAME"));

				list.add(bk);

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
	public int insertBasket(Connection con,ArrayList<Basket> list, String userId) {
		PreparedStatement pstmt =null;
		String productCode = list.get(0).getProductCode();
		int result = 0;
		String query = prop.getProperty("insertBasket");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,productCode);
			pstmt.setString(2,userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		}finally {
			close(pstmt);
		}


		return result;
	}
	public ArrayList<Basket> basketAllList(Connection con, String userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Basket> list = null;
		Basket bk =  null;
		String query = prop.getProperty("selectAllList");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, userNo);
			rset=pstmt.executeQuery();
			list = new ArrayList<Basket>();
			while(rset.next()) {
				bk=new Basket();
				bk.setProductCode(rset.getString("PRODUCT_CODE"));
				bk.setProductName(rset.getString("PRODUCT_NAME"));
				bk.setFileName(rset.getString("NEW_FILE_NAME"));
				bk.setPrice(rset.getInt("PRODUCT_MONEY"));
				bk.setContent(rset.getString("CONTENTS"));
				bk.setDeliveryPrice(rset.getInt("DELIVERY_PAY"));
				list.add(bk);
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
	public int deleteBasket(Connection con, String productCode, String userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteBasket");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userNo);
			pstmt.setString(2, productCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return result;
	}
	public ArrayList<Basket> changeTotal(Connection con, String[] productCode) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		ArrayList<Basket> list = null;
		Basket bk = null;
		String query = prop.getProperty("changeTotal");
		try {
			list = new ArrayList<Basket>();
			for(int i =0;i<productCode.length;i++) {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, productCode[i]);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					bk=new Basket();
					bk.setProductCode(rset.getString("PRODUCT_CODE"));
					bk.setProductName(rset.getString("PRODUCT_NAME"));
					bk.setFileName(rset.getString("NEW_FILE_NAME"));
					bk.setPrice(rset.getInt("PRODUCT_MONEY"));
					bk.setContent(rset.getString("CONTENTS"));
					bk.setDeliveryPrice(rset.getInt("DELIVERY_PAY"));
					list.add(bk);
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

}
