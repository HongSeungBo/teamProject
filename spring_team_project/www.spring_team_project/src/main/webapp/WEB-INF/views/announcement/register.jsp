<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
    
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
<link rel="stylesheet" type="text/css" href="/resources/css/announcement/register.css">
</head>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body>
<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<div class="outer">
		<form action="/announcement/register" method="post">
			<div class="titleDiv">
				<input type="text" class="ti" name="title" id="t" placeholder="title">
			</div>
			<div class="writerDiv">
				<input type="text" class="wr" name="writer" id="w" value="${authEmail}" readonly="readonly">
			</div>
			<div class="contentDiv">
			<textarea id="summernote" name="content"></textarea>
			</div>
			<button type="submit" class="post" id="regBtn">등록</button>
		</form>
	</div>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/announcement/register.js"></script>
</body>
</html>