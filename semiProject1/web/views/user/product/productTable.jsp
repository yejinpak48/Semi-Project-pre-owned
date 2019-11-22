<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>

	#pt{
		font-family: 'Nanum Gothic', sans-serif;
		font-weight:bord;

	}

	#productImg{
		border:2px solid black;

	}

	#title{
		font-size:2vw;
		padding:1%;
	}

	#Detail{
		font-size:1vw;
		vertical-align: top;
		padding:1%;
	}

	#price{
		font-size:2vw;
	}

	#quantity{
		font-size:2vw;
	}

	#priceAll{
		font-size:2vw;
	}

</style>
</head>

<body>
<!-- table의 border는 일단 영역 보기 편하기 위해서 사용, 실제 구동시에는 상세설명이랑 가격, 수량등 채워넣으면 border 지우는게 더 깔끔할 것 같음 -->
<table id="pt" width="100%" max-height="200px">
		<tr >
			<!-- 장바구니에 등록한 상품 사진, 품명, 상세설명 -->
			<td id="productImg" rowspan="2">
				<img src="/sp/images/flower2.PNG" width=100% hright=100%>

			</td>
			<!--  -->
			<td id="title" colspan="2" width="70%" height="30%">
				상품명
			</td>
		</tr>
		<tr >
			<td id="Detail" colspan="2">
				상세설명상세설명상세설명상세설명상세설명상세설명상세설명
				상세설명상세설명상세설명상세설명상세설명상세설명상세설명
				상세설명상세설명상세설명상세설명상세설명상세설명상세설명
			</td>
		</tr>
		<tr>
			<td id="price" width="30%">
				금액 :<% %>
			</td>
			<td id="quantity" width="20%">
				수량 : <% %>
			</td>
			<td id="priceAll" width="50%">
				상품 총 금액 : <% %>
			</td>
		</tr>
	</table>
</body>
</html>