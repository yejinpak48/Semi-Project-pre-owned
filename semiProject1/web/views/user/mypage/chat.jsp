<%@page import="com.kh.bvengers.user.chat.model.vo.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Chat ch = (Chat)request.getAttribute("ch");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<style>
	#messageWindow {
		width: 70%;
		border: 1px solid black;
	}
	#sendDiv #inputMessage{
		width: 60%;
		margin: 10px auto;
	}
	.chatBtn{
		width: 10%;
		color: red;
		background: #ffe6e6;
	}
</style>
<body>
	<header><%@ include file="../hfl/header.jsp"%></header>
	<fieldset align="center">
		<textarea name="" id="messageWindow" cols="50" rows="20" readonly></textarea>
		<br />
		<div id=sendDiv>
			<input type="text" id="inputMessage" />
			<input type="button" value="보내기" id="sendBtn" class="chatBtn" onclick="send();" />
		</div>
		<div id="exit">
			<input type="button" value="상담 종료" id="endChat" class="chatBtn" onclick="endChat();"/>
		</div>
	</fieldset>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>
</body>
<script>
	var textarea = document.getElementById("messageWindow");
	var id = "<%=loginUser.getMemberId()%>";
	var no = "<%=ch.getMemberNo()%>";
	var webSocket = new WebSocket('ws://localhost:8001/sp/chatting?id='+no+'&subId='+id);
	var inputMessage = document.getElementById('inputMessage');
	var date = new Date();
	var time = " ["+ (date.getMonth()+1) +"월 " + date.getDate() + "일 " + date.getHours() + "시 " + date.getMinutes() +"분" +"]";
	webSocket.onerror = function(event) {
		onError(event);
	};
	webSocket.onopen = function(event) {
		onOpen(event);
	};
	webSocket.onmessage = function(event) {
		onMessage(event);
	};
   function preChat(){
	   textarea += document.getElementById("pre").value;
	   console.log($("#pre").val());
	};
	 $(document).ready(function() {
			$(document).bind('keydown', function(e) {
				if (e.keyCode == 13) {
		   			send();
				}
			});
		});
    /* window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0); */

    $(document).ready(function(){
    	var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    });

	function onMessage(event) {
		var message = event.data;
		var idx = message.indexOf(":");
		var sender = message.substring(0, idx);
		var msg = message.substring(idx+1);


		if(id != sender){
			textarea.value += sender + ": " + msg + "\n" +time +"\n";
		}
	};
	function onOpen(event) {
		textarea.value += "\n" + time;
		<%if (!loginUser.getMemberId().equals("admin")){%>
			textarea.value += "\n 중고愛 민족 1:1 문의입니다. \n 상담원과 연결될때 까지 기다려주세요 \n";
		<%} else {%>
			textarea.value += "\n 중고愛 민족 1:1 문의입니다. \n";
			webSocket.send(id + "*" + no + ": " + "상담원과 연결되었습니다.");
		<%}%>
	};
	function onError(event) {
		alert(event.data);
	};

	function send() {
		if (inputMessage.value != "" && inputMessage.value != null) {
			textarea.value += id + ": " + inputMessage.value + "\n" + time + "\n";
			webSocket.send(id + "*" + no + ": " + inputMessage.value);
			inputMessage.value = "";

			<%-- location.href="<%=request.getContextPath()%>/updateChat.ch?no="+no; --%>
			var content = document.getElementById("messageWindow").value;
			$.ajax({
				url:"updateChat.ch",
				data:{no:no, content:content},
				type:"post",
				success:function(){
					console.log("성공");
				}, error:function(){
					console.log("실패");
				}
			});

		} else {
			alert("메세지를 입력해주세요");
		};
	};
	function endChat(){
		if(confirm("상담을 종료하시겠습니까?")){
			alert("상담을 종료합니다.");
			$.ajax({
				url:"endChat.ch",
				data:{no:no},
				type:"post",
				success:function(){
					history.go(-1);
				}
			});
		};
	};
</script>
</html>