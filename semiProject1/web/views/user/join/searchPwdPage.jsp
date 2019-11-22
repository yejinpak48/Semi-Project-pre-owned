<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap"
	rel="stylesheet">
<title>비밀번호 찾기</title>
<style>
form {
	width: 40%;
	background: white;
	color: black;
	border-radius: 5px;
	font-family: 'Sunflower', sans-serif;
	border-radius: 5px;
	border: 1px solid black;
}

.btn1 {
	background: black;
	color: white;
	border-radius: 5px;
}

.btn_search {
	width: 49%;
	height: 50px;
	border: 1px solid black;
	font-size: 16px;
	font-family: 'Sunflower', sans-serif;
	background-color: black;
	border-radius: 5px;
	color: white;
}

.btn_searchPwd {
	width: 64px;
	height: 25px;
	border: 1px solid black;
	font-size: 13px;
	font-family: 'Sunflower', sans-serif;
	background-color: black;
	color: white;
}

.btn_pwd {
	margin: 0 auto;
	width: 15%;
	height: 30px;
	border: 1px solid black;
	font-size: 13px;
	font-family: 'Sunflower', sans-serif;
	border-radius: 5px;
	background-color: black;
	color: white;
}

.btn_pwd:hover {
	color: white;
}
</style>
</head>
<header><%@ include file="../hfl/header.jsp"%></header>
<body>
	<div align="center">

		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<form action="<%=request.getContextPath()%>/findPwd.me" method="post">
			<div align="center" class="btn1">
				<a href="searchIdPage.jsp">
					<button type="button" class="btn_search" float="left">아이디
						찾기</button>
				</a> <a href="#">
					<button type="button" class="btn_search" float="left">비밀번호
						찾기</button>
				</a>
			</div>


			<br>
			<br> <label for="searchPhone">이메일로 찾기</label> <br> <input
				type="text" placeholder="아이디" name="userId" id="userId" size=30><br>
			 <input
				type="text" placeholder="이름" name="userName" id="userName" size=30><br>
			<input type="text" name="hiddencard" value="1" id="hiddencard"
				style="display: none"> <input type="text" name="hiddenvalue"
				value="0" id="hiddenvalue" style="display: none"> <input
				type="email" placeholder="이메일" name="email" id="email" size=18>
			<button type="button" class="btn_searchPwd" onclick="send_pwd(2);">인증번호</button>
			<br> <br>
			<br> <input type="button" value="로그인하러가기" class="btn_pwd"
				id="ok" onclick="send_pwd(1);"> &nbsp; &nbsp; <input
				type="reset" value="취소" class="btn_pwd"> <br>
			<br>
		</form>
	</div>
	<script>
	function send_pwd(index){
		
		if(index == 1){
			if($("#hiddenvalue").val()=="1"){
			 $(function(){
				$("form").submit();
			}); 
			}
			
		
		}else if(index ==2){
			var userId = $("#userId").val();
			var userName = $("#userName").val();
			var email = $("#email").val();
			$.ajax({
				url:"/sp/checkuser.me",
				type:"post",
				async:false,
				data:{userId:userId,userName:userName,email:email},
				success:function(data){
					if(data==="fail"){
						alert("존재하지 않는 회원입니다.");
					}else{
						alert("존재하는 회원입니다.\n이메일로 임시 비밀번호를 보내드렸습니다.\n임시 비밀번호로 로그인 하세요");
						$("#hiddenvalue").val("1");
					
			}
				}
				});
			

		
	}
	}
	</script>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>
</body>
</html>