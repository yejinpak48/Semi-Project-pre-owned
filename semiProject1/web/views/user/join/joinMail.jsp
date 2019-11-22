<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String num =(String) request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

	if(num==="1" && num!=null){
		alert("인증되었습니다.");
	}else{
		alert("인증실패! 다시 시도해주세요");
	}

</script>
</head>
<body>
</body>
</html>