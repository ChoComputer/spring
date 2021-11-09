<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 아임포트 모듈 -->
<script type="text/javascript"src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 제이쿼리 임포트 (이게있어야 위의 모듈 실행됨) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>품질좋은 상품목록</h1>
<div class="itemSection">
	<div class="itemCard">
		<div class="itemTitle">
			<h2>헬창 근육보충제</h2>
		</div>
		<div class="itemContent">
			<h2>단백질 보충제</h2>
		</div>
		<div class="itemPrice">
			<p data-price="300">300원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>상품2</h2>
		</div>
		<div class="itemContent">
			<h2>상품2설명</h2>
		</div>
		<div class="itemPrice">
			<p data-price="200">200원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
</div>
<script>

var itemPrice=0;
var itemTitle="";
var merchant_uid="";
$(".itemSection").on("click",".orderBtn",function(){
	itemPrice=$(this).parent().siblings(".itemPrice").children().attr("data-price");
	itemTitle=$(this).parent().siblings(".itemTitle").children().text();
	d= new Date();
	merchant_uid="order"+d.getTime();
	iamport();
})	;

function iamport(){
	IMP.init('imp28690241');
	IMP.request_pay({
		pg:'html5_inicis',
		pay_method:'card',
		merchant_uid:merchant_uid,
		name:itemTitle,
		amount:itemPrice,
		buyer_email:'impoart@siot.do',
		buyer_name:'구매자이름',
		buyer_tel:'010-1234-5678',
		buyer_addr:'구매자주소',
		buyer_postcode:'구매자우편번호',
	},function(rsp){
		console.log(rsp);
		if(rsp.success){
			$.ajax({
				type:'post',
				url:'/order',
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"POST"
				},
				dataType:"text",
				data:JSON.stringify({
					itemname:itemTitle,
				})amount:itemPrice,
				merchant_uid:merchant_uid
			}),
			success:function(){
				alert(itemTitle+"결제완료");
			}
		}else{
			var msg='결제에 실패함';
			msg+='에러내용 : '+rsp.error_msg;
		}
		alert(msg);
	});
}
iamport(); // 실제로 상점호출
</script>
</body>
</html>