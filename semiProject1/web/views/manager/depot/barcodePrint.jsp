<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%
    	String [] result = (String[]) request.getParameter("value").split(",");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/sp/js/jquery-barcode.js"></script>
<style>
	tr{
		border:1px solid black;		
	}
	td{
		border:1px solid black;
		text-align:center;
		
	}
	th{
		border:1px solid black;
		text-align:center;
	}
	#depotMain{
		width:80%;
		height: 80%;
		border:2px solid black;
	}
	#print-button{
		width:100%;
		background: black;
		color:white;
		border-radius: 50px;
	}
	#create{
		width:100%;
		background: black;
		color:white;
		border-radius: 50px;
	}
</style>
</head>
<body>
	<table id="depotMain" align="center">
		<caption>출력 내용 </caption>
		<thead>
			<tr id="btnArea">
			<td><button onclick="createBarCode();" id="create">생성</button></td>
			<td colspan="5"><button id="print-button" onclick="javaScript:window.print(); printBarcode();">인쇄하기</button></td>
			</tr>
		</thead>	
			<tr>
				<th>번호</th>
				<th>상품코드</th>
				<th>바코드넘버</th>
			</tr>
			<% for(int i =0;i<result.length;i++) {%>
			<tr>
				<td><%=i+1%></td>
				<td><label id="productCode<%=i%>"><%=result[i] %></label></td>
				<td id="barcode"><div id="bcTarget<%=i%>"></div></td>
			</tr>
			<%} %>			
	</table>
<script type="text/javascript">	
	function createBarCode(){
		if(<%=result.length%>>=10){
			alert("10개 이하로 선택후 출력해주세요");
		}else{
			 $("#bcTarget0").barcode($("#productCode0").text(), "codabar");
			 $("#bcTarget1").barcode($("#productCode1").text(), "codabar");
			 $("#bcTarget2").barcode($("#productCode2").text(), "codabar");
			 $("#bcTarget3").barcode($("#productCode3").text(), "codabar");
			 $("#bcTarget4").barcode($("#productCode4").text(), "codabar");	
			 $("#bcTarget5").barcode($("#productCode5").text(), "codabar");
			 $("#bcTarget6").barcode($("#productCode6").text(), "codabar");
			 $("#bcTarget7").barcode($("#productCode7").text(), "codabar");
			 $("#bcTarget8").barcode($("#productCode8").text(), "codabar");
			 $("#bcTarget9").barcode($("#productCode9").text(), "codabar");
			
		}		
	}		
</script>
	
</body>
</html>