<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<HashMap<String, Object>> dataList = (ArrayList<HashMap<String, Object>>) request.getAttribute("dataList");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>

.statisticsOuter{
	padding-left: 10%;
	padding-right: 10%;
	margin:0 auto;
	margin-top: 5%;
}
#graphs {
	border: 1px dashed black;
	width: 80%;
}

.datatable {
	visibility:hidden;
}

#datatable td{
	border: 1px solid black;
	background: #eee;
}

#datatable, #datatable th {
	border: 2px solid black;
	background: #ddd;
}

.newTable {
	margin-top:10px;
	width: 80%;
}

.newTable td{
	border: 1px solid black;
	background: #eee;
}

.newTable, .newTable th{
	border: 2px solid black;
	background: #ddd;
}
</style>
</head>
<header><%@ include file="../hfl/managerHeader.jsp"%></header>
<body>
	<div class="statisticsOuter" align="center">
		<div id="graphs" class="statics" style="width:100%;">
			<div id="container" class="statisticsContainer"style="wdith:80%; min-width: 310px; height: 400px; margin: 0 auto"></div><!-- 차트가 생성 -->
		</div>
		<div id=stat class="staticsTable">
			<!-- 가로테이블 -->
			<table id="newTable" class="newTable">
		   		<thead>
			        <tr><th></th>
						<%for(int i = 0; i < dataList.size(); i++) {%>
							<th>
								<%=dataList.get(dataList.size()-i-1).get("payDate") %>
							</th>
						<%} %>
					</tr>
				</thead>
				<tbody>
					<tr><th>결제 건수</th>
						<%for(int i = 0; i < dataList.size(); i++){ %>
							<td>
								<%=dataList.get(dataList.size()-i-1).get("row") %>
							</td>
						<%} %>
					</tr>
					<tr><th>결제 금액</th>
						<%for(int i = 0; i < dataList.size(); i++){ %>
							<td>
								<%=dataList.get(dataList.size()-i-1).get("allPay") %>(만)
							</td>
						<%} %>
					</tr>
				</tbody>
			</table>
			<!-- 세로테이블 -->
			<table id="datatable" class="datatable">
	    		<thead>
			        <tr><th></th>
			            <th>결제건수</th>
			            <th>결제금액</th>
			        </tr>
			    </thead>
			    <tbody class="tableBody">
			    	
			        <%
			        	int length = dataList.size();
			        	if(length > 7){
			        		length = 7;
			        	}
			        	for(int i = 0; i < length; i++) {
			        %>
			        	<tr>
			        		<th><%=dataList.get(dataList.size()-i-1).get("payDate") %></th>
			        		<td><%=dataList.get(dataList.size()-i-1).get("row") %></td>
			        		<td><%=dataList.get(dataList.size()-i-1).get("allPay") %></td>
			        	</tr>
			        <%} %>
		    	</tbody>
			</table>
			
			
		</div>
	</div>
	
	<%@ include file="../hfl/footer.jsp"%>
	
	<script>
		Highcharts.chart('container', {
		    data: {
		        table: 'datatable'
		    },
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: '고객현황'
		    },
		    yAxis: {
		        allowDecimals: false,
		        title: {
		            text: '건수/금액(만)'
		        }
		    },
		    tooltip: {
		        formatter: function () {
		            return '<b>' + this.series.name + '</b><br/>'/*  +
		                this.point.y + ' '  */+ this.point.name.toLowerCase();
		        }
		    }
		    
		});
		
	</script>
</body>
</html>