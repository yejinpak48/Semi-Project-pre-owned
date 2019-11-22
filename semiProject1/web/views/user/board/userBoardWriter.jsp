<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.container {
	margin-top:5%;
	margin-bottom:3%;
}
.table-bordered {
	margin:0 auto;
}
.userBo{
	text-align:center;
}
</style>
</head>
<head><%@include file ="../hfl/header.jsp" %></head>
<body>
	<br>
	<br>
	<div class="container">
	<h2 align="center">공지사항</h2><br>
	<table class="table-bordered">
	 	<form class="userForm">
			<tr>
				<th class="userBo">제목</th>
				<td><input type="text" name="title" class="form-control"></td>
			</tr>
			<tr>
				<th class="userBo">파일첨부</th>
				<td><input type="file" name="filename" class="form-control"></td>
			</tr>
			<tr>
				<th class="userBo">글내용</th>
				<td><textarea cols="100" rows="30" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="등록">
					<input type="reset" value="초기화">
					<input type="button" value="글 리스트로" onclick="location.href='userboard.jsp'">
			</tr>
		</form>
	</table>
	</div>
	<br>
	<br>
</body>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
</html>