<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<head><%@include file ="../hfl/header.jsp" %></head>
<body>
	<br>
	<br>
	<h2 align="center">게시글 작성</h2>
	<div class="container">
	<table class="table table-bordered">
	 	<form>
			<tr>
				<th>제목:</th>
				<td><input type="text" name="tietle" class="form-control" ></td>
			</tr>
			<tr>
				<th>파일첨부:</th>
				<td><input type="file" name="filename" class="form-control"></td>
			</tr>
			<tr>
				<th>글내용:</th>
				<td><textarea cols="40" rows="50" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="reset" value="수정">
					<input type="button" value="글 리스트로">
			</tr>
		</form>
	</table>
	</div>
	<br>
	<br>
</body>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
</html>