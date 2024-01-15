<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
#error-message-pwd{
    font-size: 11px;
    color: red;
    font-weight: bold;
    margin-bottom: 10px;
}
.ptag{
	font-size: 12px;
	color: red;
	font-weight: bold;
}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/member/changePwd.css">
</head>
<body>
<div class="newPwdDiv">
<form action="/member/modifyPwd" method="post" class="newPwdForm" onsubmit="return validateForm()">
<input type="hidden" name="email" value="${mvo.email }">
<span class="pwdSpan">비밀번호</span> <input type="password" value="" name="pwd" class="newPwd1"> <br>
<div id="error-message-pwd"></div>
<span class="pwdSpan">비밀번호 확인</span> <input type="password" class="newPwd2" name="pwd2">
<c:if test="${not empty errMsg}">
        <p class="ptag">${errMsg}</p>
    </c:if>
<button type="submit">등록</button>
</form>
</div>
</body>
<script type="text/javascript" src="/resources/js/member/changePwd.js"></script>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</html>