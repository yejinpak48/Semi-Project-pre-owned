<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import = "java.util.*,com.kh.bvengers.board.model.vo.*,com.kh.bvengers.user.myPage.model.vo.*"%>
<%
ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
BoardPageInfo pi = (BoardPageInfo)request.getAttribute("pi");
int listCount = pi.getListCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/d3js/5.9.0/d3.min.js"></script>
<style>
#board {
	text-align: center;
}

#wirte {
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
</style>
</head>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>

	<div class="container">
		<br> <br>
		<hr>
		<h2 id="board">게시물 관리</h2>
		<table class="table" id="messageArea">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성날짜</th>

				</tr>
			</thead>
			<tbody>
				<%for (Board b : list) {%>
				<tr class = "row1">
				<input type = "hidden" value = "<%=b.getPostsId() %>">
				<td><%= b.getPostsId() %></td>
				<td><%= b.getPostsTitle() %></td>
				<td><%= b.getMemberName() %></td>
				<td><%= b.getCreateDate() %></td>
			<%} %>
			</tbody>

		</table>

			<br><br><br><br>
		  <div class = "pagingArea" align ="center" class="pagination" >
		<button onclick = "location.href = '<%=request.getContextPath()%>/smbs.mb?currentPage=1'"><<</button>
		<%if(currentPage <= 1) {%>
		<button disabled><</button>
		<%} else{%>
	<button onclick = "location.href='<%=request.getContextPath()%>/smbs.mb?currentPage=<%=currentPage-1%>'"><</button>
		<%}
		%>
			<%for (int p = startPage; p <= endPage; p++) {
				if(currentPage == p){
			%>
				<button disabled><%= p %></button>
			<%} else{ %>
					<button onclick = "location.href='<%=request.getContextPath()%>/smbs.mb?currentPage=<%=p%>'"><%= p %></button>
			<% }
			}
			%>


			<%if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<%}else{ %>
			<button onclick ="location.href='<%=request.getContextPath()%>/smbs.mb?currentPage=<%=currentPage + 1%>'">></button>
			<%} %>
			<button onclick = "location.href='<%=request.getContextPath()%>/smbs.mb?currentPage=<%=maxPage%>'">>></button>

      </div>

	</div>

<script>
	$(function(){
		$("#messageArea td").mouseenter(function(){

			$(this).parent().css({"background":"#ffe6e6","cursor":"pointer"});
		}).mouseout(function(){
				$(this).parent().css({"background":"white"});
		}).click(function(){
			var num = $(this).parent().children("input").val();
			 location.href="<%=request.getContextPath()%>/SelectUserBoardServelt.ma?num=" + num;
		});
	});

	 function search(){
   	  $(function(){
   		  var id = $("select[id = 'select']").val();
   		  var input = $("input[id = 'inputSearch']").val();
   		  $.ajax({
   			url:"/sp/SearchManagerQnAServelt.qna",
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

			            $(this).parent().css({"background":"darkgray","cursor":"pointer"});
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
</html>