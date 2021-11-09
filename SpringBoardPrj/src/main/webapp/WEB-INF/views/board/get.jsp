<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: green;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 1.jquery입력받을수 있도록 처리 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<!-- 2.body하단에 <scrtpt>태그 작성후 var bno=${vo.bno}로 글 번호를 받은다음
 		function geAllList()를 test.jsp에서 복붙ㅅ해서 게시물별 페이지에서 잘 작동하는지 확인-->
</head>
<body>
	<h1>상세페이지</h1>
	글번호 : ${vo.bno}
	<br /> 글제목 : ${vo.title}
	<br /> 글본문 : ${vo.content}
	<br /> 글쓴이 : ${vo.writer}
	<br /> 쓴날짜 : ${vo.regdate} / 최종수정날짜 : ${vo.updatedate}
	<br />
	<button><a href="/board/list?page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}">목록으로</a></button></br>

	<%--  pageNum(page로받앗음list에서),searchType,keyword 들어오는지 여부 디버깅 
		EL의 ${param.파라미터명}을 이용하여 확인 가능 위의 세파라미터는 list에서 오는거임--%>
	글번호 : ${param.page}
	<br> 검색조건 : ${param.searchType }
	<br> 키워드 : ${param.keyword }
	<br>

	<!-- 글 삭제용 버튼 
	글 삭제가 되면, 리스트페이지로 넘어가는데, 삭제로 넘어오는 경우는
	alert()창을 띄워서 "글이 삭제되었습니다" 가 출력되도록 로직을 짜주세요.-->
	<form action="/board/remove" method="post">
		<input name="bno" type="hidden" value="${vo.bno}"> <input
			type="submit" value="삭제">
	</form>
	<!-- 수정페이지로 넘어가는 버튼을 추가해주세요. -->
	<form action="/board/boardmodify" method="post">
	 <input
			type="hidden" name="bno" value="${vo.bno }" /> <input type="hidden"
			name="page" value="${param.page}"> <input type="hidden"
			name="searchType" value="${param.searchType}"> <input
			type="hidden" name="keyword" value="${param.keyword}"> <input
			type="submit" value="수정">
			${vo.updatedate }
	</form>
	<hr>

	
	<hr>
	<h2>댓글영역</h2>
	<div>
		<div>
			REPLYER<input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY<input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">add REPLY</button>
	</div>
	<div class="row">
		<h3 class="text-primary">댓글</h3>
		<div id="replies">
			<!-- 댓글이 들어갈 위치 -->
		</div>
	</div>

	<!-- 모달 요소는 안 보이기 때문에 어디 넣어도 되지만 보통html요소들끼리 놨을때 제일 아래쪽에 작성하는 경우가 많다. -->
	<div id="modDiv" style="display: none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>

	<!-- 코드는 위에서 아래로 실행되므로 ajax스크립트태그는 맨아래에 -->
	<script type="text/javascript">
	var bno=${vo.bno};
	function getAllList() {
		
		$.getJSON(//localhost:8181/replies/all/8 404 board가 껴서 나오기 때문에 반드시 /로 시작해야합니다. get404오류떳엇음 replies앞에 / 안붙여서
						"/replies/all/" + bno,
						function(data) {
							// data변수가 바로 얻어온 json데이터의 집합
							console.log(data);
							// str 변수 내부에 문자형태로 html코드작성
					
							var str = "";
							$(data).each(function(){
								// this.updateDate를 표출하면 시간이 unix시간으로 표시가되기때문에 우리가 쓰는형식으로 변환
								var timestamp =this.updatedate;
								var date=new Date(timestamp);
								// date만으로도 시간표시는 우리가 아는형태로 할수있지만 보기 불편하다
								//console.log(date);
								// date내부의 시간을 형식(format)화 해서 출력
								var formattedTime="게시일 : "+date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate()+
								" ( "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" )"
								str += "<div class='replyLi'data-rno='"+this.rno+"'><strong>@"+this.replyer+"</strong>-"+
								formattedTime+"<br>"+"<div class='replytext'>"+this.reply+"</div>"+
								"<button type='button' class='btn btn-info'>수정/삭제</button>"+"</div>";
							})

							//#replies인 ul태그 내부에 str을 끼워넣음
							// ul 내부에 <li>~~</li>를 추가해는 구문.
							$("#replies").html(str);

						});
	}
	getAllList();
	var bnum=${vo.bno};
	$("#replyAddBtn").on("click", function() {
		var repwriter = $("#newReplyWriter").val();
		var replycontent = $("#newReply").val();

		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json", //여기 문제생기면 415가뜸
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bnum,
				replyer : repwriter,
				reply : replycontent
			}),
			success : function(result) {
				if (result == 'SUCCESS') {
					alert("등록되었습니다.");
					getAllList(); // 이걸넣어야 등록된뒤 바로 갱신이됨 새로고침눌러서 갱신할 필요없음 갱신된목록이 새로조회되는거임
					// 댓글 다썻으면 댓글창 비워지도록 처리
					var repwriter = $("#newReplyWriter").val("");
					var replycontent = $("#newReply").val("");

				}//,error:function(result){}도가능
			}
		});
	});
	$("#replies").on("click", ".replyLi button", function() {
		//this는 최하위태그인 button, button의 부모면 결국 .replyLi
		var replyLi = $(this).parent();

		//.attr("속성명")을 하면 해당 속성의 값을 얻습니다
		var rno = replyLi.attr("data-rno"); // replyLi의 data-rno 속성의 값을 가져온다는뜻임
		var reply = $(this).siblings(".replytext").text(); 
		//버튼의 부모(replyLi)의 자식(.replytext)<div class="reply">의 내부텍스트 얻기
		// var reply=$(this).parent().childrens(".replytext").text();
		
		// 클릭한 버튼에 해당하는 댓글번호 + 본문이 얻어지나 디버깅
		//alert(rno + ":" + reply);
		//			alert("야호");
		console.log(rno + ":" + reply);
		// 모달 열리도록 수정
		$(".modal-title").html(rno); // 모달상단에 댓글번호 지금 이게 작동 안하는겁니다.
		$("#replytext").val(reply);	// 모달 수정창에 댓글 본문 넣기
		$("#modDiv").show("slow"); // 창에 에니메이션 효과 넣기
	});
	$("#replyDelBtn").on("click",function(){
		// 삭제에 필요한 댓글 번호 모달 타이틀부분에서 얻기
		var rno=$(".modal-title").html();
		
		$.ajax({
			type : 'delete',
			url : '/replies/'+rno,
			// 전달 테이터가 없이 url과 호출타입만으로 삭제 처리하므로
			// 이외 정보는 제공할 필요가 없음
			success : function(result){
				if(result == 'SUCCESS'){
					alert(rno+" 번 댓글이 삭제 되었습니다.");
					// 댓글 삭제 후 모달창 닫고 새 댓글 목록 갱신
					$("#modDiv").hide("slow");
					getAllList();
				}
			}
		})
	});
	// 글 수정 로직 (rno,reply 필요)
	$("#replyModBtn").on("click",function(){
		//rno(수정에 필요한 댓글번호 모달 타이틀 부분에서 얻기)
		var rno=$(".modal-title").html();
		// 수정에 필요한 본문내역을 #reply의 value값으로 얻기
		var reply=$("#replytext").val();
		console.log(reply);
		$.ajax({
			type : 'put',  // put 써도 상관없음 put 쓰면 밑도 put / patch
			url : '/replies/'+rno,
			headers :{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			dataType :'text',
			data : JSON.stringify({reply:reply}),
			success : function(result){
				alert(rno+ " 번 댓글이 수정되었습니다.");
				// 댓글 삭제후 모달창 닫고 새댓글 목록 갱신
				$("#modDiv").hide("slow");
				getAllList();
			}
		})
	});
	$("#closeBtn").on("click",function(){
		$("#modDiv").hide("slow");
	});
		

	
	</script>

</body>
</html>