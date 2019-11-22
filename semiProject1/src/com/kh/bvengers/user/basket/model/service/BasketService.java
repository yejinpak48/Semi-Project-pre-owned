package com.kh.bvengers.user.basket.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.bvengers.user.basket.model.dao.BasketDao;
import com.kh.bvengers.user.basket.model.vo.Basket;
import static com.kh.bvengers.common.JDBCTemplate.*;

public class BasketService {

	public ArrayList<Basket> selectOneBasket(String fileName, String userId) {
		Connection con =getConnection();
		ArrayList<Basket> list = new BasketDao().selectOneBasket(con,fileName);
		if(list!=null) {
			int result = new BasketDao().insertBasket(con,list,userId);
			if(result>0) {
				commit(con);
			}else {
				rollback(con);
				list=null;
			}
		}
		close(con);
		return list;
	}

	public ArrayList<Basket> basketAllList(String userNo) {
		Connection con = getConnection();
		ArrayList<Basket> list = new BasketDao().basketAllList(con,userNo);		
		return list;
	}

	public ArrayList<Basket> deleteBasketList(String[] code, String userNo) {
		Connection con = getConnection();
		ArrayList<Basket>list =null;
		int result = 0;
		
		
		for(int i =0;i<code.length;i++) {
			result =new BasketDao().deleteBasket(con,code[i],userNo);
			if(result>0) {
				commit(con);
			}else {
				rollback(con);
			}
		}
		list = new BasketDao().basketAllList(con,userNo);			
		
		return list;
	}

	public ArrayList<Basket> changeTotal(String[] productCode) {
		Connection con = getConnection();
		ArrayList <Basket> list = new BasketDao().changeTotal(con,productCode);		
		return list;
	}

}
