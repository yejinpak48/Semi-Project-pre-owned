<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String msg = (String) request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h1><%=msg %></h1>
	<script>
	function(){
		alert.log(<%=msg%>);
	};
	</script>
</body>
</html>