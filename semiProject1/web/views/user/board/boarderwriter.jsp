<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<%@include file ="../hfl/header.jsp" %>
	<br>
	<br>
	<h2 align="center">게시글 작성</h2>

	<div class="container">
	 	<form action = "<%= request.getContextPath()%>/insertNotice" method = "post" encType="multipart/form-data">
	<table class="table table-bordered">
 		<tr hidden>
			<td><input name="hiddenCode" value="1"></td>
 		</tr>
 		<tr>
			<th>제목:</th>
			<td><input type="text" name="title" class="form-control"></td>
		</tr>
		<tr>
			<th>파일첨부:</th>
			<td><input type="file" name="filename" class="form-control"></td>
		</tr>

		<tr>
			<th>글내용:</th>
		<td>
		<textarea id="summernote" name = "content"></textarea>
		</td>
			<!-- <td><textarea cols="40" rows="50" class="form-control" name = "content"></textarea></td> -->
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="초기화">
				<input type="button" value="글 리스트로" onclick="location.href='<%= request.getContextPath()%>/selectNotice.no'">
		</tr>
	</table>
		</form>
	</div>
	<br>
	<br>
</body>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
<script>
$(function() {
  $('#summernote').summernote({
    height: 300,
    lang: 'ko-KR' // 언어 세팅
  });
});
</script>
</html>