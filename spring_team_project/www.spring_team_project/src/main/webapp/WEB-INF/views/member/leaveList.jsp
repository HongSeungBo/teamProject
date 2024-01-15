<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/member/leaveList.css">
<body>
<div class="leaveListBox">
		<ul class="leaveListtUl">
			<li class="leaveListlispan">
				<span>탈퇴한 회원목록</span>
			</li>
		<c:forEach items="${leaveList }" var="leaveList">
			<li class="leaveListli"><a class="leaveListlia" href="/member/detail?email=${leaveList.email }">${leaveList.email }</a></li>										
		</c:forEach>
		</ul>
	</div>
	<div class="searchBox">
		<form action="/member/list" method="get">
			<div>
				<c:set value="${mph.mgvo.type }" var="typed"></c:set>
				<select name="type" class="searchBoxSelect">
					<option ${typed eq null ? 'selected' : ''}>선택</option>
					<option value="e" ${typed eq 'e' ? 'selected' : ''}>이메일</option>
					<option value="n" ${typed eq 'n' ? 'selected' : ''}>닉네임</option>
					<option value="en" ${typed eq 'en' ? 'selected' : ''}>이메일+닉네임</option>
				</select>
				<input name="keyword" class="searchBoxInput" value="${mph.mgvo.keyword }" type="search" placeholder="검색">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${mph.mgvo.qty }">
				<button type="submit" class="searchBoxButton">
				검색
				</button>
			</div>
		</form>
	</div>
	
	<div class="pageDiv">
		<ul class="pageUl">
			<li class="pageLi" style="${(mph.prev eq false) ? 'display:none;' : '' }">
				<a href="/member/leaveList?pageNo=${mph.startPage-1 }&qty=${mph.mgvo.qty}&type=${mph.mgvo.type}&keyword=${mph.mgvo.keyword}">
					<span>◀</span>
				</a>
			</li>
			<c:forEach begin="${mph.startPage }" end="${mph.endPage }" var="i">
				<li class="pageLi"><a class="ullia ${(i eq mph.mgvo.pageNo) ? 'active' : '' }" href="/member/list?pageNo=${i }&qty=${mph.mgvo.qty}&type=${mph.mgvo.type}&keyword=${mph.mgvo.keyword}">${i}</a></li>
   			</c:forEach>
   			<li class="pageLi" style="${(mph.next eq false) ? 'display:none;' : '' }">
			     <a  href="/member/leaveList?pageNo=${mph.endPage + 1 }&qty=${mph.mgvo.qty}&type=${mph.mgvo.type}&keyword=${mph.mgvo.keyword}">
			       <span>▶</span>
			     </a>
    		</li>
		</ul>
	</div>	
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
</html>