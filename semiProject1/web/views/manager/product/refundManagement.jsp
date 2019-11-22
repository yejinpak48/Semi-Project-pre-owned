<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.bvengers.product.model.vo.Refund"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.bvengers.product.model.vo.CalculPageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   ArrayList<Refund> rList = (ArrayList<Refund>)request.getAttribute("rList");
   CalculPageInfo pi = (CalculPageInfo)request.getAttribute("pi");
   int listCount = pi.getListCount();
   int currentPage = pi.getCurrentPage();
   int maxPage = pi.getMaxPage();
   int startPage = pi.getStartPage();
   int endPage = pi.getEndPage();

   DecimalFormat dc = new DecimalFormat("###,###,###,###");


%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

#refund_h {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
}
#btn_submit {
	border: 1px solid #ccc;
	background: #FFF;
	width: 50px;
	border-radius: 5px;
}
.btn_1 {
	border: 1px solid #ccc;
	background: #FFF;
	width: 50px;
	border-radius: 5px;
}

.btn_2 {
	border: 1px solid #ccc;
	background: #FFF;
	width: 50px;
	border-radius: 5px;
}

.pagingArea>button {
	background: #FFF;
	border: none;
	color: #ffb3b3;
	font-weight: bold;
	width: 2%;
	margin-bottom:10%;
}

.pagingArea button:hover {
	color: white;
	background: #ffb3b3;
	border-radius: 10px;
}

#refundMain {
	width: 95%;
	height: 80%;
	margin: auto;
	align: center;
	border-radius: 5px;
	border-collapse: collapse;
	line-height: 1.5;
}

#refundMain th {
	padding: 5px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 2px solid #ccc;
	background: white;
	color: black;
}

#refundMain td {
	padding: 5px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}

#refundMain thead th {
	padding: 5px;
	font-weight: bold;
	vertical-align: top;
	color: black;
	border-bottom: 2px solid #ccc;
}

#inOutButton {
	padding-left: 8%;
}

#inOutMain {
	width: 80%;
	margin: 0 auto;
}

#table Area {
	align: center;
	width: 90%;
}

.th {
	background: black;
	color: white;
	text-align: center;
	border: 1px solid white;
}

.od {
	text-align: center;
}
</style>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<br />
	<h3 align="center" id="refund_h">환불내역 관리</h3>
	<form action="<%=request.getContextPath()%>/refundSearch.mp" method="post">
		<div id="inOutMain">
			<br>
			<br>
			<div id="inOutButton" align="center">
				<select name="selectRefund" style="width: 20%;">
					<option value="wait">환불 대기</option>
					<option value="success">환불 완료</option>
					<option value="cancel">환불 취소</option>
				</select>
				<button type="submit" id="btn_submit">조회</button>
			</div>
			<div id="table Area">
				<table id="refundMain" align="center">
					<tr>
						<th class="th">주문번호</th>
						<th class="th">신청날짜</th>
						<th class="th">회원번호</th>
						<th class="th">상품코드</th>
						<th class="th">검수여부</th>
						<th class="th">환불상태</th>
					</tr>
					<% for(Refund r : rList) {%>
					<tr class="od">
						<td id="pno" style="display: none;"><%=r.getPno() %></td>
						<td id="pcode" style="display: none;"><%=r.getpCode() %></td>
						<td><%=r.getOno() %></td>
						<td><%=r.getrDate() %></td>
						<td><%=r.getMno() %></td>
						<td><%=r.getPno() %></td>
						<td><button type="button" class="btn_1" value="1">통과</button>
							<button type="button" class="btn_2" value="2">미통과</button></td>
						<td><%=r.getrStatus() %></td>
					</tr>
					<% } %>
				</table>
				<br>
				<br>
			</div></div>
	</form>


	<div class="pagingArea" align="center">
		<button
			onclick="location.href = '<%=request.getContextPath()%>/refundProduct.mp?currentPage1=1'"><<</button>
		<%if(currentPage <= 1) {%>
		<button disabled><</button>
		<%} else{%>
		<button
			onclick="location.href='<%=request.getContextPath()%>/refundProduct.mp?currentPage1=<%=currentPage-1%>'"><</button>
		<%}%>
		<%for (int p = startPage; p <= endPage; p++) {
            if(currentPage == p){
         %>
		<button disabled><%= p %></button>
		<%} else{ %>
		<button
			onclick="location.href='<%=request.getContextPath()%>/refundProduct.mp?currentPage1=<%=p%>'"><%= p %></button>
		<% }
         }
         %>


		<%if(currentPage >= maxPage){ %>
		<button disabled>></button>
		<%}else{ %>
		<button
			onclick="location.hreh='<%=request.getContextPath()%>/refundProduct.mp?currentPage1=<%=currentPage + 1%>'">></button>
		<%} %>
		<button
			onclick="location.href='<%=request.getContextPath()%>/refundProduct.mp?currentPage1=<%=maxPage%>'">>></button>

	</div>

	<script>

$(".btn_1").click(function(){ 
	var pass = $(this).val();
	var pno = $(this).parent().siblings().eq(0).text();
	var pcode = $(this).parent().siblings().eq(1).text();
 	location.href='<%=request.getContextPath()%>/refundChange.mp?pass='+pass+'&&pno='+pno+'&&pcode='+pcode;
 });
 
$(".btn_2").click(function(){ 
	var pass = $(this).val();
	var pno = $(this).parent().siblings().eq(0).text();
	var pcode = $(this).parent().siblings().eq(1).text();
	location.href='<%=request.getContextPath()%>/refundChange.mp?pass='+pass+'&&pno='+pno+'&&pcode='+pcode;
 });
 
</script>


</body>
</html>