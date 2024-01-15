<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
     <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/announcement/detail.css">
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
</head>
<body>
<c:set value="${avo }" var="avo"></c:set>
<div class="contentBox">
	
	<div class="de">
		<div class="detailTitle"><h1>${avo.title }</h1></div><hr>
		
		<table class="detailTable">
			<tr>
				<th>번호 : ${avo.ano }</th>
			</tr>
			<tr>
				<th>작성자 : ${avo.writer }</th>
			</tr>
			<tr>
				<th>작성일 | ${avo.regAt } </th>
			</tr>
			<c:if test="${avo.regAt ne avo.modAt }">
			<tr>
				<td>수정일 | ${avo.modAt }</td>
			</tr>
			</c:if>
	</table>
	
	</div>	
	<div class="contentvalue">
	${avo.content }
	</div>
	<div class="commentdelmodi">
	<a href="/announcement/list"><button type="button" class="announcementList">리스트보기</button></a>
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<c:if test="${authEmail  eq avo.writer }">
	<a href="/announcement/modify?ano=${avo.ano }">
	<button type="button" class="announcementMod">수정</button>
	</a>
	<a href="/announcement/remove?ano=${avo.ano }">
	<button type="button" class="announcementDel">삭제</button>
	</a>
	</c:if>
	</sec:authorize>
	</div>
</div>

</body>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript">
const isOk = <c:out value="${isOk}" />
if (isOk > 0) {
	alert('수정완료');
}
</script>
</html>