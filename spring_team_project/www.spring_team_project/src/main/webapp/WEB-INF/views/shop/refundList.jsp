<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불신청 목록</title>
<link rel="stylesheet" type="text/css" href="/resources/css/shop/refundList.css">
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<script type="text/javascript" src="/resources/js/shop/refundList.js"></script>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<div class="refund-con">
  <ul class="listUl">
    <li class="listHeader">
	  	<div class="header-box">
	      <div class="header-info"><strong>번호</strong></div>
	      <div class="header-info"><strong>가격</strong></div>
	      <div class="header-info"><strong>주문자</strong></div>
	      <div class="header-info"><strong>환불사유</strong></div>
	      <div class="header-info"><strong>환불신청날짜</strong></div>
	  	</div>
    </li>
    <c:forEach items="${list}" var="re">
      <li class="listbody" >
	      <div class="body-box">
	        <div class="body-info">${re.uid}</div>
	        <div class="body-info">${re.pmvo.amount}</div>
	        <div class="body-info">${re.pmvo.buyerEmail}</div>
	        <div class="body-info">${re.reason}</div>
	        <div class="body-button">${re.regAt}
	        	<c:if test="${re.refundses == 'N'}">
	        		<div><button class="refundBtn" data-merchantUid="${re.uid}">환불하기</button></div>
	        	</c:if>
	        	<c:if test="${re.refundses == 'Y'}">
	        		<div class="success">환불완료</div>
	        	</c:if>
	        </div>
	      </div>
      </li>
    </c:forEach>
  </ul>
</div>
<div class="div"></div>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
</html>