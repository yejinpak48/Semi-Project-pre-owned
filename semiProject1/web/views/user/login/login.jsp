<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
String msg = (String)request.getParameter("msg");
String pass = (String)request.getAttribute("pass");
String sessionId = (String) request.getSession().getAttribute("sessionId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
.btn_login {
   height: 40px;
   width: 380px;
}
input{
border-radius:5px;
}
label {
   color: red;
   font-weight: bold;
}


 body {
        margin: 0;
   }
    #myCanvas {
        display: block;
    }

#button {
        font-family: "Gill Sans", "Gill Sans MT", Calibri, sans-serif;
        font-size: 1.5em;
        padding: 7px 20px;
        left: 50%;
        width: 200px;
        top: 50%;
        border-radius: 10px;
        color: white;
        text-shadow: -1px -1px 1px rgba(0,0,0,0.8);
        border: 5px solid transparent;
        border-bottom-color: rgba(0,0,0,0.35);
        background: hsla(260, 100%, 50%, 1);
        cursor: pointer;
       outline: 0 !important;
       margin: 0  auto;

        animation: pulse 1s infinite alternate;
        transition: background 0.4s, border 0.2s, margin 0.2s;
    }
    #button:hover {
        background: hsla(220, 100%, 60%, 1);
        margin-top: -1px;

        animation: none;
    }
    #button:active {
        border-bottom-width: 0;
        margin-top: 5px;
    }
    @keyframes pulse {
        0% {
            margin-top: 0px;
        }
        100% {
            margin-top: 6px;
        }
    }


</style>
</head>
<body>
   <header><%@ include file="../hfl/header.jsp"%></header>
   <div class="header" align="center">
   <br>
      <h1>
         <label>PRE-OWNED♥NATION LOGIN</label>
      </h1>
      <br>
   </div>
   <form action="<%=request.getContextPath()%>/login.me" method="post"
      id="loginForm">
      <div align="center">
         <div class="box_login">
            <div class="inp_text">
            <input type="text" id="sessionId" name="sessionId" value="<%=sessionId%>" style="display:none;">
               <input type="text" id="loginId" name="loginId" placeholder="Id"
                  style="height: 40px; width: 380px; box-sizing: border-box; padding: 0 30px;"><br>
               <br>
            </div>
            <div class="inp_text">
               <input type="password" id="loginPw" name="password"
                  placeholder="Password"
                  style="height: 40px; width: 380px; box-sizing: border-box; padding: 0 30px;"><br>
               <br>
            </div>
               <button id="button" type="button" class="btn_login" onclick="login();">LOGIN</button>



         </div>
         <br>

      </div>
   </form>
   <div class="login_append" align="center">
      <span class="txt_find"> <a
         href="/sp/views/user/join/searchIdPage.jsp" class="link_find" style="color: black">아이디</a>
         / <a href="/sp/views/user/join/searchPwdPage.jsp" class="link_find" style="color: black">비밀번호
            찾기</a> / <a href="/sp/views/user/join/join.jsp" class="link_join" style="color: black">회원가입</a>
      </span>

      <h4 class="tit">소셜 간편 가입</h4>
      <a id="kakao-login-btn"></a> <a
         href="http://developers.kakao.com/logout"></a>
            <footer><%@ include file="../hfl/footer.jsp"%></footer>





      <script type='text/javascript'>

         Kakao.init('f96020a12441dbfb7b9174b8e19d971c');

         Kakao.Auth.createLoginButton({
            container : '#kakao-login-btn',
            success : function(authObj) {
               Kakao.API.request({
               url:'/v1/user/me',
            success:function(res){
               var nickname = res.properties['nickname'];
               var id = res.id;
               var token = authObj.access_token;
               var array = id+","+nickname+"/"+token;
               location.href="<%=request.getContextPath()%>/kakaojoin.me?array="+array;
            }
               });
            },
            fail : function(err) {
               alert(JSON.stringify(err));
            }
         });

         //]]>
      </script>
   </div>

   <script>
 function login() {
    $pwd = $("#loginPw").val();

    if($("#loginId").val() == ""){
       alert("아이디를 입력해주세요");
       $("#loginId").focus();
       //history.go(-1);
       return false;
    }
    else if($("#loginPw").val() == ""){
       alert("비밀번호를 입력해주세요");
       $("#loginPw").focus();
       return false;
    }

    if ($("#loginId").val() !="" && $("#loginPwd").val() != ""){
       $("#loginForm").submit();
    }

 };
 $(document).ready(function() {
	$(document).bind('keydown', function(e) {
		if (e.keyCode == 13) {
   			login();
		}
	});
});
$(document).ready(function(){
	$(document).bind("contextmenu", function(e) {
		return false;
	});
});
$(document)[0].oncontextmenu = function() { return false; }
$(document).mousedown(function(e) {
	if( e.button == 2 ) {
			alert('내용을 복사할 수 없습니다.');
			return false;
	} else {
			return true;
	}
});
</script>
</body>
</html>