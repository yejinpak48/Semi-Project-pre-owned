<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap"
	rel="stylesheet">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<style>
#array {
	display: none;
}

* {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
}

.postbtn {
  width: 120px;
  height: 30px;
}

.report{
	padding-right : 10%;
	padding-left : 10%;
	text-align: center;
}

.Area{
	text-align: center;
}
</style>
<body>
<div class = "report">
	<img src="<%= request.getContextPath()%>/images/alert.png" style="width:25%;"/><h1>신고하기</h1>
	<br>
<div class = "Area">
	<form action="<%=request.getContextPath()%>/ss.re" method="post">
		<textarea id="array" name="array"><%=request.getParameter("array")%></textarea>
		<textarea id="content" name="content" placeholder="신고 사유를 적어주세요"
			style="width: 80%; height: 180px;"></textarea>
		<br>
		<br>
		<button type="submit" value="확인" class="postbtn btn-danger"onclick="postbtn();">신고하기</button>
	</form>
	</div>
</div>
</body>
<script>
	function postbtn() {
		self.close();
	}
</script>
</html>