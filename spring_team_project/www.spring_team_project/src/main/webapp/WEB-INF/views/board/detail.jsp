<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
     <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<link rel="stylesheet" type="text/css" href="/resources/css/board/boardDetail.css">
</head>


<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body class="boby">

	
	<c:set value="${bvo }" var="bvo"></c:set>
	<div class="contentBox">
	
	<div class="de">
		<div class="detailTitle"><h1>${bvo.title } <span class="tag"> #${bvo.animalType }</span></h1></div><hr class="hr">
		
		<table class="detailTable">
			<tr>
				<th>번호 : ${bvo.bno }</th>
			</tr>
			<tr>
				<th>작성자 : ${bvo.writer }</th>
			</tr>
			<tr>
				<th>작성일 | ${bvo.regAt } </th>
			</tr>
			<c:if test="${bvo.regAt ne bvo.modAt }">
			<tr>
				<td>수정일 | ${bvo.modAt }</td>
			</tr>
			</c:if>
			<tr>
				<th>조회수 ${bvo.readCount }</th>
			</tr>
	</table>
	
	</div>	
	<div class="contentvalue">
	${bvo.content }
	</div>
	<div class="commentdelmodi">
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<c:if test="${authEmail  eq bvo.writer }">
	<a href="/board/modify?bno=${bvo.bno }">
	<button type="button" class="boardMod">수정</button>
	</a>
	<a href="/board/remove?bno=${bvo.bno }">
	<button type="button" class="boardDel">삭제</button>
	</a>
	</c:if>
	</sec:authorize>
	<a href="/board/list"><button type="button" class="boardList">리스트보기</button></a>
	</div>
</div>
	
	
	
	<div class="cmtAllbox">
		<!-- 댓글 등록 라인 -->

        <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.mvo.email" var="authEmail"/>

		 <div class="input-group mb-3"></div>
	      <c:set value="ㅇㅇ" var="authNick"></c:set>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.mvo.name" var="authNick"/>
		</sec:authorize>	
		 <div class="commentbox">
	      <span class="cmtspanID" id="cmtWriter">${authEmail }</span><br>
		  <input type="text" class="cmtintput" id="cmtText">
		  <button type="button" class="addBtn" id="cmtPostBtn">등록</button>

		</div>
		</sec:authorize>
		
		<!-- 댓글 표시 라인 -->
		<ul class="cmtListul" id="cmtListArea">
		  <li class="cmtListli">
		  	<div class="cmtListdiv">
		  		<div  class="cmtListIDdiv" >${authEmail }</div>
		  		<!-- Content -->
		  	</div>
		  	<span class="cmtListspan">modAt</span>
		  </li>
		</ul>
		<!-- 댓글 페이징 라인 -->
		<div class="moreview">
			<div class="mbtn">
				<button type="button" id="moreBtn" data-page="1" class="" style="visibility: hidden">댓글 더보기</button>
			</div>

		</div>
		


</div>

	<jsp:include page="../common/jsp/footer.jsp"></jsp:include> 
	<script type="text/javascript">
	const isOk = `<c:out value="${isOk}"/>`;
	if (isOk == 1) {
		alert('수정완료');
	}
	</script>
	<script type="text/javascript">
	let bnoVal = `<c:out value="${bvo.bno}"/>`;
	console.log(bnoVal);
	let authEmail = `<c:out value="${authEmail}" />`;
	console.log(authEmail);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
</body>

</html>