<%@page import="com.kh.bvengers.product.model.vo.Product"%>
<%@page import="com.kh.bvengers.board.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.bvengers.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   Product p = (Product) request.getAttribute("p");
   Board b = (Board) request.getAttribute("b");
   ArrayList<Attachment> fileList = (ArrayList<Attachment>) request.getAttribute("fileList");
   Attachment productImg = fileList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
<style>
.outer {
   margin-left: auto;
   margin-right: auto;
   margin-bottom: 50px;
}
.detail td, .detail th {
   text-align: center;
   border: 1px solid black;
}
.detail th {
   font-weight: bold;
   width : 50px;
   border: 1px solid black;
}

#titleImg {
   width: 100%;
   height: 300px;
}

#titleContentArea{
   height : 500px;
}
#price, #delivery {
	width: 100%;
	text-align:right;
	padding-right: 30px;
	letter-spacing: 0.2em;
}
.detailBtn{
   color: white;
   width: 100px;
   height: 50px;
}
#priceTd {
	height: 100px;
}
#contents {
   text-align:left;
   padding : 10px;
}
.btns{
   background: white;
   border: 1px solid black;
   width: 100px;
   height: 50px;
   margin-bottom:10%;
}
#comments{
   width: 100%;
}
#commentHeaderTable th {
   font-weight: bold;
   height: 50px;
}
.commentTables {
	margin: 0 auto;
}
#btnArea {
   width:35%;
   align:center;
   margin : 20px auto;
   text-align:center;
}
#btnArea * {
	margin: 0 auto;
}
#totalPrice {
	font-size: 1.2em;
	letter-spacing: 0.3em;
	margin-top: 15px;
	color: red;
}
.detail #writer {
	font-size : 1.2em;
	cursor : pointer;
}
.detail #writer a:hover,  .detail #writer a{
	text-decoration : none;
	color: black;
}
#productStatus{
	width: 70px;
}
textarea {
	border: 0;
}
</style>
<meta charset="UTF-8">
</head>
<body>
   <header><%@ include file="../hfl/header.jsp"%></header>
   <div class="outer" align="center">
      <form action="<%=request.getContextPath()%>/payment.pa" method="post" id="productDetail">
         <table class="detail" align="center">
            <tr>
               <th>판매자</th>
               <th colspan="3"><label id="writer"><a href="#" data-toggle="tooltip" data-placement="top" title="판매자 정보보기"><%= b.getWriter() %></a></label></th>
            </tr>
            <tr>
               <% if(p.getCompleteStatus().equals("3")) { %>
               <th id="productStatus"rowspan="3">조건부 통과 상품</th>
               <%} else {%>
               <th rowspan="3">상품</th>
               <%} %>
               <td rowspan="3" width="150px">
                  <div id="titleImgArea" align="center">
                     <input type="hidden" value="<%= b.getPostsId() %>" id="postsId" name="postsId" />
                     <img src="<%= request.getContextPath() %>/thumbnail_uploadFiles/<%= productImg.getNewFileName() %>" id="titleImg" />
                  </div>
               </td>
               <th>상품명</th>
               <td id="titleContent"><label><%= b.getPostsTitle() %></label></td>
            </tr>
            <tr>
               <td id="priceTd" colspan="2">
                  <input type="hidden" id="priceInput" value="<%=p.getProductMoney() %>"/>
                  <label id="price">원</label><br />
                  <input type="hidden" id="deliveryPay" value="<%=p.getDeliveryPay() %>"/>
                  <label id="delivery">원</label><br />
				  <label id="totalPrice"></label>
               </td>
            </tr>
            <tr>
               <td width="150px" style="border-right:0">
					<div class="position">
						<div class="svg-wrapper">
							<svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
   								<rect id="shape" height="40" width="150" />
						        <div id="text">
						          <a id="basketBtn" style="color:black" href=""><span class="spot"></span>장바구니</a>
						        </div>
							</svg>
						</div>
					</div>
				</td>
				<td width="150px" style="border-left:0">
					<div class="position">
						<div class="svg-wrapper">
							<svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
   								<rect id="shape" height="40" width="150" />
						        <div id="text">
						          <a id="orderBtn" style="color:black"><span class="spot"></span>구매하기</a>
						        </div>
							</svg>
						</div>
					</div>
				</td>
            </tr>
            <tr>
               <th>상품 설명</th>

               <td colspan="3">
                  <div id="titleContentArea">
                     <p id="contents"><%= (b.getContents()).replace("\r\n","<br>") %></p>
                  </div>
               </td>
            </tr>
            <%if(p.getCompleteStatus().equals("3")) { %>
           	<tr>
           		<th>조건부 통과 사유</th>
           		<td colspan="3"><p id="reason"><%= (p.getReason()).replace("\r\n", "<br>") %></p></td>
           	</tr>
            <%} %>
            <tr>
               <th colspan="4" id="commentHeader">댓글 작성</th>
            </tr>
            <tr>
               <td colspan="4" ><textarea id="commentContent" rows="5" cols="130" style="resize:no"></textarea></td>
            </tr>
         </table>
      </form>
      <form id="comments">
      	<div id="btnArea">
	      <button id="addBtn" type="button" class="btns"align="center">댓글 등록</button>
	      <button id="showBtn" type="button" class="btns" align="center">댓글 닫기</button>
	      <%if(loginUser != null && loginUser.getMemberId().equals(b.getWriter())) {%>
	      <button id="editBtn" type="button" class="btns" align="center">수정 요청</button>
      	  <%} else if(loginUser != null && loginUser.getMemberId().equals("admin")) {%>
	      <button id="deleteBtn" type="button" class="btns" align="center">삭제</button>
      	  <%} %>
      	</div>
      <hr />
      <table id="commentHeaderTable" class="commentTables">
         <tr>
            <th colspan="7" style = "width:800px">댓글 리스트</th>
         </tr>
      </table>
      <table id="commentSelectTable" class="commentTables" align="center">
         <tbody>
         </tbody>
      </table>
      </form>
   </div>
   <footer><%@ include file="../hfl/footer.jsp"%></footer>
   <script>
	   $(function () {
			$('[data-toggle="tooltip"]').tooltip()
		});
      $(function(){
         var postsId = <%= b.getPostsId()%>;
         $("#orderBtn").click(function(){
			$("#productDetail").submit();
         });
         $(document).ready(function(){
            $.ajax({
               url:"selectComment.pd",
               data:{postsId:postsId},
               type:"post",
               success:function(data){
                  var $commentSelectTable = $("#commentSelectTable tbody");
                  $commentSelectTable.html("");
                  for(var key in data){
                     var $tr = $("<tr>");
                     var $tr2 = $("<tr>");
                     var $hr = $("<hr>");

                     var $commentNo = $("<td>").text(data[key].commentNo).addClass("tCommentNo").css("display", "none");
                     var $writer = $("<td>").text("작성자 : ").css({'width':'60px','font-weight':'bold'});
                     var $writeTd = $("<td colspan='4'>").text(data[key].memberId).addClass("tWriter").css("width", "100px");
                     var $contentTd = $("<td colspan='2'>").text(data[key].commentContents).addClass("tContent").css({"width":"400px","height":"50px"});
                     var $dateTd = $("<td>").text(data[key].commentDate).addClass("tDate").css({'width':'200px','color':'lightgray','font-size':'10xpx'});

                     var $recommend = $("<td>").text("추천수 : " + data[key].recommendCount).addClass("tRecommend");
                     var $recommendBtn = $("<td>").text('추천').addClass("tRecommendBtn").on("click", function(){
						<%if(loginUser!=null){%>
							var writer = $(this).siblings().eq(1).text();
                        	if(confirm("추천하시겠습니까?")){
	                         	var num = $(this).siblings().eq(0).html();
	                         	var writer = $(this).siblings().eq(1).html();
	                         	$.ajax({
									url:"recommend.cm",
	                            	data:{num:num, writer:writer},
	                            	type:"post",
	                            	success:function(data){
										alert(data);
										location.reload();
	                                }, error:function(){
										alert("추천에 실패했습니다.");
	                                }
	                            });
                     		}
						<%} else {%>
							alert("로그인이 필요한 기능입니다");
                     	<%}%>
                     });
                     $tr2.append($writer);
                     $tr2.append($writeTd);

                     $tr.append($commentNo);
                     $tr.append($contentTd);
                     $tr.append($dateTd);
                     $tr.append($recommend);
                     $tr.append($recommendBtn);

                     $commentSelectTable.append($hr);
                     $commentSelectTable.append($tr2);
                     $commentSelectTable.append($tr);
                     $(".tRecommend").css("width","100px");
                     $(".tRecommendBtn").css({"width":"100px", "cursor":"pointer"});
                     $commentSelectTable.css({"width":"100%","margin":"auto"});
                  }
               },
               error:function(){
                  alert("댓글 입력 실패");
               }
            });
         });
      });

      $("#writer").on("click", function(){
         window.open("<%= request.getContextPath()%>/myInfo.me?userId=<%=b.getWriter()%>", '<%= b.getWriter()%>', 'width=400, height=600, location=no, toolbar=no, fullscreen=no');
      });
      $(function(){
         var price = numeral($("#priceInput").attr('value')).format( '0,0' );
		 var delivey = numeral($("#deliveryPay").attr('value')).format( '0,0' );
		 var iPrice = Number($("#priceInput").attr('value'));
		 var iDelivey = Number($("#deliveryPay").attr('value'));
		 var total = numeral(iPrice + iDelivey).format( '0,0' );
         $("#price").text("상품 금액 : " + price+"원");
         $("#delivery").text("배송비 : " + delivey+"원");
		 $("#totalPrice").text("합계 금액 : " + total + "원");

         $("#addBtn").click(function(){
         <% if(loginUser!=null){%>
            var writer = <%=loginUser.getMemberNo()%>;
            var content = $("#commentContent").val();
            var postsId = <%=b.getPostsId()%>;

            $.ajax({
               url:"insertComment.pd",
               data:{writer:writer, content:content, postsId:postsId},
               type:"post",
               success:function(data){
                     location.reload();
                     $(".commentTables").show();
               },
               error:function(){
                  alert("댓글 입력 실패");
               }
            });
         <%} else {%>
         	alert("로그인이 필요한 기능입니다.");
         <%}%>
         });
         $("#showBtn").click(function(){
            $(".commentTables").toggle();
            if($(this).html()=="댓글 보기"){
               $(this).html("댓글 닫기");
            } else {
               $(this).html("댓글 보기");
            };
         });
      });
       $("#basketBtn").click(function(){
          $(function(){
            var fileName="<%=productImg.getNewFileName()%>";
            $.ajax({
               url:"basketList.bk",
               type:"post",
               data:{fileName:fileName},
               success: function(data){
                  if(data==null){
                     alert("해당상품은 이미 장바구니에 있습니다.");
                  }else{
	                  if(confirm("장바구니로 이동하시겠습니까?")==true){
	                     location.href = "<%=request.getContextPath()%>/basketAllList.bk";
	                  }
                  }
               }
            })
          });
      });
       $("#editBtn").click(function(){
			alert("상품 수정 요청 페이지로 이동합니다.");
			location.href="<%=request.getContextPath()%>/update.no?num="+postsId;
       });
       $("#deleteBtn").click(function(){
          if(confirm("정말로 이 게시글을 삭제하시겠습니까?")){
            var postsId = $("#postsId").val();
            location.href="<%=request.getContextPath()%>/deleteProduct.pd?num="+postsId;
          }
       });
   </script>
</body>
</html>