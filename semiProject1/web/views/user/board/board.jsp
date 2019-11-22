<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.kh.bvengers.board.model.vo.*,com.kh.bvengers.user.myPage.model.vo.*" %>
<%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
   BoardPageInfo pi = (BoardPageInfo)request.getAttribute("pi");
   int listCount = pi.getListCount();
   int currentPage = pi.getCurrentPage();
   int maxPage = pi.getMaxPage();
   int startPage = pi.getStartPage();
   int endPage = pi.getEndPage();

   ArrayList<Board> list1 = (ArrayList<Board>)request.getAttribute("list1");
   BoardPageInfo pi1 = (BoardPageInfo)request.getAttribute("pi1");

   int listCount1 = pi1.getListCount();
   int currentPage1 = pi1.getCurrentPage();
   int maxPage1 = pi1.getMaxPage();
   int startPage1 = pi1.getStartPage();
   int endPage1 = pi1.getEndPage();



%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/d3js/5.9.0/d3.min.js"></script>
<style>
#board{
   text-align:center;
}
#wirte{
   position: absolute;
   right: 30px;
}

.spot {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;

}


.svg-wrapper {
  margin-top: 0;
  position: relative;
  width: 150px;
  /*make sure to use same height/width as in the html*/
  height: 40px;
  display: inline-block;
  border-radius: 3px;
  margin-left: 5px;
  margin-right: 5px
}


#shape {
  stroke-width: 6px;
  fill: transparent;
  stroke: #009FFD;
  stroke-dasharray: 85 400;
  stroke-dashoffset: -220;
  transition: 1s all ease;
}


#text {
  margin-top: -35px;
  text-align: center;
}

#text a {
  color: white;
  text-decoration: none;
  font-weight: 100;
  font-size: 1.1em;
}

.svg-wrapper:hover #shape {
  stroke-dasharray: 50 0;
  stroke-width: 3px;
  stroke-dashoffset: 0;
  stroke: #06D6A0;
}

.pagingArea > button {
   background:#FFF;
   border:none;
   color: #ff9999;
   font-weight:bold;
   width:20px;
   
}

#pagingArea > button {
   background:#FFF;
   border:none;
   color: #ff9999;
   font-weight:bold;
   width:20px;
   
}

</style>
</head>
<body>
<head><%@include file ="../hfl/header.jsp" %></head>
<div class="container">
   <br>
   <br>
 <h2 id="board">공지사항</h2>
  <!-- <table class="table" id = "listArea"> -->
         <table align = "center" id = "listArea" class="table">
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
     <%for(Board b : list) {%>
            <tr>
               <input type = "hidden" value = "<%=b.getPostsId() %>">
               <td><%= b.getPostsId() %></td>
               <td><%= b.getPostsTitle() %></td>
               <td><%= b.getMemberName()%></td>
               <td><%= b.getCreateDate()%></td>
               <td><%= b.getPostsViews()%></td>
            </tr>
        <%} %>
    </tbody>
  </table>
     <div class = "pagingArea" align ="center" >
      <button onclick = "location.href = '<%=request.getContextPath()%>/selectNotice.no?currentPage=1'"><<</button>
      <%if(currentPage <= 1) {%>
      <button disabled><</button>
      <%} else{%>
   <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage=<%=currentPage-1%>'"><</button>
      <%}

      %>
         <%for (int p = startPage; p <= endPage; p++) {
            if(currentPage == p){
         %>
            <button disabled><%= p %></button>
         <%} else{ %>
               <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage=<%=p%>'"><%= p %></button>
         <% }
         }
         %>
         <%if(currentPage >= maxPage){ %>
         <button disabled>></button>
         <%}else{ %>
         <button onclick ="location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage=<%=currentPage + 1%>'">></button>
         <%} %>
         <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage=<%=maxPage%>'">>></button>

      </div>


</div>
<div class="container">
   <br>
   <br>
   <hr>
  <h2 id="board">게시판</h2>
    <table class="table" id = "messageArea">
      <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
        <th>조회수</th>

      </tr>
    </thead>
    <tbody>
    <%for(Board m: list1) {%>
      <tr>
      <input type = "hidden" value = "<%=m.getPostsId() %>">
               <td><%= m.getPostsId() %></td>
               <td><%= m.getPostsTitle() %></td>
               <td><%= m.getMemberName()%></td>
               <td><%= m.getCreateDate()%></td>
               <td><%= m.getPostsViews()%></td>
      </tr>
      <%}%>
    </tbody>
      <div class="svg-wrapper" align ="center">
      <svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
        <rect id="shape" height="40" width="150" align = "center"/>
        <div id="text" align ="center" >
          <a onclick="location.href='<%=request.getContextPath()%>/views/user/board/messageBoard.jsp'" style="color:black;align:center">
          <span class="spot" align ="center" ></span>글작성</a>
        </div>
      </svg>
    </div>
  </table>
   <div id = "pagingArea" align ="center" >
      <button onclick = "location.href = '<%=request.getContextPath()%>/selectNotice.no?currentPage1=1'"><<</button>
      <%if(currentPage1 <= 1) {%>
      <button disabled><</button>
      <%} else{%>
   <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage1=<%=currentPage1-1%>'"><</button>
      <%}%>
         <%for (int p = startPage1; p <= endPage1; p++) {
            if(currentPage1 == p){
         %>
            <button disabled><%= p %></button>
         <%} else{ %>
               <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage1=<%=p%>'"><%= p %></button>
         <% }
         }
         %>


         <%if(currentPage1 >= maxPage1){ %>
         <button disabled>></button>
         <%}else{ %>
         <button onclick ="location.hreh='<%=request.getContextPath()%>/selectNotice.no?currentPage1=<%=currentPage1 + 1%>'">></button>
         <%} %>
         <button onclick = "location.href='<%=request.getContextPath()%>/selectNotice.no?currentPage1=<%=maxPage1%>'">>></button>
			</div>
      </div>
      	<br><br>
      <div align = "center">
 <select name="selecthowsearch" id = "select" name = "select" style="width:20%;">
			<option value="">--조회할목록선택--</option>
			<option value="findId" >아이디로 조회</option>
			<option value="findName" >제목으로 조회</option>
		</select>
		<input type="search" name="searchValue" id = "inputSearch" >
		<button type="button" onclick = "search();" style="border-radius: 5px; background-color: #ffcccc; color:white; width:50px">조회</button>
	</div>
</div>

   <script>
      $(function(){
         $("#listArea td").mouseenter(function(){

            $(this).parent().css({"background":"#eee6ff","cursor":"pointer"});
         }).mouseout(function(){
               $(this).parent().css({"background":"white"});
         }).click(function(){
            var num = $(this).parent().children("input").val();
             location.href="<%=request.getContextPath()%>/son.no?num=" + num;
         });
      });

      $(function(){
         $("#messageArea td").mouseenter(function(){
            $(this).parent().css({"background":"#eee6ff","cursor":"pointer"});
         }).mouseout(function(){
               $(this).parent().css({"background":"white"});
         }).click(function(){
            var num = $(this).parent().children("input").val();
             location.href="<%=request.getContextPath()%>/sub.bo?num=" + num;
         });
      });
      function search(){
    	  $(function(){
    		  var id = $("select[id = 'select']").val();
    		  var input = $("input[id = 'inputSearch']").val();
    		  $.ajax({
    			url:"/sp/sbl.sh",
    			data:{"selectid":id,"input":input},
    			type:"post",
    			success:function(data){
    				var $Tbody = $("#messageArea tbody");
    				var $pagingDiv = ("#pagingArea div");
    				$Tbody.html("");
    				$pagingDiv = ("");
					var i = 1;
					for(var i = 0; i < data["list"].length; i++){
						var $tr = $("<tr>");
						var $hiddenPostsId=$("<input hidden>");
						var $postsId = $("<td><label>").text(data["list"][i].postsId);
						var $postsTitle = $("<td><label>").text(data["list"][i].postsTitle);
						var $postsViews = $("<td><label>").text(data["list"][i].postsViews);
						var $memberName = $("<td><label>").text(data["list"][i].memberName);
						var $createDate = $("<td><label>").text(data["list"][i].createDate);
						$hiddenPostsId.attr("value",data['list'][i].postsId);
						$tr.append($hiddenPostsId);
						$tr.append($postsId);
						$tr.append($postsTitle);
						$tr.append($memberName);
						$tr.append($createDate);
						$tr.append($postsViews);
						$Tbody.append($tr);
					}
					$("#messageArea tbody td").mouseenter(function(){
			            $(this).parent().css({"background":"eee6ff","cursor":"pointer"});
			         }).mouseout(function(){
			               $(this).parent().css({"background":"white"});
			         }).click(function(){
			            var num = $(this).parent().children("input").val();;
			             location.href="<%=request.getContextPath()%>/son.no?num="+num;
			         });
    			}
    		  })
    	  });
      }
   </script>

</body>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
</html>
