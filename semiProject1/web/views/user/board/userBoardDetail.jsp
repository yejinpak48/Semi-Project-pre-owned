<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*,com.kh.bvengers.board.model.vo.*,java.util.HashMap"%>
 <% Board b = (Board)request.getAttribute("b");
 	Attachment a = (Attachment)request.getAttribute("fileList");

 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
.report{
	width:150px;
}

.tableArea{
		border:1px solid white;
		width:800px;
		height:100%;
		margin:0 auto;
}

#content{
		height:230px;
	}

#buttonArea{
	width:100%;
	padding-left:70%;
}

.right{
 	text-align:right;
 	}
.writer,.view,.date{
 	align:left;
 	} 
button{
  background:#f7e6ff;
  color:#fff;
  border:none;
  position:relative;
  height:40px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
button:hover{
  background:#fff;
  color: #ffb3b3;
}
:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background:  #ffb3b3;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}
 	
</style>
</head>
<head><%@include file ="../hfl/header.jsp" %></head>
<body>
<div>
	<br>
	<br>
	<div class="container">
	 	<form action = "<%= request.getContextPath()%>/son.no" method = "post" encType="multipart/form-data">
	<div class = "tableArea">
			<hr>
		<table align = "center" width = "800px">
	<tr>
		<td colspan = "7"><h2><%= b.getPostsTitle() %></h2></td>
		</tr>
	<tr>
			<td class="right">작성자zz : </td>
			<td class="writer"><span><%= b.getMemberId() %></span></td>
			<td class="right">조회수 : </td>
			<td class="view"><span><%= b.getPostsViews() %></span></td>
			<td class="right">작성일 : </td>
			<td class="date"><span><%= b.getCreateDate() %></span></td>
		</tr>
		</table>
				<hr>
				<table align = "center" width = "800px">
				<tr>
				<% if(!(a.getNewFileName().equals("사진없음"))){%>
				<td colspan = "6"><img id="titleImg" style=" align:center; margin:0 auto; width:100%; height:150%;"src="<%=request.getContextPath()%>/thumbnail_uploadFiles/<%= a.getNewFileName()%>"></td>
				<%}else{%>
				<td colspan = "6"></td><%}%>
				</tr>

				<tr>
					<td colspan = "6" align = "center"><h3><%= (b.getContents()).replace("\r\n","<br>") %></h3></td>
				</tr>

			</table>
		</div>

				<br><br><br>
		</form>
	<hr>
</div>
</div>

	<div class = "replyArea">

	<div >
		<div class = "replayWriterArea" style = " width:800px; height:100%; margin-left:30%; align:center">
				<table align = "center">
				<tr>
					<td><textarea rows = "3" cols = "80" id = "replyContent"></textarea></td>
					<td>&nbsp;&nbsp;</td>
					<td><button class = "enroll" id = "addReply" style = "height:60px; maring-left:34%">댓글 등록</button>	</td>
				</tr>
			</table>
				<div >
				<h5>&nbsp;</h5>
			</div>
			</div>
				<br>		
		<div style = "margin-left:30%">			
		<table id="replySelectTable" class="commentTables" align="center">
			<tr>
				<th colspan="7" style = "width:800px">댓글 리스트</th>
			</tr>
		</table>

		<br>

		<br>

		<table id="replySelectTable" class="commentTables" align="center">
			<tbody>
			<tr>
<!-- 			<td colspan="2" class="tWriter"><span></span></td>
				<td colspan="3" class="tContent"></td>
				<td class="tDate"></td> -->
			</tr>
			</tbody>
			<tfoot>

			</tfoot>
		</table>
		</div>
		</div>
			<br><br>
		<div id = "buttonArea">
		<% if (loginUser != null && loginUser.getMemberId().equals("admin")|| loginUser.getMemberId().equals(b.getMemberId())){ %>
			<button type = button onclick = "location.href= '<%= request.getContextPath()%>/sonn.no?num=<%=b.getPostsId() %>'">수정하기</button>
			<button type = button onclick = "location.href= '<%= request.getContextPath()%>/ubds.up?num=<%=b.getPostsId() %>'">삭제하기</button>
		<%} %>

	<% if (loginUser != null && (loginUser.getMemberId().equals("admin")|| !(loginUser.getMemberId().equals(b.getMemberId())))){ %>
		<button id= "report" class="report" align = "left" onclick = "report();">
		신고하기
		<img src="<%= request.getContextPath()%>/images/alert.png" style="width:18px;"/>
		</button>
			<%} %> 
		</div>
	<br>
	<br>
 <footer><%@ include file="../hfl/footer.jsp" %></footer>
	</div>
	<script>
	$(function(){
		$("#replySelectTable").on("click", function(){
			$(this).val = "";
		});
	});


	$(function(){
		var postsId = <%= b.getPostsId()%>;
		$(document).ready(function(){
			$.ajax({
				url:"selectComment.pd",
				data:{postsId:postsId},
				type:"post",
				success:function(data){
					var $replySelectTable = $("#replySelectTable tfoot");
					var $replyWriter = $("#replyWriter span")
					$replySelectTable.html("");
						for(var key in data){
							var $tr = $("<tr>");
							var $tr2  = $("<tr>");
							var $hr = $("<hr>");
							var $writer = $("<td>").text("작성자 : ").css({'width':'60px','font-weight':'bold'});
							var $writeTd = $("<td colspan='2'>").text(data[key].memberId).css("width", "100px");
							var $contentTd = $("<td colspan='2'>").text(data[key].commentContents).css("width","400px");
							var $dateTd = $("<td>").text(data[key].commentDate).css({'width':'200px','color':'lightgray','font-size':'10xpx'});

						$tr2.append($writer);
						$tr2.append($writeTd);
						$tr.append($contentTd);
						$tr.append($dateTd);

						$replySelectTable.append($hr);
						$replySelectTable.append($tr2);
						$replySelectTable.append($tr);
					}
				},
				error:function(){
					alert("댓글 입력 실패");
				}

			});
		});

	});



	$(function(){
		$("#addReply").click(function(){
			<% if(loginUser!=null){%>
			var writer =  <%= loginUser.getMemberNo()%>;
		    var postId = <%= b.getPostsId()%>;
		    var content = $("#replyContent").val();

		    $.ajax({
		    	url:"iwc.bo",
		    	data:{"writer":writer, "content":content, "postId":postId},
		    	type:"post",
		    	success:function(data) 	 {
		    		location.reload();
					$("#replySelectTable tfoot").show();


		    	},
		    	error:function(){
		    		console.log("실패!");
		    	}
		    });
		<%}else{ %>
			alert("로그인이 필요합니다!");
			location.href="views/user/login/login.jsp";

			<%}%>
		});
	});

	function report(){
		  var writer = <%= b.getMemberNo()%>;
		  console.log(writer);
		  var postId =<%= b.getPostsId()%>;
		  console.log(postId);
		  var array = writer+"/"+postId;
	      console.log(array);

	      var url = "views/user/board/report.jsp?array="+array;

	      window.open(url,'신고하기','width=430,height=450,status=no,scrollbars=yes');
		};

	</script>

</body>
</html>





