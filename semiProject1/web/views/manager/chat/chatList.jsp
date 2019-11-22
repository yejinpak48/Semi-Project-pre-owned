<%@page import="com.kh.bvengers.user.chat.model.vo.Chat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Chat> chList = (ArrayList) request.getAttribute("chList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅리스트</title>
<style>
#chatListTable {
	margin: 0 auto;
}
#chatListTable td,  #chatListTable th{
	border: 1px solid black;
	text-align: center;
	font-size: 1.1em;
}
#chatListTable th {
	font-size: 1.2em;
}
#chatListTable thead * {
	border: 1px solid black;
	height: 30px;
}

#chatListTable tbody td {
	height: 50px;
}

#chatListTable .memberNo {
	width: 100px;
}

#chatListTable .memberId {
	width: 200px;
}

#chatListTable .chatDate {
	width: 100px;
}
#chatListTable .answerBtn {
	background: white;
	color: black;
	border: none;
}
#chatListTable .chatStatus{
	width: 150px;
}
#chatListTable .answerBtn:hover {
	font-size: 1.3em;
	font-weight: bolder;
}
</style>
</head>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<div id="outer">
		<div id="chatDiv">
			<div class="chatListDiv">
				<table id="chatListTable">
					<thead>
						<tr>
							<th class="tableHeader" colspan="5">회원 문의 내역</th>
						</tr>
						<tr>
							<td class="memberNo">회원번호</td>
							<td class="memberId">회원ID</td>
							<td class="chatDate">문의일</td>
							<td colspan="2" class="chatStatus">완료 여부</td>
						</tr>
					</thead>
					<tbody>
						<%
						for (Chat ch : chList) {
							if(ch.getMemberNo().equals("1")){
								continue;
							}
						%>

						<tr>
							<td class="memberNo"><%=ch.getMemberNo()%></td>
							<td class="memberId"><%=ch.getMemberId()%></td>
							<td class="chatDate"><%=ch.getChatDate()%></td>
							<%if(ch.getAnswer().equals("1")){ %>
							<td class="chatStatus">답변 완료</td>
							<td></td>
							<%} else {%>
							<td class="chatStatus" style="color:red">답변 대기</td>
							<td><button type="button" class="answerBtn chatStatus" onclick="location.href='<%=request.getContextPath()%>/joinChat.ch?no='+<%=ch.getMemberNo() %>">답변하기</button></td>
							<%} %>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>