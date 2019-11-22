<%@page import="com.kh.bvengers.board.model.vo.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Calculate> list = (ArrayList<Calculate>) request.getAttribute("list");
	BoardPageInfo bi = (BoardPageInfo) request.getAttribute("bi");
	int listCount = bi.getListCount();
	int currentPage = bi.getCurrentPage();
	int maxPage = bi.getMaxPage();
	int startPage = bi.getStartPage();
	int endPage = bi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
#sec1 {
	width: 100%;
	height: 100%;
	padding-right: 20%;
	padding-left: 20%;
	margin: auto;
	align:center;
}

#area {
	width: 100%;
	padding: 2%;
	margin: 0 auto;
	text-align: center;
}

.contentsDiv{
	border:3px solid #acf;
	border-top:10px solid #acf;
	width:80%;
	margin:0 auto;
}

#deliTable {
	align: center;
}

#deliTable tr {
	align: center;
	border-bottom: 1px solid #acf;
}

#deliTable tr td {
	align: center;
}

#deliTable tr td img {
	width: 30%;
	align: center;
}

.board {
	width: 90%;
	margin: auto;
	align: center;
	border-spacing: 10px;
	text-align:center;
}

.th {
	border: 1px solid white;
	background: #acf;
	color: white;
	text-align: center;
}

.upStatus{
	border-radius: 10px;
	border:1px solid #faa;
	background:#fde;
	font-size: .8em;
}

.board_tr{
	align: center;
	border-bottom: 1px solid #acf;
}

.border_td{
	height:30px;
}

board .row0 {
	background: #eee;
	margin: auto;
}

.pageNo {
	margin: auto;
}
.gumehaukjung{
align:center;
}
#numberBox{
	margin-left: 40%;
}
</style>
</head>
<body>
<%@ include file="../hfl/managerHeader.jsp" %>

		<br />
		<div>
			<h2 align="center">구매내역 조회</h2>
			<div class="contentsDiv">
			<br /><br />
			<div id="inOutButton" align="center">
			
				<form action="searchDeli.deli">
				
				<input type="hidden" value="<%=list.size()%>" name="rowCount" id="rowCount" />
				<input type="hidden" value="<%=currentPage %>" name="curr" />
				<select name="selOption" style="width:10%; ">
					<option value="select">선택</option>
					<option value="wait">주문완료</option>
					<option value="success">배송중</option>
					<option value="sellfail">구매확정</option>
				</select>
				<span> <input type="date" name="selectDate" id="selectDate"/>
					<button type="submit" style="border-radius: 5px; background-color: black; color: white;">조회</button>
				</span>
				</form>
			</div>	
			<br />
				<table class="board">
					<tr class="row0">
						<th class="th">번호</th>
						<th class="th">판매자ID</th>
						<th class="th">판매 상품</th>
						<th class="th">구매자ID</th>
						<th class="th">주문날짜</th>
						<th class="th">발생일자</th>
						<th class="th">진행상태</th>
						<th class="th">상태변경</th>
					</tr>
					<tbody>
						<% int rowCount = 0;
							for(Calculate c : list){
						%>
							<tr class="board_tr">
								<input type="hidden" value="<%= c.getDeliveryNo() %>" name="deliNo">
								<input type="hidden" value="<%=rowCount%>" name="rowCount" id="rowCount" />
								<td class="border_td"><%= c.getOrderNo() %></td>
								<td class="border_td"><%= c.getSellerId() %></td>
								<td class="border_td"><%= c.getPostsTitle() %></td>
								<td class="border_td"><%= c.getBuyerId() %></td>
								<td class="border_td"><%= c.getOrderDate() %></td>
								<td class="border_td"><%= c.getCalculateDate() %></td>
								<td class="border_td">
									<!-- 검수테이블 id를 검색 후 검색결과가 있을 경우 정산완료, null일 경우 아래 if문 실행하도록 이후에 보수 -->
									<% if(c.getDeliveryStatus().equals("1")){%>
										주문완료
									<%}else if(c.getDeliveryStatus().equals("2")){ %>
										배송중
									<%}else if(c.getDeliveryStatus().equals("3")){ %>
										구매확정
									<%} %>
								</td>
								<td class="border_td">
									<button type="button" name="status" class="upStatus"
												onclick="location.href='<%=request.getContextPath()%>/deliSucess?deliNo=<%=c.getDeliveryNo()%>'">상태변경</button>
								</td>
		
							</tr>
						<% 	rowCount++;
							}
						%>
					</tbody>
				</table>
				<br />
				<div class="pagingArea" align="center">
					<button type="button" onclick="location.href='<%=request.getContextPath()%>/productManagement?currentPage=1'"><<</button>
		
					<%if(currentPage <= 1){%>
						<button disabled><</button>
					<%}else{ %>
						<button type="button" onclick="location.href='<%=request.getContextPath()%>/productManagement?currentPage=<%=currentPage -1%>'"><</button>
					<%} %>
		
					<%for(int p = startPage; p <= endPage; p++){
						if(currentPage == p){
					%>
							<button type="button" disabled><%= p %></button>
						<%}else{ %>
							<button type="button" onclick="location.href='<%=request.getContextPath()%>/productManagement?currentPage=<%=p%>'"><%=p %></button>
					<%
						}
					}
					%>
		
		
					<%if(currentPage >= maxPage){ %>
						<button type="button" disabled>></button>
					<%}else{ %>
						<button type="button" onclick="location.href='<%=request.getContextPath()%>/productManagement?currentPage=<%=currentPage + 1 %>'">></button>
					<%} %>
					<button type="button" onclick="location.href='<%=request.getContextPath()%>/productManagement?currentPage=<%= maxPage%>'">>></button>
				</div>
				<br />
			</div>
		</div>
	
				<%-- <div id="numberBox"><%@ include file="../hfl/pagination.jsp" %></div> --%>
		
	<br /><br /><br /><br />
	<br>
	<footer><%@ include file="../hfl/footer.jsp" %></footer>

</body>
</html>