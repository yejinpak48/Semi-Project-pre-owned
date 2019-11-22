<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
<style>
.r1 {
	width:100%;
	height:100%;
	margin-top:40px;
}
.r2 {
	margin-top:50px;
	margin-right:10%;
}

.btn_change {
	width:60px;
	height: 25px;
	margin-left:auto;
	margin-right:auto;
	margin-top:50px;
	margin-bottom:50px;
	font-size: 14px;
	border-radius: 5px;
	border:1px solid black;
	background:#FFF;
}
</style>
</head>
<body>
		<div class="c1">
			<h3 align="center">비밀번호 변경</h3>
			<br>
			<h4 align="center">띄어쓰기 없는 영문 소문자,숫자포함 6~20자 사용가능</h2>
			<h4 align="center">(아이디,생년월일,동일한 연속문자 숫자 사용 불가능)</h2><br>
			<form action="">
				<div align="center">
				<a>기존 비밀번호 입력</a>
				<input type = "password" class = pwd name = pwd><br>
				<a>새 비밀번호 입력 </a>
				<input type = "password" class = pwd name = newPwd><br>
				<a>새 비밀번호 확인 </a>
				<input type = "password" class = pwd name = rePwd>
				<br><br>
				<button type="button" class="btn_change">확인</button>
				<button onclick="window.close();" class="btn_change">취소</button>
				</div>
			</form>
		</div>
		
<script>

$(".btn_change").click(function(){
	window.opener.location.href="myPage.jsp";
	window.close();
});
</script>
</body>
</html>










