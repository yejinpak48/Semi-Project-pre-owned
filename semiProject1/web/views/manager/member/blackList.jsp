<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.member.model.vo.*,com.kh.bvengers.user.member.model.vo.*"%>
	<%
	ArrayList<SANCTION> list = (ArrayList<SANCTION>)request.getAttribute("list");
	MMemberPageInfo pi = (MMemberPageInfo) request.getAttribute("pi");
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

<style>
#depotMain {
	width: 95%;
	height: 80%;
	margin:auto;
}

table td{
height:30px;
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
	background: black;
	color: white;
	text-align: center;
}

#inOutButton {
	padding-left:8%;
}
#inOutMain {
	border :2px solid black;
	width:80%;
	margin:0 auto;
}
#table Area{
align:center;
width:90%;

}
.th{
background:lightgray;
color:black;
text-align:center;
border:1px solid white;
height:50px;
}
</style>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<br />
	<h2 align="center">경고회원
	 관리</h2>
	<form action="<%=request.getContextPath()%>/badsearch.me" method="post">
	<div id="inOutMain"><br><br>
		<div id="inOutButton" align="center">

		<select name="selecthowsearch" style="width:20%;">
			<option value="stopbadman" id="howsearch" >정지회원조회 조회</option>
			<option value="badblackman" id="howsearch" >블랙리스트조회 조회</option>
		</select>
		<input type="search" name="searchValue" id="searchValue" placeholder="회원번호 입력">
		<button type="submit" id="josushi" style="border-radius: 5px; background-color: black; color:white;">조회</button>
	</div><br><br>
	<script>
/* $("#josushi").on("click",function(){
	 $("form").submit();
}); */
	</script>
		<div id="table">
			<table id="depotMain" align="center">
				<tr>
		<th class="th">제재번호</th>
		<th class="th">회원번호</th>
		<th class="th">사유</th>
		<th class="th">발생일자</th>
		<th class="th">구분</th>
		<th class="th">상태</th>
		<th class="th">활동정지기간</th>
	</tr>
	<% for(SANCTION s : list){ %>
	<tr>
	<td align="center"><%=s.getSanctionNo() %></td>
	<td align="center"><%=s.getMemberNo() %></td>
	<td align="center"><a class="detail"><%=s.getReason() %></a></td>
	<td align="center"><%=s.getSanctionDate() %></td>
	<td align="center"><%=s.getSanctionDiv() %></td>
	<td align="center"><%=s.getSanctionStatus() %></td>
	<td align="center"><%=s.getStopTerm() %></td>
	</tr>
	<%} %>

<%-- 			<%@ include file="rearchMemberResult.jsp" %>
 --%>			</table><br>
		</div><br>
	</div><br><br>
		</form>

<div class="pagingArea" align="center">
	<button onclick="location.href='<%=request.getContextPath()%>/badmanlist.me?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/badmanlist.me?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/badmanlist.me?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }
			%>

			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/badmanlist.me?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/badmanlist.me?currentPage=<%=maxPage%>'">>></button>
			<br><br><br>
				<footer><%@ include file="../hfl/footer.jsp"%></footer>
			
</div>
<script>
$("#depotMain td").mouseenter(function(){
	$(this).parent().css({"background":"lightgray","cursor":"pointer"});
}).mouseout(function(){
	$(this).parent().css({"background":"white"});
});
$(".detail").click(function(){
	var num = $(this).text();
	location.href='<%=request.getContextPath()%>/son.no?num='+num;
});
</script>
</body>
</html>