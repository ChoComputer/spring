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
	<!--  post는 db가 변동될떄 씀 -->
	<form action="/board/register" method="post"> 
		<div class="row">
			글제목 : <input type="text" name="title" required/><br/>
		</div>
		<div class="row">
			글본문 : <textarea rows="5" cols="10" name="content" required></textarea><br/>
		</div>
		<div class="row">
			글쓴이 : <input type="text" name="writer" required/><br/>
		</div>
		<button type="submit">제출</button>
		<button type="reset">초기화</button>
	</form>

</body>
</html>