package com.kh.bvengers.manager.depot.model.servies;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.bvengers.manager.depot.model.dao.DepotDao;
import com.kh.bvengers.manager.depot.model.vo.Depot;
import static com.kh.bvengers.common.JDBCTemplate.*;

public class DepotService {

	public ArrayList<Depot> selectCheckAll(int currentPage, int limit) {
		Connection con = getConnection();
		
		ArrayList<Depot>list = new DepotDao().selectCheckAll(con,currentPage,limit);
		
		close(con);
		return list;
	}

	public int requsetCheck(Depot d) {
		Connection con =getConnection();
		
		int result = new DepotDao().insertLocation(con,d);
		int result1= new DepotDao().updateStatus(con,d);
		int result2 = new DepotDao().updateOpen(con,d.getProductCode());
				
		int lastNum =0;
		if(result>0||result1>0||result2>0) {
			commit(con);
			lastNum=1;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return lastNum;
	}

	public ArrayList<Depot> selectAllList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().selectAllList(con,currentPage,limit);
		close(con);
		return list;
	}

	public ArrayList<Depot> selectBarcodeAllList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().selectBarcodeAllList(con,currentPage,limit);
		close(con);
		return list;
	}

	public ArrayList<Depot> selectInList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list =new DepotDao().selectInList(con,currentPage,limit);
		close(con);		
		return list;
	}

	public int updateRelesDate(String str) {
		Connection con = getConnection();
		int result = new DepotDao().updateRelesDate(con,str);		
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<Depot> outProductList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().outProductList(con,currentPage,limit);		
		close(con);
		return list;
	}

	public int getAllListCount() {
		Connection con = getConnection();
		int listCount = new DepotDao().getAllListCount(con);
		
		close(con);
		return listCount;
	}

	public int getOutProductListCount() {
		Connection con = getConnection();
		int listCount = new DepotDao().getOutProductListCount(con);
		
		close(con);
		return listCount;
	}

	public int getBarcodeCreateListCount() {
		Connection con = getConnection();
		int listCount = new DepotDao().getBarcodeCreateListCount(con);
		
		close(con);
		return listCount;
	}

	public int getInDepotCount() {
		Connection con = getConnection();
		int listCount = new DepotDao().getInDepotCount(con);
		
		close(con);
		return listCount;
	}

	public int getCheckListCount() {
		Connection con = getConnection();
		int listCount = new DepotDao().getCheckListCount(con);
		
		close(con);
		return listCount;
	}

	public int searchL(String location) {
		Connection con =getConnection();
		int listCount = new DepotDao().serchL(con,location);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListL(String location, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListL(con,location,currentPage,limit);
		close(con);
		return list;
	}

	public int searchId(String insertDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().serchId(con,insertDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListId(String insertDate, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListId(con,insertDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchRd(String releaseDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().serchRd(con,releaseDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListRd(String releaseDate, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListRd(con,releaseDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchP(String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().serchP(con,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListP(String productCode, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListP(con,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLId(String location, String insertDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().serchLId(con,location,insertDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLId(String location, String insertDate, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLId(con,location,insertDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLRd(String location, String releaseDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchLRd(con,location,releaseDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLRd(String location, String releaseDate, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLRd(con,location,releaseDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLP(String location, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchLP(con,location,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLP(String location, String productCode, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLP(con,location,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchIdRd(String insertDate, String releaseDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchIdRd(con,insertDate,releaseDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListIdRd(String insertDate, String releaseDate, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListIdRd(con,insertDate,releaseDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchIdp(String insertDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchIdp(con,insertDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListIdp(String insertDate, String productCode, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListIdp(con,insertDate,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchRdP(String releaseDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchRdP(con,releaseDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListRdP(String releaseDate, String productCode, int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListRdP(con,releaseDate,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLIdRd(String location, String insertDate, String releaseDate) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchLIdRd(con,location,insertDate,releaseDate);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLIdRd(String location, String insertDate, String releaseDate, int currentPage,
			int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLIdRd(con,location,insertDate,releaseDate,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLIdP(String location, String insertDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchLIdP(con,location,insertDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLIdP(String location, String insertDate, String productCode, int currentPage,
			int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLIdP(con,location,insertDate,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchLRdP(String location, String releaseDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchLRdP(con,location,releaseDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListLRdP(String location, String releaseDate, String productCode, int currentPage,
			int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListLRdP(con,location,releaseDate,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchIdRdP(String insertDate, String releaseDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchIdRdP(con,insertDate,releaseDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListIdRdP(String insertDate, String releaseDate, String productCode, int currentPage,
			int limit) {
		Connection con = getConnection();
		ArrayList<Depot> list = new DepotDao().searchListIdRdP(con,insertDate,releaseDate,productCode,currentPage,limit);
		close(con);
		return list;
	}

	public int searchTotal(String location, String insertDate, String releaseDate, String productCode) {
		Connection con =getConnection();
		int listCount = new DepotDao().searchTotal(con,location,insertDate,releaseDate,productCode);
		close(con);
		return listCount;
	}

	public ArrayList<Depot> searchListTotal(String location, String insertDate, String releaseDate, String productCode,
			int currentPage, int limit) {
		  Connection con = getConnection();
			ArrayList<Depot> list = new DepotDao().searchListTotal(con,location,insertDate,releaseDate,productCode,currentPage,limit);
			close(con);
			return list;
	}

	public ArrayList<Depot> excelPrint(String[] productCode) {
		Connection con = getConnection();
		ArrayList <Depot> list = new DepotDao().excelPrint(con,productCode);
		close(con);
		return list;
	}

	public Depot productDetail(String productCode) {
		Connection con = getConnection();
		Depot list = new DepotDao().productDetail(con,productCode);		
		return list;
	}

}
