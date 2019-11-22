<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import = "java.util.*, com.kh.bvengers.board.model.*,java.util.*,com.kh.bvengers.board.model.vo.*,com.kh.bvengers.user.myPage.model.vo.*"%>
<%
   ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("List");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
   #sec1 {
         width:100%;
         height:100%;
         padding-top:2%;
         padding-right:20%;
         padding-bottom:2%;
         padding-left:20%;
         margin:auto;
   }

   .board{
      width:80%;
      margin:auto;
      align:center;
        border-spacing: 10px;
   }

   .row0{
      background:#eee;
      margin:auto;
      border-bottom: 2px solid #555;
   }
   .row1,.row2, .row3, .row4, .row, .row6, .row7, .row8, .row9, .row10{
      border-top: 1px solid #ccc;
      border-bottom: 1px solid #ccc;
   }
   .pageNo{
      margin:auto;
   }

   #writer{
      margin-top:2px;
      float:right;
      background:white;
      border:1px solid skyblue;
   }

   dt, dd {
      padding: 10px;
   }

   dt {
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      border:1px solid black;
      margin-bottom: 5px;
   }
   dt:hover{
      background:#ededed;
   }
   dt span {
      display: inline-block;
      width: 5px;
      height: 5px;
      background-color: black;
      vertical-align: middle;
      margin-right: 10px;
    }

   dd {
      background:    -webkit-linear-gradient(#fff, #eee);
      background:    -moz-linear-gradient(#fff, #eee);
      background:    -o-linear-gradient(#fff, #eee);
      background:    -webkit-gradient(linear, 0 0, 0 100%, from(#fff), to(#eee));
      background:    linear-gradient(#fff, #eee);

      margin-bottom: 5px;
      display: none;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
    }
    #accordionArea{
       padding-right:2%;
       padding-left:2%;
       width:80%;
       margin:0 auto;
       border:1px solid #ddd;

    }

	#findRoad:hover{
		cursor: pointer;
	 }
	 #accordionArea{
	 	padding-right:2%;
	 	padding-left:2%;
	 	width:80%;
	 	margin:0 auto;
	 	border:1px solid #ddd;

	 }
	 #map {
	 	margin: 0 auto;
	 }
	 #mapOuter{
	 	text-align: center;
	 }
	 #mapOuter label{
	 	font-size: 1.5em;
		margin-bottom: 50px;
	 	margin-top: 50px;
	 	font-weight: bold;
	 }
	 #findRoad{
	 margin-top: 10px;
	 	background: black;
	 	color: white;
	 	width: 100px;
	 	height: 50px;
	 }
</style>
</head>
<body>
	<!-- header 영역 -->
	<header><%@ include file="../hfl/header.jsp" %></header>

	<section id="sec1">
<div id="accordionArea" style = " border: 5px solid red; border-color: white ;">
   <h2 align="left" > 자주 찾는 질문</h2>
   <%for (Board b : list) {%>
   <dl >
      <dt style = " border: 2px solid; border-color: #ccccff;" ><span></span><%=b.getPostsTitle()%></dt>
      <dt style = " border: 2px solid; border-color: #ccccff;"><span></span><%=b.getContents() %></dt>
   </dl>
   <% }%>
   </div>
   <script>
   $(document).ready(function(){
      $('dt').removeClass('on').next().slideUp();
   })
   $('dt').on('click', function () {

       if ($(this).hasClass('on')) {
           slideUp();
       } else {
           slideUp();
           $(this).addClass('on').next().slideDown();
       }
       function slideUp() {
           $('dt').removeClass('on').next().slideUp();
       };

   })
   </script>

   </section>
   <br />
   <div id="mapOuter">
   <label>찾아오시는 길</label>
   <%@ include file="../serviceCenter/aboutCompany.jsp" %><br>
       <div class="svg-wrapper" align ="center">
      <svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
        <rect id="shape" height="40" width="150" align = "center"/>
        <div id="text" align ="center">
          <a style="color:black; background:white;align:center" id="findRoad">
          <span class="spot" align ="center" >길찾기</span></a>
        </div>
      </svg>
    </div>
     </div>
   <!-- footer 영역 -->
   <footer><%@ include file="../hfl/footer.jsp" %></footer>
   <script>
   		$("#findRoad").click(function(){
   	   		window.open("https://map.kakao.com/link/to/kh정보교육원,37.499117,127.032882");
   	   	});
   </script>
</body>
</html>