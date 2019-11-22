package com.kh.bvengers.user.chat.model.service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.bvengers.user.member.model.dao.MemberDao;

import static com.kh.bvengers.common.JDBCTemplate.*;
public class TargetDao {
	Properties prop = new Properties();

	public TargetDao() {
		// TODO Auto-generated constructor stub

		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String selectTarget(String no, Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String target = null;

		String query = prop.getProperty("selectTarget");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, no);

			rset = pstmt.executeQuery();
			if(rset.next()) {
				target = rset.getString("MEMBER_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return target;
	}

}
