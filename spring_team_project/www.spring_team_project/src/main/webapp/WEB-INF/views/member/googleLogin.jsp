<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
<head>
	<title>google login</title>
</head>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body>
	<script type="text/javascript">
	const emailVal = `<c:out value="${mvo.email}"/>`
	const nameVal = `<c:out value="${mvo.name}"/>`
	</script>
	
	<script type="text/javascript" src="/resources/js/googleLogin.js"></script>
	
	<h1>${mvo.email}</h1>
	
		<form action="/member/login" method="post" id="login">
			<input id="emailLoin" name="email" hidden="true">
			<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
		</form>
	
	
		<form action="/member/register" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
			<input id="emailRegister" name="email" hidden="true">
			<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
			<input id="nameRegister" name="name" hidden="true">
			<input id="phoNumRegister" name="phoNum" hidden="true">
		</form>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>	
</html>