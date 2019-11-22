<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.depot.model.vo.*"%>
    <%
     	ArrayList<Depot> list = (ArrayList<Depot>) request.getAttribute("list");
	    DepotPageInfo pi  = (DepotPageInfo) request.getAttribute("pi");
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
<title>Insert title here</title>
<style>
	#depotMain{
		width:80%;
		height: 80%;
		border:2px solid black;
		margin: 0 auto;
	}
	tr{
		border:1px solid black;
	}
	#th{
		border:1px solid white;
		background:black;
		color:white;
		text-align:center;
	}
	#inOutButton{
		padding-left:73%;
	}
	td{
		text-align: center;
	}
</style>
<body>
<%@ include file = "../hfl/managerHeader.jsp" %>
	<br />
	<h3 align="center">출고 관리</h3>
	<br />
	<div id="inOutMain">
		<div id="table Area">
			<table id="depotMain" align="center">
			<tr>
				<th id="th">번호</th>
				<th id="th">상품코드</th>
				<th id="th">회원아이디</th>
				<th id="th">위치관리번호</th>
				<th id="th">적치 일자</th>
				<th id="th">출고 일자</th>
			</tr>
			<% for(Depot d: list) {%>
			<tr>
				<td><%=d.getProductNumber() %></td>
				<td><%=d.getProductCode() %></td>
				<td><%=d.getSelerId() %></td>
				<td><%=d.getLocationCode() %></td>
				<td><%=d.getCheckDate() %></td>
				<td><%=d.getReleaseDate() %></td>
			</tr>
			<%} %>
			</table>
		</div>
	</div>
	<br />
	<div class="pagingArea" align="center">
			<button onclick="location.href='<%=request.getContextPath()%>/outProductList.dp?currentPage=1'"><<</button>
			
			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/outProductList.dp?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>
			
			<% for(int p = startPage; p <= endPage; p++){ 
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/outProductList.dp?currentPage=<%=p%>'"><%= p %></button>
			<% 
				}
			   } 
			%>
			
			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/outProductList.dp?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/outProductList.dp?currentPage=<%=maxPage%>'">>></button>
		</div>

</body>
</html>