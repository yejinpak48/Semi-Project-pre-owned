<%@page import="java.text.DecimalFormat"%>
<%@page import="com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo"%>
<%@page import="com.kh.bvengers.user.myPage.model.vo.myPage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   ArrayList<myPage> rList = (ArrayList<myPage>)request.getAttribute("rList");
   MyPagePageInfo pi = (MyPagePageInfo)request.getAttribute("pi");
   int listCount = pi.getListCount();
   int currentPage = pi.getCurrentPage();
   int maxPage = pi.getMaxPage();
   int startPage = pi.getStartPage();
   int endPage = pi.getEndPage();

   ArrayList<myPage> cList = (ArrayList<myPage>)request.getAttribute("cList");
   MyPagePageInfo pi1 = (MyPagePageInfo)request.getAttribute("pi1");

   int listCount1 = pi1.getListCount();
   int currentPage1 = pi1.getCurrentPage();
   int maxPage1 = pi1.getMaxPage();
   int startPage1 = pi1.getStartPage();
   int endPage1 = pi1.getEndPage();

   DecimalFormat dc = new DecimalFormat("###,###,###,###");


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

$(document).ready(function() {

    var now = new Date();
    var firstDate, lastDate;

    firstDate = new Date(now.getFullYear(),now.getMonth(),1);
    lastDate = new Date(now.getFullYear(),now.getMonth()+1,0);

    $('#startdate').val($.datepicker.formatDate('yy-mm-dd', firstDate));
    $('#enddate').val($.datepicker.formatDate('yy-mm-dd', lastDate));

    // Datepicker
    $(".datepicker").datepicker({
        dateFormat : 'yy-mm-dd',
//         buttonImage : '/images/datepicker.png',
        buttonImageOnly : true,
        changeMonth : true, // 월선택 select box 표시 (기본은 false)
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        changeYear : true, // 년선택 selectbox 표시 (기본은 false)
        showButtonPanel : true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
        currentText: '오늘 날짜',
        closeText: '닫기',
        onClose : function ( selectedDate ) {

            var eleId = $(this).attr("id");
            var optionName = "";

            if(eleId.indexOf("StartDate") > 0) {
                eleId = eleId.replace("StartDate", "EndDate");
                optionName = "minDate";
            } else {
                eleId = eleId.replace("EndDate", "StartDate");
                optionName = "maxDate";
            }

            $("#"+eleId).datepicker( "option", optionName, selectedDate );
            $(".searchDate").find(".chkbox2").removeClass("on");
        }
    });

        $('#searchStartDate').datepicker("option","onClose", function( selectedDate ) {
            // 시작일 datepicker가 닫힐때
            // 종료일의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
            $("#searchEndDate").datepicker( "option", "minDate", selectedDate );
            $(".searchDate").find(".chkbox2").removeClass("on");
        });


        //종료일.
        $('#searchEndDate').datepicker("option","onClose", function( selectedDate ) {
            // 종료일 datepicker가 닫힐때
            // 시작일의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
            $("#searchStartDate").datepicker( "option", "maxDate", selectedDate );
            $(".searchDate").find(".chkbox2").removeClass("on");
        });

        $('#searchEndDate').datepicker("option","onSelect", function( selectedDate ) {
            // 종료일 datepicker가 닫힐때
            // 시작일의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
            $("#searchStartDate").datepicker( "option", "maxDate", selectedDate );
            $(".searchDate").find(".chkbox2").removeClass("on");

          /*   var f = document.frm;
            $(".adminbuildenergyno").remove(); //name값이 다수여서 지워여야함
            f.submit(); */
        });


/*         $(".dateclick").dateclick();    // DateClick
        $(".searchDate").schDate();        // searchDate */

    });

    // Search Date

    $.fn.schDate = function() {
       var $obj = $(this);
       /* var $chk = $obj.find("input[type=radio]"); */
       $("#dateType3").click(function(){
       var d1 = $(this).parent().siblings().eq(0).text();
    	  $("#dateType4").parent(".chkbox2").removeClass("on");
    	  $("#dateType5").parent(".chkbox2").removeClass("on");
    	  $("#dateType3").parent(".chkbox2").addClass("on");
       });
       $("#dateType4").click(function(){
     	  $("#dateType3").parent(".chkbox2").removeClass("on");
     	  $("#dateType5").parent(".chkbox2").removeClass("on");
     	  $("#dateType4").parent(".chkbox2").addClass("on");
        });
       $("#dateType5").click(function(){
      	  $("#dateType3").parent(".chkbox2").removeClass("on");
      	  $("#dateType4").parent(".chkbox2").removeClass("on");
      	  $("#dateType5").parent(".chkbox2").addClass("on");
         });

       /* $chk.click(function() {
           $('input:not(:checked)').parent(".chkbox2").removeClass("on");
           $('input:checked').parent(".chkbox2").addClass("on");
       }); */
    }

    // DateClick
    jQuery.fn.dateclick = function() {
       var $obj = $(this);
       $obj.click(function() {
           $(this).parent().find("input").focus();
       });
    }

    function setSearchDate(start) {

       var num = start.substring(0, 1);
       var str = start.substring(1, 2);

       var today = new Date();

       //var year = today.getFullYear();
       //var month = today.getMonth() + 1;
       //var day = today.getDate();

       var endDate = $.datepicker.formatDate('yy-mm-dd', today);
       $('#searchEndDate').val(endDate);

       if(str == 'd'){
           today.setDate(today.getDate() - num);
       }else if (str == 'w'){
           today.setDate(today.getDate() - (num*7));
       }else if (str == 'm'){
           today.setMonth(today.getMonth() - num);
           today.setDate(today.getDate() + 1);
       }

       var startDate = $.datepicker.formatDate('yy-mm-dd', today);
       $('#searchStartDate').val(startDate);

       // 종료일은 시작일 이전 날짜 선택하지 못하도록 비활성화
       $("#searchEndDate").datepicker("option", "minDate", startDate);

       // 시작일은 종료일 이후 날짜 선택하지 못하도록 비활성화
       $("#searchStartDate").datepicker("option", "maxDate", endDate);

       /* var f = document.form;
       f.method = "post";
       $(".adminbuildenergyno").remove(); //name값이 다수여서 지워여야함 */

    };


    $(document).ready(function() {

        var now1 = new Date();
        var firstDate1, lastDate;

        firstDate1 = new Date(now1.getFullYear(),now1.getMonth(),1);
        lastDate1 = new Date(now1.getFullYear(),now1.getMonth()+1,0);

        $('#startdate1').val($.datepicker.formatDate('yy-mm-dd', firstDate1));
        $('#enddate1').val($.datepicker.formatDate('yy-mm-dd', lastDate1));

        // Datepicker
        $(".datepicker").datepicker({
            dateFormat : 'yy-mm-dd',
//             buttonImage : '/images/datepicker.png',
            buttonImageOnly : true,
            changeMonth : true, // 월선택 select box 표시 (기본은 false)
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            changeYear : true, // 년선택 selectbox 표시 (기본은 false)
            showButtonPanel : true, // 하단 today, done  버튼기능 추가 표시 (기본은 false)
            currentText: '오늘 날짜',
            closeText: '닫기',
            onClose : function ( selectedDate ) {

                var eleId1 = $(this).attr("id");
                var optionName1 = "";

                if(eleId1.indexOf("StartDate") > 0) {
                    eleId1 = eleId1.replace("StartDate1", "EndDate1");
                    optionName1 = "minDate1";
                } else {
                    eleId1 = eleId1.replace("EndDate1", "StartDate1");
                    optionName1 = "maxDate1";
                }

                $("#"+eleId1).datepicker( "option", optionName1, selectedDate1 );
                $(".searchDate1").find(".chkbox21").removeClass("on");
            }
        });

            $('#searchStartDate1').datepicker("option","onClose", function( selectedDate1 ) {
                // 시작일 datepicker가 닫힐때
                // 종료일의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                $("#searchEndDate1").datepicker( "option", "minDate1", selectedDate1 );
                $(".searchDate1").find(".chkbox21").removeClass("on");
            });


            //종료일.
            $('#searchEndDate1').datepicker("option","onClose", function( selectedDate1 ) {
                // 종료일 datepicker가 닫힐때
                // 시작일의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
                $("#searchStartDate1").datepicker( "option", "maxDate1", selectedDate1 );
                $(".searchDate1").find(".chkbox2").removeClass("on");
            });

            $('#searchEndDate1').datepicker("option","onSelect", function( selectedDate1 ) {
                // 종료일 datepicker가 닫힐때
                // 시작일의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정
                $("#searchStartDate1").datepicker( "option", "maxDate1", selectedDate1 );
                $(".searchDate1").find(".chkbox21").removeClass("on");

              /*   var f = document.frm;
                $(".adminbuildenergyno").remove(); //name값이 다수여서 지워여야함
                f.submit(); */
            });


    /*         $(".dateclick").dateclick();    // DateClick
            $(".searchDate").schDate();        // searchDate */

        });


        function setSearchDate1(start1) {

           var num1 = start1.substring(0, 1);
           var str1 = start1.substring(1, 2);

           var today1 = new Date();

           //var year = today.getFullYear();
           //var month = today.getMonth() + 1;
           //var day = today.getDate();

           var endDate1 = $.datepicker.formatDate('yy-mm-dd', today1);
           $('#searchEndDate1').val(endDate1);

           if(str1 == 'd'){
               today1.setDate(today1.getDate() - num1);
           }else if (str1 == 'w'){
               today1.setDate(today1.getDate() - (num1*7));
           }else if (str1 == 'm'){
               today1.setMonth(today1.getMonth() - num1);
               today1.setDate(today1.getDate() + 1);
           }

           var startDate1 = $.datepicker.formatDate('yy-mm-dd', today1);
           $('#searchStartDate1').val(startDate1);

           // 종료일은 시작일 이전 날짜 선택하지 못하도록 비활성화
           $("#searchEndDate1").datepicker("option", "minDate1", startDate1);

           // 시작일은 종료일 이후 날짜 선택하지 못하도록 비활성화
           $("#searchStartDate1").datepicker("option", "maxDate1", endDate1);

           /* var f = document.form;
           f.method = "post";
           $(".adminbuildenergyno").remove(); //name값이 다수여서 지워여야함 */

        };



</script>
<style>

#pagingArea {
	width:60%;
	margin-top:25px;
	margin-left:auto;
	margin-right:auto;
}

.pagingArea1 > button {
	background:#FFF;
	border:none;
	color:#ffb3b3;
	font-weight:bold;
	width:2%;

}
.pagingArea1 button:hover{
		color: white;
		background: #ffb3b3;
		border-radius: 10px;
}
.pagingArea2 > button {
	background:#FFF;
	border:none;
	color:#ffb3b3;
	font-weight:bold;
	width:2%;

}
.pagingArea2 button:hover{
		color: white;
		background: #ffb3b3;
		border-radius: 10px;
	}

.td_select {
	width:87%;
}
.searchBox {
   width: 100%;
   height:70%;
   padding: 2%;
   margin: 0 auto;
   text-align: center;
}

.searchdiv {
   width: 100%;
   height:70%;
   padding: 2%;
   margin: 0 auto;
   text-align: center;
}

.th_select {
	width:13%;
	margin-left:auto;
	margin-right:auto;

}

#selectDate {
   background:#FFF;
}

input[type=button] {
   border:1px solid #ccc;
   background:#FFF;
   width:50px;
   border-radius:5px;
}

.th_refund {
	text-align:center;
}


.tt_div {
	width:60%;
	height:100%;
	margin-left:auto;
	margin-right:auto;

}

.t_div {
	width:100%;
	height:70%;
	margin: 0 auto;
	text-align: center;

}


.board1 {
	width: 80%;
	margin: auto;
	align: center;
	
	border-radius:5px;
	border-collapse: collapse;
    line-height: 1.5;
}
 .board1 th {

    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FFF;
}

.board1 td {

    padding: 5px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

.board1 thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    border-bottom: 2px solid #ccc;
}


.board2 {
	width: 80%;
	margin: auto;
	align: center;
	
	border-radius:5px;
	border-collapse: collapse;
    line-height: 1.5;
}
 .board2 th {

    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FFF;
}

.board2 td {

    padding: 5px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

.board2 thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    border-bottom: 2px solid #ccc;
}

.rl {
	text-align:center;
}
.rl2 {
	text-align:center;
}
.pageNo {
	margin: auto;
}

.page-control {
	margin-top:5%;
}
</style>
</head>
<body>
	<header><%@ include file="../hfl/header.jsp"%></header>
	<br>
	<header><%@ include file="../hfl/myPageList.jsp"%></header>
<div class="tt_div">
<div class="searchdiv">
  <table class="searchBox">
                   <!-- <caption style="font-size:20px;">주문조회</caption> -->
     <colgroup>
         <col width="123px">
         <col width="*">
     </colgroup>
     <h3>환불조회</h3>
     <tbody>
         <tr>
             <th class="th_select">기간별 조회</th>
             <td class="td_select">
                <span class="chkbox2">
                       <input type="button" name="dateType" id="dateType3" value="1주일" onclick="setSearchDate('1w')"/>
                       <label for="dateType3"></label>
                   </span>
                   <span class="chkbox2">
                       <input type="button" name="dateType" id="dateType4" value="2주일" onclick="setSearchDate('2w')"/>
                       <label for="dateType4"></label>
                   </span>
                   <span class="chkbox2">
                       <input type="button" name="dateType" id="dateType5" value="1개월" onclick="setSearchDate('1m')"/>
                       <label for="dateType5"></label>
                   </span>
               <span class="dset">
                   <input type="text" class="datepicker inpType" name="startdate" id="searchStartDate" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.startdate }" >               </span>
               <span class="demi">~</span>
               <span class="dset">
                 <input type="text" class="datepicker inpType" name="enddate" id="searchEndDate" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.enddate }" >
               </span>
               <span>
                   	<input type="button" name="selectDate" id="selectDate"  onclick="search1();" value="조회">
                   </span>
                   </td>
               </tr>
           <tbody>
       </table>
</div>

	<br>
		<div align=center>
			<table class = "board1">
				<thead>
				<tr >
					<th class="th_refund">주문번호</th>
					<th class="th_refund">환불신청일</th>
					<th class="th_refund">상품명</th>
					<th class="th_refund">환불금액</th>
					<th class="th_refund">상태</th>
				</tr>
				</thead>
				<tbody>
				<% for(myPage m : rList) {%>
				<tr class="rl">
				<td><%=m.getOno() %></td>
				<td><%=m.getrDate() %></td>
				<td><%=m.getPname() %></td>
				<% String price = dc.format(m.getDtPay());%>
				<td><%=price %>원</td>
				<td><%=m.getRefundStatus() %></td>
			</tr>
			<% } %>
			</tbody>
			</table>
			</div><br>
		<div id="pagingArea1">
		<div class="pagingArea1" align="center">
			<button
				onclick="location.href = '<%=request.getContextPath()%>/refundList.mp?currentPage=1'"><<</button>
			<%
				if (currentPage <= 1) {
			%>
			<button disabled><</button>
			<%
				} else {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/refundList.mp?currentPage=<%=currentPage - 1%>'"><</button>
			<%
				}
			%>
			<%
				for (int p = startPage; p <= endPage; p++) {
					if (currentPage == p) {
			%>
			<button disabled><%=p%></button>
			<%
				} else {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/refundList.mp?currentPage=<%=p%>'"><%=p%></button>
			<%
				}
				}
			%>


			<%
				if (currentPage >= maxPage) {
			%>
			<button disabled>></button>
			<%
				} else {
			%>
			<button
				onclick="location.hreh='<%=request.getContextPath()%>/refundList.mp?currentPage=<%=currentPage + 1%>'">></button>
			<%
				}
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/refundList.mp?currentPage=<%=maxPage%>'">>></button>
			</div>
	</div></div>
	<div class="tt_div">
		<div class="searchdiv">
			<table class="searchBox">
				<!-- <caption style="font-size:20px;">주문조회</caption> -->
				<colgroup>
					<col width="123px">
					<col width="*">
				</colgroup>
				<h3>정산조회</h3>
				<tbody>
					<tr>
						<th class="th_select">기간별 조회</th>
						<td class="td_select"><span class="chkbox21"> <input
								type="button" name="dateType" id="dateType7" value="1주일"
								onclick="setSearchDate1('1w')" /> <label for="dateType7"></label>
						</span> <span class="chkbox21"> <input type="button"
								name="dateType" id="dateType8" value="2주일"
								onclick="setSearchDate1('2w')" /> <label for="dateType8"></label>
						</span> <span class="chkbox21"> <input type="button"
								name="dateType" id="dateType9" value="1개월"
								onclick="setSearchDate1('1m')" /> <label for="dateType9"></label>
						</span> <!-- <div class="clearfix">
               시작일 --> <span class="dset"> <input type="text"
								class="datepicker inpType" name="startdate1"
								id="searchStartDate1" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.startdate1 }">
						</span> <span class="demi">~</span> <!-- 종료일 --> <span class="dset">
								<input type="text" class="datepicker inpType" name="enddate1"
								id="searchEndDate1" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.enddate1 }">
						</span> <span> <input type="button" name="selectDate1"
								id="selectDate1" onclick="search2();" value="조회">
						</span></td>
					</tr>
				<tbody>
			</table>
		</div>

		<br>
		<div align=center>
			<table class="board2">
			<thead>
				<tr>
					<th class="th_refund">정산번호</th>
					<th class="th_refund">정산날짜</th>
					<th class="th_refund">정산상태</th>
					<th class="th_refund">정산금액</th>
					<th class="th_refund">회원 정보</th>
				</tr>
				</thead>
				<tbody>
				<% for(myPage m : cList) { %>
				<tr class="rl2">
					<td><%=m.getAno() %></td>
					<td><%=m.getaDate() %></td>
					<td><%=m.getaStatus() %></td>
					<% String price = dc.format(m.getaPrice());%>
					<td><%=price %>원</td>
					<td>은행명 : <%=m.getbCode()%><br> 계좌번호 : <%=m.getAccountNo() %><br>
						예금주 : <%=m.getAname() %></td>
				</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
<div id="pagingArea">
	<div class = "pagingArea2" align ="center" >
      <button onclick = "location.href = '<%=request.getContextPath()%>/refundList.mp?currentPage1=1'"><<</button>
      <%if(currentPage1 <= 1) {%>
      <button disabled><</button>
      <%} else{%>
   <button onclick = "location.href='<%=request.getContextPath()%>/refundList.mp?currentPage1=<%=currentPage1-1%>'"><</button>
      <%}%>
         <%for (int p = startPage1; p <= endPage1; p++) {
            if(currentPage1 == p){
         %>
            <button disabled><%= p %></button>
         <%} else{ %>
               <button onclick = "location.href='<%=request.getContextPath()%>/refundList.mp?currentPage1=<%=p%>'"><%= p %></button>
         <% }
         }
         %>


         <%if(currentPage1 >= maxPage1){ %>
         <button disabled>></button>
         <%}else{ %>
         <button onclick ="location.hreh='<%=request.getContextPath()%>/refundList.mp?currentPage1=<%=currentPage1 + 1%>'">></button>
         <%} %>
         <button onclick = "location.href='<%=request.getContextPath()%>/refundList.mp?currentPage1=<%=maxPage1%>'">>></button>

      </div></div>

	<footer><%@ include file="../hfl/footer.jsp"%></footer>

	<script>
    function search1(){
    	$(function(){
    		var start =$("input[id='searchStartDate']").val();
    		var end = $("input[id='searchEndDate']").val();
    		$.ajax({
    			url:"refundDate.mp",
    			type:"post",
    			data:{"start":start,"end":end},
    			success:function(data){
    				var $boardTbody1 = $(".board1 tbody");
    				var $pagingDiv1 = $(".pagingArea1");
    				$boardTbody1.html("");
    				$pagingDiv1.html("");
    				for(var i = 0; i < data["rList"].length; i++){
    					var $tr = $("<tr class='rl'>");
    					var $ono = $("<td>").text(data["rList"][i].ono);
    					var $rDate = $("<td>").text(data["rList"][i].rDate);
    					var $pname = $("<td>").text(data["rList"][i].pname);
    					var $dtPay = $("<td>").text(data["rList"][i].dtPay);
    					var $rStatus = $("<td>").text(data["rList"][i].refundStatus);
						var $price = $("<td>").text(numeral(data["rList"][i].dtPay).format('0,0')+"원");

    					$tr.append($ono);
    					$tr.append($rDate);
    					$tr.append($pname);
    					$tr.append($price);
    					$tr.append($rStatus);
    					$boardTbody1.append($tr);
    				}
    				
    				var currentPage = data["pi"].currentPage;
    				var endPage = data["pi"].endPage;
    				var limit = data["pi"].limit;
    				var listCount = data["pi"].listCount;
    				var maxPage = data["pi"].maxPage;
    				var startPage = data["pi"].startPage;

    				var $pagingDiv2 =$("<div class='pagingArea1' align='center'>");
    				var $firstBtn = $("<button>").text('<<');
    				var $preBtn = $("<button>").text('<');
    				var $numBtn = $("<button>");
    				var $nextBtn =$("<button>").text('>');
    				var $lastBtn =$("<button>").text('>>');


    				$firstBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=1"');
    				if(currentPage <= 1){
    					$preBtn.attr('disabled',true);
    				}else{
    					$preBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=currentPage - 1"');
    				}
    				for(var i = startPage ; i <= endPage ;i++){
    					if(currentPage == i){
    						$numBtn.attr('disabled',true);
    					}else{
    						$numBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=i"');
    					}
    					$numBtn.text(i);

    				}
    				if(currentPage >= maxPage){
    					$nextBtn.attr('disabled',true);
    				}else{
    					$nextBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=currentPage + 1 "');
    				}
    				$lastBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=maxPage"');

    				$pagingDiv2.append($firstBtn);
    				$pagingDiv2.append($preBtn);
    				$pagingDiv2.append($numBtn);
    				$pagingDiv2.append($nextBtn);
    				$pagingDiv2.append($lastBtn);

    				$pagingDiv1.append($pagingDiv2);
    			},
    		});
    	});
    }
	function newPage1(page){
		var start =$("input[id='searchStartDate']").val();
		var end = $("input[id='searchEndDate']").val();
		var currentPage = page;
		$.ajax({
			url:"refundDate.mp",
			type:"post",
			data:{"start":start,"end":end,"currentPage":page},
			success:function(data){
				var $boardTbody1 = $(".board1 tbody");
				var $pagingDiv1 =$(".pagingArea1");
				$boardTbody1.html("");
				$pagingDiv1.html("");
				for(var i = 0; i < data["rList"].length; i++){
					var $tr = $("<tr class='rl'>");
					var $ono = $("<td>").text(data["rList"][i].ono);
					var $rDate = $("<td>").text(data["rList"][i].rDate);
					var $pname = $("<td>").text(data["rList"][i].pname);
					var $dtPay = $("<td>").text(data["rList"][i].dtPay);
					var $rStatus = $("<td>").text(data["rList"][i].refundStatus);
					var $price = $("<td>").text(numeral(data["rList"][i].dtPay).format('0,0')+"원");

					$tr.append($ono);
					$tr.append($rDate);
					$tr.append($pname);
					$tr.append($price);
					$tr.append($rStatus);
					$boardTbody1.append($tr);
				}
				
				var currentPage = data["pi"].currentPage;
				var endPage = data["pi"].endPage;
				var limit = data["pi"].limit;
				var listCount = data["pi"].listCount;
				var maxPage = data["pi"].maxPage;
				var startPage = data["pi"].startPage;
				
				var $pagingDiv2 =$("<div class='pagingArea1' align='center'>");
				var $firstBtn = $("<button>").text('<<');
				var $preBtn = $("<button>").text('<');
				
				var $nextBtn =$("<button>").text('>');
				var $lastBtn =$("<button>").text('>>');
				
				
				$pagingDiv2.append($firstBtn);
				$pagingDiv2.append($preBtn);
				$firstBtn.attr('onclick',"newPage1("+currentPage+")");						
				if(currentPage <= 1){
					$preBtn.attr('disabled',true);							
				}else{
					$preBtn.attr('onclick',"newPage1("+(currentPage-1)+")");						
				}
				for(var i = startPage ; i <= endPage ;i++){		
					var $numBtn = $("<button>");
					if(currentPage == i){
						$numBtn.attr('disabled',true);																
					}else{
						$numBtn.attr('onclick',"newPage1("+i+")");																
					}
					$numBtn.text(i);
					$pagingDiv2.append($numBtn);
				}
				if(currentPage >= maxPage){
					$nextBtn.attr('disabled',true);							
				}else{
					$nextBtn.attr('onclick','newPage1('+(currentPage+1)+')');							
				}
				$lastBtn.attr('onclick','newPage1('+maxPage+')');						
				
				$pagingDiv2.append($nextBtn);
				$pagingDiv2.append($lastBtn);
				
				$pagingDiv1.append($pagingDiv2);							
				}		
		});
	}

    function search2(){
    	$(function(){
    		var start =$("input[id='searchStartDate1']").val();
    		var end = $("input[id='searchEndDate1']").val();
    		$.ajax({
    			url:"calculateDate.mp",
    			type:"post",
    			data:{"start":start,"end":end},
    			success:function(data){
    				var $boardTbody2 = $(".board2 tbody");
    				var $pagingDiv2 =$(".pagingArea2");
    				$boardTbody2.html("");
    				$pagingDiv2.html("");
    				for(var i = 0; i < data["cList"].length; i++){
    					var $tr = $("<tr class='rl2'>");
    					var $ano = $("<td>").text(data["cList"][i].ano);
    					var $aDate = $("<td>").text(data["cList"][i].aDate);
    					var $aStatus = $("<td>").text(data["cList"][i].aStatus);
						var $price = $("<td>").text(numeral(data["cList"][i].aPrice).format('0,0')+"원");
    					var $br = $("</br>");
    					var $bCode = $("<td>").html("은행명 : "+data["cList"][i].bCode+"<br/>"+"계좌번호 : "+data["cList"][i].accountNo+"<br/>"+"예금주 : "+data["cList"][i].aname);


    					$tr.append($ano);
    					$tr.append($aDate);
    					$tr.append($aStatus);
    					$tr.append($price);
    					$tr.append($bCode);
    					$boardTbody2.append($tr);
    				}
    				var currentPage = data["pi"].currentPage;
    				var endPage = data["pi"].endPage;
    				var limit = data["pi"].limit;
    				var listCount = data["pi"].listCount;
    				var maxPage = data["pi"].maxPage;
    				var startPage = data["pi"].startPage;

    				var $pagingDiv2 =$("<div class='pagingArea2' align='center'>");
    				var $firstBtn = $("<button>").text('<<');
    				var $preBtn = $("<button>").text('<');
    				var $numBtn = $("<button>");
    				var $nextBtn =$("<button>").text('>');
    				var $lastBtn =$("<button>").text('>>');


    				$firstBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=1"');
    				if(currentPage <= 1){
    					$preBtn.attr('disabled',true);
    				}else{
    					$preBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=currentPage - 1"');
    				}
    				for(var i = startPage ; i <= endPage ;i++){
    					if(currentPage == i){
    						$numBtn.attr('disabled',true);
    					}else{
    						$numBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=i"');
    					}
    					$numBtn.text(i);

    				}
    				if(currentPage >= maxPage){
    					$nextBtn.attr('disabled',true);
    				}else{
    					$nextBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=currentPage + 1 "');
    				}
    				$lastBtn.attr('onclick','location.href="<%=request.getContextPath()%>/refundList.mp?currentPage=maxPage"');

    				$pagingDiv2.append($firstBtn);
    				$pagingDiv2.append($preBtn);
    				$pagingDiv2.append($numBtn);
    				$pagingDiv2.append($nextBtn);
    				$pagingDiv2.append($lastBtn);

    				$pagingDiv1.append($pagingDiv2);
    			},
    		});
    	});
    }
    

	function newPage2(page){
		var start =$("input[id='searchStartDate1']").val();
		var end = $("input[id='searchEndDate1']").val();
		$.ajax({
			url:"calculateDate.mp",
			type:"post",
			data:{"start":start,"end":end,"currentPage":page},
			success:function(data){
				var $boardTbody2 = $(".board2 tbody");
				var $pagingDiv2 =$(".pagingArea2");
				$boardTbody2.html("");
				$pagingDiv2.html("");
				for(var i = 0; i < data["cList"].length; i++){
					var $tr = $("<tr class='rl2'>");
					var $ano = $("<td>").text(data["cList"][i].ano);
					var $aDate = $("<td>").text(data["cList"][i].aDate);
					var $aStatus = $("<td>").text(data["cList"][i].aStatus);
					var $price = $("<td>").text(numeral(data["cList"][i].aPrice).format('0,0')+"원");
					var $br = $("</br>");
					var $bCode = $("<td>").html("은행명 : "+data["cList"][i].bCode+"<br/>"+"계좌번호 : "+data["cList"][i].accountNo+"<br/>"+"예금주 : "+data["cList"][i].aname);


					$tr.append($ano);
					$tr.append($aDate);
					$tr.append($aStatus);
					$tr.append($price);
					$tr.append($bCode);
					$boardTbody2.append($tr);
				}
				
				var currentPage = data["pi"].currentPage;
				var endPage = data["pi"].endPage;
				var limit = data["pi"].limit;
				var listCount = data["pi"].listCount;
				var maxPage = data["pi"].maxPage;
				var startPage = data["pi"].startPage;
				
				var $pagingDiv2 =$("<div class='pagingArea2' align='center'>");
				var $firstBtn = $("<button>").text('<<');
				var $preBtn = $("<button>").text('<');
				
				var $nextBtn =$("<button>").text('>');
				var $lastBtn =$("<button>").text('>>');
				
				
				$pagingDiv2.append($firstBtn);
				$pagingDiv2.append($preBtn);
				$firstBtn.attr('onclick',"newPage2("+currentPage+")");						
				if(currentPage <= 1){
					$preBtn.attr('disabled',true);							
				}else{
					$preBtn.attr('onclick',"newPage2("+(currentPage-1)+")");						
				}
				for(var i = startPage ; i <= endPage ;i++){		
					var $numBtn = $("<button>");
					if(currentPage == i){
						$numBtn.attr('disabled',true);																
					}else{
						$numBtn.attr('onclick',"newPage2("+i+")");																
					}
					$numBtn.text(i);
					$pagingDiv2.append($numBtn);
				}
				if(currentPage >= maxPage){
					$nextBtn.attr('disabled',true);							
				}else{
					$nextBtn.attr('onclick','newPage2('+(currentPage+1)+')');							
				}
				$lastBtn.attr('onclick','newPage2('+maxPage+')');						
				
				$pagingDiv2.append($nextBtn);
				$pagingDiv2.append($lastBtn);
				
				$pagingDiv1.append($pagingDiv2);							
				}		
		});
	}
</script>
</body>
</html>