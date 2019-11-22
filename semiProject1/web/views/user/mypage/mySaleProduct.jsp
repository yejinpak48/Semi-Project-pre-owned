<%@page import="com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo"%>
<%@page import="com.kh.bvengers.user.myPage.model.vo.myPage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<myPage> sList = (ArrayList<myPage>) request.getAttribute("sList");
	MyPagePageInfo pi = (MyPagePageInfo) request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<style>

.t_div {
	width:70%;
	height:100%;
	margin-left:auto;
	margin-right:auto;

}
#pagingArea {
	width:100%;
	margin-top:25px;
	margin-left:auto;
	margin-right:auto;
}

.pagingArea > button {
	background:#FFF;
	border:none;
	color:#ffb3b3;
	font-weight:bold;
	width:2%;
}
.pagingArea button:hover{
		color: white;
		background: #ffb3b3;
		border-radius: 10px;
	}


#sec1 {
	width: 100%;
	height: 100%;
	padding-right: 20%;
	padding-left: 20%;
	margin:0 auto;
}

.board {
	width: 90%;
	margin: auto;
	align: center;
	
	border-radius:5px;
	border-collapse: collapse;
    line-height: 1.5;
}
 .board th {

    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FFF;
}

.board td {

    padding: 5px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

.board thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    border-bottom: 2px solid #ccc;
}
 

.th1 {
	text-align:center;
	
}
.od {
	text-align:center;
}
</style>

</head>
<body>
	<!-- header 영역 -->
	<header><%@ include file="../hfl/header.jsp" %></header>
	<header><%@ include file="../hfl/myPageList.jsp"%></header>
	
		<div class="t_div" style="margin-right:10%;"><br>
			<h3 align="center">나의 판매현황</h3>
			<table class="board">
				<thead>
				<tr>
					<th class="th1">게시글 번호</th>
					<th class="th1">상품명</th>
					<th class="th1">판매여부</th>
					<th class="th1">정산여부</th>
					<th class="th1">남은 보관일</th>
				</tr>
				</thead>
				<% for(myPage m : sList){%>
				<tbody>
				<tr class="od">
					<td><%=m.getPid() %></td>
					<td><%=m.getPname() %></td>
					<td><%=m.getPayStatus()%></td>
					<td><%=m.getaStatus() %></td>
					<td><%=m.getkDate() %>일</td>
				</tr>
				</tbody>
				<%}%>
			</table>
		
<%-- 페이징처리 --%>
<div id="pagingArea">
		<div class="pagingArea" align="center">
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/mySaleProduct.mp?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/mySaleProduct.mp?currentPage=<%=currentPage - 1%>'"><</button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){ %>
					<button class="btn_paging" disabled><%= p %></button>
			<% } else { %>
					<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/mySaleProduct.mp?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }
			%>

			<% if(currentPage >= maxPage){ %>
			<button class="btn_paging" disabled>></button>
			<% }else{ %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/mySaleProduct.mp?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/mySaleProduct.mp?currentPage=<%=maxPage%>'">>></button>
		</div>
		</div></div>
		
	<footer><%@ include file="../hfl/footer.jsp" %></footer>
	
	<script>
	$(function(){
        $(".board td").mouseenter(function(){

           $(this).parent().css({"background":"darkgray","cursor":"pointer"});
        }).mouseout(function(){
              $(this).parent().css({"background":"white"});
        })
     });

	
	</script>
</body>
</html>























