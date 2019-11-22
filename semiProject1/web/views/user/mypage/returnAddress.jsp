<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>

.return1 {
	margin-top:10%;
	margin-left:auto;
	margin-right:auto;
	width:55%;
	border:2px solid black;
	border-radius: 5px;
	margin-bottom:50px;
	padding-top:5%;
	padding-bottom:5%;
}

.return2 {
	width:100%;
	margin-right: auto;
	margin-left: auto;
	margin-top: 50px;
}

.btn_return{
	margin-left:10px;
	height:30px; 
	width:30%;
	font-size: 15px;
	font-family:'Nanum Gothic', sans-serif;
	font-weight:550;
	border-radius: 5px;
	border:1px solid black;
	background:#FFF;
	}
	
</style>
</head>
<body>
<header><%@ include file="../hfl/header.jsp" %></header>
	<div class="return1" align="center">
		<h3>&nbsp;반품/환불 신청 완료</h3>
		<p>&nbsp;받는 이 : 병벤져스<br>
		&nbsp;주소 : 서울 특별시 강남구 테헤란로 14길 6 남도 빌딩<br>
		&nbsp;연락처 : 02-6952-2012<br><br><br>
		위 주소로 물품을 보내주시면 검수 후 환불 안내드리겠습니다.</p>
	<div class="return2">
		<input type="submit" value="확인" class="btn_return" onclick="location.href='<%=request.getContextPath()%>/index.jsp'">
	</div>
	</div>
		<footer><%@ include file="../hfl/footer.jsp" %></footer>
	<script>
	</script>
		
		
</body>
</html>