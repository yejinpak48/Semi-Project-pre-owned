<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<header><%@ include file="../hfl/managerHeader.jsp" %></header>
	<br>
	<br>
	<h2 align="center">자주찾는 질문 작성</h2>

	<div class="container" style = "width:80%; height:150%">
	 	<form action = "<%= request.getContextPath()%>/fqs.qb" method = "post">
	<table class="table table-bordered" >
 		<tr hidden>
			<td><input name="hiddenCode" value="2"></td>
 		</tr> 
 		<tr>
			<th>제목:</th>
			<td><input type="text" name="title" class="form-control"></td>
		</tr>
		<tr>
			<th>글내용:</th>
		<td>
		<textarea id="summernote" name = "content" style = "width:80%; height:300%"></textarea>
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