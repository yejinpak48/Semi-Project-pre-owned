<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.kh.bvengers.board.model.vo.*,java.util.HashMap"%>
 <%
	Board b = (Board) request.getAttribute("b");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>

td{
	border:1px solid lightgray;
	}
.tableArea{
		border:1px solid white;
		width:800px;
		height:100%;
		margin:0 auto;
}
#content{
		height:230px;
	}

</style>
</head>
<head><%@include file ="../hfl/header.jsp" %></head>
<body>

	<br>
	<br>
	<div class="container">
	 	<form  name="notice" action = "<%= request.getContextPath()%>/update.no" method = "post" encType="multipart/form-data" >
	<div class = "tableArea">
			<hr>
			<table align = "center" width = "800px">
				<tr><th style = "border:1px solid lightgray">제목</th></tr>
				<tr><td colspan = "4">
					<textarea align = "center" name = "title" id="title"style = "width:800px" ><%=b.getPostsTitle()%></textarea>
				</td></tr>
				<tr style = "border:1px solid lightgray">
					<td>작성자</td>
					<td align = "center" id = "writer" name = "writer"><span><%=b.getWriter() %></span></td>
					<td>작성일</td>
					<td align = "center"><span><input type="text" value="<%=b.getCreateDate()%>"></span></td>
				</tr>
				<tr>
			<th style = "border:1px solid lightgray" >파일첨부:</th>
			<td><input type="file" name="filename" class="form-control"></td>
			<th>글번호 : </th>
			<td ><input type="text" value="<%=b.getPostsId() %>" name="no" id="no"></td>
		</tr>
				<tr>
			<th style = "border:1px solid lightgray">글내용:</th>
		<td colspan = "4">
		<textarea id="summernote" name = "content" ><%=b.getContents()%></textarea>

		</td>
		</tr>
			</table>
		</div>
		<br><br>
		<div align="center">
                  <button type = "button" id="sm" name="sm" onclick="submit();">작성 완료</button>
		</div>
				<br><br><br>
		</form>
		</div>
	<br>
	<br>
 <footer><%@ include file="../hfl/footer.jsp" %></footer>

	<script>
$(function() {
  $('#summernote').summernote({
    height: 300,
    lang: 'ko-KR' // 언어 세팅
  });
});

$(function(){
	var title = $("#title").val();
 	var content = $("textarea").eq(1).html();
	var no = $("#no").val();
	var hapche = title+content+no;
	<%-- location.href = '<%=request.getContextPath()%>/update.no?hapche='+hapche; --%>
});

function submit() {
	document.notice.submit();
}
</script>

</body>
</html>





