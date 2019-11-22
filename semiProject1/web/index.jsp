<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.bvengers.product.model.vo.Product"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Product p = (Product) request.getAttribute("p");
	DecimalFormat dc = new DecimalFormat("###,###,###,###");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
.box {
	text-align: right;
	padding-right: 20px;
}
.container-fluid {
	text-align: center;
}

.menu {
	padding: 10px;
}
.container {
	min-height: 1000px;
}
#myCarousel {
	height: 350px;
}
.item {
	width: 100%;
}
#powerLink td{
	padding: 10px;
}
.powerLinkForm {
	margin-top: 20px;
}
</style>

</head>
<!-- header 영역 -->
<body>
<header><%@ include file="views/user/hfl/header.jsp"%></header>
	<div class="container">
		<br>
		<br>
		<form action="<%=request.getContextPath()%>/select.pd" method=post
			encType=multipart/form-data>
		</form>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>


			<div class="carousel-inner">
				<div class="item active">
					<img src="images/mainLogo.PNG" alt="ad1">
				</div>
				<div class="item">
					<img src="images/coc.png" alt="ad2">
				</div>
				<div class="item">
					<img src="images/phinix.png" alt="ad3">
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev"></a>
			<a class="right carousel-control" href="#myCarousel"data-slide="next"></a>
		</div>
	<br>
	<h3 align="center">최신상품</h3>
	<form action="" id="powerLinkForm">
		<table align="center" id="powerLink">
			<tbody>
				<tr id="imgTable"></tr>
				<tr id="titleTable"></tr>
				<tr id="priceTable"></tr>
			</tbody>
		</table>
	</form>
	</div>
	<!-- footer 영역 -->
	<script>
		$(function(){
			$.ajax({
				url:"power.pd",
				type:"get",
				success:function(data){
					var $tableImg = $("#imgTable");
					var $tableTitle = $("#titleTable");
					var $tablePrice = $("#priceTable");
					$tableImg.html("");
					$tableTitle.html("");
					$tablePrice.html("");
					var number = new Array();
					for(var key in data){
						var file = data[key].fileName;
						var src = "<%= request.getContextPath()%>/thumbnail_uploadFiles/";
						var $num = $("<td>&nbsp;").text(data[key].postsId);
						var $imgTd = $("<td>")
						var $imageLabel = $("<label>");
						var $image = $("<img>").attr("src",src+file).css({"width":"200px", "height":"250px"}).css({"margin":"0 auto"}).addClass("powerProduct"+key);
						var $titleTd = $("<td>").text(data[key].title);
						var $priceTd = $("<td>").text(numeral(data[key].price).format('0,0')+"원");


						$imageLabel.append($image);
						$imgTd.append($imageLabel);

						$tableImg.append($imgTd);
						$tableImg.append($num);

						$tableTitle.append($titleTd);
						$tableTitle.css({"text-align":"center"})

						$tablePrice.append($priceTd);
						$priceTd.css({"text-align":"center", "font-size":"20px"});

						number.push(data[key].postsId);

						$titleTd.css({"font-size":"1.3em", "font-weight":"bold"});

						$($num).css("display","none");
						$(".powerProduct"+key).css({"cursor":"pointer"});

						$("#powerLink td").click(function(){
							var num = $(this).next().html();
							location.href="<%=request.getContextPath()%>/selectOne.pd?num=" + num;
						});
					}
				},
				error:function(){
				}
			});
		});
	</script>

	<footer><%@ include file="views/user/hfl/footer.jsp"%></footer>
</body>
</html>