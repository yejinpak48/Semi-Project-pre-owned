<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
btn{
border-radius: 5px;
background-color: black;
border:1px solid white;

}
</style>
</head>
<header><%@ include file="../hfl/managerHeader.jsp" %></header>
<body>
<br>
	<br>
	<h2 align="center">답변 작성</h2>
	<div class="container">
	<table class="table table-bordered">
	 	<form>
			<tr>
				<th>제목:</th>
				<td><h4>회원이 작성한 글 제목</h4>
				</td>
				
			</tr>
			
			<tr>
				<th>글내용:</th>
				<td><textarea cols="40" rows="50" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답변등록" style="border-radius: 5px; background-color: black; color:white;">
					<input type="reset" value="초기화" style="border-radius: 5px; background-color: black; color:white;">
					<input type="button" value="글 리스트로" style="border-radius: 5px; background-color: black; color:white;" onclick="location.href='board.jsp'">
					<button onclick="" style="border-radius: 5px; background-color: black;color:white;">회원 정보 확인</button>
			</tr>
		</form>
	</table>	
	</div>
	<br>
	<br>
</body>
</html>