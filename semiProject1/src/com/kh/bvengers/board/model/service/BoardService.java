package com.kh.bvengers.board.model.service;

import static com.kh.bvengers.common.JDBCTemplate.close;
import static com.kh.bvengers.common.JDBCTemplate.commit;
import static com.kh.bvengers.common.JDBCTemplate.getConnection;
import static com.kh.bvengers.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.bvengers.board.model.dao.BoardDao;
import com.kh.bvengers.board.model.vo.Attachment;
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.board.model.vo.Calculate;
import com.kh.bvengers.board.model.vo.Comment;
import com.kh.bvengers.board.model.vo.PowerLink;
import com.kh.bvengers.product.model.dao.ProductDao;
import com.kh.bvengers.product.model.vo.Calcul;

public class BoardService {

	public ArrayList<HashMap<String, Object>> selectProductList() {
		Connection con = getConnection();
		ArrayList<HashMap<String, Object>> list = new BoardDao().selectProductList(con);

		close(con);
		return list;
	}

	public HashMap<String, Object> selectOneProduct(int num) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;

		int result = new BoardDao().updateCount(con, num);

		if (result > 0) {
			hmap = new BoardDao().selectOneProduct(con, num);
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return hmap;
	}

	//공지사항 게시판 작성용
		public int insertNotice(Board b, ArrayList<Attachment> fileList) {
			Connection con = getConnection();

			int result = new BoardDao().insertNoticeContent(con,b);

			int result1 = 0;

			if(fileList.get(0).getOrginFileName() != null) {

			if(result > 0) {
				String postId = new BoardDao().selectCurrval(con)+"";
				for(int i = 0; i < fileList.size(); i++) {
					fileList.get(i).setPostsId(postId);
				}
			}
			result1= new BoardDao().insertAttachment(con, fileList);

			}else {
				result1 = 1;
			}
			if(result > 0 && result1 >0) {
				commit(con);
				result = 1;
			}else {
				rollback(con);
			}
			return result;
		}
		//자주찾는 질문 작성
		public int insertNotice(Board b) {
			Connection con = getConnection();

			int result = new BoardDao().insertFrequentQuestion(con,b);

			if(result > 0) {
				commit(con);
			}else {
				rollback(con);
			}

			close(con);

			return result;
		}



	public ArrayList<HashMap<String, Object>> searchProductByTitle(String value) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		ArrayList<HashMap<String, Object>> list = new BoardDao().searchProductByTitle(con, value);
		close(con);
		return list;
	}

	public ArrayList<HashMap<String, Object>> searchProductByCategory(String value) {
		Connection con = getConnection();
		ArrayList<HashMap<String, Object>> list = new BoardDao().searchProductByCategory(con, value);
		close(con);
		return list;
	}

	public ArrayList<HashMap<String, Object>> searchProductByContent(String value) {
		Connection con = getConnection();
		ArrayList<HashMap<String, Object>> list = new BoardDao().searchProductByContents(con, value);
		close(con);
		return list;
	}


	public ArrayList<PowerLink> powerLink() {
		Connection con = getConnection();
		ArrayList<PowerLink> list = new BoardDao().powerLink(con);
		return list;
	}

	public int[] getListCount(int num) {
		Connection con = getConnection();


		int listCount = new BoardDao().getListCount(con, num);


		int listCount2 = new BoardDao().getListCount2(con);

		int[] array = new int[2];

		array[0] = listCount;
		array[1] = listCount2;

		close(con);

		return array;

	}

	public int getListQandACount(int num, String uno) {

		Connection con = getConnection();

		int listCount = new BoardDao().getListQnACount(con,num,uno);

		close(con);

		return listCount;
	}

	public ArrayList<Board> selectList(int currentPage, int limit){

		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectList(con,currentPage,limit);

		close(con);

		return list;
	}


	public ArrayList<Board> selectList1(int currentPage1, int limit1) {

		Connection con = getConnection();

		ArrayList<Board>list1 = new BoardDao().selectList1(con,currentPage1,limit1);

		close(con);

		return list1;
	}

	public ArrayList<Board> selectQandAList(int currentPage1, int limit, int num ,String uno) {

		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectQandAList(con,currentPage1,limit,num,uno);


		close(con);

		return list;
	}



	public ArrayList<Board> selectQuestionList(int limit) {

		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectQuestionList(con,limit);


		close(con);

		return list;
	}

	public ArrayList<Board> selectManagerList(int currentPage, int limit, int num) {

		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectManagerQnAList(con,currentPage,limit,num);

		close(con);


		return list;
	}


	public HashMap<String, Object> selectOneNotice(int num) {
		Connection con = getConnection();

		HashMap<String, Object> hmap = null;

		Board b = null;

		Attachment at = null;

    int result = new BoardDao().updateCount(con,num);

		b = new BoardDao().selectOnecontent(con,num);

		at = new BoardDao().selectOnePicture(con,num);

		hmap = new HashMap<String,Object>();
		hmap.put("board", b);
		hmap.put("attachment", at);

		if(result > 0 && hmap != null) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);

		return hmap;
	}

	public ArrayList<HashMap<String, Object>> mainList(String value) {
	      Connection con = getConnection();
	      ArrayList<HashMap<String, Object>> list = new BoardDao().mainList(con, value);

	      close(con);
	      return list;

	}


	public ArrayList<Comment> insertComment(Comment b) {
		Connection con = getConnection();
		ArrayList<Comment> commentList = null;

		int result = new BoardDao().insertComment(con, b);

		if(result > 0) {
			commit(con);
			commentList = new BoardDao().selectCommentList(con, b.getPostsId());
		} else {
			rollback(con);
		}
		close(con);
		return commentList;
	}

	public ArrayList<Comment> selectComment(Comment b) {
		Connection con = getConnection();
		ArrayList<Comment> commentList = null;

		commentList = new BoardDao().selectCommentList(con, b.getPostsId());
		close(con);
		return commentList;
	}


	public ArrayList<Calculate> paymentManagement() {
		Connection con = getConnection();

		ArrayList<Calculate> list = new BoardDao().paymentManagement(con);

		int count = list.size() +1;
		for(int i = 0; i < list.size(); i++) {
			int result = 0;

			result = new BoardDao().checkStatus(con, list.get(i));

			if(result > 0) {
				count--;
			}
		}

		close(con);


		return list;
	}

	public int insertReport(String dustId, String post_id, String content, String reporter) {
		Connection con = getConnection();

		int result = new BoardDao().insertReport(con,dustId,post_id,content,reporter);

		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}

		return result;
	}


	public int getListCount() {
		Connection con = getConnection();

		int listCounter = new BoardDao().getListCount(con);
		return listCounter;
	}

	public ArrayList<Calculate> paymentManagement(int currentPage, int limit) {
		Connection con = getConnection();

		ArrayList<Calculate> list = new BoardDao().paymentManagement(con, currentPage, limit);

		int count = list.size() +1;
		for(int i = 0; i < list.size(); i++) {
			int result = 0;

			result = new BoardDao().checkStatus(con, list.get(i));

			if(result > 0) {
				count--;
			}
		}

		close(con);

		return list;
	}

	public Board selectOne(int num) {
		Connection con = getConnection();

		Board b = new BoardDao().selectOne(con, num);


		close(con);

		return b;
	}

public int updateNotice(Board b, ArrayList<Attachment> fileList) {
      Connection con = getConnection();


      int resulttitle = new BoardDao().updateNotice(con,b);

      int resultcontent = new BoardDao().updateNoticeContent(con,b);

      int result1 = 0;
      int updateResult = 0;

      int resultatt = new BoardDao().SelectCount(con,b);



      if(resultatt > 0) {

      if(fileList !=null) {

       result1= new BoardDao().insertNoticePicture(con, fileList,b);
      }else {
         result1 = 1;
      }

      }else {
         updateResult = new BoardDao().insertNewPicture(con,b,fileList);
      }
      int result = 0;
      if(resulttitle>0&&resultcontent>0) {
         commit(con);
         result = 1;
      }else {
         rollback(con);
      }
      close(con);
      return result;
   }

	public int insertpicture(Board b, ArrayList<Attachment> fileList) {
		Connection con = getConnection();

		int result1= new BoardDao().insertNoticePicture(con, fileList,b);

		if( result1 >0) {
			commit(con);

		}else {
			rollback(con);
		}

		close(con);

		return result1;
	}

	public int changeDeliStatus(String deliNo) {
		Connection con = getConnection();

		int result = new BoardDao().changeDeliStatus(con, deliNo);
		Calcul cal = new BoardDao().calculateInfo(con, deliNo);

		int result1 = 0;
		if(cal != null) {
			result1 = new BoardDao().insertCalculate(con, cal);
		}

		if(result > 0 && result1 > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public int countlistSearch(String input) {
		Connection con = getConnection();
		int listCount = new BoardDao().getBoardIdListCount(con,input);

		close(con);

		return listCount;
	}

	public ArrayList<Board> searchListId(String type, String input, int currentPage, int limit) {

		Connection con  = getConnection();
		ArrayList<Board> list = new BoardDao().searchListId(con,input,type,currentPage,limit);
		close(con);

		return list;
	}

	public int countlistNameSearch(String input) {
		Connection con = getConnection();
		int listCount = new BoardDao().getBoardIdListNameCount(con, input);

		close(con);

		return listCount;
	}

	public ArrayList<Board> searchListName(String input, int currentPage, int limit) {

		Connection con  = getConnection();
		ArrayList<Board> list = new BoardDao().searchListName(con,input,currentPage,limit);
		close(con);

		return list;
	}

	public int countQnAlistSearch(String input) {
		Connection con = getConnection();
		int listCount = new BoardDao().getManagerQnAtitleCount(con,input);

		close(con);

		return listCount;

	}

	public ArrayList<Board> searchListTitleList(String type, String input, int currentPage, int limit) {
		Connection con  = getConnection();
		ArrayList<Board> list = new BoardDao().searchQnAListTitle(con,input,currentPage,limit);
		close(con);

		return list;
	}

	public int getFrequentListCount(int num) {

		Connection con = getConnection();

		int listCount = new BoardDao().getListCount(con, num);


		close(con);
		return listCount;
	}

	public ArrayList<Board> selectFrequentList(int currentPage, int limit, int num) {
		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectFrequentList(con,currentPage,limit,num);

		close(con);

		return list;
	}

	public int getListFrequentCount(int num) {
		Connection con = getConnection();

		int listCount = new BoardDao().getListFrequentCount(con, num);

		return listCount;
	}

	public ArrayList<Board> selectFrequentQuestionList(int currentPage1, int limit, int num) {

		Connection con = getConnection();

		ArrayList<Board>list = new BoardDao().selectFrequentQuestionList(con,currentPage1,limit,num);

		close(con);

		return list;


	}

	public int updateBoardStatus(String status, int num) {
			Connection con = getConnection();

	      int result = new BoardDao().updateBoardStatus(con,status,num);
	      close(con);
	      return result;

	}

	public int recommendComment(String num) {
		Connection con = getConnection();
		int result = new BoardDao().recommendComment(con, num);
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public int getListCountall(String selOption, String selectDate) {
		Connection con = getConnection();
		
		int result = new BoardDao().getListCountall(con, selOption, selectDate);
		
		close(con);

		return result;
	}

	public int getListCountSeachOp(String selOption) {
		Connection con = getConnection();
		
		int result = new BoardDao().getListCountSeachOp(con, selOption);
		
		close(con);

		return result;
	}

	public int getListCountSeachDt(String selectDate) {
		Connection con = getConnection();
		
		int result = new BoardDao().getListCountSeachDt(con, selectDate);
		
		close(con);

		return result;

	}

	public ArrayList<Calculate> selectBoardSearch(int currentPage, int limit, String selOption, String selectDate) {
		Connection con = getConnection();
		
		ArrayList<Calculate> list = new BoardDao().selectBoardSearch(con, currentPage, limit, selOption, selectDate);
		
		close(con);

		return list;
	}
}




















