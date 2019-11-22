<%@page import="com.kh.bvengers.product.model.vo.CalculPageInfo"%>
<%@page import="com.kh.bvengers.product.model.vo.Calcul"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Calcul> list = (ArrayList<Calcul>) request.getAttribute("list");
	CalculPageInfo ci = (CalculPageInfo) request.getAttribute("ci");
	int listCount = ci.getListCount();
	int currentPage = ci.getCurrentPage();
	int maxPage = ci.getMaxPage();
	int startPage = ci.getStartPage();
	int endPage = ci.getEndPage();
	int limit = (int) request.getAttribute("limit");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.bootpay.co.kr/js/bootpay-3.0.2.min.js" type="application/javascript"></script>
<style>
#depotMain {
	width: 95%;
	height: 80%;
	margin:0 auto;
	padding:auto;
}
.depotMainTd{
	height:30px;
}
.btn-option{
	text-align:center;
	background: #fed4d4;
	border-radius: 15px;
	border:1px solid white;
	font-size:.8em;
	border-right: 1px solid #aa7878;
	border-bottom: 1px solid #aa7878;
	
}

tr {
	border: 1px solid #acf;
}
.successTd{
	border-left: 1px sloid #acf;
}
.th {
	border: 1px solid white;
	background: #acf;
	color: white;
	text-align: center;
}

#inOutButton {
	padding-left: 8%;
}

#inOutMain {
	border: 2px solid #5af;
	border-top: 10px solid #5af;
	
	width: 80%;
	margin: 0 auto;
}

#table Area {
	align: center;
	width: 90%;
}

</style>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<br />
	<div>
		<h2 align="center">정산내역 관리</h2>
		<div id="inOutMain">
			<br>
			<br>
			<div id="inOutButton" align="center">
				<form action="search.cal">
				
				<input type="hidden" value="<%=list.size()%>" name="rowCount" id="rowCount" />
				<input type="hidden" value="<%=currentPage %>" name="curr" />
				<input type="hidden" value="<%=limit %>" name="limited" />
				<select name="selOption" style="width:10%; ">
					<option value="select">선택</option>
					<option value="wait">정산대기</option>
					<option value="success">정산완료</option>
					<option value="sellfail">환불처리</option>
				</select>
				<span> <input type="date" name="selectDate" id="selectDate"/>
					<button type="submit" style="border-radius: 5px; background-color: black; color: white;">조회</button>
				</span>
				</form>
			</div>
			
			
			<br>
			<br>
			<div id="table Area">
				
				<table id="depotMain" class="depotMain">
					<thead>
						<tr>
							<th class="th">No.</th><!-- 정산번호 -->
							<th class="th">거래번호</th>
							<th class="th">정산금액</th>
							<th class="th">회원번호</th>
							<th class="th">정산여부</th><!-- 1 : 정산 대기, 2: 정산완료, 3.환불처리 -->
							<th class="th">이력발생일자</th>
							<th class="th">정산</th>
							<th class="th">환불</th>
						</tr>
					</thead>
					<tbody align="center">
					<% int rowCount = 0;
						for(Calcul c : list){
					%>
						<tr>
							<input type="hidden" value="<%=rowCount%>" name="rowCount" id="rowCount" />
							<td class="adjCode depotMainTd"><%= c.getAdjustNo() %></td>
							<td class="depotMainTd"><%= c.getPayDtno() %></td>
							<td class="depotMainTd"><%= c.getPrice() %></td>
							<td class="depotMainTd"><%= c.getMemberNo() %></td>
							<td class="depotMainTd">
							<%if(c.getAdjustDiv().equals("1")) {%>
							정산대기
							<%}else if(c.getAdjustDiv().equals("2")) {%>
							정산완료
							<%}else{ %>
							환불처리
							<%} %>
							</td>
							<td  class="depotMainTd" style="border-right:1px solid #acf;"><%= c.getAdjustDate() %></td>
							<%if(c.getAdjustDiv().equals("1")){ %>
								<td  class="depotMainTd" style="width:8%;"><button class="btn-option success" value="<%= c.getAdjustNo() %>" style="float: center; margin-right: 5px; width:90%;">정산처리</button></td>
								<td  class="depotMainTd" style="width:8%; border:1px solid #acf;"><button class="btn-option fail" value="<%= c.getReceipt() %>" style="float: center; margin-right: 2.5%; width:90%;">환불처리</button></td>
							<%}else{ %>
								<td  class="depotMainTd" colspan="2">처리완료</td>
							<%} %>
							
						</tr>
					<% 	rowCount++;
						} 
					%>
				</tbody>
				</table>
				<br>
				<div class="pagingArea" align="center">
				<button type="button" onclick="location.href='<%=request.getContextPath()%>/selectCalculate.cal?currentPage=1'"><<</button>
				
				<%if(currentPage <= 1){%>
					<button disabled><</button>
				<%}else{ %>
					<button type="button" onclick="location.href='<%=request.getContextPath()%>/selectCalculate.cal?currentPage=<%=currentPage -1%>'"><</button>
				<%} %>
				
				<%for(int p = startPage; p <= endPage; p++){ 
					if(currentPage == p){
				%>
						<button type="button" id="nowPage" value="<%= p %>" disabled><%= p %></button>
					<%}else{ %>
						<button type="button" onclick="location.href='<%=request.getContextPath()%>/selectCalculate.cal?currentPage=<%=p%>'"><%=p %></button>
				<%
					}
				}
				%>
			
				
				<%if(currentPage >= maxPage){ %>
					<button type="button" disabled>></button>
				<%}else{ %>
					<button type="button" onclick="location.href='<%=request.getContextPath()%>/selectCalculate.cal?currentPage=<%=currentPage + 1 %>'">></button>
				<%} %>
				<button type="button" onclick="location.href='<%=request.getContextPath()%>/selectCalculate.cal?currentPage=<%= maxPage%>'">>></button>
			</div>
				
				
				<br>
			</div>
		</div>
	</div>
	<script>
		$(".success").click(function(){
				var code = $(this).attr('value');
				var limit = <%=limit%>;
				var currentPage = $("#nowPage").val();
				
				$.ajax({
					url:"disposeSuccess.cal",
					type:"post",
					data:{"code":code, "currentPage":currentPage, "limit":limit},
					success:function(data){
							window.location.reload();	
					}
				});
		});
			
		
		$(".fail").click(function(){
			var codeTd = $(this).parents().parents().children().eq(1)[0].innerHTML;
			
			var code = codeTd;
			var limit = <%=limit%>;
			var currentPage = $("#nowPage").val();
			
			var receipt_id = $(this).attr('value');
			var application_id = "5d2fec7c396fa61e224d5733";
			var private_key = "ejl6AnJUiKPi72RjxiJd578NY7KzMSiq4p5FxWXmC6U=";
			$.ajax({
				url:"https://api.bootpay.co.kr/request/token",
		        type:"post",
		        dataType:'json',
		        data:{application_id:application_id, private_key:private_key},
		        success:function(data){
		        	console.log(data["data"]["token"]);
		        	var token = data["data"]["token"];
		        	$.ajax({
		        		url:"https://api.bootpay.co.kr/cancel.json",
		        		type:"post",
		        		dataType:'json',
		        		data:{"receipt_id":receipt_id},
		        		beforeSend:function(xhr){
		        			xhr.setRequestHeader("Content-Type","application/json");
                            xhr.setRequestHeader("Authorization", token);
                        },
		        		
		        		success:function(data){
		        			console.log(data);
		        		}
		        	});
		        	
		        	/* $.ajax({
		        		url:"https://api.bootpay.co.kr/cancel",
		        		type:"post",
		        		headers: {"Authorization":token}, 
		        		beforeSend:function(xhr){
                            xhr.setRequestHeader("Authorization", token);
                        },
		        		success:function(data){
		        			
		        		}
		        	}); */
		        	
		        	/* $.ajax({
						url:"disposeFail.cal",
						type:"post",
						data:{"code":code, "currentPage":currentPage, "limit":limit},
						success:function(data){
								window.location.reload();	
						}
					}); */
		        }
			});
		});
	</script>

</body>
</html>











