<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.bvengers.product.model.vo.Category"%>
<%@page import="com.kh.bvengers.board.model.vo.Attachment"%>
<%@page import="com.kh.bvengers.board.model.vo.Posts"%>
<%@page import="com.kh.bvengers.product.model.vo.Product"%>
<%@page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<String> myAdd = (ArrayList<String>) request.getAttribute("myAdd");
	Product pr = (Product) request.getAttribute("pr");
	Posts po = (Posts) request.getAttribute("po");
	Attachment att = (Attachment) request.getAttribute("att");
	Category cate = (Category) request.getAttribute("cate");
	ArrayList<HashMap<String, Object>> productPay = (ArrayList<HashMap<String, Object>>) request.getAttribute("productPay");
	//HashMap<String, Object> productPay = (HashMap<String, Object>) request.getAttribute("productPay");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.bootpay.co.kr/js/bootpay-3.0.2.min.js" type="application/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
<style>
* {
	font-family: 'Nanum Gothic', sans-serif;
}

#sec1 {
	width: 100%;
	height: 100%;
	padding-right: 20%;
	padding-left: 20%;
	margin-top: 10px;
}

.outerArea {
	align: center;
	width: 100%;
	height: 100%;
	margin-top: 70px;
	padding-top: 2%;
	border:5px solid #ddf;
}

#payForm {
	align: center;
	font-size: 1.2vw;
}

#paymentPrice {
	text-align: right;
}

#message {
	width: 100%;
	resize: none;
}

.pt {
	margin: 0 auto;
	width: 80%;
	max-height: 200px;
	padding-top: 5%;
	border:1px solid #aabfde;
	border-radius:5px;
	font-family: 'Nanum Gothic', sans-serif;
}

#payForm table td {
	padding: 4px;
}

#productImg {
	text-align:center;
	border: 2px solid #aabfde;
}

.postsTitle {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 1.8vw;
	padding: 1%;
}

#Detail {
	font-size: 1vw;
	vertical-align: top;
	padding: 1%;
	border:1px solid #aabfde;
}

#price{
	font-size: 1.5vw;
	text-align:right;
	padding:1%;
}

#deliPay{
	font-size: 1vw;
	padding:1%;
}

#quantity {
	font-size: 1.5vw;
}

#priceAll {
	font-size: 1.5vw;
}

.payInfo{
	width:80%;
	border:1px solid #aabfde;
	margin: 0 auto;
}

.payInfo th{
	border:1px solid #aabfde;


}
.payInfo td{

	border:1px solid #aabfde;
}

.payInfo input {
	margin:0 auto;
	border: 1px solid #abd;
	border-radius:5px;
 	width:100%;
}

.addressNum, .myAddress{
	display: inline-block;
	padding: .5em .75em;
	color: #fff;
	font-size: 0.9vw;
	line-height: normal;
	vertical-align: middle;
	background-color: #7799dd;
	cursor: pointer;
	border: 1px solid #7090d0;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}

#phone1::-webkit-outer-spin-button, #phone1::-webkit-inner-spin-button,
#phone2::-webkit-outer-spin-button, #phone2::-webkit-inner-spin-button,
#phone3::-webkit-outer-spin-button, #phone3::-webkit-inner-spin-button{
    -webkit-appearance: none;
}

.buy{
	width:50%;
	display: inline-block;
	padding: .5em .75em;
	color: #fff;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #7799dd;
	cursor: pointer;
	border: 1px solid #7090d0;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}
.buy:hover{
	width:100%;
	display: inline-block;
	padding: .5em .75em;
	color: #fff;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #ff5500;
	cursor: pointer;
	border: 1px solid #7090d0;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}
</style>


</head>
<body>
	<!-- header 영역 -->
	<header><%@ include file="../hfl/header.jsp"%></header>


	<div><%@ include file="../hfl/list.jsp"%></div>
	<section id="sec1">
		<div id="area" class="outerArea">
			<!-- 장바구니 목록(상품테이블) -->
			<table class="pt">
				<tr>
				<%
					String productCode = "";
					String priceSplit = "";
					int count = productPay.size();
					int totalPrice = 0;
					for(int i = 0; i < productPay.size(); i++){ %>
					<!-- 장바구니에 등록한 상품 사진, 제목, 카테고리, 품명, 가격,  -->
					<td id="productImg" rowspan="3" width="20%">
						<img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%=productPay.get(i).get("newFileName") %>" width=95% hright=100%>
					</td>

					<td class="postsTitle" colspan="3" width="70%" height="30%"><label><%= productPay.get(i).get("postsTitle") %></label></td> <!-- 게시글 -제목: 제거 -->
				</tr>
				<tr>
					<td id="Detail"><label>제품명 | <%= productPay.get(i).get("productName") %></label></td>
					<td id="Detail"><label>카테고리 | <%= productPay.get(i).get("mainCateDiv") %>><%= productPay.get(i).get("categoryDiv") %></label></td>
					<td id="Detail"><label>판매자 | <%= productPay.get(i).get("memberId") %></label></td>
				</tr>
				<tr>
					<td id="deliPay" style="border:1px solid #aabfde;"><label>택배비 : <%= productPay.get(i).get("deliPay") %>원</label></td>
					<td colspan="2" id="price" ><label><%= productPay.get(i).get("productMoney") %>원</label></td>

				</tr>
					<input type="hidden" value="<%=totalPrice += Integer.parseInt(productPay.get(i).get("deliPay")+"") + Integer.parseInt(productPay.get(i).get("productMoney")+"") %>"/>
					<input type="hidden" value="<% productCode += productPay.get(i).get("productCode") + ","; %>"/>
					<input type="hidden" value="<% priceSplit += productPay.get(i).get("productMoney") + ","; %>"/>
				<%} %>
			</table>
			<hr width="80%"/>
			<!-- 정보 입력창 -->
			<form id="payForm" action="<%=request.getContextPath()%>/okPay.pa" name="payForm" method="post" align="center">
				<table align="center" class="payInfo">
					<tr>
						<th width="20%"><label>총 결제금액</label></th>
						<td><input type="text" name="paymentPrice" id="paymentPrice"
							value="<%= totalPrice %>원" readonly></td>
					</tr>
					<tr>
						<th><label>택배 수령자 </label></th>
						<td><input type="text" placeholder="수령자 이름을 입력하세요"
							name="userName" id="userName"></td>
					</tr>
					<tr>
						<th><label>우편번호 </label></th>
						<td>
							<input type="text" placeholder="우편번호" name="addNum" id="addNum" style="width:62%"  readonly>
							<input type="button" name="addressNum" class="addressNum" value="우편번호찾기" onclick="addFind();" style="width:18%;">
							<input type="button" name="myAddress" class="myAddress" value="나의 집주소로" onclick="myAdd();" style="width:18%;">
						</td>
					</tr>
					<tr>
						<th><label>주소 </label></th>
						<td><input type="text" placeholder="주소" name="address" id="address" readonly></td>
					</tr>
					<tr>
						<th><label>나머지주소</label></th>
						<td><input type="text" placeholder="나머지 주소" name="subAddress" id="subAddress"></td>
					</tr>
					<tr>
						<th><label>연락처 </label></th>
						<td>
							<input type="number" name="phone1" id="phone1" style="width:30%" maxlength="3" oninput="phoneNum(this);"> -
							<input type="number" name="phone2" id="phone2" style="width:30%" maxlength="4" oninput="phoneNum(this);"> -
							<input type="number" name="phone3" id="phone3" style="width:30%" maxlength="4" oninput="phoneNum(this);">
							
							<input type="hidden" name="productCode" value="<%= productCode %>"/>
							<input type="hidden" name="priceSplit" value="<%= priceSplit %>"/>
							<input type="hidden" name="receiptId" id="receiptId" value=""/>
							<input type="hidden" name="count" value="<%=count %>" />
						</td>
					</tr>
					<tr>
						<th><label>이메일</label></th>
						<td><input type="email" placeholder="aaaa123@bbbb.ccc" id="mail" name="mail"/></td>
					</tr>
					<tr>
						<th><label>배송시 요구사항 </label></th>
						<td><textarea placeholder="요구사항을 입력해주세요" width=100% name="message" rows="7" id="message"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" class="buy" value="결제하기" onclick="paymentApi();">
							</td>
					</tr>
				</table>
				<input type="submit" class="paytestBtn" value="결제 성공 시  테스트" hidden/>
			</form>
			<br />
		</div>
	</section>
	<script type="text/javascript">
	 	function phoneNum(e){
	 	    if(e.value.length > e.maxLength){
	            e.value = e.value.slice(0, e.maxLength);
	        }
	    }
	 	function myAdd(){
	 		var add1 = "<%=myAdd.get(0)%>";
	 		var add2 = "<%=myAdd.get(1)%>";
	 		var add3 = "<%=myAdd.get(2)%>";
	 		document.getElementById('addNum').value = add1;
	 		document.getElementById('address').value = add2;
	 		document.getElementById('subAddress').value = add3;

	 	}

	 	function addFind(){
	 			var list = new Array();

	 			$("#select:checked").each(function(index, item){
	 			   list.push($(item).val());
	 			});

	 			var code = list.join(',');
	 			console.log(code);

			new daum.Postcode({
		           oncomplete: function(data) {

		               var addr = '';
		               var extraAddr = '';

		               if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                   addr = data.roadAddress;
		               } else { 							// 사용자가 지번 주소를 선택했을 경우(J)
		                   addr = data.jibunAddress;
		               }

		                   if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                       extraAddr += data.bname;
		                   }
		                   // 건물명이 있고, 공동주택일 경우 추가한다.
		                   if(data.buildingName !== '' && data.apartment === 'Y'){
		                       extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                   }
		                   // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                   if(extraAddr !== ''){
		                       extraAddr = ' (' + extraAddr + ')';
		                   }

		               // 우편번호와 주소 정보를 해당 필드에 넣는다.
		               document.getElementById('addNum').value = data.zonecode;
		               document.getElementById("address").value = addr;
		               // 커서를 상세주소 필드로 이동한다.
		               document.getElementById("subAddress").focus();
		           }
		       }).open();
		}

		function paymentApi(){
			var userName = document.getElementById('userName').value;

			var addNum = document.getElementById('addNum').value;
			var address = document.getElementById('address').value;
			var subAddress = document.getElementById('subAddress').value;
			var addr = '우편번호 : ' + addNum + ' 주소 : ' + address + ' ' + subAddress;

			var phone1 = document.getElementById('phone1').value;
			var phone2 = document.getElementById('phone2').value;
			var phone3 = document.getElementById('phone3').value;
			var phoneNum = phone1 + '-' + phone2 + '-' + phone3;

			var message = document.getElementById('message').value;
			var mail = document.getElementById('mail').value;

			var length = <%= productPay.size()%>-1;
			var postTitle = '';
			var productName = '';
			if(length > 0){
				postTitle = '<%= productPay.get(0).get("postsTitle") %>외' + length + '개 상품';
				productName = '<%= productPay.get(0).get("productName") %>외' + length + '개 상품';
			}else{
				postTitle = '<%= productPay.get(0).get("postsTitle") %>';
				productName = '<%= productPay.get(0).get("productName") %>';
			}



			BootPay.request({
				price: '<%= totalPrice %>', //실제 결제되는 가격
				application_id: "5d2fec7c396fa61e224d5730",
				name: postTitle, //결제창에서 보여질 이름
				pg: '',
				method: '', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
				show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
				items: [
					{
						item_name: productName, //상품명
						qty: 1, //수량
						unique: '<%=productPay.get(0).get("postsId") %>', //해당 상품을 구분짓는 primary key
						price: '<%=productPay.get(0).get("productMoney") %>', //상품 단가
						cat1: '<%= productPay.get(0).get("mainCateDiv") %>', // 대표 상품의 카테고리 상, 50글자 이내
						cat2: '<%= productPay.get(0).get("categoryDiv") %>', // 대표 상품의 카테고리 중, 50글자 이내
					}
				],
				user_info: {
					username: userName,
					email: mail,
					addr: addr,
					phone: phoneNum
				},
				order_id: '<%=productPay.get(0).get("postsId") %>', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
				params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
				//account_expire_at: '2018-05-25', // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. )
				extra: {
				    //start_at: '2019-05-10', // 정기 결제 시작일 - 시작일을 지정하지 않으면 그 날 당일로부터 결제가 가능한 Billing key 지급
					//end_at: '2022-05-10', // 정기결제 만료일 -  기간 없음 - 무제한
			        vbank_result: 1, // 가상계좌 사용시 사용, 가상계좌 결과창을 볼지(1), 말지(0), 미설정시 봄(1)
			        quota: '0,2,3' // 결제금액이 5만원 이상시 할부개월 허용범위를 설정할 수 있음, [0(일시불), 2개월, 3개월] 허용, 미설정시 12개월까지 허용
				}
			}).error(function (data) {
				//결제 진행시 에러가 발생하면 수행됩니다.
			}).cancel(function (data) {
				//결제가 취소되면 수행됩니다.
			}).ready(function (data) {
				// 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
			}).confirm(function (data) {
				//결제가 실행되기 전에 수행되며, 주로 재고를 확인하는 로직이 들어갑니다.
				//주의 - 카드 수기결제일 경우 이 부분이 실행되지 않습니다.
				var enable = true; // 재고 수량 관리 로직 혹은 다른 처리
				if (enable) {
					this.transactionConfirm(data); // 조건이 맞으면 승인 처리를 한다.
				} else {
					this.removePaymentWindow(); // 조건이 맞지 않으면 결제 창을 닫고 결제를 승인하지 않는다.
				}
			}).close(function (data) {
			    // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
			}).done(function (data) {
				console.log(data);
				var receiptId = data["receipt_id"];
				$("#receiptId").val(receiptId);
				console.log(receiptId);
				document.getElementById('payForm').submit();
				
				//결제가 정상적으로 완료되면 수행됩니다.
				//비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
			});
		}


		$(document).ready(function(){
			$('form[name="payForm"]').bind('submit', function(){
				if($('input[name="userName"]').val()==''){
					alert('수령자 성함을 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="addressNum"]').val()==''){
					alert('주소를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="myAddress"]').val()==''){
					alert('주소를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="address"]').val()==''){
					alert('주소를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="subaddress"]').val()==''){
					alert('주소를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="phone1"]').val()==''){
					alert('핸드폰 번호를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="phone2"]').val()==''){
					alert('핸드폰 번호를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="phone3"]').val()==''){
					alert('핸드폰 번호를 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="mail"]').val()==''){
					alert('메일을 입력해주세요');
					$(this).focus();
					return false;
				}else if($('input[name="message"]').val()==''){
					alert('요구사항을 입력해주세요');
					$(this).focus();
					return false;
				}
			});
		});

	</script>



	<!-- footer 영역 -->
	<footer><%@ include file="../hfl/footer.jsp" %></footer>

</body>
</html>