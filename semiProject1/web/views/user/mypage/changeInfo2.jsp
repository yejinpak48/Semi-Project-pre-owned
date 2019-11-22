<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.bvengers.user.member.model.vo.Member"%>
    <%
    Member m = (Member) request.getAttribute("m");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>h4 {
	text-align:center;
}
input {
	margin:0 auto;
	border-radius: 5px;
	height:32px;
}

.change_div {
	width:40%;
	margin-right:auto;
	margin-left:auto;
	padding-top:20px;
	padding-bottom:20px;
	border:5px double #ffe6e6;
}
.btn_update {
	margin-top:15%;
	margin-left:auto;
	matgin-right:auto;
	height:30px;
	width:25%;
	border: 1px solid black;
	font-size: 15px;
	font-weight:550;
	border-radius: 5px;
	background-color: #FFF;
}

.btn_overlap {
	width:86px;
	height:30px;
	border: 1px solid black;
	border-radius: 5px;
	background-color: #FFF;
}
</style>
</head>
<body>
<header><%@ include file="../hfl/header.jsp" %></header>

<%
	String [] add =(String []) m.getAddress().split("\\$");
	%>
	<header><%@ include file="../hfl/myPageList.jsp"%></header>
<br><br><br><br>
<div class="change_div">
	<h4><%=m.getMemberName() %>님의 회원 정보 수정</h4>
	<br><br>
	<form method="post">
		<table class="change_info" style="margin-left:auto; margin-right:auto;">
			<tr>
				<td><label>아이디</label></td>
				<td><input type="text" name="memberId" size=35% value="<%=m.getMemberId()%>" readonly><br></td>
			</tr>
			<tr>
				<td><label>비밀번호&nbsp;</label></td>
				<td><input type="password" name="memberpassword" size=35%></td>
			</tr>
			<tr>
				<td><label>이름</label></td>
				<td><input type="text" name="memberName" size=35% value="<%=m.getMemberName()%>" readonly></td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" name="email" size=35% value="<%=m.getEmail()%>"></td>
			</tr>
			<tr>
				<td><label>연락처</label></td>
				<td><input type="tel" name="phone" size=35% value="<%=m.getPhone()%>"></td>
			</tr>
			<tr>
				<td><label>우편번호</label></td>
				<td><input type="text" id="sample6_postcode" size=20 name="address1" value="<%=add[0]%>">
				<input type="button" name="zipCode"class="btn_overlap" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
			</tr>
			<tr>
				<td><label>주소</label></td>
				<td><input type="text" size=35% id="sample6_address" name="address2" value="<%=add[1]%>"></td>
				<td></td>
			</tr>
			<tr>
			<td><label>상세주소</label></td>
			<td><input type="text" size=35% id="sample6_detailAddress" name="address3" value="<%=add[2]%>"></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="수정" class="btn_update">
					<input type="button" value="취소" class="btn_update" onclick="goMyPage();">
				</td>
			</tr>
		</table>
	</form></div>

	<br><br><br>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
		<script>
		function sample6_execDaumPostcode(){
			new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("sample6_detailAddress").value = extraAddr;

	                } else {
	                    document.getElementById("sample6_detailAddress").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample6_postcode').value = data.zonecode;
	                document.getElementById("sample6_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
		}
		function goMyPage(){
			location.href="<%=request.getContextPath()%>/views/user/mypage/myPage.jsp";
		}
		</script>
	<footer><%@ include file="../hfl/footer.jsp" %></footer>
</body>
</html>