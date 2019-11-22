<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function checkjoin(index){
	if(index == 1){
	var getCheck = RegExp(/^[a-zA-Z0-9]{4,12}$/);
	var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	var buf = new Array(13);

	if($("#memberId").val() == ""){
		alert("아이디를 입력하세요");
		$("#memberId").focus();
		$("#memberId").val("");
		return false;
	}

	if(!getCheck.test($("#password").val())){
		alert("비밀번호를 다시 설정하세요");
		$("#password").val("");
		$("#password").focus();
		return false;
	}

	if($("#password").val() != ($("#password2").val())){
		alert("비밀번호를 확인하세요");
		$("#password").val("");
		$("#password2").val("");
		$("#password").focus();
		return false;
	}

 	if(($("#peoplejb").val()=="")||($("#peoplejb2").val()=="")){
		alert("주민등록번호를 입력하세요");
		$("#peoplejb").focus();
		return false;
	}

 	if()

if($("#memberPassword").val() == ($("#memberPassword2").val())){
	$("form").submit();


}<
	}else if(index==2){
		var email = $("#email").val();
		window.open('<%=request.getContextPath()%>/send.me?email='+email,'인증번호','width=430,height=450,status=no,scrollbars=yes');

	}
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
.box_join {
   width:55%;
   border:2px solid black;
   margin:0 auto;
   border-radius: 5px;
}
.input_join {
   width:40%;
   margin:0 auto;
   border-radius: 5px;
   height:32px;
}
.input_join1 {
   margin:0 auto;
   border-radius: 5px;
   height:32px;
}
.input_join2 {
   margin:0 auto;
   border-radius: 5px;
   height:32px;
}
.btn_join{
   height:40px;
   width:40%;
   margin:0 auto;
   border: 1px solid black;
   font-size: 15px;
   font-family:'Nanum Gothic', sans-serif;
   font-weight:550;
   border-radius: 5px;
   background-color: black;
   color: white;
   }
.btn_overlap,.btn_overlap1 {
   margin:0 auto;
   border: 1px solid black;
   font-size: 14px;
   font-family:'Nanum Gothic', sans-serif;
   font-weight:550;
   border-radius: 5px;
   background-color: black;
   color: white;
   }
.btn_join:hover {color:white;}
</style>
</head>
<header><%@ include file="../hfl/header.jsp" %></header>
<body>
		<form action="<%=request.getContextPath() %>/insertMember.me" method="post">
		<div class = "header" align="center">
		<br><br>

		<br>
	</div>

	<div class="box_join" align="center">
		<h2>중고愛민족 회원가입</h2>
	<div class="box_login" align="center">
			<br><br>
			<input type="text" id="memberId" name="memberId" placeholder="4~12자의 영문+숫자" maxlength="12" style="width:30%;"><br><br>
			<div class="btn_overlap" style="width:10%;" >중복확인</div><br><br>
			<input type="password" id="password" name="password" placeholder="  4~12자의 영문+숫자" style="width:40%;"><br><br>
			<input type="password" id="password2" name="password2" placeholder="  Password 확인" style="width:40%;"><br><br>
			<input type="text" id="memberName" name="memberName" placeholder="  이름" style="width:40%;"><br><br>
			<input type="text" id="peoplejb" name="peoplejb" placeholder=" 주민등록번호" maxlength="6" style="width:20%;">-
			<input type="password" id="peoplejb2" name="peoplejb2" placeholder=" 주민등록번호" maxlength="7"style="width:20%;"><br><br>
			<input type="email" id="email" name="email" placeholder="  이메일" style="width:30%;">
			<button type="button" class="btn_overlap1" style="width:10%;" onclick="checkjoin(2);">이메일인증</button><br><br>
			<input type="tel" id="phone" name="phone" placeholder="  phone" style="width:40%;"><br><br>
			<input type="text" id="sample6_postcode" name = "address1" placeholder="우편번호" style="width:30%;">
			<input type="button" name="zipCode"class="btn_overlap" onclick="sample6_execDaumPostcode()" style="width:10%;" value="우편번호 찾기"><br><br>
			<input type="text"  id="sample6_address" name="address2" placeholder="  주소" style="width:40%;"><br><br>
			<input type="text"  id="sample6_detailAddress" name="address3" placeholder="  상세주소" style="width:40%;"><br><br>
			<input type="text"  id ="sample6_extraAddress" name="address4" placeholder="  참고항목" style="width:40%;"><br><br>
		<br><br>
		<input type="button" value="가입" class="btn_join"  onclick="checkjoin(1);"><br><br>
		<input type="reset" value="취소" class="btn_join" onclick="goMain();">
		<br><br>
	</div>
	</div>
		</form>
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
                       document.getElementById("sample6_extraAddress").value = extraAddr;
                   } else {
                       document.getElementById("sample6_extraAddress").value = '';
                   }

                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('sample6_postcode').value = data.zonecode;
                   document.getElementById("sample6_address").value = addr;
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("sample6_detailAddress").focus();
               }
           }).open();
      }
      function goMain(){
         location.href="<%=request.getContextPath()%>/index.jsp";
      }
       $(function(){
          $(".btn_overlap").click(function(){
             var memberId = $("#memberId").val();

             $.ajax({
                url:"/sp/memberIdCheck.me",
                type:"post",
                data:{memberId:memberId},
                success:function(data){

                   if(data === "fail"){
                      alert("아이디가 중복됩니다.");
                   }else{
                      alert("사용 가능합니다.");
                   }
                },
                error:function(){
                }
             });
          });
       });
      </script>

      <br><br>
</body>
      <footer><%@ include file="../hfl/footer.jsp" %></footer>
</html>