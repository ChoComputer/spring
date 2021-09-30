<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상세페이지</h1>
	글번호 : ${vo.bno}<br/>
	글제목 : ${vo.title}<br/>
	글본문 : ${vo.content}<br/>
	글쓴이 : ${vo.writer}<br/>
	쓴날짜 : ${vo.regdate} / 최종수정날짜 : ${vo.updatedate}<br/>
	<button><a href="/board/list?page=${cri.page }&searchType=${cri.searchType}&keyword=${cri.keyword}">목록으로</a></button><br>
	
	<%--  pageNum(page로받앗음list에서),searchType,keyword 들어오는지 여부 디버깅 
		EL의 ${param.파라미터명}을 이용하여 확인 가능 위의 세파라미터는 list에서 오는거임--%>
		글번호 : ${param.page}<br>
		검색조건 : ${param.searchType }<br>
		키워드 : ${param.keyword }<br>
	
	<!-- 글 삭제용 버튼 
	글 삭제가 되면, 리스트페이지로 넘어가는데, 삭제로 넘어오는 경우는
	alert()창을 띄워서 "글이 삭제되었습니다" 가 출력되도록 로직을 짜주세요.-->
	<form action="/board/remove" method="post">
		<input name="bno" type="hidden" value="${vo.bno}" >
		<input type="submit" value="삭제">
	</form>
	<!-- 수정페이지로 넘어가는 버튼을 추가해주세요. -->
	<form action="/board/boardmodify" method="post">
			수정날짜 : <input name="updateDate" type="text" class="form-control"
			readonly=true value=${board.updateDate}><br> 
			<input type="hidden" name="bno" value="${vo.bno }" /> 
			<input type="hidden" name="page" value="${param.page}"> 
			<input type="hidden" name="searchType" value="${param.searchType}"> 
			<input type="hidden" name="keyword" value="${param.keyword}"> 
		<input type="submit" value="수정">
	</form>
	
	
</body>
</html>