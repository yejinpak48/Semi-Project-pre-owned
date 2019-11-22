<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.user.basket.model.vo.*"%>
    <%
    	ArrayList <Basket> list = (ArrayList <Basket>) request.getAttribute("list");
    	int totalPrice =0;
    	int totalDP =0;
    	DecimalFormat dc = new DecimalFormat("###,###,###,###");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
  <style>
  #total{
  	text-align: center;
  	margin: 15px;
  }
  #totalInfo{
  	text-align: center;
  }
  #fileImage{
  	width: 200px;
  	height: 200px;
  	border:2px solid black;
  }
  #select{
  	margin-top: 155%;
  	margin-left:0 auto;
  	margin-right: 0 auto;
  	width: 30px;
  	height: 30px;
  }
  #th3{
  	text-align: center;
  }
  #td1{
  	text-align: center;
  	margin-top: 120%;
  }
  #productName{
  	margin-top:25%;
  }
  #info ,#info2{
  	margin-top:90%;
  }
  #info1{
  	margin-top:118%;
  }
  #directBtn{
  	margin-top:67%;
  }
  #basketList{
  	width: 60%;
  	margin: 0 auto;
  	margin-top:5%;
  	min-height: 500px;
  }
  #basketTitle{
  	text-align: center;
  	margin-bottom: 6%;
  }
  #listremove{
  	border-radius:15px;
  	border:1px solid purple;
  	background: white;
  	color: purple;
  	width: 15%; 
  }
  #listremove:hover{
  	background: purple;
  	color:white;
  }
  #choiceOrder{
  	border-radius:15px;
  	border:1px solid purple;
  	background: white;
  	color: purple;
  	width: 15%; 
  }
  #choiceOrder:hover{
  	background: purple;
  	color:white;
  }
  #orderProduct{
  	border-radius:15px;
  	border:1px solid purple;
  	background: white;
  	color: purple;
  	width: 15%; 
  }
  #orderProduct:hover{
  	background: purple;
  	color:white;
  }
  </style>
</head>
<body>
<%@ include file="../hfl/header.jsp"%>
<%@ include file="../hfl/list.jsp"%>
<div id="basketList">
	<h2 id="basketTitle">장바구니</h2>
	<button onclick="removeList();" id="listremove">리스트제거</button>
  <table class="table">
    <thead>
      <tr>
        <th id="th3">선택</th>
        <th id="th3"></th>
        <th id="th3">상품명</th>
        <th id="th3">상품금액</th>
        <th id="th3">배송비</th>
        <th id="th3">주문금액</th>
      </tr>
    </thead>
    <tbody>
    <%for(Basket bk : list){ %>
      <tr>
        <td id="td1"><input type="checkbox" id="select" value="<%=bk.getProductCode() %>" onclick="changeTotal();" checked/></td>
        <td id="td1"><img src="<%=request.getContextPath() %>/thumbnail_uploadFiles/<%=bk.getFileName() %>" alt=""  id="fileImage"/></td>
        <td id="td1"><h4 id="productName"><%=bk.getProductName() %></h4></td>
        <td id="td1"><p id="info2"><%=dc.format(bk.getPrice()) %>원</p></td>
        <td id="td1"><p id="info1"><%=dc.format(bk.getDeliveryPrice()) %>원</p></td>
        <td id="td1"><p id="info"><%=dc.format(bk.getPrice()+bk.getDeliveryPrice()) %>원</p></td>
        <td hidden><%=dc.format(totalPrice+=bk.getPrice()) %></td>
        <td hidden><%=dc.format(totalDP+=bk.getDeliveryPrice()) %></td>
      </tr>
      <%} %>
    </tbody>
  </table>
  <table id="totalInfo" align="center">
  <thead>
	  	<tr>
	  		<th id="th3">총 주문금액&nbsp;&nbsp;</th>
	  		<th></th>
	  		<th id="th3">총 배송비&nbsp;&nbsp;</th>
	  		<th></th>
	  		<th id="th3">총 결제 예정 금액&nbsp;&nbsp;</th>
	  	</tr>
  	</thead>
  	<tbody>
	  	<tr>
	  		<td><%=dc.format(totalPrice)%>원 &nbsp;&nbsp;</td>
	  		<td>+ &nbsp;&nbsp;</td>
	  		<td><%=dc.format(totalDP)%>원 &nbsp;&nbsp;</td>
	  		<td>= &nbsp;&nbsp;</td>
	  		<td><%=dc.format(totalPrice+totalDP) %>원</td>
	  	</tr>
  	</tbody>
  </table>
  <div id="total">
  	<button onclick="choiceProduct();" id="choiceOrder">선택 상품 주문</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<button onclick="selectAll();" id="orderProduct">전체 상품 주문</button>
  </div>
</div>
<script>
	function removeList(){
		$(function(){
			var code = "";
			
			$("#select:checked").each(function(index){
				code+=$(this).val()+","
			});
			
			$.ajax({
				url:"deleteList.bk",
				type:"post",
				data:{"code":code},
				success:function(data){
						window.location.reload();	
				}
			});
		});
	}
	function selectAll(){
		$(function(){
			   var list = new Array();
			   $("input[id=select]").each(function(index, item){
			      list.push($(item).val());
			   });
			
			var code = list.join(',');
			console.log(code);
			
			location.href="<%= request.getContextPath()%>/payment.pa?code="+code;
			
			
		});
	}
	
	function choiceProduct(){
		$(function(){
			   var list = new Array();
			   $("#select:checked").each(function(index, item){
			      list.push($(item).val());
			   });
			
			var code = list.join(',');
			console.log(code);
			
			location.href="<%= request.getContextPath()%>/payment.pa?code="+code;
			
			
		});
	}
	function changeTotal(){
		var code = "";
		
		$("#select:checked").each(function(index){
			code+=$(this).val()+","
		});
		$.ajax({
			url:"changeTotal.bk",
			type:"post",
			data:{"code":code},
			success:function (data){
				
				console.log(data);
				var totalPrice=0;
				var totaldelivery=0;
				
				for(var i = 0; i < data.length; i++){
					totalPrice+=data[i].price;
					totaldelivery+=data[i].deliveryPrice;
				}
				 var $total = $("#totalInfo tbody");
					$total.html("");
					var $tr = $("<tr>");
					var $totalP = $("<td>").text(totalPrice);
					var $plus = $("<td>").text("+");
					var $deliveryP = $("<td>").text(totaldelivery);
					var $equal = $("<td>").text("=");
					var $price= $("<td>").text(totalPrice+totaldelivery);
					
					$tr.append($totalP);
					$tr.append($plus);
					$tr.append($deliveryP);
					$tr.append($equal);
					$tr.append($price);
					$total.append($tr);				
			}
		});
	} 
	
</script>
<footer><%@ include file="../hfl/footer.jsp"%></footer>
</body>
</html>