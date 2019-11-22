<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String check = (String) request.getAttribute("pass");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap"
	rel="stylesheet">
<title>비밀번호 찾기</title>
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

	.btn_searchPwd {
	width:64px;
	height: 25px;
	border: 1px solid black;
	font-size: 13px;
	font-family: 'Sunflower', sans-serif;
	background-color: black;
	color: white;
	}

	.btn_pwd {
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

	.btn_pwd:hover {color:white;}

</style>
</head>
<header><%@ include file="../hfl/header.jsp" %></header>
<body>
	<div align="center">

		<br><br><br><br><br><br><br>
<form action="<%=request.getContextPath()%>/changePwd.me" method="post">
	<h1>비밀번호 재설정</h1>
	<input type="password" id="password" name="password" placeholder="바꿀 비밀번호를 입력하세요" style="width:50%">
	<input type="password" id="password2" name="password2" placeholder="한번 더 입력하세요" style="width:50%"><br><br>
	<input type="button" value="확인" class="okok" onclick="okok();"><br><br>
	<input type="text" id="hiddenvalue" name="hiddenvalue" style="display:none;" value="<%=check%>">
	</form>
	</div>
	<script>
function okok(){
	var key="<%=check%>";
	var userinput=$("#userinput").val();
	var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
	if(($("#password").val()=="")||($("#password2").val()=="")){
		alert("비밀번호를 입력해주세요");
		return false;
	}

	if((!getCheck.test($("#password").val()))||!getCheck.test($("#password2").val())){
		alert("비밀번호를 다시 설정하세요");
		return false;
	}
	if(($("#password").val() == $("#password2").val())){
		alert("비밀번호가 변경되었습니다!");
		$("form").submit();
	}




}
</script>
	<br><br><br><br><br><br><br>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
</body>
</html>