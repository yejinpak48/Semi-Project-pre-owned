<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   ArrayList<ArrayList<HashMap<String, Object>>> datalist = (ArrayList<ArrayList<HashMap<String, Object>>>) request.getAttribute("dataList");
   
   ArrayList<HashMap<String, Object>> allDatalist = datalist.get(0);
   ArrayList<HashMap<String, Object>> calculateDatalist = datalist.get(1);
   ArrayList<HashMap<String, Object>> refundDatalist = datalist.get(2);
   
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
                 <tr><th>단위(만)</th>
                  <%for(int i = 0; i < allDatalist.size(); i++) {%>
                     <th>
                        <%=allDatalist.get(allDatalist.size()-i-1).get("payDate") %>
                     </th>
                  <%} %>
               </tr>
            </thead>
            <tbody>
               <tr><th>매출이익</th>
                  <%for(int i = 0; i < allDatalist.size(); i++){ %>
                     <td>
                        <%=allDatalist.get(allDatalist.size()-i-1).get("allPrice") %>
                     </td>
                  <%} %>
               </tr>
               <tr><th>환불금액</th>
                  <%for(int i = 0; i < refundDatalist.size(); i++){ %>
                     <td>
                        <%=refundDatalist.get(refundDatalist.size()-i-1).get("refundParice") %>
                     </td>
                  <%} %>
               </tr>
               
               <tr><th>총 판매금액</th>
                  <%for(int i = 0; i < calculateDatalist.size(); i++){ %>
                     <td>
                        <%=calculateDatalist.get(calculateDatalist.size()-i-1).get("calParice") %>
                     </td>
                  <%} %>
               </tr>
            </tbody>
         </table>
         <!-- 세로테이블 -->
         <table id="datatable" hidden>
             <thead>
                 <tr>
                     <th></th>
                     <th>매출이익</th>
                     <th>환불금액</th>
                     <th>총 판매금액</th>
                 </tr>
             </thead>
             <tbody class="tableBody">
                
                 <%
                    int length = allDatalist.size();
                    if(length > 7){
                       length = 7;
                    }
                    for(int i = 0; i < length; i++) {
                       
                 %>
                    <tr>
                       <th><%=allDatalist.get(allDatalist.size()-i-1).get("payDate") %></th>
                       <td><%=calculateDatalist.get(calculateDatalist.size()-i-1).get("calParice") %></td>
                       <td><%=refundDatalist.get(refundDatalist.size()-i-1).get("refundParice") %></td>
                       <td><%=allDatalist.get(allDatalist.size()-i-1).get("allPrice") %></td>
                    </tr>
                 <%} %>
             </tbody>
         </table>
         <br />
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
              text: '매출현황'
          },
          yAxis: {
              allowDecimals: false,
              title: { 
                  text: '금액(만)'
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