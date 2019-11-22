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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
</head>
<style>
	#depotMain{
		width:80%;
		height: 80%;
		border:2px solid black;
		margin: 0px auto;
	}
	tr{
		border:1px solid black;
		text-align: center;
	}
	#th3{
		border:1px solid white;
		text-align:center;
		background: black;
		color:white;
	}
	td{
		border: 1px solid black;
		text-align:center;
	}
	#searchButton{
		padding-left:73%;
	}
	#depotMain button{
		border-radius: 20px;
		background: black;
		color: white;
	}
	#depotMain button:hover{
		border-radius:20px;
		background: ligthgray;
		color:white;
	}
</style>
<body>
<%@ include file = "../hfl/managerHeader.jsp" %>
	<br />
	<h3 align="center"><label for="">검수요청 관리</label></h3>
		<br />
	<div id="checkMain">
		<div id="table Area">
			
			<table id="depotMain" align="center">
			<tr>
				<th id="th3">번호</th>
				<th id="th3">상품코드</th>
				<th id="th3">카테고리</th>
				<th id="th3">상품명</th>			 
				<th id="th3">검수 요청일자</th>
				<th id="th3">검수진행상태</th>
			</tr>
			<%for(Depot d :list) {%>
			<tr>
				<td><%=d.getProductNumber() %></td>
				<td><%=d.getProductCode() %></td>
				<td hidden><input type="hidden" value="<%=d.getProductCode() %>" name="updateProduct" /></td>
				<td><%=d.getProductCate() %></td>
				<td><%=d.getProductName() %></td>
				<td><%=d.getCheckDate() %></td>
				<td><%if(d.getCheckStatus().equals("1")){ %>
						<button onclick="location.href='<%=request.getContextPath()%>/views/manager/depot/depotCheck.jsp?value=<%=d.getProductCode() %>,<%=d.getProductName() %>,<%=d.getProductCate() %>,<%=d.getFileName() %>'" style="color:white">검수요청</button>
					<%}else if(d.getCheckStatus().equals("2")) {%>
						<button onclick="location.href='<%=request.getContextPath()%>/views/manager/depot/depotCheck.jsp?value=<%=d.getProductCode() %>,<%=d.getProductName() %>,<%=d.getProductCate() %>,<%=d.getFileName() %>'" style="color:white">검수중</button>
					<%}else{ %>
						<label for="">검수완료</label>
						<button onclick="location.href='<%=request.getContextPath()%>/views/manager/depot/depotCheck.jsp?value=<%=d.getProductCode() %>,<%=d.getProductName() %>,<%=d.getProductCate() %>,<%=d.getFileName() %>'" style="color:white">수정</button>
					<%} %>
				</td>
				
			</tr>	
			<%} %>
			
			</table>
		</div>
	</div>
		<br>
	<div class="pagingArea" align="center">
			<button onclick="location.href='<%=request.getContextPath()%>/list.dp?currentPage=1'"><<</button>
			
			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/list.dp?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>
			
			<% for(int p = startPage; p <= endPage; p++){ 
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/list.dp?currentPage=<%=p%>'"><%= p %></button>
			<% 
				}
			   } 
			%>
			
			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/list.dp?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/list.dp?currentPage=<%=maxPage%>'">>></button>
		</div>

</body>
</html>