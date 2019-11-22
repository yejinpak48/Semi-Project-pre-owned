<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import = "java.util.*,com.kh.bvengers.board.model.vo.*,com.kh.bvengers.user.myPage.model.vo.*"%>
<%
ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
BoardPageInfo pi = (BoardPageInfo)request.getAttribute("pi");
int listCount = pi.getListCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/d3js/5.9.0/d3.min.js"></script>
<title>Insert title here</title>
<style>
#board {
	text-align: center;
}

#wirte {
	position: absolute;
	right: 30px;
}

.spot {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
}

.svg-wrapper {
	margin-top: 0;
	position: relative;
	width: 150px;
	/*make sure to use same height/width as in the html*/
	height: 40px;
	display: inline-block;
	border-radius: 3px;
	margin-left: 5px;
	margin-right: 5px
}

#shape {
	stroke-width: 6px;
	fill: transparent;
	stroke: #009FFD;
	stroke-dasharray: 85 400;
	stroke-dashoffset: -220;
	transition: 1s all ease;
}

#text {
	margin-top: -35px;
	text-align: center;
}

#text a {
	color: white;
	text-decoration: none;
	font-weight: 100;
	font-size: 1.1em;
}

.svg-wrapper:hover #shape {
	stroke-dasharray: 50 0;
	stroke-width: 3px;
	stroke-dashoffset: 0;
	stroke: #06D6A0;
}
</style>
</head>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>

	<div class="container">
		<br> <br>
		<hr>
		<h2 id="board">자주찾는 질문</h2>
		<table class="table" id="listArea">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성날짜</th>


				</tr>
			</thead>
			<tbody>
				<%for (Board b : list) {%>
				<tr class = "row1">
				<input type = "hidden" value = "<%=b.getPostsId() %>">
				<td><%= b.getPostsId() %></td>
				<td><%= b.getPostsTitle() %></td>
				<td><%= b.getMemberName() %></td>
				<td><%= b.getCreateDate() %></td>

			<%} %>
			</tbody>

		</table>

			<br><br><br><br>
		  <div class = "pagingArea" align ="center">
		<button onclick = "location.href = '<%=request.getContextPath()%>/SelectFrequentQuestionList.fq?currentPage=1'"><<</button>
		<%if(currentPage <= 1) {%>
		<button disabled> <</button>
		<%} else{%>
	<button onclick = "location.href='<%=request.getContextPath()%>/SelectFrequentQuestionList.fq?currentPage=<%=currentPage-1%>'"><</button>
		<%}
		%>
			<%for (int p = startPage; p <= endPage; p++) {
				if(currentPage == p){
			%>
				<button disabled><%= p %></button>
			<%} else{ %>
					<button onclick = "location.href='<%=request.getContextPath()%>/SelectFrequentQuestionList.fq?currentPage=<%=p%>'"><%= p %></button>
			<% }
			}
			%>


			<%if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<%}else{ %>
			<button onclick ="location.href='<%=request.getContextPath()%>/SelectFrequentQuestionList.fq?currentPage=<%=currentPage + 1%>'">></button>
			<%} %>
			<button onclick = "location.href='<%=request.getContextPath()%>/SelectFrequentQuestionList.fq?currentPage=<%=maxPage%>'">>></button>

      </div>
      	<div align = "right">
      <button class="btn btn-outline-danger"  onclick="location.href='<%=request.getContextPath()%>/views/manager/board/frequentQuestionwrite.jsp'">작성하기</button>
      </div>

	</div>

<script>
	$(function(){
		$("#listArea td").mouseenter(function(){

			$(this).parent().css({"background":"#ffe6e6","cursor":"pointer"});
		}).mouseout(function(){
				$(this).parent().css({"background":"white"});
		}).click(function(){
			var num = $(this).parent().children("input").val();
			 location.href="<%=request.getContextPath()%>/sofq.mm?num=" + num;
		});
	});

	

	</script>
</body>
</html>