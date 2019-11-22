package com.kh.bvengers.user.member.model.dao;

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

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.kh.bvengers.user.member.model.vo.Member;
import com.kh.bvengers.user.member.model.vo.Seller;
import com.kh.bvengers.user.myPage.model.vo.myPage;

public class MemberDao {
	Properties prop = new Properties();

	public MemberDao(){

		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Member loginCheck(Connection con, String memberId, String memberPwd) {
		PreparedStatement pstmt = null;
		Member loginUser = null;
		ResultSet rset = null;

		String query = prop.getProperty("loginSelect");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				loginUser = new Member();

				loginUser.setMemberNo(rset.getString("MEMBER_NO"));
				loginUser.setMemberId(rset.getString("MEMBER_ID"));
				loginUser.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				loginUser.setMemberName(rset.getString("MEMBER_NAME"));
				loginUser.setEmail(rset.getString("EMAIL"));
				loginUser.setAddress(rset.getString("ADDRESS"));
				loginUser.setPhone(rset.getString("PHONE"));
				loginUser.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginUser.setRetireDate(rset.getDate("RETIRE_DATE"));
				loginUser.setRetire(rset.getString("RETIRE"));
				loginUser.setMemberDiv(rset.getString("MEMBER_DIV"));
				loginUser.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				loginUser.setBankCode(rset.getString("BANK_CODE"));
				loginUser.setAccountNo(rset.getString("ACCOUNT_NO"));
				loginUser.setGradeCode(rset.getString("GRADE_CODE"));
				loginUser.setSellCount(rset.getInt("SELL_COUNT"));

				System.out.println(loginUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return loginUser;
	}
	public int insertMember(Connection con, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertMember");

		try {
			pstmt = con.prepareStatement(query);


			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	public int changeMember(Connection con, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("changeMember");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getMemberPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getMemberId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Member> selectAll(Connection con) {
		ArrayList<Member> list = null;
		Statement stmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAll");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();

			while(rset.next()) {
				Member m = new Member();

				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}

		return list;
	}
	public ArrayList<Member> searchId(Connection con, int currentPage, int limit, String searchValue) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;
		String query = prop.getProperty("searchId");
		try {
			pstmt = con.prepareStatement(query);


			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member m = new Member();

				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
			//	m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return list;
	}
	public ArrayList<Member> searchName(Connection con, int currentPage, int limit, String searchValue) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("searchName");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage-1)*limit +1;
			int endRow = startRow + limit-1;

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member m = new Member();

				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
			//	m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return list;
	}
	public ArrayList<Member> searchLevel(Connection con, int currentPage, int limit, String searchValue) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("searchLevel");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage-1)*limit +1;
			int endRow = startRow + limit-1;

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member m = new Member();

				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
			//	m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
	public ArrayList<Member> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Member> mlist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (currentPage-1)*limit +1;
		int endRow = startRow + limit-1;

		String query = prop.getProperty("selectListwithPageing");
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();

			mlist = new ArrayList<Member>();
			while(rset.next()) {
			Member m = new Member();

			m.setMemberId(rset.getString("MEMBER_ID"));
			//m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
			m.setMemberName(rset.getString("MEMBER_NAME"));
			//m.setEmail(rset.getString("EMAIL"));
			m.setAddress(rset.getString("ADDRESS"));
			m.setPhone(rset.getString("PHONE"));
			m.setEnrollDate(rset.getDate("ENROLL_DATE"));
			//m.setRetireDate(rset.getDate("RETIRE_DATE"));
			//m.setRetire(rset.getString("RETIRE"));
			//m.setMemberDiv(rset.getString("MEMBER_DIV"));
			//m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
			//m.setBankCode(rset.getString("BANK_CODE"));
			//m.setAccountNo(rset.getString("ACCOUNT_NO"));
			m.setGradeCode(rset.getString("GRADE_CODE"));
			m.setSellCount(rset.getInt("SELL_COUNT"));


			mlist.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return mlist;
	}
	public Member findUserId(Connection con, String memberName, String phone, String email) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("findUserId");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);

			rset = pstmt.executeQuery();
			if(rset.next()) {
				m=new Member();
				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return m;
	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;

		String query = prop.getProperty("selectListCount");

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
	public Member showDetail(Connection con, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("showDetail");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getString("MEMBER_NO"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setEmail(rset.getString("EMAIL"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setRetireDate(rset.getDate("RETIRE_DATE"));
				m.setRetire(rset.getString("RETIRE"));
				m.setMemberDiv(rset.getString("MEMBER_DIV"));
				m.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				m.setBankCode(rset.getString("BANK_CODE"));
				m.setAccountNo(rset.getString("ACCOUNT_NO"));
				m.setGradeCode(rset.getString("GRADE_CODE"));
				m.setSellCount(rset.getInt("SELL_COUNT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return m;
	}
	public Member checkPwd(Connection con, String memberNo, String password) {
		PreparedStatement pstmt = null;
		Member checkPwd = null;
		ResultSet rset = null;

		String query = prop.getProperty("checkPwd");

		try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, memberNo);
		pstmt.setString(2, password);

		rset = pstmt.executeQuery();

		if(rset.next()) {
			checkPwd = new Member();

			checkPwd.setMemberNo(rset.getString("MEMBER_NO"));
			checkPwd.setMemberId(rset.getString("MEMBER_ID"));
			checkPwd.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
			checkPwd.setMemberName(rset.getString("MEMBER_NAME"));
			checkPwd.setEmail(rset.getString("EMAIL"));
			checkPwd.setAddress(rset.getString("ADDRESS"));
			checkPwd.setPhone(rset.getString("PHONE"));
			checkPwd.setEnrollDate(rset.getDate("ENROLL_DATE"));
			checkPwd.setRetireDate(rset.getDate("RETIRE_DATE"));
			checkPwd.setRetire(rset.getString("RETIRE"));
			checkPwd.setMemberDiv(rset.getString("MEMBER_DIV"));
			checkPwd.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
			checkPwd.setBankCode(rset.getString("BANK_CODE"));
			checkPwd.setAccountNo(rset.getString("ACCOUNT_NO"));
			checkPwd.setGradeCode(rset.getString("GRADE_CODE"));
			checkPwd.setSellCount(rset.getInt("SELL_COUNT"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return checkPwd;
	}

	public int deleteMember(Connection con, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("deleteMember");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			return result;
	}



	public int upblack(Connection con, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("upblack");

		try {
			pstmt = con.prepareStatement(query);


		} catch (SQLException e) {
			e.printStackTrace();
		}


		return result;
  }
	public Seller searchInfo(Connection con, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Seller s = null;
		String id = '%'+userId+'%';
		String query = prop.getProperty("searchSellerInfo");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				s = new Seller();
				s.setId(rset.getString(1));
				s.setName(rset.getString(2));
				s.setEnrollDate(rset.getDate(3));
				s.setSellCount(rset.getInt(4));
				s.setGrade(rset.getString(5));
				s.setProduct(rset.getString(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return s;
	}
	public int memberidCk(Connection con, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("memberidCk");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}


		return result;
	}
	public int checkuser(Connection con, String memberId, String memberName, String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;

		String query = prop.getProperty("checkuser");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberName);
			pstmt.setString(3, email);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	public int changePwd(Connection con, String memberId, String memberPwd) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("changePwd");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberPwd);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}


		return result;
	}
	public int kakaojoin(Connection con, String id, String nickname, String token) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("kakaojoin");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, token);
			pstmt.setString(3, nickname);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	public Member kakaologin(Connection con, String id) {
		PreparedStatement pstmt = null;
		Member loginUser = null;
		ResultSet rset = null;

		String query = prop.getProperty("kakaologin");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				loginUser = new Member();

				loginUser.setMemberNo(rset.getString("MEMBER_NO"));
				loginUser.setMemberId(rset.getString("MEMBER_ID"));
				loginUser.setMemberPassword(rset.getString("MEMBER_PASSWORD"));
				loginUser.setMemberName(rset.getString("MEMBER_NAME"));
				loginUser.setEmail(rset.getString("EMAIL"));
				loginUser.setAddress(rset.getString("ADDRESS"));
				loginUser.setPhone(rset.getString("PHONE"));
				loginUser.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginUser.setRetireDate(rset.getDate("RETIRE_DATE"));
				loginUser.setRetire(rset.getString("RETIRE"));
				loginUser.setMemberDiv(rset.getString("MEMBER_DIV"));
				loginUser.setAccountHolder(rset.getString("ACCOUNT_HOLDER"));
				loginUser.setBankCode(rset.getString("BANK_CODE"));
				loginUser.setAccountNo(rset.getString("ACCOUNT_NO"));
				loginUser.setGradeCode(rset.getString("GRADE_CODE"));
				loginUser.setSellCount(rset.getInt("SELL_COUNT"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return loginUser;
	}
	public int kakaoidCk(Connection con, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = prop.getProperty("kakaoidCk");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	public int temporaryPwd(Connection con, String memberId, String newPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("temporaryPwd");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

}