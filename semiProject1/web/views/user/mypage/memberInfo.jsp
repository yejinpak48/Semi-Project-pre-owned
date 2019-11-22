<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
.checkInfo {
	margin: 0 auto;
	margin-top:10%;
	margin-bottom:10%;
	width:35%;
	border-radius:5px;
	border:5px double #ffe6e6;
	background-color:#FFF;
}

button{
  background:#ffe6e6;
  color:white;
  border:none;
  border-radius:5px;
  position:relative;
  width:120px;
  height:40px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
button:hover{
  background:#fff;
  color:#ffb3b3;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #ffb3b3;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}
</style>
</head>
<header><%@ include file="../hfl/header.jsp" %></header>
<body>

	<header><%@ include file="../hfl/myPageList.jsp"%></header>
<div align="center" class="checkInfo">
<br><br>
	<form action="<%=request.getContextPath()%>/memberInfo.me" method="post">
		<h4 align="center">아이디 <%=loginUser.getMemberId()%>님</h4><br>
		<input type="password" placeholder=" 비밀번호를 입력해주세요." name="password" id="password" size=30 style="border-radius:5px; height:32px;"><br><br>
		<button class="btn_info">확인</button><br><br><br><br>
	</form>
</div>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
<script>


</script>
</body>
</html>