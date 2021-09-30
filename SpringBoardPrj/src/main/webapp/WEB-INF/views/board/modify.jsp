<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${vo.bno }번 글 수정 페이지입니다.</h1>
	${vo }<br>
	    <%--get.jsp에서 받아옴 --%>
	    글번호 : ${param.page}<br>
		검색조건 : ${param.searchType }<br>
		키워드 : ${param.keyword }<br>
		<!-- 수정을 하면서 결과페이지에 페이지번호, 검색조건,키워드를 넘겨야함
			수정로직은 post라 어쩔수없이 컨트롤러를 경유해서 데이터를 전달해야함
			controller내부의 modify메서드에 위의 3가지 정보를 모두 처리할수 있는 SearchCriteria를 추가로 선언해 받아줍니다. -->
	<form action="/board/modify" method="post">
		<input type="hidden" name="bno" value="${vo.bno }"/>
		<input type="hidden" name="page" value="${param.page}"> 
		<input type="hidden" name="searchType" value="${param.searchType}"> 
		<input type="hidden" name="keyword" value="${param.keyword}"> 
		제목:<input type="text" name="title" value="${vo.title}" /><br/>
		본문:<textarea rows="10" cols="50" name="content">${vo.content}
			</textarea><br/>
		글쓴이:<input type="text" name="writer" 
				value="${vo.writer }" readonly /><br/>
		<input type="submit" value="제출" />
	</form>
</body>
</html>







