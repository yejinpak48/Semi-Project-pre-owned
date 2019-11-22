<%@page import="com.kh.bvengers.manager.depot.model.vo.Depot"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Depot d = (Depot) request.getAttribute("list");
    	
    	String status = "";
    	if(d.getCompletStatus().equals("1")){
    		status="통과";
    	}else if(d.getCompletStatus().equals("2")){
    		status="불통과";
    	}else{
    		status= "조건부 통과";
    	}
    	System.out.print(d.getFileName());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<style>
	* {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
	}
	tr{
		border: 1px solid black;
	}
	td{
		border:1px solid black;
	}
	table{
		border:2px solid black;
		margin: 0 auto;
		background: white;
	}
	body{
		text-align: center;
		background: lightgray;
	}
	th{
		background: black;
		color:white;
	}
	img{
		width: 150px;
	}
</style>
</head>
<body>
	<br />
	<h3><label for="">상품 상세 정보</label></h3>
	<br />
	<br />
	<table>
		<tr>
			<td colspan="2" rowspan="2"><img src="<%=request.getContextPath() %>/thumbnail_uploadFiles/<%=d.getFileName() %>" alt="" /></td>
			<th><label for="">상품코드</label></th>
			<td><label for=""><%=d.getProductCode() %></label></td>
		</tr>
		<tr>
			<th><label for="">상품명</label></th>
			<td><label for=""><%=d.getProductName() %></label></td>
		</tr>
		<tr>
			<th><label for="">위치관리번호</label></th>
			<td colspan="3"><label for=""><%=d.getLocationCode() %></label></td>
		</tr>
		<tr>
			<th ><label for="">적치일자</label></th>
			<td colspan="3"><label for=""><%=d.getCheckDate() %></label></td>
		</tr>
		<tr>
			<th><label for="">출고일자</label></th>
			<td colspan="3"><label for=""><%=d.getReleaseDate() %></label></td>
		</tr>
		<tr>
			<th><label for="">검수상태</label></th>
			<td colspan="3"><label for=""><%=status %></label></td>
		</tr>
			<%if(d.getCompletStatus().equals("3")) {%>
		<tr>
			<th colspan="4">조건부 통과 사유</th>
		</tr>
		<tr>
			<td colspan="4"><%=d.getCheckPassContent() %></td>
		</tr>			
			<%} %>
		
	</table>
</body>
</html>