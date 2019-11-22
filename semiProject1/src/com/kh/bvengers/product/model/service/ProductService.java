package com.kh.bvengers.product.model.service;

import static com.kh.bvengers.common.JDBCTemplate.close;
import static com.kh.bvengers.common.JDBCTemplate.commit;
import static com.kh.bvengers.common.JDBCTemplate.getConnection;
import static com.kh.bvengers.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.bvengers.board.model.vo.Attachment;
import com.kh.bvengers.board.model.vo.Posts;
import com.kh.bvengers.board.model.vo.PostsContents;
import com.kh.bvengers.manager.member.model.dao.ManagerMemberDao;
import com.kh.bvengers.manager.member.model.vo.Report;
import com.kh.bvengers.product.model.dao.ProductDao;
import com.kh.bvengers.product.model.vo.Calcul;
import com.kh.bvengers.product.model.vo.Payment;
import com.kh.bvengers.product.model.vo.Product;
import com.kh.bvengers.product.model.vo.Refund;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.myPage.model.Dao.MyPageDao;
import com.kh.bvengers.user.myPage.model.vo.myPage;

public class ProductService {

	//상품등록
		public int insertProductPost(Member member, Posts posts, PostsContents postsContents, Product product,
				ArrayList<Attachment> fileList) {
			Connection con = getConnection();
			int result = 0;
			int result1 = 0;
			int result2 = 0;
			int result3 = 0;
			int result4 = 0;
			int result5 = 0;
			int result6 = 0;

			result1 = new ProductDao().insertProductPost(con, posts);

			if(result1 > 0) {
				int postsId = new ProductDao().selectPostsCurrval(con);
				postsContents.setPostsId(postsId);
				product.setPostId(postsId+"");
				for(int i = fileList.size()-1; i >= 0; i--) {
				fileList.get(i).setPostsId(postsId+"");
				}
			}else {
				return result;
			}

			result2 = new ProductDao().insertPostContents(con, postsContents);
			result3 = new ProductDao().insertProduct(con, product);
			result4 = new ProductDao().insertProductCheck(con, product);

			result5 = new ProductDao().insertAttachments(con, fileList);
			result6 = new ProductDao().updateMemberEtc(con, member);

			if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0 && result6 > 0) {
				commit(con);
				result = 1;
			}else {
				rollback(con);
			}

			close(con);


			return result;
		}

		public ArrayList<HashMap<String, Object>> productPay(String postsId) {
			Connection con = getConnection();

			ArrayList<HashMap<String, Object>> productPay = new ProductDao().productPay(con, postsId);

			close(con);

			return productPay;
		}

		public int okPay(Payment pay, int count, String[] codeList, String[] priceList) {
			Connection con = getConnection();
			int result = 0;
			int result1 = 0;
			int result2 = 0;
			int result3 = 0;
			int result4 = 0;
			int result5 = 0;
			String siteCheck = null;
			String orderNo = "";

			int check = 0;
			for(int i = 0; i < count; i++) {
				pay.setProductCode(codeList[i]);
				pay.setPayMoney(Integer.parseInt(priceList[i]));

				result1 = new ProductDao().insertPayOrder(con, pay);

				orderNo = new ProductDao().selectOrderCurr(con);
				pay.setOrderNo(orderNo);

				siteCheck = new ProductDao().selectSite(con, pay);

				if(siteCheck == null) {	//테이블에 주소 등록이 안되있을 시
					result2 = new ProductDao().insertSite(con, pay);

					siteCheck = new ProductDao().siteCurr(con);

				}else {
					result2 = 1;
				}

				pay.setDeliverySiteCode(siteCheck);

				result3 = new ProductDao().insertDelivery(con, pay);

				result4 = new ProductDao().disabledPostOpen(con, pay);

				result5 = new ProductDao().deleteBacket(con, codeList[i]);

				if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
					check += 1;
				}
			}

			if(check == count) {
				commit(con);
				result = 1;
			}else {
				rollback(con);
			}

			return result;
		}

		public int getListCount() {
			Connection con = getConnection();

			int result = new ProductDao().getListCount(con);


			return result;
		}

		public ArrayList<Calcul> selectCalcul(int currentPage, int limit) {
			Connection con = getConnection();

			ArrayList<Calcul> list = new ProductDao().selectCalcul(con, currentPage, limit);

			close(con);

			return list;


		}

		public ArrayList<Calcul> disposeSuccess(String[] code, int currentPage, int limit) {
			Connection con = getConnection();
			ArrayList<Calcul> list = null;
			int result1 = 0;
			int result2 = 0;
			String seller = "";
			for(int i = 0; i < code.length; i++) {
				result1 = new ProductDao().disposeSuccess(con, code[i]);
				seller = new ProductDao().searchSeller(con, code[i]);
				result2 = new ProductDao().updateSellerInfo(con, seller);
				if(result1 > 0 &&result2 > 0) {
					commit(con);
				}else {
					rollback(con);
				}

			}

			list = new ProductDao().selectCalcul(con, currentPage, limit);

			return list;
		}

		public ArrayList<Calcul> disposeFail(String[] code, int currentPage, int limit) {
			Connection con = getConnection();
			ArrayList<Calcul> list = null;
			int result = 0;

			for(int i = 0; i < code.length; i++) {
				result = new ProductDao().disposeFail(con, code[i]);
				if(result > 0 ) {
					commit(con);
				}else {
					rollback(con);
				}

			}

			list = new ProductDao().selectCalcul(con, currentPage, limit);

			return list;
		}

		public int getListCountall(String selOption, String selectDate) {
			Connection con = getConnection();

			int result = new ProductDao().getListCountall(con, selOption, selectDate);

			close(con);

			return result;
		}

		public int getListCountSeachOp(String selOption) {
			Connection con = getConnection();

			int result = new ProductDao().getListCountSeachOp(con, selOption);

			close(con);

			return result;
		}

		public int getListCountSeachDt(String selectDate) {
			Connection con = getConnection();

			int result = new ProductDao().getListCountSeachDt(con, selectDate);

			close(con);

			return result;
		}

		public ArrayList<Calcul> selectCalculSearch(int currentPage, int limit, String selOption, String selectDate) {
			Connection con = getConnection();

			ArrayList<Calcul> list = new ProductDao().selectCalculSearch(con, currentPage, limit, selOption, selectDate);

			close(con);

			return list;
		}

		public ArrayList<Calcul> selectCalculSearchOp(int currentPage, int limit, String selOption) {
			Connection con = getConnection();

			ArrayList<Calcul> list = new ProductDao().selectCalculSearchOp(con, currentPage, limit, selOption);

			close(con);

			return list;
		}

		public ArrayList<Calcul> selectCalculSearchDt(int currentPage, int limit, String selectDate) {
			Connection con = getConnection();

			ArrayList<Calcul> list = new ProductDao().selectCalculSearchDt(con, currentPage, limit, selectDate);

			close(con);

			return list;
		}



		public int getRefundManagerListCount() {
			Connection con = getConnection();

			int listCount = new ProductDao().getRefundManagerListCount(con);

			close(con);

			return listCount;
		}



		public ArrayList<Refund> selectRefundManagerList(int currentPage, int limit) {
			Connection con = getConnection();

			ArrayList<Refund> rList = new ProductDao().selectRefundManagerList(con, currentPage, limit);

			close(con);

			return rList;


		}

		public int passRefund(String pass, String pno, String pcode) {
			Connection con = getConnection();
			int result = 0;
			int result1 = 0;
			int result2 = 0;


			if(pass.equals("1")) {
				result1 = new ProductDao().passRefund1(con, pass, pno);
				result2 = new ProductDao().passAdjust1(con, pass, pcode);

			}else if(pass.equals("2")) {
				result1 = new ProductDao().passRefund2(con, pass, pno);
				result2 = new ProductDao().passAdjust2(con, pass, pcode);
			}

			if(result1 > 0 && result2 > 0) {
				commit(con);
				result = 1;
			}else {
				rollback(con);
			}


			return result;
		}

		public ArrayList<HashMap<String, Object>> productPay(String[] list) {
			Connection con = getConnection();

			ArrayList<HashMap<String, Object>> productPay = new ProductDao().productPay(con, list);

			close(con);

			return productPay;
		}

		public ArrayList<String> searchMyAdd(String memberNo) {
			Connection con = getConnection();
			ArrayList<String> searchMyAdd = new ProductDao().searchMyAdd(con, memberNo);

			close(con);

			return searchMyAdd;
		}

		public ArrayList<Refund> searchWait(int currentPage, int limit) {
			Connection con = getConnection();
			ArrayList<Refund> rList = new ProductDao().searchWait(con, currentPage, limit);
			close(con);
			return rList;

		}

		public ArrayList<Refund> searchSuccess(int currentPage, int limit) {
			Connection con = getConnection();
			ArrayList<Refund> rList = new ProductDao().searchSuccess(con, currentPage, limit);
			close(con);
			return rList;
		}

		public ArrayList<Refund> searchCancel(int currentPage, int limit) {
			Connection con = getConnection();
			ArrayList<Refund> rList = new ProductDao().searchCancel(con, currentPage, limit);
			close(con);
			return rList;
		}

		public int getrListCount() {
			Connection con = getConnection();
			int listCount = new ProductDao().getrListCount(con);
			close(con);
			return listCount;
		}

		public int deleteProduct(String num) {
			Connection con = getConnection();
			int result = new ProductDao().deleteProduct(con, num);

			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			return result;
		}


}
