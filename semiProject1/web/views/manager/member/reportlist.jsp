<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.manager.member.model.vo.*,com.kh.bvengers.user.member.model.vo.*"%>
	<%
	ArrayList<Report> list = (ArrayList<Report>)request.getAttribute("list");
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
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<title>Insert title here</title>
<style>
<style>
h2 {
	font-family: 'Poor Story', cursive;
	margin: 0;
	padding: 0;
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


.mstop,.blacklist{
background:red;
border:1px solid lightyellow;
color:white;
}
</style>
<body>
	<%@ include file="../hfl/managerHeader.jsp"%>
	<br />
	<h2 align="center">신고대상 관리</h2>
	<form action="<%=request.getContextPath()%>/reportsearch.me" method="post">
	<div id="inOutMain"><br><br>
		<div id="inOutButton" align="center">

		<select name="selecthowsearch" style="width:20%;">
			<option value="before">처리전</option>
			<option value="after">처리완료</option>
		</select>
		<!-- <input type="search" name="searchValue" id="searchValue"> -->
		<button type="submit" style="border-radius: 5px; background-color: black; color:white;">조회</button>
	</div><br><br>
		<div id="table Area">
			<table id="depotMain" align="center">
				<tr>
		<th class="th">신고번호</th>
		<th class="th">신고자</th>
		<th class="th">신고대상</th>
		<th class="th">신고근거</th>
		<th class="th">신고일자</th>
		<th class="th">신고사유</th>
		<th class="th">블랙리스트 등록</th>
		<th class="th">회원정지</th>
	</tr>
	<%for(Report r : list){ %>
	<tr>
	<td align="center"><%=r.getReportNo() %></td>
	<td align="center"><%=r.getReporter() %></td>
	<td align="center"><%=r.getMemberDest() %></td>
	<td align="center"><a class="detail"><%=r.getPostsId() %></a></td>
	<td align="center"><%=r.getReportDate() %></td>
	<td align="center"><%=r.getReportComments() %></td>
	<td align="center"><input type="button" class="blacklist" value="등록하기"></td>
	<td align="center">
	<select name = "term" id="term">
	<option value="3">3일</option>
	<option value="7">1주</option>
	<option value="30">한달</option>
	</select>
	<input type="button" class="mstop" value="정지"></td>
	</tr>
	<%} %>

<%-- 			<%@ include file="rearchMemberResult.jsp" %>
 --%>			</table><br>
		</div><br>
	</div><br><br>
		</form>

<div class="pagingArea" align="center">
	<button onclick="location.href='<%=request.getContextPath()%>/reportList.me?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button onclick="location.href='<%=request.getContextPath()%>/reportList.me?currentPage=<%=currentPage - 1%>'"> < </button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){
			%>
					<button disabled><%= p %></button>
			<% } else { %>
					<button onclick="location.href='<%=request.getContextPath()%>/reportList.me?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }
			%>

			<% if(currentPage >= maxPage){ %>
			<button disabled>></button>
			<% }else{ %>
			<button onclick="location.href='<%=request.getContextPath()%>/reportList.me?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>

			<button onclick="location.href='<%=request.getContextPath()%>/reportList.me?currentPage=<%=maxPage%>'">>></button>
</div>
<script>
$(".detail").click(function(){
	var num = $(this).text();
	location.href='<%=request.getContextPath()%>/son.no?num='+num;
});

$("#depotMain td").mouseenter(function(){
	$(this).parent().css({"background":"lightgray","cursor":"pointer"});
}).mouseout(function(){
	$(this).parent().css({"background":"white"});
});

$(function(){
$(".blacklist").click(function(){
	var memberId = $(this).parent().siblings().eq(2).text();
	var reason = $(this).parent().siblings().eq(3).text();
	$.ajax({
		url:"/sp/blacklist.me",
		type:"post",
		data:{memberId:memberId,reason:reason},
		success:function(data){
			if(data==="success"){
				alert("블랙리스트처리완료");
			}else{
				alert("처리 실패!");
			}
		}
	});
});
});
$(function(){
	$(".mstop").click(function(){
	var memberId = $(this).parent().siblings().eq(2).text();
	var term = $("#term option:selected").val();
	var reason = $(this).parent().siblings().eq(3).text();
	$.ajax({
		url:"/sp/memberStop.me",
		type:"post",
		data:{memberId:memberId,term:term,reason:reason},
		success:function(data){
			if(data==="success"){
				alert("정지완료");
			}else{
				alert("정지실패")
			}
		}
	});
	});
});
</script>
<br><br>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>

</body>
</html>