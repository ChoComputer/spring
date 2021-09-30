<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글 수정 jsp</h1>
		<form action="/board/modify" method="post">
<div class="container">
			글번호 : ${vo.bno }<br/>
		<div class="row">
			글제목 : <input type="text" placeholder=${vo.title } required/><br/>
		</div>
		<div class="row">
			글본문 : <textarea rows="5" cols="10" placeholder="${vo.content }" required></textarea><br/>
		</div>
		<div class="row">
			글쓴이 : <input type="text" value=${vo.writer }  readonly/><br/>
		</div>
		쓴날짜 : ${vo.regdate }/ 최종 수정 날짜 : ${vo.updatedate }<br/>
			<input name="bno" type="hidden" value="${vo.bno }">
			<input name="title" type="hidden" value="${vo.title }">
			<input name="content" type="hidden" value="${vo.content }">
			<input type="submit" value="수정">
</div>
		</form>
		<a href="/board/list">목록으로</a>

</body>
</html>