<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.depot.model.vo.*"%>
  <%
	String file_name = "DepotList";
	String ExcelName = new String(file_name.getBytes(),"UTF-8")+".xls";
	response.setContentType("application/vnd.ms-excel;UTF-8");
	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName);
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Pragma", "no-cache");
	
	String table = request.getParameter("excel_table");
	ArrayList<Depot> list = (ArrayList<Depot>) request.getAttribute("list");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="excel_form" id="excel_form" action="excel_download.jsp" method="post" target="_blank">
<input type="hidden" name="excel_table" id="excel_table" />
<input type="hidden" name="file_name" id="file_name" />
<table id="depotMain" align="center">
			<thead>
			<tr style="background: black;color:white;">
				<th id="th3">번호</th>
				<th id="th3">상품코드</th>
				<th id="th3">상품명</th>
				<th id="th3">위치관리번호</th>
				<th id="th3">적치 일자</th>
				<th id="th3">출고 일자</th>
			</tr>
			</thead>
			<tbody>
			<%for(Depot d :list) {%>
			<tr id="list">
				<td><label for=""><%=d.getProductNumber() %></label></td>
				<td><label for=""><%=d.getProductCode() %></label></td>
				<td><label for=""><%=d.getProductName() %></label></td>
				<td><label for=""><%=d.getLocationCode() %></label></td>
				<td><label for=""><%=d.getCheckDate() %></label></td>
				<td><label for=""><%=d.getReleaseDate() %></label></td>
			</tr>
			<%} %>
			</tbody>
		</table>
</form>
<script>
$("#excel_table").val($("#depotMain").html());
$("#excel_form").submit();
</script>
</body>
</html>