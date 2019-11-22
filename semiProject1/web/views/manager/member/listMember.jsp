<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.* , com.kh.bvengers.user.member.model.vo.*"%>
	<%
	ArrayList<Member> list = (ArrayList<Member>) request.getAttribute("list");
	MemberPageInfo pi = (MemberPageInfo) request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<style>
<style>
h2 {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
}
#depotMain {
	width: 80%;
	height: 80%;
	margin:auto;
}


#depotMain {
	width: 80%;
	margin: auto;
	align: center;
	
	border-radius:5px;
	border-collapse: collapse;
    line-height: 1.5;
}
 #depotMain th {

    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FFF;
    text-align:center;
}

#depotMain td {
	height:30%;
    padding: 5px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

#depotMain thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    border-bottom: 2px solid #ccc;
    height:50px;
    text-align:center;
}


#th {
	border: 1px solid white;
	background: lightgray;
	color: black;
	text-align: center;
}

#inOutButton {
	padding-left: 8%;
}

#inOutMain {
	border: 2px solid black;
	width: 80%;
	margin: 0 auto;
}

#table Area {
	align: center;
	width: 80%;
}

.mbdetail{
background:lightyellow;
border-radius:10px;
}
.mbdetail:hover{
background:white;
border:1px solid yellow;
}

.th {
	background: lightgray;
	color: black;
	text-align: center;
	border: 1px solid white;
}
table th{
text-align:center;
height:50px;
}
table td{
text-align:center;
height:30px;
}
.btns{
margin-left:13%;
font-weight:bold;
font-size:3;
}
#chk{
input-align:center;
}
</style>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<br />
	<h2 align="center">회원 관리</h2>

	<div id="inOutMain">
		<br>
		<br>
		<div id="inOutButton" align="center">
			<select name="selecthowsearch" id="selecthowsearch" style="width: 30%;">
				<option value="findId">아이디로 조회</option>
				<option value="findName">이름으로 조회</option>
				<option value="findLevel">등급으로 조회</option>
			</select> <input type="search" name="searchValue" id="searchValue">
			<button id = "searchbtn"
				style="border-radius: 5px; background-color: black; color: white;">조회</button>
		</div>
		<br>
		<br>
		<div id="table Area">
			<table id="depotMain" align="center">
			<thead>
				<tr>
					<!-- <th class="th"><input type="checkbox" id="chkAll"></th> -->
					<th class="th">아이디</th>
					<th class="th">이름</th>
					<th class="th">전화번호</th>
					<th class="th">등급</th>
					<th class="th">주소</th>
					<th class="th">가입일</th>
					<th class="th">판매횟수</th>
					<th class="th">회원 상세 조회</th>
				</tr>
				</thead>
				<%for(Member m : list){
					String grade = m.getGradeCode();
				%>
				<tr>
					<!-- <td><input type="checkbox" class="chk"></td> -->
					<td><%=m.getMemberId() %></td>
					<td><%=m.getMemberName() %></td>
					<td><%=m.getPhone() %></td>
					<td><%=m.getGradeCode() %></td>
					<td><%=m.getAddress()%></td>
					<td><%=m.getEnrollDate() %></td>
					<td><%=m.getSellCount() %></td>
					<td><button class="mbdetail">상세보기</button></td>
				</tr>
				<%} %>
			</table>
			<br>
			<br>
		</div>
	</div>
<br><br>
<script>

$("#depotMain td").mouseenter(function(){
	$(this).parent().css({"background":"lightgray","cursor":"pointer"});
}).mouseout(function(){
	$(this).parent().css({"background":"white"});
});

 	  $(".mbdetail").click(function(){
			var mi = $(this).parent().siblings().eq(0).text();
			var option = "width = 600px, height = 600px";
		 window.open('<%=request.getContextPath()%>/mbdetail.me?mi='+mi, "회원정보", option);
		 });
	$(".chk").on("click",function(){
		var chkid = $(this).parent().siblings().eq(0).text();
	location.href='<%=request.getContextPath()%>/updateBL.me?chkid='+chkid;
	})

/* 	$("#chkAll").on("click", function() {
	      var chkAll = $(this).is(":checked");
	      if (chkAll)
	        $("#depotMain input:checkbox").prop("checked", true);
	      else
	        $("#depotMain input:checkbox").prop("checked", false);
	    }); */
	$("#searchbtn").on("click",function(){
		var howselect = $("#selecthowsearch option:selected").val();
		var value = $("#searchValue").val();
		var send = $("#selecthowsearch option:selected").val() + "$" + $("#searchValue").val();
		 location.href='<%=request.getContextPath()%>/searchMember.me?send='+send;

	});
</script>
<div class="pagingArea" align="center">
	<button onclick="location.href='<%=request.getContextPath()%>/memberList.me?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/memberList.me?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/memberList.me?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }

			%>

			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/memberList.me?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/memberList.me?currentPage=<%=maxPage%>'">>></button>
			<br><br>
				<footer><%@ include file="../hfl/footer.jsp"%></footer>
			
</div>
</body>
</html>