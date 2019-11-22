<%@page import="com.kh.bvengers.product.model.vo.Product"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Product p = (Product) request.getAttribute("p");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	text-align:
}

.carousel-inner>.item>img {
	top: 0;
	left: 0;
	min-width: 100%;
	min-height: 400px;
}
</style>

</head>
<!-- header 영역 -->
<header><%@ include file="views/user/hfl/header.jsp"%></header>
<body>
	<div class="container">
		<br>
		<br>
		<h3 align="center">광고배너</h3>
			<button type="button" onclick="submit();">테스트용</button>
			<script>
				function submit(){
					var no = <%=loginUser.getMemberNo()%>;
					location.href="<%=request.getContextPath()%>/chat.ch?no="+no;
				}
			</script>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>


			<div class="carousel-inner">
				<div class="item active">
					<img src="images/galxy.PNG" alt="ad1">
				</div>

				<div class="item">
					<img src="images/flower1.PNG" alt="ad2">
				</div>

				<div class="item">
					<img src="images/flower2.PNG" alt="ad3">
				</div>
			</div>


			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
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
	<!-- footer 영역 -->
	<footer><%@ include file="views/user/hfl/footer.jsp"%></footer>
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
						var $num = $("<td>").text(data[key].postsId);
						var $imgTd = $("<td>")
						var $imageLabel = $("<label>");
						var $image = $("<img>").attr("src",src+file).css({"width":"250px", "height":"300px"}).css({"margin":"0 auto"}).addClass("powerProduct"+key);
						var $titleTd = $("<td>").text(data[key].title);
						var $priceTd = $("<td>").text(data[key].price+"원");


						$imageLabel.append($image);
						$imgTd.append($imageLabel);

						$tableImg.append($imgTd);
						$tableImg.append($num);

						$tableTitle.append($titleTd);
						$tableTitle.css({"text-align":"center"})

						$tablePrice.append($priceTd);
						$priceTd.css({"text-align":"center", "font-size":"20px"});

						number.push(data[key].postsId);

						$($num).css("display","none");
						$(".powerProduct"+key).css({"cursor":"pointer"});
						<%-- var link = "<%=request.getContextPath()%>/selectOne.pd?num="+number[i]; --%>

						<%-- $(".powerProduct0").on("click", function(){
							location.href='<%=request.getContextPath()%>/selectOne.pd?num='+number[0];
						});
						$(".powerProduct1").on("click", function(){
							location.href='<%=request.getContextPath()%>/selectOne.pd?num='+number[1];
						});
						$(".powerProduct2").on("click", function(){
							location.href='<%=request.getContextPath()%>/selectOne.pd?num='+number[2];
						});
						$(".powerProduct3").on("click", function(){
							location.href='<%=request.getContextPath()%>/selectOne.pd?num='+number[3];
						});
						$(".powerProduct4").on("click", function(){
							location.href='<%=request.getContextPath()%>/selectOne.pd?num='+number[4];
						}); --%>

						$("#powerLink td").click(function(){
							var num = $(this).next().html();
							location.href="<%=request.getContextPath()%>/selectOne.pd?num=" + num;
						})
					}
				},
				error:function(){
				}
			});
		});

	</script>
</body>
</html>