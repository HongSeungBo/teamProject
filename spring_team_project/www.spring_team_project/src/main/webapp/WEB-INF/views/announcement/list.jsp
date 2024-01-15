<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/announcement/list.css">
</head>
<body>
<div class="outer">
<div class="listbox">
  <ul class="listUl">
    <li class="listHeader">
      <span>번호</span>
      <span>제목</span>
      <span>작성자</span>
      <span>작성일</span>
    </li>
    
    <c:forEach items="${list}" var="avo">
      <li class="content">
        <span>${avo.ano}</span>
        <a href="/announcement/detail?ano=${avo.ano}" class="listTitle">${avo.title}</a>
        <span>${avo.writer}</span>
        <span>${avo.regAt}</span>
      </li>
    </c:forEach>
  </ul>
</div>
</div>
<div class="ulpage">
	 <ul class="ulpage">
  <!-- 이전 -->
    <li style="${(bph.prev eq false) ? 'display:none;' : '' }">
      <a href="/announcement/list?pageNo=${bph.startPage-1 }&qty=${bph.pgvo.qty}">
        <span>◀</span>
      </a>
    </li>
   <c:forEach begin="${bph.startPage }" end="${bph.endPage }" var="i">
    <li class="page-item"><a class="ullia ${(i eq bph.pgvo.pageNo) ? 'active' : '' }" href="/announcement/list?pageNo=${i }&qty=${bph.pgvo.qty}">${i}</a></li>
    </c:forEach>
    <!-- 다음 -->
    <li style="${(bph.next eq false) ? 'display:none;' : '' }">
      <a  href="/announcement/list?pageNo=${bph.endPage + 1 }&qty=${bph.pgvo.qty}">
        <span >▶</span>
      </a>
    </li>
  </ul>
</div>

<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript">

const isdel = `<c:out value = "{isdel}" />`;
if (isdel > 0) {
	alert('삭제완료');
}
</script>
</body>
</html>