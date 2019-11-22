package com.kh.bvengers.user.myPage.model.Service;

import static com.kh.bvengers.common.JDBCTemplate.close;
import static com.kh.bvengers.common.JDBCTemplate.commit;
import static com.kh.bvengers.common.JDBCTemplate.getConnection;
import static com.kh.bvengers.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.bvengers.user.myPage.model.Dao.MyPageDao;
import com.kh.bvengers.user.myPage.model.vo.myPage;

public class MyPageService {

	public ArrayList<myPage> selectMyPageList(String memberNo, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> mplist = new MyPageDao().selectMyPageList(con, memberNo, currentPage, limit);
		
		close(con);
		
		return mplist;
	}

	
	//전체 게시물 수 조회용 메소드
	public int getListCount(String memberNo) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getListCount(con, memberNo);
		
		close(con);
		
		return listCount;
	}


	public ArrayList<myPage> selectDeliReadyList(String memberNo) {
		Connection con = getConnection();
		
		ArrayList<myPage> drlist = new MyPageDao().selectDeliReadyList(con, memberNo);
		
		close(con);
		
		return drlist;
	}


	public ArrayList<myPage> selectOrderLookList(String memberNo, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> olList = new MyPageDao().selectOrderLookList(con, memberNo, currentPage, limit);
		
		close(con);
				
		return olList;
	
	}


	public ArrayList<myPage> selectOrderDetailList(String memberNo, String ono) {
		Connection con = getConnection();
		ArrayList<myPage> odList = new MyPageDao().selectOrderDetailList(con, memberNo, ono);

		close(con);
		
		return odList;
	}


	public int getOrderLookListCount(String memberNo) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getOrderLookListCount(con, memberNo);
		
		close(con);
		
		return listCount;
	}


	public int getOrderDateCount(String memberNo, String start, String end) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getOrderDateCount(con, memberNo, start, end);
		
		close(con);
		
		return listCount;
	}


	public ArrayList<myPage> orderDateList(String memberNo, String start, String end, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> dateList = new MyPageDao().selectOrderDateList(con, memberNo, start, end, currentPage, limit);
		
		close(con);
		
		return dateList;
	}


	public int[] getrcListCount(String memberNo) {
		Connection con = getConnection();

		int listCountR = new MyPageDao().getListCountR(con, memberNo);
		int listCountC = new MyPageDao().getListCountC(con, memberNo);

		int[] array = new int[2];

		array[0] = listCountR;
		array[1] = listCountC;

		close(con);

		return array;
	}


	public ArrayList<myPage> selectRefundList(String memberNo, int currentPage, int limit) {
		Connection con = getConnection();

		ArrayList<myPage> rList = new MyPageDao().selectRefundList(con, memberNo, currentPage, limit);

		close(con);

		return rList;
		}


	public ArrayList<myPage> selectCalculateList(String memberNo, int currentPage1, int limit1) {
		Connection con = getConnection();
		ArrayList<myPage> cList = new MyPageDao().selectCalculateList(con, memberNo, currentPage1, limit1);
		
		close(con);
		
		return cList;
	
	}


	public int getRefundDateCount(String memberNo, String start, String end) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getRefundDateCount(con, memberNo, start, end);
		
		close(con);
		
		return listCount;
	}


	public ArrayList<myPage> refundDateList(String memberNo, String start, String end, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> rList = new MyPageDao().selectRefundDateList(con, memberNo, start, end, currentPage, limit);
		
		close(con);
		
		return rList;
	}


	public int getCalculateDateCount(String memberNo, String start, String end) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getCalculateDateCount(con, memberNo, start, end);
		
		close(con);
		
		return listCount;
	}


	public ArrayList<myPage> calculateDateList(String memberNo, String start, String end, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> cList = new MyPageDao().selectCalculateDateList(con, memberNo, start, end, currentPage, limit);
		
		close(con);
		
		return cList;
	}


	public int refundApply(String ono, String memberNo) {
		Connection con = getConnection();
		int result = 0;
		int result1 = 0;
		int result2 = 0;

		
		String pno = new MyPageDao().selectPayNo(con, ono, memberNo);
		String pcode = new MyPageDao().selectPcode(con, ono);
		result1 = new MyPageDao().passRefund(con, pno);
		result2 = new MyPageDao().passProduct(con, pcode);
		if(result1 > 0 && result2 > 0) {
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		
		
		return result;
	}


	public int getSaleListCount(String memberNo) {
		Connection con = getConnection();
		int listCount = new MyPageDao().getSaleListCount(con, memberNo);
		close(con);
		
		return listCount;
	
	}


	public ArrayList<myPage> selectSaleList(String memberNo, int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<myPage> sList = new MyPageDao().selectSaleList(con, memberNo, currentPage, limit);
		
		close(con);
		
		return sList;
	}


	public int getCancelLookCount(String memberNo) {
		Connection con = getConnection();

		int listCount = new MyPageDao().getCancelLookCount(con, memberNo);
		
		close(con);
		
		return listCount;
	
	
	}


	public ArrayList<myPage> selectCancelLook(String memberNo, String ono, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<myPage> cList = new MyPageDao().getCancelLookList(con, memberNo, currentPage, limit);
		close(con);
		
		return cList;
	}


	public int getCancelDateLookCount(String memberNo, String start, String end, int currentPage, int limit) {
		Connection con = getConnection();
		
		int listCount = new MyPageDao().getCancelDateLookCount(con, memberNo, start, end, currentPage, limit);
		
		close(con);
		
		return listCount;
		
		
	}


	public ArrayList<myPage> cancelDateList(String memberNo, String start, String end, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<myPage> dateList = new MyPageDao().selectCancelDateList(con, memberNo, start, end, currentPage, limit);
		
		
		close(con);
		
		return dateList;
	}


	public int cancelApply(String ono, String memberNo) {
		Connection con = getConnection();
		int result = 0;
		
		String pno = new MyPageDao().selectPayNo(con, ono, memberNo);
		
		result = new MyPageDao().cancelOrder(con, pno);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
		
	}


	public ArrayList<myPage> selectRefundDate(String memberNo, String ono) {
		Connection con = getConnection();
		ArrayList<myPage> refundDate = null;
		
		refundDate = new MyPageDao().selectRefundDate(con, memberNo, ono);
		
		close(con);
		
		return refundDate;
	}

}



