<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>all adress</h1>

<sec:authorize access="isAnonymous()">
<!-- 로그인 안한 (익명) 사용자 경우 -->
	<a href="/customLogin">로그인</a>
	</sec:authorize>
<sec:authorize access="isAuthenticated()">
<!-- 로그인 한 (인증된) 사용자 경우 -->
<!-- 로그인한 유저는 xx님 환영합니다라고 출력 -->
<sec:authentication property="principal.member.userName"/>님 환영합니다<br>

<!-- c태그라이브러리 if 이용해 운영자 25만 너무반갑습니다
아래와 같이 var속성을 지정하면 property의 정보를 var변수명에 저장 -->
<sec:authentication property="principal" var="secuInfo"/>
${secuInfo.member.userName }<br/>
<c:if test="${secuInfo.member.userName eq '운영자25' }">
		너무 반갑습니다.<br>
</c:if>
	<a href="/customLogout">로그아웃</a>
	</sec:authorize>
</body>
</html>