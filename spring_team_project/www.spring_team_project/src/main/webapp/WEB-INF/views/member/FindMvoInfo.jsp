<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/member/findMvoInfo.css">
</head>
<body>
<div class="outer">
<form action="/member/FindMvoInfo" method="post" class="findForm">
        <div class="emailDiv">
       <span class="sp">email :</span> <input type="email" id="email" class="emailInput" name="email" required placeholder="example@OOO.com">
        </div>
        <div class="phonDiv">
       <span class="sp">전화번호 :</span> <input type="tel" class="phon" name="phon1">- <input type="tel" class="phon" name="phon2"> - <input type="tel" class="phon" name="phon3"> 
        </div>
        <button type="submit">비밀번호 찾기</button>
    </form>
    <c:if test="${not empty errMsg}">
        <div class="errBox">
            <h4 style="color: red;"><c:out value="${errMsg}" /></h4>
        </div>
    </c:if>
    
</div>    
</body>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</html>