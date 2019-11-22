<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.depot.model.vo.*"%>
    <%
       ArrayList <Depot> list = (ArrayList <Depot>) request.getAttribute("list");
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
<style>
   #depotMain{
      width:80%;
      height: 80%;
      border:2px solid black;
      margin: 0px auto;
   }
   tr{
      border:1px solid black;
   }
   #th{
      border:1px solid white;
      background:black;
      text-align:center;
      color:white;
   }
   td{
      text-align:center;
   }
   #inOutButton{
      padding-left:73%;
   }
   #requestRelease{
   	width: 80%;   	
   	 margin: 0 auto;
   	border:1px solid black;
   	text-align: center;
   }
   #requestProduct{
   	width: 80%;
   	
   }
   .button-7{
   width:100%;
  border:2px solid #34495e;
  text-align:center;
  cursor:pointer;
  position:relative;
  box-sizing:border-box;
  overflow:hidden;
  margin:0 auto;
}
.button-7 a{
  font-family:arial;
  font-size:16px;
  color:#34495e;
  text-decoration:none;
  line-height:50px;
  transition:all .5s ease;
  z-index:2;
  position:relative;
}
.eff-7{
   width:100%;  
  border:0px solid black;
  position:absolute;
  transition:all .5s ease;
  z-index:1;
  box-sizing:border-box;
}
.button-7:hover .eff-7{
  border:70px solid black;
}
.button-7:hover a{
  color:#fff;
}
</style>
<body>
<%@ include file = "../hfl/managerHeader.jsp" %>
   <br />
   <h3 align="center">입고 관리</h3>
   <br />
   <div id="inOutMain">   		
      <div id="table Area">
         <table id="depotMain" align="center">
         <tr>
         	<td colspan="6"><input type="text" id="requestProduct"	value="" width="50%" onkeypress="if(event.keyCode==13){enterEvent();}" /></td>
        	<td colspan="2">
        		<div class="button-7">
				    <div class="eff-7"></div>
				    <a id="request" onclick="request()"> 출고요청</a>
			  </div>
   			</td>
         </tr>
         <tr>
         	<th id="th"></th>
            <th id="th">번호</th>
            <th id="th">상품코드</th>
            <th id="th">회원아이디</th>
            <th id="th">위치관리번호</th>
            <th id="th">검수상태</th>
            <th id="th">적치 일자</th>
            <th id="th">출고여부</th>
         </tr>
         <%for(Depot d :list){ %>
            <tr>
            	<td><input type="checkBox" value="<%=d.getProductCode()%>" onclick="appendText();" id="checkStatus"/></td>
               <td><%=d.getProductNumber() %></td>
               <td><%=d.getProductCode() %><input type="hidden" id="productCode" value="<%=d.getProductCode()%>"/></td>
               <td><%=d.getSelerId() %></td>
               <td><%=d.getLocationCode() %></td>
               <td><%=d.getCompletStatus() %></td>
               <td><%=d.getCheckDate() %></td>
               <td><%if(d.getPayStatus()==null){
            		d.getReleaseDate();
            	}else{%> 
					결제완료
            	<%} %>
            	</td>
            </tr>
         <%} %>
         </table>

      </div>
   </div>
		<br>
	<div class="pagingArea" align="center">
			<button onclick="location.href='<%=request.getContextPath()%>/inDepot.dp?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/inDepot.dp?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/inDepot.dp?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }
			%>

			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/inDepot.dp?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/inDepot.dp?currentPage=<%=maxPage%>'">>></button>
		</div>
   <script>
   	$(".button-7").click(function(){
   	 var productCode = $("input[id='requestProduct']").val();
		$(function (){
			 var aCode = productCode;
			 $.ajax({
			 url:"outProduct.do",
			 type:"post",
			 data:{"productCode":aCode},
			 success:function(data){
				 window.location.reload();
			 }
		 });
		 });
   	});
  	
  
     function appendText(){
         var text = $("input[id='requestProduct']");
         text.val("");
    	 var code="";
    	 $("#checkStatus:checked").each(function(index){
				code+=$(this).val()+","
			});
    	 text.val(code);
    	 
    	}
    function enterEvent(){
    	 var text = $("input[id='requestProduct']");
    	var code ="";	
    	 text.each(function(index){
    			code=$(this).val()+","
    		});
    	 text.val(code);
    }     
   </script>

</body>
</html>