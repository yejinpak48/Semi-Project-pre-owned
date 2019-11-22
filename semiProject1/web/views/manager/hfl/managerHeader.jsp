<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.kh.bvengers.user.member.model.vo.Member" %>

<%
    Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script><meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<title>중고愛민족</title>
<style>
body {
	-webkit-font-smoothing: subpixel-antialiased;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-ms-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

* {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
}

#chatLi {
	cursor: pointer;
}

.dropdwn-toggle {
	cursor: pointer;
}

#logoDiv {
  margin-bottom: 10px;
  animation-duration: 1s;
  animation-name: slidein;
}

@keyframes slidein {
  from {
  	opacity: 0;
  }

  to {
  	opacity: 100;
  }
}
.navbar-brand:hover {
	-webkit-transform: scale(1.1); /*  크롬 */
	-moz-transform: scale(1.1); /* FireFox */
	-o-transform: scale(1.1); /* Opera */
	transform: scale(1.1);
	transition: transform .35s;
	-o-transition: transform .35s;
	-moz-transition: transform .35s;
	-webkit-transition: transform .35s;
}

#logoImg:hover, input[type=button]:hover {
	-webkit-transform: scale(1.1); /*  크롬 */
	-moz-transform: scale(1.1); /* FireFox */
	-o-transform: scale(1.1); /* Opera */
	transform: scale(1.1);
	transition: transform .35s;
	-o-transition: transform .35s;
	-moz-transition: transform .35s;
	-webkit-transition: transform .35s;
}

li:hover, button:hover {
	-webkit-transform: scale(1.2); /*  크롬 */
	-moz-transform: scale(1.2); /* FireFox */
	-o-transform: scale(1.2); /* Opera */
	transform: scale(1.2);
	transition: transform .35s;
	-o-transition: transform .35s;
	-moz-transition: transform .35s;
	-webkit-transition: transform .35s;
	background: white;
}

.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover,
	.navbar-inverse .navbar-nav>.open>a:focus {
	background: white;
}

.pagingArea button {
	width: 5%;
	color: black;
	background: white;
	border-radius: 50px;
}

.pagingArea button:hover {
	color: white;
	background: black;
	border-radius: 10px;
}
.pagingArea1 button{
      width:5%;
      color: black;
      background: white;
      border-radius: 50px;
	}
.pagingArea1 button:hover{
		color: white;
		background: black;
		border-radius: 10px;
	}


.navbar {
	border-right: 3px solid #ffe6e6;
	border-bottom: 3px solid #ffe6e6;
	border-top: none;
	border-left: none;
	background: white;
}
</style>
</head>
<body>
<body oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
<div align="center" id="logoDiv">
   <a href="<%=request.getContextPath()%>/smnl.mm"><img src="<%= request.getContextPath()%>/images/logo.jpg" id="logoImg" style="width:25%" /></a>
</div>
<%-- <% if(loginUser != null && loginUser.getMemberId().equals("admin")) {%> --%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()%>/smnl.mm" style="color:black" >중고 愛 민족</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
             <li class="dropdown"><a style="color:black" class="dropdwn-toggle" data-toggle="dropdown"> 상품관리<span class="caret"></span> </a>
               <ul class="dropdown-menu">
               <li><a href="<%=request.getContextPath()%>/selectProduct.mp">상품조회/삭제</a></li>
               <li><a href="<%=request.getContextPath()%>/selectCalculate.cal">정산 내역 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/productManagement">구매 내역 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/refundProduct.mp">환불 내역 관리</a></li>
               </ul>
            </li>
             <li class="dropdown"><a style="color:black" class="dropdwn-toggle" data-toggle="dropdown"> 회원관리<span class="caret"></span> </a>
                <ul class="dropdown-menu">
               <li><a href="<%=request.getContextPath()%>/memberList.me">고객정보</a></li>
               <li><a href="<%=request.getContextPath()%>/badmanlist.me">경고회원 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/reportList.me">신고정보</a></li>
            </ul>
             </li>
           <li class="dropdown"><a style="color:black" class="dropdwn-toggle" data-toggle="dropdown"> 통계 <span class="caret"></span></a>
              <ul class="dropdown-menu">
               <li><a href="<%=request.getContextPath()%>/memberStatistics">고객현황</a></li>
               <li><a href="<%=request.getContextPath()%>/salesStatistics.st">매출현황</a></li>
            </ul>
           </li>
           <li class="dropdown"><a style="color:black" class="dropdwn-toggle" data-toggle="dropdown"> 게시글관리 <span class="caret"></span></a>
               <ul class="dropdown-menu">
               <li><a href="<%=request.getContextPath()%>/smbs.mb">게시판 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/smqs.mq">고객센터 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/SelectFrequentQuestionList.fq">자주찾는 질문 관리</a></li>
               </ul>
           </li>
              <li class="dropdown"><a style="color:black" class="dropdwn-toggle" data-toggle="dropdown" > 창고관리 <span class="caret"></span></a>
               <ul class="dropdown-menu">
               <li><a href="<%=request.getContextPath() %>/listall.dp">창고 전체 현황</a></li>
               <li><a href="<%=request.getContextPath() %>/list.dp">검수요청관리</a></li>
               <li><a href="<%=request.getContextPath()%>/barcode.dp">바코드 출력</a></li>
               <li><a href="<%=request.getContextPath()%>/inDepot.dp">입고 관리</a></li>
               <li><a href="<%=request.getContextPath()%>/outProductList.dp">출고 관리</a></li>
            </ul>
              </li>
         </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a style="color:black" onclick="logout();"><span class="glyphicon glyphicon-user" style="color:black" ></span> 관리자</a></li>
        <li id="chatLi"><a style="color:black" onclick="location.href='<%=request.getContextPath()%>/chatList.ch'">채팅 리스트</a></li>
        <li><a id="chatCount" style="color:black"></a></li>
      </ul>
    </div>
<script>
function logout(){
   location.href="<%=request.getContextPath()%>/logout.me";
  }
$(function(){
    $.ajax({
       url:"chatCount.ch",
       type:"get",
       success:function(data){
          var count = "대기중인 상담 문의 : " + data + "건";
          $("#chatCount").append(count);
          console.log(data);
       }
    });
   });
$(document).ready(function() {
	$(document).bind('keydown', function(e) {
		if (e.keyCode == 123) {
			e.preventDefault();
			e.returnValue = false;
			alert("개발자 도구는 사용하실 수 없습니다.");
		}
	});
});
document.onmousedown = disableclick;
status = "마우스 오른쪽 버튼은 사용하실 수 없습니다.";

function disableclick(event) {
	if (event.button == 2) {
		alert(status);
		return false;
	}
}
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
  </div>
</nav>
</body>
</html>