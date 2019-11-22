package com.kh.bvengers.user.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.bvengers.user.member.model.dao.MemberDao;
import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.member.model.vo.Seller;

import static com.kh.bvengers.common.JDBCTemplate.*;
public class MemberService {

	public Member loginCheck(String memberId, String memberPwd) {
		Connection con = getConnection();
		Member loginUser = new MemberDao().loginCheck(con,memberId,memberPwd);
		close(con);
		return loginUser;
	}

	public int insertMember(Member m) {
		Connection con = getConnection();
		int result = new MemberDao().insertMember(con,m);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}


	public int changeMember(Member m) {
		Connection con = getConnection();

		int result = new MemberDao().changeMember(con, m);

		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<Member> selectAll() {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().selectAll(con);

		close(con);
		return list;
	}

	public ArrayList<Member> searchMember(int currentPage, int limit, String selecthowsearch, String searchValue) {
		Connection con = getConnection();
		ArrayList<Member> list = null;
		if(selecthowsearch.equals("findId")) {
			list = new MemberDao().searchId(con,currentPage,limit,searchValue);
		}else if(selecthowsearch.equals("findName")) {
			list = new MemberDao().searchName(con,currentPage,limit,searchValue);
		}else {
			list = new MemberDao().searchLevel(con,currentPage,limit,searchValue);
		} 

		close(con);
		return list;
	}

	public ArrayList<Member> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		ArrayList<Member> mlist = new MemberDao().selectList(con,currentPage,limit);
		close(con);
		return mlist;
	}

	public Member findUserId(String memberName, String phone, String email) {
		Connection con = getConnection();
		Member m = new MemberDao().findUserId(con,memberName,phone,email);
		close(con);
		return m;

	}
	public int getListCount() {
		Connection con = getConnection();
		int listCount = new MemberDao().getListCount(con);

		close(con);
		return listCount;
	}

	public Member showDetail(String memberId) {
		Connection con = getConnection();
		Member m = new MemberDao().showDetail(con,memberId);
		close(con);
		return m;
	}

	public Member checkPwd(String memberNo, String password) {
		 Connection con = getConnection();
		 Member checkPwd = new MemberDao().checkPwd(con, memberNo, password);
		 close(con);


		return checkPwd;
	}

	public int deleteMember(String memberId) {
		Connection con = getConnection();
		int result = new MemberDao().deleteMember(con, memberId);

		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	

	public Seller searchInfo(String userId) {
		Connection con = getConnection();
		Seller s = new MemberDao().searchInfo(con, userId);
		close(con);
		return s;
	}

	public int memberidCk(String memberId) {
		Connection con = getConnection();
		int result = new MemberDao().memberidCk(con,memberId);
		close(con);
		return result;
	}

	public int checkuser(String memberId, String memberName, String email) {
		Connection con = getConnection();
		int result = new MemberDao().checkuser(con,memberId,memberName,email);
		close(con);
		return result;
	}

	public int changePwd(String memberId, String memberPwd) {
		Connection con = getConnection();
		int result = new MemberDao().changePwd(con,memberId,memberPwd);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int kakaojoin(String id, String nickname, String token) {
		Connection con = getConnection();
		int result = new MemberDao().kakaojoin(con,id,nickname,token);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public Member kakaologin(String id) {
		Connection con = getConnection();
		Member loginUser = new MemberDao().kakaologin(con,id);
		close(con);
		return loginUser;
	}

	public int kakaoidCk(String id) {
		Connection con = getConnection();
		int result = new MemberDao().kakaoidCk(con,id);
		close(con);
		return result;
	}

	public int temporaryPwd(String memberId, String newPwd) {
		Connection con = getConnection();
		int result = new MemberDao().temporaryPwd(con,memberId,newPwd);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
