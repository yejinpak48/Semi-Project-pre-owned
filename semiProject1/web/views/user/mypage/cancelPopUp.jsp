<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ono = (String) request.getParameter("ono");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<style>
* {
   font-family: 'Poor Story', cursive;
   margin: 0;
   padding: 0;
}
.r11 {
	width:100%;
	height:100%;
	margin-top:40px;
}
.r22 {
	margin-top:80px;
	margin-right:10%;
}
.btn_cancel1 {
	width:60px;
	height: 25px;
	margin-left:auto;
	margin-right:auto;
	margin-top:50px;
	margin-bottom:50px;
	font-size: 14px;
	border-radius: 5px;
	border:1px solid black;
	background:#FFF;
}
.btn_cancel2 {
	text-align:center;
	margin-top:20px;
}
</style>
</head>
<body>
<div class="r11">
	<div><img src="<%=request.getContextPath()%>/images/payment.png" style="float:left; margin-right:20px; margin-left:30px;"><h3>주문 취소 신청</h3></div>
	<br>
	<p>
		주문 취소 신청은 배송 전에만 가능합니다.<br>
		취소 신청 시 카드 취소 처리까지는 카드 회사에 따라 1~3영업일이 추가로 소요 됩니다.
	</p>
	<input type="text" id="hiddenvalue" value="<%=ono %>" style="display:none;">
<div class="r22" align="right">
	안내 사항을 모두 확인하였으며, 이에 동의합니다.&nbsp;<input type="checkbox" name="cancel_check" class="cancel_check">
</div>
<div class="btn_cancel2">
	<button id="check_c" disabled class="btn_cancel1">확인</button>
	<button onclick="window.close();" class="btn_cancel1">취소</button>
</div>
</div>
<script>
	$(function(){
		$(".cancel_check").change(function(){
			if($(".cancel_check").is(":checked")){

			$("#check_c").prop("disabled", false);
		}else{
			$("#check_c").attr("disabled", "disabled");
		}
		})
	});
			$("#check").click(function(){
				var ono =  $("#hiddenvalue").val();
				window.opener.location.href='<%=request.getContextPath()%>/cancelApply.mp?ono='+ono;
				window.close();
			});


</script>
</body>
</html>