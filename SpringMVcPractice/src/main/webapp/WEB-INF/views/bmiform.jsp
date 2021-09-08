<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>BMI구하기</h1>
<form action="/bmi" method="post">
	<input type="number" name="height" placeholder="키"><br/>
	<input type="number" name="weight" placeholder="몸무게"><br/>
	<input type="submit"value="제출"> <br/>
	
</form>

</body>
</html>