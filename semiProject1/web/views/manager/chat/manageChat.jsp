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
<body>
	<header><%@ include file="../hfl/managerHeader.jsp"%></header>
	<fieldset>
		<textarea name="" id="messageWindow" cols="50" rows="10" rows="10" readonly></textarea>
		<br /> <input type="text" id="inputMessage" /> <input type="button" value="send" onclick="send();" />
	</fieldset>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>
</body>
<script>
	var textarea = document.getElementById("messageWindow");
	var no = <%=ch.getMemberNo()%>;
	var webSocket = new WebSocket('ws://localhost:8001/sp/chatting?id='+no);
	var inputMessage = document.getElementById('inputMessage');

	webSocket.onerror = function(event) {
		onError(event);
	};
	webSocket.onopen = function(event) {
		onOpen(event);
	};
	webSocket.onmessage = function(event) {
		onMessage(event);
	};
   function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
    window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0);
	function onMessage(event) {
		var message = event.data;
		var idx = message.indexOf(":");

		var sender = message.substring(0, idx);
		var msg = message.substring(idx+1);

		if(sender!="admin"){
			textarea.value += sender " : " + msg + "\n";
		}
	};
	function onOpen(event) {
		textarea.value += "연결 성공\n";
	};
	function onError(event) {
		alert(event.data);
	};

	function send() {
		if (textarea.value != "") {
			textarea.value += id + ": " + inputMessage.value + "\n";
			webSocket.send(id + "*" + no + ": " + inputMessage.value);
			inputMessage.value = "";
		} else {
			inputMessage.value = "";
		}
		;
	};
</script>
</html>