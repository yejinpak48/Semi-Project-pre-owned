<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.depot.model.vo.*"%>
    <%
    	ArrayList<Depot> list = (ArrayList<Depot>) request.getAttribute("list");
    	DepotPageInfo pi  = (DepotPageInfo) request.getAttribute("pi");
    	int listCount = pi.getListCount();
    	int currentPage = pi.getCurrentPage();
    	int maxPage = pi.getMaxPage();
    	int startPage = pi.getStartPage();
    	int endPage = pi.getEndPage();
    	String num = "num";
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<style>
	#depotMain{
		width:80%;
		height: 80%;
		border:2px solid black;
		margin: 0px auto;
	}
	tr{
		border:1px solid white;
	}
	td{
		border:1px solid white;
		text-align:center;

	}
	#th3{
		border:1px solid white;
		text-align: center;
		background: black;
		color:white;
	}
	#location{
		padding-right: 190px;
	}
	#date{
		padding-right: 190px;
	}
	#searchProductCode{
		padding-right: 190px;
	}
	#selectSearch{
		width: 80%;
		margin: 0 auto;
		height: 12%;
		}
		
	#productSearchImg{
		width: 30%;
		height: 30%;
	}
	.wrap {
	    text-align: center;
	}
	
	#search ,#excelGo {
	    -webkit-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	    -moz-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	    -ms-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	    -o-transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	    transition: all 200ms cubic-bezier(0.390, 0.500, 0.150, 1.360);
	    display: block;
	    margin: 20px auto;
	    text-decoration: none;
	    border-radius: 4px;
	    padding: 20px 30px;
	}
	
	.button {
	    color: black;
	    box-shadow: black 0 0px 0px 2px inset;
	}
	
	.button:hover {
	    color: white;
	    box-shadow: black 0 0px 0px 40px inset;
	}
	
	.button2 {
	    color: black;
	    box-shadow: black 0 0px 0px 2px inset;
	}
	
	.button2:hover {
	    color: white;
	    box-shadow: black 0 80px 0px 2px inset;
	}
	#searchImg{
		width: 70%;
	}
	select{
		width: 90px;
	  font-family: inherit;
	  background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%;  
	  -webkit-appearance: none;
	     -moz-appearance: none;
	          appearance: none;
	  border: 1px solid #999;
	  border-radius: 0px;
	}
	select option{
		background: black;
		color:white;
		margin: 0 auto;
	}
	
</style>
<body>
	<%@ include file ="/views/manager/hfl/managerHeader.jsp" %>
	<br>
	<h2 align="center"><label for="">창고현황</label></h2>
	<br />

	<div id="selectSearch" align="center">
			<table id="depotSearch">
				<tr>
					<td rowspan="6" width="30%"><img src="<%=request.getContextPath() %>/images/searchProduct.PNG" id="searchImg"/></td>
					<th id="th3">창고 위치</th>
					<td>
					<select name="root" id="">
						<option value="">--물품--</option>
						<option value="PC">PC</option>
						<option value="NOTE">노트북</option>
						<option value="PRO">생활가전</option>
					</select>
					물품명
					<select name="session" id="">
						<option value="">--세션--</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
					세션명
					<select name="floor" id="">
						<option value="">--층--</option>
						<option value="a">a</option>
						<option value="b">b</option>
						<option value="c">c</option>
					</select>
					층
					<select name="room" id="">
						<option value="">--호--</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					호
					</td>			    
				</tr>
				<tr>
					<th id="th3">적치 일자</th>					
					<td><input type="date" style="width: 100%;" id="insertDate"/></td>					
				</tr>
				<tr>
					<th id="th3">출고 일자</th>
					<td><input type="date" style="width: 100%;" id="releaseDate"/></td>
				</tr>
				<tr>
					<th id="th3">상품코드</th>
					<td><input type="text" style="width: 100%;" id="productCode"/></td>
				</tr>
				<tr>
					<td colspan="8">
					<div class="wrap">
				        <a class="button" id="search" ><label for="">Search</label></a>
				        <a class="button2" id="excelGo" ><label for="">ExcelDown</label></a>
				    </div>
					</td>
				</tr>
			</table>
	</div>
	<script>
	$("#search").click(function (){
		$(function(){
			var root = $("select[name='root']").val();
			var session = $("select[name='session']").val();
			var floor = $("select[name='floor']").val();
			var room = $("select[name='room']").val();
			var insertDate = $("input[id='insertDate']").val();
			var releaseDate = $("input[id='releaseDate']").val();
			var productCode =$("input[id='productCode']").val();
			
			
			$.ajax({
				url:"search.dp",
				data:{"root":root,"session":session,"floor":floor,"room":room,"insertDate":insertDate,"releaseDate":releaseDate,"productCode":productCode},
				type:"post",
				success:function(data){
					var $depotTbody = $("#depotMain tbody");
					var $pagingDiv1 =$("#pagingArea div");
					$depotTbody.html("");
					$pagingDiv1.html("");
					var i = 1;
					for(var i = 0; i < data["list"].length; i++){
						var $tr = $("<tr>");
						var $num = $("<td>").text(data["list"][i].productNumber);
						var $code = $("<td>");
						var $location=$("<td>").text(data["list"][i].locationCode);
						var $name = $("<td>").text(data["list"][i].productName);
						var $checkd = $("<td>").text(data["list"][i].checkDate);
						var $released = $("<td>");
						var $hidden = $("<input type='hidden' id='productCode1'>");
						var $detail = $("<a>").text(data["list"][i].productCode);
						
						$detail.attr("onclick","productDetailPopup("+data["list"][i].productCode+")")
						$hidden.attr('value',data["list"][i].productCode);
						if(data["list"][i].releaseDate==null){
							$released.text("미출고");
						}else{
							$released.text(data["list"][i].releaseDate);
						}
						$code.append($hidden);
						$tr.append($num);
						$code.append($detail);
						$tr.append($code);
						$tr.append($name);
						$tr.append($location);
						$tr.append($checkd);
						$tr.append($released);
						$depotTbody.append($tr);
					}
					var currentPage = data["pi"].currentPage;
					var endPage = data["pi"].endPage;
					var limit = data["pi"].limit;
					var listCount = data["pi"].listCount;
					var maxPage = data["pi"].maxPage;
					var startPage = data["pi"].startPage;

					var $pagingDiv2 =$("<div class='pagingArea' align='center'>");
					var $firstBtn = $("<button>").text('<<'); 
					var $preBtn = $("<button>").text('<');

					var $nextBtn =$("<button>").text('>');
					var $lastBtn =$("<button>").text('>>');


					$pagingDiv2.append($firstBtn);
					$pagingDiv2.append($preBtn);
					$firstBtn.attr('onclick',"newPage("+currentPage+")");
					if(currentPage <= 1){
						$preBtn.attr('disabled',true);
					}else{
						$preBtn.attr('onclick',"newPage("+(currentPage-1)+")");
					}
					for(var i = startPage ; i <= endPage ;i++){
						var $numBtn = $("<button>");
						if(currentPage == i){
							$numBtn.attr('disabled',true);
						}else{
							$numBtn.attr('onclick',"newPage("+i+")");
						}
						$numBtn.text(i);
						$pagingDiv2.append($numBtn);
					}
					if(currentPage >= maxPage){
						$nextBtn.attr('disabled',true);
					}else{
						$nextBtn.attr('onclick','newPage('+(currentPage+1)+')');
					}
					$lastBtn.attr('onclick','newPage('+maxPage+')');

					$pagingDiv2.append($nextBtn);
					$pagingDiv2.append($lastBtn);

					$pagingDiv1.append($pagingDiv2);
					}
			});
		});
	});
		
		$(function(){
			$("#excelGo").click(function(){
				var productCode = $("input[id='productCode1']");
				var code = "";
				productCode.each(function(){
					code += $(this).val()+",";
				});
				 location.href="<%=request.getContextPath()%>/excelGo.dp?value="+code; 
			});
		});

		function newPage(page){
			var location = $("select[id='depotLocation']").val();
			var insertDate = $("input[id='insertDate']").val();
			var releaseDate = $("input[id='releaseDate']").val();
			var productCode =$("input[id='productCode']").val();
			var currentPage = page;
			$.ajax({
				url:"search.dp",
				data:{"location":location,"insertDate":insertDate,"releaseDate":releaseDate,"productCode":productCode,"currentPage":currentPage},
				type:"post",
				success:function(data){
					var $depotTbody = $("#depotMain tbody");
					var $pagingDiv1 =$("#pagingArea div");
					$depotTbody.html("");
					$pagingDiv1.html("");
					var i = 1;
					for(var i = 0; i < data["list"].length; i++){
						var $tr = $("<tr>");
						var $num = $("<td>").text(data["list"][i].productNumber);
						var $code = $("<td>");
						var $location=$("<td>").text(data["list"][i].locationCode);
						var $name = $("<td>").text(data["list"][i].productName);
						var $checkd = $("<td>").text(data["list"][i].checkDate);
						var $released = $("<td>").text(data["list"][i].releaseDate);
						var $hidden = $("<input type='hidden' id='productCode1'>");
						var $detail = $("<a>").text(data["list"][i].productCode);						
						$detail.attr("onclick","productDetailPopup("+data["list"][i].productCode+")")
						$hidden.attr('value',data["list"][i].productCode);
						$code.append($hidden);
						$tr.append($num);
						$code.append($detail);
						$tr.append($code);
						$tr.append($name);
						$tr.append($location);
						$tr.append($checkd);
						$tr.append($released);
						$depotTbody.append($tr);
					}
					var currentPage = data["pi"].currentPage;
					var endPage = data["pi"].endPage;
					var limit = data["pi"].limit;
					var listCount = data["pi"].listCount;
					var maxPage = data["pi"].maxPage;
					var startPage = data["pi"].startPage;

					var $pagingDiv2 =$("<div class='pagingArea' align='center'>");
					var $firstBtn = $("<button>").text('<<');
					var $preBtn = $("<button>").text('<');

					var $nextBtn =$("<button>").text('>');
					var $lastBtn =$("<button>").text('>>');


					$pagingDiv2.append($firstBtn);
					$pagingDiv2.append($preBtn);
					$firstBtn.attr('onclick',"newPage("+currentPage+")");
					if(currentPage <= 1){
						$preBtn.attr('disabled',true);
					}else{
						$preBtn.attr('onclick',"newPage("+(currentPage-1)+")");
					}
					for(var i = startPage ; i <= endPage ;i++){
						var $numBtn = $("<button>");
						if(currentPage == i){
							$numBtn.attr('disabled',true);
						}else{
							$numBtn.attr('onclick',"newPage("+i+")");
						}
						$numBtn.text(i);
						$pagingDiv2.append($numBtn);
					}
					if(currentPage >= maxPage){
						$nextBtn.attr('disabled',true);
					}else{
						$nextBtn.attr('onclick','newPage('+(currentPage+1)+')');
					}
					$lastBtn.attr('onclick','newPage('+maxPage+')');

					$pagingDiv2.append($nextBtn);
					$pagingDiv2.append($lastBtn);

					$pagingDiv1.append($pagingDiv2);
					}
			});
		}
		function productDetailPopup(productCode){			
			window.open("productDetail.dp?code="+productCode,"상품상세","width=300px,height=400px");
			
		}
	</script>
	<br>
	<br><br>
		<table id="depotMain">
			<thead>
			<tr >
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
				<td><%=d.getProductNumber() %></td>
				<td><a onclick="productDetailPopup(<%=d.getProductCode() %>);"><%=d.getProductCode() %></a><input type="hidden" value="<%=d.getProductCode()%>" id='productCode1'/></td>
				<td><%=d.getProductName() %></td>
				<td><%=d.getLocationCode() %></td>
				<td><%=d.getCheckDate() %></td>
				<td><%if(d.getReleaseDate()==null){ %>
					미출고
				<%}else{ %>
					<%=d.getReleaseDate() %>	
				<%}%>
				</td>
			</tr>
			<%} %>
			</tbody>
		</table>

		<br>
	<div id ="pagingArea">
		<div class="pagingArea" align="center">
				<button onclick="location.href='<%=request.getContextPath()%>/listall.dp?currentPage=1'"><<</button>

				<% if(currentPage <= 1){ %>
				<button disabled><</button>
				<% }else { %>
				<button onclick="location.href='<%=request.getContextPath()%>/listall.dp?currentPage=<%=currentPage - 1%>'"> < </button>
				<% } %>

				<% for(int p = startPage; p <= endPage; p++){
					if(currentPage == p){
				%>
						<button disabled><%= p %></button>
				<% } else { %>
						<button onclick="location.href='<%=request.getContextPath()%>/listall.dp?currentPage=<%=p%>'"><%= p %></button>
				<%
					}
				   }
				%>

				<% if(currentPage >= maxPage){ %>
				<button disabled>></button>
				<% }else{ %>
				<button onclick="location.href='<%=request.getContextPath()%>/listall.dp?currentPage=<%=currentPage + 1 %>'">></button>
				<% } %>

				<button onclick="location.href='<%=request.getContextPath()%>/listall.dp?currentPage=<%=maxPage%>'">>></button>
			</div>
	</div>
	<br />
</body>
<footer><%@ include file ="/views/manager/hfl/footer.jsp" %> </footer>
</html>