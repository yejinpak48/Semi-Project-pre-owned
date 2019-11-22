<%@ page import="com.kh.bvengers.user.myPage.model.vo.MyPagePageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.kh.bvengers.user.myPage.model.vo.*"%>
<%

	ArrayList<myPage> olList = (ArrayList<myPage>) request.getAttribute("olList");
	ArrayList<myPage> dateList = (ArrayList<myPage>) request.getAttribute("dateList");
	MyPagePageInfo pi  = (MyPagePageInfo) request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

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


</script>
<style>


.dateBoard {
	width: 80%;
	margin: auto;
	align: center;
	
	border-radius:5px;
	border-collapse: collapse;
    line-height: 1.5;
}
 .dateBoard th {

    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FFF;
}

.dateBoard td {

    padding: 5px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

.dateBoard thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    border-bottom: 2px solid #ccc;
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

.tt_div {
	width:60%;
	margin-left:auto;
	margin-right:auto;

}

.t_div {
	width:100%;
	height:70%;
	margin: 0 auto;
	text-align: center;

}

#pagingArea {
	width:60%;
	margin-top:25px;
	margin-left:auto;
	margin-right:auto;
}

.pagingArea > button {
	background:#FFF;
	border:none;
	color:#ffb3b3;
	font-weight:bold;
	width:20px;
	
}

.btn_od {
	border:1px solid #ccc;
	background:#FFF;
	width:60px;
}

.th_orderLook {
	text-align:center;
}


.dateBoard {
	width: 80%;
	margin: auto;
	align: center;
	border-spacing: 10px;
}


tr > th, tr> td{
	width: 25%;
	margin: auto;
	text-align:center;
	border-spacing: 10px;
}


</style>
</head>
<body>
	<header><%@ include file="../hfl/header.jsp"%></header>
	<header><%@ include file="../hfl/myPageList.jsp"%></header>
<div class="tt_div">
<div class="searchdiv">
  <table class="searchBox">
                   <!-- <caption style="font-size:20px;">주문조회</caption> -->
     <colgroup>
         <col width="123px">
         <col width="*">
     </colgroup>
     <h3>주문조회</h3><br>
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


           <!-- <div class="clearfix">
               시작일 -->
               <span class="dset">
                   <input type="text" class="datepicker inpType" name="startdate" id="searchStartDate" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.startdate }" >               </span>
               <span class="demi">~</span>
               <!-- 종료일 -->
               <span class="dset">
                 <input type="text" class="datepicker inpType" name="enddate" id="searchEndDate" style="border:1px solid #ccc;" value="${adminBuildEnergyVo.enddate }" >
               </span>
               <span>
                   	<input type="button" name="selectDate" id="selectDate"  onclick="search();" value="조회">
                   </span>
                   </td>
               </tr>
           <tbody>
       </table>
</div>

		<div class="t_div">
			<br />
			<table align = "center" class = "dateBoard">
				<thead>
				<tr>
					<th class="th_orderLook">주문번호</th>
					<th class="th_orderLook">주문일자</th>
					<th class="th_orderLook">상품정보</th>
					<th class="th_orderLook">주문상태</th>
				</tr>
				</thead>
				<tbody>
				<% for(myPage p : olList){%>
				<tr class="od">
					<td><%=p.getOno() %></td>
					<td><%=p.getoDate() %></td>
					<td><%=p.getPname()%><br><button class="btn_od">주문상세</button></td>
					<td><%=p.getPstatus() %></td>
				</tr>
				<%}%>
				</tbody>
			</table>
		</div></div>
<%-- 페이징처리 --%>
<div id="pagingArea">
		<div class="pagingArea" align="center">
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/orderLook.mp?currentPage=1'"><<</button>

			<% if(currentPage <= 1){ %>
			<button disabled><</button>
			<% }else { %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/orderLook.mp?currentPage=<%=currentPage - 1%>'"><</button>
			<% } %>

			<% for(int p = startPage; p <= endPage; p++){
				if(currentPage == p){ %>
					<button class="btn_paging" disabled><%= p %></button>
			<% } else { %>
					<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/orderLook.mp?currentPage=<%=p%>'"><%= p %></button>
			<%
				}
			   }
			%>

			<% if(currentPage >= maxPage){ %>
			<button class="btn_paging" disabled>></button>
			<% }else{ %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/orderLook.mp?currentPage=<%=currentPage + 1 %>'">></button>
			<% } %>
			<button class="btn_paging" onclick="location.href='<%=request.getContextPath()%>/orderLook.mp?currentPage=<%=maxPage%>'">>></button>
		</div>
		</div>
	<br>
	<footer><%@ include file="../hfl/footer.jsp"%></footer>

<script>

    $(".btn_od").click(function(){
    	var ono = $(this).parent().siblings().eq(0).text();
     	location.href='<%=request.getContextPath()%>/orderDetail.mp?ono='+ono;
     });


    function search(){
    	$(function(){
    		var start =$("input[id='searchStartDate']").val();
    		var end = $("input[id='searchEndDate']").val();
    		$.ajax({
    			url:"orderDate.mp",
    			data:{"start":start,"end":end},
    			type:"post",
    			success:function(data){
    				var $dateTbody = $(".dateBoard tbody");
    				var $pagingDiv1 = $("#pagingArea div");
    				$dateTbody.html(""); 
    				$pagingDiv1.html("");
    				for(var i = 0; i < data["dateList"].length; i++){
    					var $tr = $("<tr class='od'>");
    					var $ono = $("<td>").text(data["dateList"][i].ono);
    					var $oDate = $("<td>").text(data["dateList"][i].oDate);
    					var $pname = $("<td>").text(data["dateList"][i].pname);
    					var $br = $("</br>");
    					var $btn_od = $("<button class='btn_od'>").text("주문상세");
    					var $pstatus = $("<td>").text(data["dateList"][i].pstatus);
    					var ono1 = data["dateList"][i].ono;
    					 $btn_od.attr("onclick",'searchDate('+ono1+')');
    					$tr.append($ono);
    					$tr.append($oDate);
    					$tr.append($pname);
    					$pname.append($br);
    					$pname.append($btn_od);
    					$tr.append($pstatus);
    					$dateTbody.append($tr);
    				}

    				var currentPage = data["pi"].currentPage;
					var endPage = data["pi"].endPage;
					var limit = data["pi"].limit;
					var listCount = data["pi"].listCount;
					var maxPage = data["pi"].maxPage;
					var startPage = data["pi"].startPage;
					
					var $pagingDiv2 =$("<div class='pagingArea' align='center'>");
					var $firstBtn = $("<button>").text('<<');
					var $preBtn = $("<button>").text('<');
					
					var $nextBtn =$("<button>").text('>');
					var $lastBtn =$("<button>").text('>>');
    				

					$pagingDiv2.append($firstBtn);
					$pagingDiv2.append($preBtn);
					$firstBtn.attr('onclick',"newPage("+currentPage+")");						
					if(currentPage <= 1){
						$preBtn.attr('disabled',true);							
					}else{
						$preBtn.attr('onclick',"newPage("+(currentPage-1)+")");						
					}
					for(var i = startPage; i <= endPage; i++){		
						var $numBtn = $("<button>");
						if(currentPage == i){
							$numBtn.attr('disabled',true);																
						}else{
							$numBtn.attr('onclick',"newPage("+i+")");																
						}
						$numBtn.text(i);
						$pagingDiv2.append($numBtn);
					}
					if(currentPage >= maxPage){
						$nextBtn.attr('disabled',true);							
					}else{
						$nextBtn.attr('onclick','newPage('+(currentPage+1)+')');							
					}
					$lastBtn.attr('onclick','newPage('+maxPage+')');						
					
					$pagingDiv2.append($nextBtn);
					$pagingDiv2.append($lastBtn);
					
					$pagingDiv1.append($pagingDiv2);							
					}		
			});
		});

    }
    function searchDate(ono){
    	location.href='<%=request.getContextPath()%>/orderDetail.mp?ono='+ono;
    }
    

	function newPage(page){
		var start =$("input[id='searchStartDate']").val();
		var end = $("input[id='searchEndDate']").val();
		var currentPage = page;
		$.ajax({
			url:"orderDate.mp",
			data:{"start":start,"end":end,"currentPage":currentPage},
			type:"post",
			success:function(data){
				var $dateTbody = $(".dateBoard tbody");
				var $pagingDiv1 = $("#pagingArea div");
				$dateTbody.html(""); 
				$pagingDiv1.html("");
				for(var i = 0; i < data["dateList"].length; i++){
					var $tr = $("<tr class='od'>");
					var $ono = $("<td>").text(data["dateList"][i].ono);							
					var $oDate = $("<td>").text(data["dateList"][i].oDate);
					var $pname = $("<td>").text(data["dateList"][i].pname);
					var $br = $("</br>");
					var $btn_od = $("<button class='btn_od'>").text("주문상세");
					var $pstatus = $("<td>").text(data["dateList"][i].pstatus);
					var ono1 = data["dateList"][i].ono;    					
					 $btn_od.attr("onclick",'searchDate('+ono1+')');
					$tr.append($ono);
					$tr.append($oDate);
					$tr.append($pname);
					$pname.append($br);
					$pname.append($btn_od);
					$tr.append($pstatus);						
					$dateTbody.append($tr);
				}
				
				var currentPage = data["pi"].currentPage;
				var endPage = data["pi"].endPage;
				var limit = data["pi"].limit;
				var listCount = data["pi"].listCount;
				var maxPage = data["pi"].maxPage;
				var startPage = data["pi"].startPage;
				
				var $pagingDiv2 =$("<div class='pagingArea' align='center'>");
				var $firstBtn = $("<button>").text('<<');
				var $preBtn = $("<button>").text('<');
				
				var $nextBtn =$("<button>").text('>');
				var $lastBtn =$("<button>").text('>>');
				
				
				$pagingDiv2.append($firstBtn);
				$pagingDiv2.append($preBtn);
				$firstBtn.attr('onclick',"newPage("+currentPage+")");						
				if(currentPage <= 1){
					$preBtn.attr('disabled',true);							
				}else{
					$preBtn.attr('onclick',"newPage("+(currentPage-1)+")");						
				}
				for(var i = startPage; i <= endPage; i++){		
					var $numBtn = $("<button>");
					if(currentPage == i){
						$numBtn.attr('disabled',true);																
					}else{
						$numBtn.attr('onclick',"newPage("+i+")");																
					}
					$numBtn.text(i);
					$pagingDiv2.append($numBtn);
				}
				if(currentPage >= maxPage){
					$nextBtn.attr('disabled',true);							
				}else{
					$nextBtn.attr('onclick','newPage('+(currentPage+1)+')');							
				}
				$lastBtn.attr('onclick','newPage('+maxPage+')');						
				
				$pagingDiv2.append($nextBtn);
				$pagingDiv2.append($lastBtn);
				
				$pagingDiv1.append($pagingDiv2);							
				}		
		});
	}
</script>





</body>
</html>