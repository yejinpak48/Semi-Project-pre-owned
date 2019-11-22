<%@page import="com.kh.bvengers.user.myPage.model.vo.myPage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
</head>
<style>

#deli_img:hover {
   -webkit-transform: scale(1.1); /*  크롬 */
   -moz-transform: scale(1.1); /* FireFox */
   -o-transform: scale(1.1); /* Opera */
   transform: scale(1.1);
   transition: transform .35s;
   -o-transition: transform .35s;
   -moz-transition: transform .35s;
   -webkit-transition: transform .35s;
}
#deli_img {
  animation-duration: 2s;
  animation-name: moveRight;

}

@keyframes moveRight {
  from {
     margin-left:-20%;
  }

  to {
     margin-left: 80%;
    transform:rotate(360deg);
  }
}

* {
   font-family: 'Poor Story', cursive;
   margin: 0;
   padding: 0;
}
.div_deli {
	width:50%;
	margin-top:50px;
	margin-left:auto;
	margin-right:auto;

}
#tekbeCompnayList {
    width: 250px;
    height: 30px;
    padding-left: 10px;
    font-size: 16px;
    color: black;
    border: 1px solid black;
    border-radius: 3px;
    margin-top:10%;
    margin-left:auto;
 	margin-right:auto;
}

#invoiceNumberText {
    width: 240px;
    height: 30px;
    padding-left: 10px;
    font-size: 16px;
    color: black;
    border: 1px solid black;
    border-radius: 3px;
    margin-left:auto;
 	margin-right:auto;
}

#tekbeCompnayName, #invoiceNumber{
  color:black;
  font-weight: bold;
  margin-left:auto;
  margin-right:auto;
  font-size: 16px;
}

#myButton1 {
  margin-left:30%;
  background: #FFF;
  background-image: -webkit-linear-gradient(top, #FFF, #FFF);
  background-image: -moz-linear-gradient(top, #FFF, #FFF);
  background-image: -ms-linear-gradient(top, #FFF, #FFF);
  background-image: -o-linear-gradient(top, #FFF, #FFF);
  background-image: linear-gradient(to bottom, #FFF, #FFF);
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0px;
  color: black;
  font-size: 16px;
  padding: 5px 5px 5px 5px;
  text-decoration: none;
}

table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid black;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color:#f3f6f7;
}

</style>
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>

<script>

$(document).ready(function(){
	var myKey = "OVPc0EILBoWsYekipkJdaQ"; // sweet tracker에서 발급받은 자신의 키 넣는다.

		// 택배사 목록 조회 company-api
        $.ajax({
            type:"GET",
            dataType : "json",
            url:"https://info.sweettracker.co.kr/api/v1/companylist?t_key="+myKey,
            success:function(data){

            		// 방법 1. JSON.parse 이용하기
            		var parseData = JSON.parse(JSON.stringify(data));

            		// 방법 2. Json으로 가져온 데이터에 Array로 바로 접근하기
            		var CompanyArray = data.Company; // Json Array에 접근하기 위해 Array명 Company 입력

            		var myData="";
            		$.each(CompanyArray,function(key,value) {
	            			myData += ('<option value='+value.Code+'>'+value.Name + '</option>');
            		});
            		$("#tekbeCompnayList").html(myData);
            }
        });

		// 배송정보와 배송추적 tracking-api
        $("#myButton1").click(function() {
        	var t_code = $('#tekbeCompnayList option:selected').attr('value');
        	var t_invoice = $('#invoiceNumberText').val();
            $.ajax({
                type:"GET",
                dataType : "json",
                url:"http://info.sweettracker.co.kr/api/v1/trackingInfo?t_key="+myKey+"&t_code="+t_code+"&t_invoice="+t_invoice,
                success:function(data){
                	var myInvoiceData = "";
                	if(data.status == false){
                		myInvoiceData += ('<p>'+data.msg+'<p>');
                	}else{
	            		myInvoiceData += ('<tr>');
	            		myInvoiceData += ('<th>'+"보내는사람"+'</td>');
	            		myInvoiceData += ('<th>'+data.senderName+'</td>');
	            		myInvoiceData += ('</tr>');
	            		myInvoiceData += ('<tr>');
	            		myInvoiceData += ('<th>'+"제품정보"+'</td>');
	            		myInvoiceData += ('<th>'+data.itemName+'</td>');
	            		myInvoiceData += ('</tr>');
	            		myInvoiceData += ('<tr>');
	            		myInvoiceData += ('<th>'+"송장번호"+'</td>');
	            		myInvoiceData += ('<th>'+data.invoiceNo+'</td>');
	            		myInvoiceData += ('</tr>');
	            		myInvoiceData += ('<tr>');
	            		myInvoiceData += ('<th>'+"송장번호"+'</td>');
	            		myInvoiceData += ('<th>'+data.receiverAddr+'</td>');
	            		myInvoiceData += ('</tr>');
                	}


                	$("#myPtag").html(myInvoiceData)

                	var trackingDetails = data.trackingDetails;


            		var myTracking="";
            		var header ="";
            		header += ('<tr>');
            		header += ('<th>'+"시간"+'</th>');
            		header += ('<th>'+"장소"+'</th>');
            		header += ('<th>'+"유형"+'</th>');
            		header += ('<th>'+"전화번호"+'</th>');
        			header += ('</tr>');

            		$.each(trackingDetails,function(key,value) {
	            		myTracking += ('<tr>');
            			myTracking += ('<td>'+value.timeString+'</td>');
            			myTracking += ('<td>'+value.where+'</td>');
            			myTracking += ('<td>'+value.kind+'</td>');
            			myTracking += ('<td>'+value.telno+'</td>');
	            		myTracking += ('</tr>');
            		});

            		$("#myPtag2").html(header+myTracking);

                }
            });
        });

});


</script>
<body>
<div class="deli_div" style="height:100px;"><img src="<%=request.getContextPath()%>/images/delivery-truck1.png" style="float:left; margin-top:10px;" id="deli_img"></div>
<br><br><div class="div_d"style="width:100%;" align="center"><br><h3>배송 조회</h3>
<span id="tekbeCompnayName" size=20>택배회사명: </span>
<select id="tekbeCompnayList" name="tekbeCompnayList"></select><br/><br/>
<span id="invoiceNumber">운송장번호: </span>
<input type="text" id="invoiceNumberText" name="invoiceNumberText"><br/><br/>
<button id="myButton1">택배 조회하기 </button></div>
<br/>
<br/>
<div>
	<table id="myPtag"></table>
</div>
<br/>
<div>
	<table id="myPtag2"></table>
</div>
</body>
</html>