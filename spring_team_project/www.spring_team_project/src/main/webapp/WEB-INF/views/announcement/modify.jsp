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

<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/announcement/modify.css">
</head>
<body>
<c:set value="${avo }" var="avo"></c:set>
<div class="modifyBox">	
		<form action="/announcement/modify" method="post" id="myForm">
			<div>
			  <input type="text" class="bnum" name="ano" id="bn" value="${avo.ano }" readonly="readonly">
			</div>
			<div>
			  <input type="text" class="titlee" name="title" id="t" value="${avo.title }" required="required">
			</div>
			<div>
			  <input type="text" class="writ" name="writer" id="w" value="${avo.writer }" readonly="readonly">
			</div>
			<div>
			<textarea id="summernote" name="content">${avo.content }</textarea>
			</div>
			<button type="submit" class="modBtn" id="regBtn">수정</button>
		</form>
		<hr>
		<a href="/announcement/list">
	<button type="button" class="listBBtn"> 리스트 </button>
	</a>
	</div>	
</body>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/announcement/modify.js"></script>
</html>