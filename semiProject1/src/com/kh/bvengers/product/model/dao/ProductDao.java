package com.kh.bvengers.product.model.dao;

import static com.kh.bvengers.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.bvengers.board.model.vo.Attachment;
import com.kh.bvengers.board.model.vo.Posts;
import com.kh.bvengers.board.model.vo.PostsContents;
import com.kh.bvengers.product.model.vo.Calcul;
import com.kh.bvengers.product.model.vo.Payment;
import com.kh.bvengers.product.model.vo.Product;
import com.kh.bvengers.product.model.vo.Refund;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.vo.myPage;

public class ProductDao {

	private Properties prop = new Properties();

	public ProductDao() {
		String fileName = ProductDao.class.getResource("/sql/user/product/product-board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int insertProductPost(Connection con, Posts posts) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertProductPost");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, posts.getPostsTitle());
			pstmt.setString(2, posts.getMemberNo()+"");

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int insertPostContents(Connection con, PostsContents postsContents) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertPostContents");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, postsContents.getPostsId());
			pstmt.setString(2, postsContents.getContents());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int insertProduct(Connection con, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertProduct");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, product.getProductCode());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductMoney());
			pstmt.setString(4, product.getProductCate());
			pstmt.setString(5, product.getMemberNo());
			pstmt.setString(6, product.getKeepDate());
			pstmt.setString(7, product.getPostId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int insertProductCheck(Connection con, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertProductCheck");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, product.getProductCode());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}



	public int insertAttachments(Connection con, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertAttachments");

		try {
			for(int i = 0; i < fileList.size(); i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, fileList.get(i).getOrginFileName());
				pstmt.setString(2, fileList.get(i).getNewFileName());
				pstmt.setString(3, fileList.get(i).getFileSrc());
				pstmt.setString(4, fileList.get(i).getPostsId());
				pstmt.setString(5, fileList.get(i).getProductCode());
				int level = 0;
				if(i == 0) {
					level = 0;
				}else {
					level = 1;
				}
				pstmt.setInt(6, level);

				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}



	public int selectPostsCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int postsId = 0;

		String query = prop.getProperty("selectPostsCurrval");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				postsId = rset.getInt("currval");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}


		return postsId;
	}

	public ArrayList<HashMap<String, Object>> productPay(Connection con, String postsId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>> productPay = null;
		HashMap<String, Object> hmap = null;

		String query = prop.getProperty("productPay");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, postsId);

			rset = pstmt.executeQuery();

			productPay = new ArrayList<HashMap<String, Object>>();
			while(rset.next()) {
				hmap = new HashMap<String, Object>();
				hmap.put("postsId", postsId);
				hmap.put("productCode", rset.getString("PRODUCT_CODE"));
				hmap.put("productName", rset.getString("PRODUCT_NAME"));
				hmap.put("productMoney", rset.getInt("PRODUCT_MONEY"));
				hmap.put("productCate", rset.getString("PRODUCT_CATE"));
				hmap.put("memberId", rset.getString("MEMBER_ID"));
				hmap.put("postsTitle", rset.getString("POSTS_TITLE"));
				hmap.put("newFileName", rset.getString("NEW_FILE_NAME"));
				hmap.put("categoryCode", rset.getString("CATEGORY_CODE"));
				hmap.put("categoryDiv", rset.getString("SUBCATE"));
				hmap.put("mainCateDiv", rset.getString("MAINCATE"));
				hmap.put("deliPay",  rset.getString("DELIVERY_PAY"));


				productPay.add(hmap);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}

		return productPay;
	}



	public int updateMemberEtc(Connection con, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateMemberEtc");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getAccountHolder());
			pstmt.setString(2, member.getBankCode());
			pstmt.setString(3, member.getAccountNo());
			pstmt.setString(4, member.getMemberId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int insertPayOrder(Connection con, Payment pay) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertPayOrder");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pay.getMemberNo());
			pstmt.setString(2, pay.getProductCode());
			pstmt.setString(3, pay.getPayMoney()+"");
			pstmt.setString(4, pay.getMemberNo());
			pstmt.setString(5, pay.getPayMoney()+"");
			pstmt.setString(6, pay.getReceipt());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public String selectSite(Connection con, Payment pay) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String siteNum = null;

		String query = prop.getProperty("selectSite");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pay.getSubDeliverySite());
			pstmt.setString(2, pay.getMemberNo());
			rset = pstmt.executeQuery();

			if(rset.next()) {
				siteNum = rset.getString("DELIVERY_SITE_NO");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return siteNum;
	}

	public int insertSite(Connection con, Payment pay) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertSite");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pay.getDeliverySite());
			pstmt.setString(2, pay.getRecieverName());
			pstmt.setString(3, pay.getRecieverPhone());
			pstmt.setString(4, pay.getMemberNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public String selectOrderCurr(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String orderNo = "";

		String query = prop.getProperty("orderCurr");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				orderNo = rset.getString("currval");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}

		return orderNo;
	}

	public String siteCurr(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		String siteNo = "";

		String query = prop.getProperty("siteCurr");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				siteNo = rset.getString("currval");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}

		return siteNo;
	}

	public int insertDelivery(Connection con, Payment pay) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertDelivery");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pay.getDeliverySiteCode());
			pstmt.setString(2, pay.getMemberNo());
			pstmt.setString(3, pay.getOrderNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int disabledPostOpen(Connection con, Payment pay) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("disabledPostOpen");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pay.getProductCode());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("selectCalculateCount");

		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}

		return listCount;
	}

	public ArrayList<Calcul> selectCalcul(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Calcul> list = null;

		String query = prop.getProperty("selectCalculate");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<Calcul>();

			while(rset.next()) {
				Calcul c = new Calcul();

				c.setAdjustNo(rset.getString("adNo"));
				c.setPayDtno(rset.getString("pno"));
				c.setPrice(rset.getString("money"));
				c.setMemberNo(rset.getString("memberNo"));
				c.setAdjustDiv(rset.getString("div"));
				c.setAdjustDate(rset.getString("aDate"));
				c.setReceipt(rset.getString("receipt"));
				list.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return list;
	}

	public int disposeSuccess(Connection con, String code) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("disposeSuccess");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int disposeFail(Connection con, String code) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("disposeFail");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int getListCountall(Connection con, String selOption, String selectDate) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("calculateSearchCount1");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selOption);
			pstmt.setString(2, selectDate);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return listCount;
	}

	public int getListCountSeachOp(Connection con, String selOption) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("calculateSearchCount2");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selOption);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return listCount;
	}

	public int getListCountSeachDt(Connection con, String selectDate) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("calculateSearchCount3");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selectDate);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return listCount;
	}

	public ArrayList<Calcul> selectCalculSearch(Connection con, int currentPage, int limit, String selOption,
			String selectDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Calcul> list = null;

		String query = prop.getProperty("selectCalculSearch");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, selOption);
			pstmt.setString(4, selectDate);
			rset = pstmt.executeQuery();

			list = new ArrayList<Calcul>();

			while(rset.next()) {
				Calcul c = new Calcul();

				c.setAdjustNo(rset.getString("adNo"));
				c.setPayDtno(rset.getString("pno"));
				c.setPrice(rset.getString("money"));
				c.setMemberNo(rset.getString("memberNo"));
				c.setAdjustDiv(rset.getString("div"));
				c.setAdjustDate(rset.getString("aDate"));

				list.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return list;
	}

	public ArrayList<Calcul> selectCalculSearchOp(Connection con, int currentPage, int limit, String selOption) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Calcul> list = null;

		String query = prop.getProperty("selectCalculSearchOp");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, selOption);
			rset = pstmt.executeQuery();

			list = new ArrayList<Calcul>();

			while(rset.next()) {
				Calcul c = new Calcul();

				c.setAdjustNo(rset.getString("adNo"));
				c.setPayDtno(rset.getString("pno"));
				c.setPrice(rset.getString("money"));
				c.setMemberNo(rset.getString("memberNo"));
				c.setAdjustDiv(rset.getString("div"));
				c.setAdjustDate(rset.getString("aDate"));

				list.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return list;
	}

	public ArrayList<Calcul> selectCalculSearchDt(Connection con, int currentPage, int limit, String selectDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Calcul> list = null;

		String query = prop.getProperty("selectCalculSearchDt");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, selectDate);
			rset = pstmt.executeQuery();

			list = new ArrayList<Calcul>();

			while(rset.next()) {
				Calcul c = new Calcul();

				c.setAdjustNo(rset.getString("adNo"));
				c.setPayDtno(rset.getString("pno"));
				c.setPrice(rset.getString("money"));
				c.setMemberNo(rset.getString("memberNo"));
				c.setAdjustDiv(rset.getString("div"));
				c.setAdjustDate(rset.getString("aDate"));

				list.add(c);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}




	public int getRefundManagerListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("selectRefundManagerCount");


		try {
			pstmt = con.prepareStatement(query);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return listCount;
	}





	public ArrayList<Refund> selectRefundManagerList(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Refund> rList = null;

		String query = prop.getProperty("selectRefundManagerList");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Refund>();

			while(rset.next()) {
				Refund r = new Refund();

				r.setOno(rset.getString("ORDER_NO"));
				r.setrDate(rset.getDate("REFUND_DATE"));
				r.setMno(rset.getString("MEMBER_NO"));
				r.setcStatus(rset.getString("COMPLETE_STATUS"));
				r.setrStatus(rset.getString("REFUND_STATUS"));
				r.setPno(rset.getString("PAY_NO"));
				r.setpCode(rset.getString("PRODUCT_CODE"));

				rList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return rList;

	}


	public int passRefund1(Connection con, String pass, String pno) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("passRefund1");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int passRefund2(Connection con, String pass, String pno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("passRefund2");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int passAdjust1(Connection con, String pass, String pcode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("passAdjust1");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pcode);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int passAdjust2(Connection con, String pass, String pcode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("passAdjust2");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pcode);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<HashMap<String, Object>> productPay(Connection con, String[] list) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>> productPay = null;
		HashMap<String, Object> hmap = null;

		String query = prop.getProperty("basketPay");

		try {
			productPay = new ArrayList<HashMap<String, Object>>();
			for(int i = 0; i < list.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, list[i]);

				rset = pstmt.executeQuery();
				hmap = new HashMap<String, Object>();
				while(rset.next()) {
					hmap.put("postsId", rset.getString("POSTS_ID"));
					hmap.put("productCode", rset.getString("PRODUCT_CODE"));
					hmap.put("productName", rset.getString("PRODUCT_NAME"));
					hmap.put("productMoney", rset.getInt("PRODUCT_MONEY"));
					hmap.put("productCate", rset.getString("PRODUCT_CATE"));
					hmap.put("memberId", rset.getString("MEMBER_ID"));
					hmap.put("postsTitle", rset.getString("POSTS_TITLE"));
					hmap.put("newFileName", rset.getString("NEW_FILE_NAME"));
					hmap.put("categoryCode", rset.getString("CATEGORY_CODE"));
					hmap.put("categoryDiv", rset.getString("SUBCATE"));
					hmap.put("mainCateDiv", rset.getString("MAINCATE"));
					hmap.put("deliPay",  rset.getString("DELIVERY_PAY"));

					productPay.add(hmap);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}

		return productPay;
	}

	public int deleteBacket(Connection con, String codeList) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteBacket");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, codeList);

			result = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<String> searchMyAdd(Connection con, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> searchMyAdd = new ArrayList();

		String address = null;

		String query = prop.getProperty("searchMyAdd");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberNo);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				address = rset.getString("ADDRESS");
			}

			String[] addressArr = address.split("\\$");

			for(int i = 0; i < addressArr.length; i++) {
				searchMyAdd.add(addressArr[i]);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return searchMyAdd;
	}

	public String searchSeller(Connection con, String code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String seller = null;

		String query = prop.getProperty("searchSeller");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);

			rset = pstmt.executeQuery();
			if(rset.next()) {
				seller = rset.getString("MEMBER_NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return seller;
	}

	public int updateSellerInfo(Connection con, String seller) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateSellerInfo");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, seller);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Refund> searchWait(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Refund> rList = null;

		String query = prop.getProperty("selectWait");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Refund>();

			while(rset.next()) {
				Refund r = new Refund();

				r.setOno(rset.getString("ORDER_NO"));
				r.setrDate(rset.getDate("REFUND_DATE"));
				r.setMno(rset.getString("MEMBER_NO"));
				r.setcStatus(rset.getString("COMPLETE_STATUS"));
				r.setrStatus(rset.getString("REFUND_STATUS"));
				r.setPno(rset.getString("PAY_NO"));
				r.setpCode(rset.getString("PRODUCT_CODE"));

				rList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return rList;

	}


	public ArrayList<Refund> searchSuccess(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Refund> rList = null;

		String query = prop.getProperty("selectSuccess");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Refund>();

			while(rset.next()) {
				Refund r = new Refund();

				r.setOno(rset.getString("ORDER_NO"));
				r.setrDate(rset.getDate("REFUND_DATE"));
				r.setMno(rset.getString("MEMBER_NO"));
				r.setcStatus(rset.getString("COMPLETE_STATUS"));
				r.setrStatus(rset.getString("REFUND_STATUS"));
				r.setPno(rset.getString("PAY_NO"));
				r.setpCode(rset.getString("PRODUCT_CODE"));

				rList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return rList;
	}

	public ArrayList<Refund> searchCancel(Connection con, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Refund> rList = null;

		String query = prop.getProperty("selectCancel");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Refund>();

			while(rset.next()) {
				Refund r = new Refund();

				r.setOno(rset.getString("ORDER_NO"));
				r.setrDate(rset.getDate("REFUND_DATE"));
				r.setMno(rset.getString("MEMBER_NO"));
				r.setcStatus(rset.getString("COMPLETE_STATUS"));
				r.setrStatus(rset.getString("REFUND_STATUS"));
				r.setPno(rset.getString("PAY_NO"));
				r.setpCode(rset.getString("PRODUCT_CODE"));

				rList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}


		return rList;
	}

	public int getrListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;

		String query = prop.getProperty("rListCount");

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

	public int deleteProduct(Connection con, String num) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteProduct");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, num);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}



}













