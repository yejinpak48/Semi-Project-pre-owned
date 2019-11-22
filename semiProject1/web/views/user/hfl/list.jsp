<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
<style>
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

li {
	list-style: none;
}

a {
	color: black;
	text-decoration: none;
}

a:link {
	color: black;
}

a:visited {
	color: black;
	text-decoration: none;
}

.container {
	position: fixed;
	left: 0;
	top: 300px;
}

.panel-collapse {
	margin: 3%;
}

.panel-group .panel {
	width: 80%;
}
</style>
</head>
<body>
 <div class="container" style="float: left; width: 20%;">
  <div class="panel-group" id="accordion" >
    <div class="panel panel-default">
      <div class="panel-heading" style="background:#ffe6e6;">
        <h4 class="panel-title" >
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">PC</a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="w3-dropdown-content w3-bar-block w3-border">
        	<div class="panel-body">
        	<ul id="pc">
        		<li value="desktop"><a class="w3-button">본체</a></li>
        		<li value="pc_etc"><a>주변기기</a></li>
        	</ul>
        	</div>
        </div>
      </div>
    </div>
    <div class="panel panel-default" >
      <div class="panel-heading" style="background: #ffe6e6;">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">노트북</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body">
        	<ul id="notebook">
        		<li value="note"><a>노트북</a></li>
        		<li value="case"><a>노트북 가방</a></li>
        		<li value="note_etc"><a>노트북 주변기기</a></li>
        	</ul>
        </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading" style="background: #ffe6e6;">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">가전제품</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">
        	<ul>
        		<li value="ka"><a>주방용품</a></li>
        		<li value="ha"><a>생활가전</a></li>
        		<li value="camera"><a>카메라</a></li>
        	</ul>
        </div>
      </div>
    </div>
  </div>
</div>
	<script>
		$("ul").on("click", "li", function() {
			var value = $(this).attr('value');
			location.href="<%=request.getContextPath()%>/list.pd?value="+value;
		});
	</script>
</body>
</html>