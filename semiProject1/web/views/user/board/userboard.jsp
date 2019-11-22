<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.table {
	width:60%;
}
#board{
	text-align:center;
}
#wirte{
	position: absolute;
	right: 30px;
}
</style>
</head>
<body>
<head><%@include file ="../hfl/header.jsp" %></head>
<br>
<div class="container">
	<br>
	<br>
  <h2 id="board">리뷰</h2>
  <form action="" align="right">
  <select>
  	<option value="title">제목</option>
  	<option value="writer">작성자</option>
  	<option value="wdate">날짜</option>
  </select>
  <input type="text">
  <input type="submit" value="검색">
  </form>
  <table class="table">
      <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td><a href="boarderinsert.jsp">title</a></td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>2</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>3</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
        <tr>
        <td>4</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
        <tr>
        <td>5</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>6</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>7</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>8</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>9</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
      <tr>
        <td>10</td>
        <td>title</td>
        <td>writer</td>
        <td>2019-07-06</td>
      </tr>
    </tbody>
  </table>
</div>
<center>
	<ul class="pagination">
	  <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
	  <li class="page-item"><a class="page-link" href="#">1</a></li>
	  <li class="page-item"><a class="page-link" href="#">2</a></li>
	  <li class="page-item"><a class="page-link" href="#">3</a></li>
	  <li class="page-item"><a class="page-link" href="#">다음</a></li>
	  <li><input type="button" id="wirte" value="글작성" onclick="location.href='userBoardWriter.jsp'"></li>
	</ul>

</center>
</body>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
</html>