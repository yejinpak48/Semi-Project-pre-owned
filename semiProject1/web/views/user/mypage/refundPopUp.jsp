<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    String ono = (String)request.getParameter("ono");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">

<style>
.r1 {
	width:100%;
	height:100%;
	margin-top:40px;
}
.r2 {
	margin-top:80px;
	margin-right:10%;
}
.btn_refund1 {
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
.btn_refund2 {
	text-align:center;
	margin-top:20px;
}
* {
   font-family: 'Poor Story', cursive;
   margin: 0;
   padding: 0;
}
</style>
</head>
<body>
		<div class="r1">
			<div><img src="<%=request.getContextPath()%>/images/refund.png" style="float:left; margin-right:20px; margin-left:30px;"><h3>반품/환불 신청</h3></div>
			<br>
			<p>
				반품/환불 신청은 배송완료 후 7일 이내에만 가능합니다.<br> 단순변심 반품은 불가능하며 상품 파손의 경우에만
				반품이 가능합니다.<br> 단, 택배비 관련 문의는 고객센터에 직접 연락해주시길 바랍니다.
			</p>
			<input type="text" id="hiddenvalue" value="<%=ono %>" style="display:none;">
		<div class="r2" align="right">
			안내 사항을 모두 확인하였으며, 이에 동의합니다.&nbsp;<input type="checkbox" name="return_check" class="return_check">
		</div>
		<div class="btn_refund2">
			<button id="check_r" disabled class="btn_refund1">확인</button>
			<button onclick="window.close();" class="btn_refund1">취소</button>
		</div>
		</div>
		<script>
			$(function(){
				$(".return_check").change(function(){
					if($(".return_check").is(":checked")){
						
					$("#check_r").prop("disabled", false);
				}else{
					$("#check_r").attr("disabled", "disabled");
				}
				})
			});
					$("#check").click(function(){
						var ono =  $("#hiddenvalue").val();
						window.opener.location.href='<%=request.getContextPath()%>/refundApply.mp?ono='+ono;
						window.close();
					});
		</script>
</body>
</html>