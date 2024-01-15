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



<link rel="stylesheet" type="text/css" href="/resources/css/board/boardRegister.css">
</head>

<jsp:include page="../common/jsp/header.jsp"></jsp:include>

<body>

	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<div class="textBox">	
		<form action="/board/register" method="post" enctype="multipart/form-data" id="myForm">
			<div class="select"> <span class="tag">태그 :</span>
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
			</div>
			<div>
			  <input type="text" class="ti" name="title" id="t" placeholder="title" required="required">
			</div>
			<div>
			  <input type="text" class="wr" name="writer" id="w" value="${authEmail }" readonly="readonly">
			</div>
			<div class="mb-3">
			<textarea id="summernote" name="content"></textarea>
			</div>
			<button type="submit" class="post" id="regBtn">등록</button>
		</form>
	</div>	
	
	
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/board/boardRegister.js"></script>


</body>

</html>