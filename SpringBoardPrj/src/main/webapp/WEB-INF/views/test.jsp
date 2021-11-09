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
</head>
<body>

	<h2>Ajax test</h2>
	<ul id="replies">
	</ul>
	<div>
		<div>
			REPLYER<input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY<input type="text" name="reply" id="newReply">
		</div>
		<button id="replyAddBtn">add REPLY</button>
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

	<!-- jquery는 이곳에 -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- 코드는 위에서 아래로 실행되므로 ajax스크립트태그는 맨아래에 -->
	<script type="text/javascript">
		var bno = 8;
		function getAllList() {
			
			$.getJSON(
							"/replies/all/" + bno,  // replies 앞에 /있던없던 상관없음?
							function(data) {
								// data변수가 바로 얻어온 json데이터의 집합
								console.log(data.length);
								// str 변수 내부에 문자형태로 html코드작성
								var tr = "";
								str = "<li><a href='https://naver.com'>네이버링크</a></li>";
								var str = "";
								$(data)
										.each(
												function() {
													//$(data).each()는 향상된 for문 처럼 내부데이터를 하나하나 반복합니다.
													// 내부 this는 댓글 하나하나입니다
													console.log(this);
													console
															.log("-------------------------");
													str += "<li data-rno='"+this.rno+"' class='replyLi'>"
															+ this.rno
															+ ":"
															+ this.reply
															+ " AND  "
															+ this.replyer
															+ "<button>수정/삭제</button>"
															+ "</li>";
												});

								//#replies인 ul태그 내부에 str을 끼워넣음
								// ul 내부에 <li>~~</li>를 추가해는 구문.
								$("#replies").html(str);

							});
		}
		getAllList();
		var bnum = 8 // 이해용으로 변수명 바꾼거임 이왕이면 변수명 통일하는게 좋다 밑의 변수명 비교임 왼쪽이 기존 오른쪽이 요페이지 변수명
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
					}//,error:function(result){}도가능
				}
			});
		});

		//이벤트위임 버튼이 독립적으로 작용하게 위임처리를 함 2번째에 부모 자식 요소로 적혀있음
		// 내가 현재 이벤트를 걸려는 집단(button) 을 포함하면서 범위가 제일 좁은
		// #replies로 시작조건을 잡습니다
		// .on("click","목적지 태그까지의 요소들",function(){실행문})
		// 과 같이 위임시는 파라미터가 3개 들어갑니다
		$("#replies").on("click", ".replyLi button", function() {
			//this는 최하위태그인 button, button의 부모면 결국 .replyLi
			var replyLi = $(this).parent();

			//.attr("속성명")을 하면 해당 속성의 값을 얻습니다
			var rno = replyLi.attr("data-rno");
			var reply = replyLi.text(); // li태그 글씨만 얻어오기

			// 클릭한 버튼에 해당하는 댓글번호 + 본문이 얻어지나 디버깅
			//alert(rno + ":" + reply);
			//			alert("야호");
			console.log(rno + ":" + reply);
			// 모달 열리도록 수정
			$(".modal-title").html(rno); // 모달상단에 댓글번호
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
		
	</script>
</body>
</html>