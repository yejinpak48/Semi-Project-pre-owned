package com.kh.bvengers.user.chat.model.service;

import java.sql.Connection;

import static com.kh.bvengers.common.JDBCTemplate.*;

public class TargetService {

	public TargetService() {
		// TODO Auto-generated constructor stub
	}

	public String selectTarget(String no) {
		Connection con = getConnection();
		String target = new TargetDao().selectTarget(no, con);
		close(con);
		return target;
	}

}
