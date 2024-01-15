<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/board/boardModify.css">
</head>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body>
	
	
	<c:set value="${bvo }" var="bvo"></c:set>
	<div class="modifyBox">	
		<form action="/board/modify" method="post" enctype="multipart/form-data" id="myForm">
			<div>
			  <input type="text" class="bnum" name="bno" id="bn" value="${bvo.bno }" readonly="readonly">
			</div>
			<div>
			  <input type="text" class="titlee" name="title" id="t" value="${bvo.title }" required="required">
			</div>
			<div>
			  <input type="text" class="writ" name="writer" id="w" value="작성자" readonly="readonly">
			</div>
			<div>
			<textarea id="summernote" name="content">${bvo.content }</textarea>
			</div>
			<button type="submit" class="modBtn" id="regBtn">수정</button>
		</form>
		<hr>
		<a href="/board/list">
	<button type="button" class="listBBtn"> 리스트 </button>
	</a>
	</div>	
	
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
	
	
	<script type="text/javascript" src="/resources/js/board/boardModify.js"></script>
</body>
</html>