<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.bvengers.user.myPage.model.vo.myPage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<myPage> odList = (ArrayList<myPage>) request.getAttribute("odList");
	String msg = (String)request.getAttribute("msg");
	DecimalFormat dc = new DecimalFormat("###,###,###,###");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
<style>
.d123 {
	height:80%;
}

button{
	border:1px solid #bcbcbc;
	background:#FFF;
	width:70px;
}

.t123 {
	margin-left:30%;
	border: 1px solid #bcbcbc;
	text-align:center;
	width:60%;
}

.t123 > td {
	border: 1px solid #bcbcbc;
	margin:0 auto;
}
.t123 > tr {
	border: 1px solid #bcbcbc;
}

.th3 {
	border:1px solid #bcbcbc;
	text-align: center;
}
.th4{
	text-align: center;
	width:20%;
	border:1px solid #bcbcbc;
}
</style>
</head>


<body>
	<header><%@ include file="../hfl/header.jsp"%></header>
	<header><%@ include file="../hfl/myPageList.jsp"%></header>
	<div class="d123">
	<table class="t123">
		<caption>결제 정보</caption>
		<tbody>
			<tr>
			<% for(myPage m : odList){
				%>
				<th class="th3">주문금액</th>
				<% String price = dc.format(m.getDtPay());
			 	   String total = dc.format(m.getDtPay()+m.getdPay());
				%>
				<td class="th3"><%=total%>원</td>
				<td class="th3">상품금액 <%=price%> + 배송비 <%=m.getdPay() %>원</td>
			</tr>
			<tr>
				<th class="th3">총 결제금액</th>
				<td class="th3"><%=total%>원</td>
				<td class="th3">신용카드 <%=total%>원</td>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<br>
	<table class="t123">
		<caption>주문 상품 정보</caption>
		<thead>
			<tr>
				<th class="th3">주문번호</th>
				<th class="th3">상품명</th>
				<th class="th3">상품금액</th>
				<th class="th3">주문상태</th>
				<th class="th3">운송장번호</th>
				<th class="th3">신청</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="th3"><%=m.getOno()%></th>
				<td class="th3"><%=m.getPname()%></td>
				<td class="th3"><%=price%>원</td>
				<td class="th3"><%=m.getPstatus() %></td>
				<td><%=m.getDname()%>&nbsp;<%=m.getInNo()%>&nbsp;<button onclick="window.open('<%=request.getContextPath()%>/views/user/mypage/deliveryAPI.jsp','window_name','width=800,height=500,location=yes,status=no,scrollbars=yes');">배송조회</button></td>
				<% if(m.getPstatus().equals("배송 완료")) { %>
				<td class="th3"><button class="returnPopUp">환불신청</button><br>
				<button class="btn_cancel"style="display:none;">주문취소</button></td>
				<% } if(m.getPstatus().equals("배송 준비중")) { %>
				<td class="th3"><button class="btn_cancel">주문취소</button><br>
				<button class="returnPopUp" style="display:none;">환불신청</button></td>
				<% }else { %>
				<td class="th3"><button class="btn_cancel"style="display:none;">주문취소</button><br>
				<button class="returnPopUp" style="display:none;">환불신청</button></td>
				<% } %>
			</tr>
		</tbody>
	</table>
	<br>
	<br>
	<br>
	<table class="t123">
		<caption>배송지 정보</caption>
		<tbody>
			<tr>
				<th class="th4">받는 사람</th>
				<td rowspan="5"><%=m.getrName() %><br>
				<%=m.getdSite() %><br>
				<%=m.getrPhone() %><br></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<br>
	</div>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>
	<script>

	$(function(){
		
	$(".returnPopUp").click(function(){ 
    	var ono = $(this).parent().siblings().eq(0).text();
    	window.open('<%=request.getContextPath()%>/views/user/mypage/refundPopUp.jsp?ono='+ono,'window_name','width=700,height=400,location=yes,status=no,scrollbars=no')
     });
	});
	
	$(function(){
		
		$(".btn_cancel").click(function(){
			var ono = $(this).parent().siblings().eq(0).text();
			window.open('<%=request.getContextPath()%>/views/user/mypage/cancelPopUp.jsp?ono='+ono,'window_name','width=700,height=400,location=yes,status=no,scrollbars=yes')
		});
	});
</script>


</body>
</html>