<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<style>
	*{margin : 10px; padding : 10px;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 맨처음 ${board } 써넣고 ?bno=숫자 쳐서 board로 받아온게 나오나 확인한후 밑을 작성 --%>
<div class="container">
			글번호 : ${vo.bno }<br/>
		<div class="row">
			글제목 : <input type="text" value=${vo.title } readonly/><br/>
		</div>
		<div class="row">
			글본문 : <textarea rows="5" cols="10" readonly>${vo.content }</textarea><br/>
		</div>
		<div class="row">
			글쓴이 : <input type="text" value=${vo.writer }  readonly/><br/>
		</div>
		쓴날짜 : ${vo.regdate }/ 최종 수정 날짜 : ${vo.updatedate }<br/>
		<a href="/board/list">목록으로</a>
		<!-- 글 삭제용 버튼 -->
		<!--  글 삭제되면 리스트페이지로 이동하는데 삭제로 넘어오는 경우 alert()창을 띄워 글이 삭제되었습니다 추가하기 -->
		<form action="/board/remove" method="post">
			<input name="bno" type="hidden" value="${vo.bno }">
			<input type="submit" value="삭제">
		</form>
		<form action="/board/boardmodify" method="post">
			<input name="bno" type="hidden" value="${vo.bno }">
			<input type="submit" value="수정하기">
		</form>
		
		
		</div>
		
		
	

</body>
</html>