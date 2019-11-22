<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#select-area{
	width:80%;
	border:2px solid #ffd8d9;
	margin:0 auto;
	border-radius: 5px;
}
#select{
width:100%;
border-radius:1px;
}
table{
width:80%;
border:1px solid black;
}
</style>
</head>
<header><%@ include file="../hfl/managerHeader.jsp" %></header>
<body>
<br><br><br>
<div align="center" id="select-area">
<h1>주문조회 관리</h1>
<form>
	<div id="select">
		<select name="select" style="width:10%">
			<option value="">주문상태</option>
			<option value="">배송전</option>
			<option value="">환불신청</option>
		</select>
	<span>
		<select name="select" style="width:20%;">
			<option value="findId">아이디로 조회</option>
			<option value="findName">이름으로 조회</option>
			<option value="findLevel">등급으로 조회</option>
		</select>
		<input type="search" name="searchValue">
		<button type="submit" style="border-radius: 5px; background-color: #ffd8d9;">조회</button>
	</span>
	</div>
	<br><br>
</form>
	<table align="center">
	<tr>
		<th>주문번호</th>
		<th>구매자아이디</th>
		<th>상품게시물</th>
		<th>답변여부</th>
		<th>진행 상태</th>
		<th>환불신청여부</th>
	</tr>
	</table><br><br>
	<%@ include file="../hfl/pagination.jsp" %>
</div>
</body>
</html>