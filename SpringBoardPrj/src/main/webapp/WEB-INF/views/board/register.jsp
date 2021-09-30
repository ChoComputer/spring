<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 입력 창</h1>
	
	<form action="/board/register" method="post">
		제목:<input type="text" name="title" /><br/>
		본문:<textarea rows="10" cols="50" name="content"></textarea><br/>
		글쓴이:<input type="text" name="writer" /><br/>
		<input type="submit" value="제출" />
	</form>
	
	
</body>
</html>