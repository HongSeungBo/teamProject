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
<link rel="stylesheet" type="text/css" href="/resources/css/board/boardList.css">
<body>

	
	<div class="boardlist">
		<div class="search">
	  <form action="/board/list" method="get">
	  <div>
	  <c:set value="${bph.pgvo.type }" var="typed"></c:set>
	   <select name="type" id="inputGroupSelect01">
	    <option value=null ${typed eq null ? 'selected' : ''}>선택</option>
	    <option value="t" ${typed eq 't' ? 'selected' : ''} >제목</option>
	    <option value="w" ${typed eq 'w' ? 'selected' : ''}>작성자</option>
	    <option value="c" ${typed eq 'c' ? 'selected' : ''}>내용</option>
	    <option value="tw" ${typed eq 'tw' ? 'selected' : ''}>제목+작성자</option>
	    <option value="tc" ${typed eq 'tc' ? 'selected' : ''}>제목+내용</option>
	    <option value="cw" ${typed eq 'cw' ? 'selected' : ''}>내용+작성자</option>
	    <option value="tcw" ${typed eq 'tcw' ? 'selected' : ''}>전체</option>
	  </select>
	  <c:set value="${bph.pgvo.animalType }" var="typea"></c:set>
	  <select name="animalType" id="inputGroupSelect01">
	    <option value=null ${typea eq null ? 'selected' : ''}>선택</option>
	    <option value="개" ${typea eq '강아지' ? 'selected' : ''}>강아지</option>
	    <option value="고양이" ${typea eq '고양이' ? 'selected' : ''}>고양이</option>
	    <option value="거북이" ${typea eq '거북이' ? 'selected' : ''}>거북이</option>
	    <option value="친질라" ${typea eq '친질라' ? 'selected' : ''}>친질라</option>
	    <option value="앵무" ${typea eq '앵무' ? 'selected' : ''}>앵무</option>
	    <option value="햄스터" ${typea eq '햄스터' ? 'selected' : ''}>햄스터</option>
	    <option value="뱀" ${typea eq '뱀' ? 'selected' : ''}>뱀</option>
	    <option value="양서류" ${typea eq '양서류' ? 'selected' : ''}>양서류</option>
	    <option value="관상어" ${typea eq '관상어' ? 'selected' : ''}>관상어</option>
	  </select>
	  
	        <input name="keyword" value="${bph.pgvo.keyword }" type="search" placeholder="Search" >
	        <input type="hidden" name="pageNo" value="1">
	        <input type="hidden" name="qty" value="${bph.pgvo.qty}">
	        <button type="submit" class="searchBtn">
	        Search
	        </button>
	     		</div>
	      </form>
		</div>
	
	
	<!--  -->
	<div class="listbox">
  <ul class="listUl">
    <li class="listHeader">
      <span>번호</span>
      <span>제목</span>
      <span>작성자</span>
      <span>작성일</span>
      <span>조회수</span>
      <span>동물</span>
    </li>
    
    <c:forEach items="${list}" var="bvo">
      <li class="content">
        <span>${bvo.bno}</span>
        <a href="/board/detail?bno=${bvo.bno}" class="listTitle">${bvo.title} <c:if test="${bvo.cmtQty ne 0}"><span class="cmtcount">[${bvo.cmtQty}]</span></c:if></a>
        <span>${bvo.writer}</span>
        <span>${bvo.regAt}</span>
        <span>${bvo.readCount}</span>
        <span>${bvo.animalType}</span>
      </li>
    </c:forEach>
  </ul>
</div>
	<div >
<div class="boardRegister"><a href="/board/register"><button type="button" class="registerBtn">글쓰기</button></a></div>
  <ul class="ulpage">
  <!-- 이전 -->
    <li style="${(bph.prev eq false) ? 'display:none;' : '' }">
      <a href="/board/list?pageNo=${bph.startPage-1 }&qty=${bph.pgvo.qty}&type=${bph.pgvo.type}&keyword=${bph.pgvo.keyword}">
        <span>◀</span>
      </a>
    </li>
   <c:forEach begin="${bph.startPage }" end="${bph.endPage }" var="i">
    <li class="page-item"><a class="ullia ${(i eq bph.pgvo.pageNo) ? 'active' : '' }" href="/board/list?pageNo=${i }&qty=${bph.pgvo.qty}&type=${bph.pgvo.type}&keyword=${bph.pgvo.keyword}">${i}</a></li>
    </c:forEach>
    <!-- 다음 -->
    <li style="${(bph.next eq false) ? 'display:none;' : '' }">
      <a  href="/board/list?pageNo=${bph.endPage + 1 }&qty=${bph.pgvo.qty}&type=${bph.pgvo.type}&keyword=${bph.pgvo.keyword}">
        <span >▶</span>
      </a>
    </li>
  </ul>

</div>
</div>
	
	
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
	
	
	
	
	<script type="text/javascript">
	const isdel = `<c:out value="${isdel}"/>`;
	if (isdel == 1) {
		alert('삭제완료');
	}
	const isUp = `<c:out value="${isUp}" />`;
	if(isUp == 1){
		alert('등록 완료');
	}
	</script>
</body>





</html>