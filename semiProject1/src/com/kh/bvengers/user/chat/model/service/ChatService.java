package com.kh.bvengers.user.chat.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.bvengers.user.chat.model.dao.ChatDao;
import com.kh.bvengers.user.chat.model.vo.Chat;

import static com.kh.bvengers.common.JDBCTemplate.*;
public class ChatService {

	public int selectChat(String no) {
		Connection con = getConnection();

		int result = new ChatDao().selectChat(con, no);

		if(result > 0) {
			result = new ChatDao().newChat(con, no);
		} else if(result == 0) {
			result = new ChatDao().createChat(con, no);
			commit(con);
		}

		close(con);
		return result;
	}

	public Chat joinChat(int no) {
		Connection con = getConnection();

		Chat ch = new ChatDao().joinChat(con, no);
		close(con);
		return ch;
	}

	public ArrayList<Chat> selectChatList() {
		Connection con = getConnection();
		ArrayList<Chat> chList = new ChatDao().selectChatList(con);

		close(con);

		return chList;
	}

	public int updateChat(String no, String content) {
		Connection con = getConnection();
		int result = new ChatDao().updateChat(con, no, content);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public void endChat(String no) {
		Connection con = getConnection();
		int result = new ChatDao().endChat(con, no);

		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
	}

	public ArrayList<Chat> chatCount() {
		Connection con = getConnection();
		ArrayList<Chat> list = new ChatDao().chatCount(con);

		return list;
	}

}
