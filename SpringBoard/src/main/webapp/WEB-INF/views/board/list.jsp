<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<style>
* {
	margin: 10px;
	padding: 10px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  jsp가아니라 .jsp파일이 바로 실행 안됨 주소로 직접 적어야 접속이됨 -->
	<h1>게시물 목록</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>최종수정</th>
		</tr>
		<c:forEach var="board" items="${list }">
			<!-- 보드컨트롤러에서 list로 넘긴다고 했으니까 -->
			<tr>
				<td>${board.bno }</td>
				<td><a href="/board/get?bno=${board.bno}">${board.title }</a></td>
				<td>${board.writer }</td>
				<td>${board.regdate }</td>
				<td>${board.updatedate }</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/board/register"><button>글쓰기</button></a>
	<form action="/board/list" method="get">
		<input type="text" name="keyword" value="${keyword }" placeholder="검색어">
		<input type="submit" value="검색">
	</form>
		
	<script>
		// 컨트롤러에서 success라는 일므으로 날린 자료가 들어오는지 확인
		// 그냥 list페이지로 접근시에는 success를 날려주지 않아서
		// 아무것도 들어오지 않고 remove 로직의 결과로만 넘어왔을때 데이터가 전달됨
		var result = "${success}";
		var bno ="${bno}";
		console.log(result);
		if(result=="success"){
			alert(bno+"번째 글이 삭제가 되었습니다")
		}
	</script>



</body>
</html>