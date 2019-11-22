<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>


.productContainer {
	padding:auto;
	margin:0 auto;
}

.title {
	font-size: 20px;
	margin: 0 auto;
}

.pull {
	border: 1px solid #fbaad9;
	font-size: 14px;
	width: 10%;
	font-weight: 550;
	border-radius: 10px;
	background-color: #ffd8d9;
	color: black;
	text-align: center;
}

.pull:hover{
	border: 1px solid #fbaad9;
	font-size: 14px;
	width: 10%;
	font-weight: 550;
	border-radius: 10px;
	background-color: #fbaad9;
	color: black;
	text-align: center;
}

.danger {
	color: red;
	font-size: 5px;
}

#fileLabel {
	display: inline-block;
	padding: .5em .75em;
	color: #fff;
	font-size: .8em;
	line-height: normal;
	vertical-align: middle;
	background-color: #7799dd;
	cursor: pointer;
	border: 1px solid #7090d0;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	width: 25%;
}

#searchLabel {
	display: inline-block;
	padding: .5em .75em;
	color: #fff;
	font-size: .85em;
	line-height: normal;
	vertical-align: middle;
	background-color: #7799dd;
	cursor: pointer;
	border: 1px solid #7090d0;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	height:100%;
}

.pullRight {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.fileName {
	width: 70%;
	display: inline-block;
	padding: .5em .75em;
	font-size: inherit;
	font-family: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #f5f5f5;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

#accountNo::-webkit-outer-spin-button, #accountNo::-webkit-inner-spin-button,
	#productMoney::-webkit-outer-spin-button, #productMoney::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
}

.enrollForm{
	width:80%;
	margin:0 auto;
	padding-right: auto;
	padding-left: auto;
	align:center;
}

table-bordered{
	padding:0 auto;
	margin:0 auto;
}

</style>
</head>
<body>

<%@ include file="../hfl/header.jsp" %>
<%@ include file="../hfl/list.jsp" %>
<%if(loginUser!=null) {%>
<br /><br />

	<div class="productContainer">
		<form action="<%=request.getContextPath()%>/insert.po" class="enrollForm" method="post" encType="multipart/form-data">
			<h2 style="margin-top:0px; margin-bottom:10px; text-align:center; align:center;">상품등록</h2>
			<table class="table table-bordered" style="align:center; width:70%; margin:0 auto;">
				<tr>
					<th width="15%">제목 </th>
					<td colspan="4"><input type="text" size=30 name="postsTitle" class="form-control"/></td>
				</tr>
				<tr>
					<th width="15%">카테고리</th>
					<td width="15%">
						<select class="cate_enroll" name="mainCate" style="width:100%;">
							<option id="pc" value="pc">PC
							<option id="laptop" value="laptop">노트북
							<option id="digital" value="digital">가전제품
						</select>
					</td>
					<th width="15%">상세카테고리</th>
					<td width="25%" colspan="2">
						<select id="sub_cate" class="sub_cate sub_cate1" name="subCate1" style="width:80%;">
								<option id="desktop" value="desktop">데스크탑
								<option id="pcEtc" value="pcEtc">PC 주변기기
						</select>
						<select id="sub_cate" class="sub_cate sub_cate2" name="subCate2" style="width:80%;">
								<option id="case" value="case">노트북 가방
								<option id="notebook" value="notebook">노트북
								<option id="notebookEtc" value="notebookEtc">노트북 주변기기
						</select>
						<select id="sub_cate" class="sub_cate sub_cate3" name="subCate3" style="width:80%;">
							<option id="ha" value="ha">생활가전
							<option id="ka" value="ka">주방가전
							<option id="camera" value="camera">카메라
						</select>
					</td>
	            </tr>
	            <tr>
	                <th>상품명 </th>
	                <td colspan="4"><input cols="10" size=30 name="productName" class="form-control" style="resize:none"></td>
	            </tr>
	            <tr>
	                <th>가격 </th>
	                <td colspan="4"><input type="number" name="productMoney" id="productMoney" class="form-control"></td>
	            </tr>
	            <tr>
	                <th>상품 이미지</th>
	                <td colspan="4">
	                	<div>
		                	<label for="file1" id="fileLabel">이미지 업로드</label>
		                	<input class="fileName" value="파일선택" disabled="disabled">
		                	<input type="file" name="file1" class="pullRight" id="file1" style="width:50%;"/><br />
		                	<p class="danger">*첫번째 업로드 사진이 메인 이미지가 됩니다.</p>
						</div>
	                	<div>
		                	<label for="file2" id="fileLabel">이미지 업로드</label>
		                	<input class="fileName" value="파일선택" disabled="disabled">
		                	<input type="file" name="file2" class="pullRight" id="file2" style="width:50%;"/><br />
	                	</div>
	                	<div>
							<label for="file3" id="fileLabel">이미지 업로드</label>
		                	<input class="fileName" value="파일선택" disabled="disabled">
		                	<input type="file" name="file3" class="pullRight" id="file3" style="width:50%;"/><br />
						</div>
						<div>
							<label for="file4" id="fileLabel">이미지 업로드</label>
		                	<input class="fileName" value="파일선택" disabled="disabled">
							<input type="file" name="file4" class="pullRight" id="file4" style="width:50%;"/><br />
	                	</div>
	                </td>
	            </tr>
	            <tr>
	                <th>상품 설명 </th>
	                <td colspan="4">
	                	<textarea cols="10" rows="10" name="contents" class="form-control" style="resize:none"></textarea>
	                	<!-- <input type="text" class=""/> -->
	                </td>
	            </tr>
	            <tr>
	            	<th>예금주</th>
	            	<td>
	            		<input type="text" name="accountHolder" id="accountHolder" class="form-control" readonly/>
	            	</td>
	            	<th>은행명</th>
	            	<td><input type="text" name="bankCode" id="bankCode" class="form-control" readonly/></td>
	            	<td width="10%" style="padding-top:auto; padding-bottom:auto; center; text-align:center; margin-top:20px; margin-right: auto; margin-left: auto;">
	            		<button type="button" onclick="searchBank();" name="searchBtn" id="searchBtn" hidden>계좌조회</button>
	            		<label for="searchBtn" id="searchLabel" style="height:90%; width:90%">계좌조회</label>
	            	</td>
	            </tr>
	            <tr>
		            <th>입금될 계좌번호</th>
		            <td colspan="4"><input type="text" placeholder="-제외한 계좌번호를 입력해주세요" name="accountNo" id="accountNo" class="form-control" readonly/></td>
	            </tr>
	            <tr>
	                <th>상품 보관일자 </th>
	                <td colspan="4"><input type="date" name="keepDate" style="width:40%;" class="form-control" id="keepDate"/></td>
	            </tr>
	            <tr>
	                <th colspan="6">
	                	<input type="hidden" name="loginId"/>
	                	<label for="btn_goHome" class="pull-left pull">홈으로</label>
	                	<label for="btn_enroll" class="pull-right pull">등록</label>
	                	
	                    <input type="submit" value="등록" id="btn_enroll" class="pull-right pull" hidden/>
	                    <input type="button" value="홈으로" id="btn_goHome" class="pull-left pull" onclick="location.href='<%=request.getContextPath() %>/index.jsp'" hidden/>
	                </th>
	            </tr>
			</table>
        </form>
        <script>
        function searchBank(){
        	window.open('/sp/views/user/product/searchBank.jsp', "", "width=700, height=300, top=50, left=50");
        }
        	$(function(){
	        			$(".sub_cate1").show();
	        			$(".sub_cate2").hide();
	        			$(".sub_cate3").hide();

        		$(".cate_enroll").change(function(){
	        		var select = $(".cate_enroll").val();

	        		if(select == "pc"){
	        			$(".sub_cate1").show();
	        			$(".sub_cate2").hide();
	        			$(".sub_cate3").hide();

	        		}else if(select == "laptop"){
	        			$(".sub_cate1").hide();
	        			$(".sub_cate2").show();
	        			$(".sub_cate3").hide();
	        		}else{
	        			$(".sub_cate1").hide();
	        			$(".sub_cate2").hide();
	        			$(".sub_cate3").show();
	        		}

        		});

        		$(".pullRight").change(function(){

        			if(window.FileReader){
        				var fileName = $(this)[0].files[0].name;
       				} else {
       					var fileName = $(this).val().split('/').pop().split('\\').pop();
					}
					$(this).siblings(".fileName").val(fileName);


        		});





        	});
        </script>

</div>
<footer><%@ include file="../hfl/footer.jsp" %></footer>
<%}else{%>
	<script>
		$(function(){
			alert("로그인후 이용해주세요");
			history.back();
		});
	</script>
<%} %>
</body>
</html>