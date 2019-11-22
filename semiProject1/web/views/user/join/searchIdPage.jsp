<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
<title>아이디 찾기</title>
<style>
	form {
		width:40%;
		background:white;
		color:black;
		border-radius: 5px;
		font-family: 'Sunflower', sans-serif;
		border-radius: 5px;
		border:1px solid black;
	}
	
	.btn1 {
		background:black;	
		color:white;
		border-radius: 5px;
	}
	.btn_search {
	width:49%;
	height: 50px;
	border: 1px solid black;
	font-size: 16px;
	font-family: 'Sunflower', sans-serif;
	background-color: black;
	border-radius: 5px;
	color: white;
	}
	
	.btn_searchId {
	width:64px;
	height: 25px;
	border: 1px solid black;
	font-size: 13px;
	font-family: 'Sunflower', sans-serif;
	background-color: black;
	color: white;
	}
	btn_searchId.active-color { color:#000; }
	
	.btn_id {
	margin:0 auto;
	width: 10%;
	height: 30px;
	border: 1px solid black;
	font-size: 13px;
	font-family: 'Sunflower', sans-serif;
	border-radius: 5px;
	background-color: black;
	color: white;
	}
	
	.btn_id:hover {color:white;}
	
</style>
</head>
<header><%@ include file="../hfl/header.jsp" %></header>
<body>
	<div align="center">

		<br><br><br><br><br><br><br>
		<form action="<%=request.getContextPath()%>/findId.me" method="post">
			<div align="center" class="btn1">
  			<a href="#"> <button type="button" class="btn_search" float="left" id="btn_searchId">아이디 찾기</button></a>
  			<a href="searchPwdPage.jsp"> <button type="button" class="btn_search" float="left" id="btn_searchPwd">비밀번호 찾기</button></a>
			</div>
			
			
			<br><br>
			
			
			<label for="searchPhone">이메일로 찾기</label>
			
			<br>
			
			<input type="text" placeholder="이름" name="userName" id="userName" size=30><br>
			<input type="text" placeholder="휴대폰" name="phone" id="phone" size=30><br>
			<input type="tel" placeholder="이메일" name="email" id="email" size=30><br>
			
			
			<br><br>
			
			<input type="button" value="확인" class="btn_id" onclick="findId();"> &nbsp; &nbsp;
			<input type="reset" value="취소" class="btn_id">
			<br><br>
		</form>
	</div>
	<br><br><br><br><br><br><br>
	<footer><%@ include file="../hfl/footer.jsp" %></footer>
<script>
function findId(){
	$("form").submit();
}
</script>
     
	
</body>
</html>