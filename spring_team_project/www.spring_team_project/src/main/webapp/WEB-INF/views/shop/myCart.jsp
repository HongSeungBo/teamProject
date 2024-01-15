<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/resources/css/shop/myCart.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.mvo.email" var="authEmail"/>
</sec:authorize>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<div class="cart-con">
<c:if test="${not empty list }">
	<div class="con">
		<h2><strong>${list[0].email }</strong>님의 장바구니 목록</h2>
		<ul>
			<c:forEach items="${list }" var="item">
				<li class="wish-list-li">
					<div class="box-li">
						<div class="listbox">
							<span class="wish-list-Info" data-pno="${item.pno }" data-pname="${item.productName }" data-price="${item.price }">${item.pno }</span>
							<div class="wish-list-imgBox">
								<img class="imgBox-img" src="/upload/${fn: replace(item.saveDir, '\\', '/')}/${item.uuid}_th_${item.fileName}" alt="${item.fileName}">
							</div>
						</div>
						<div class="listbox-info">
							<span class="wish-list-Info" >${item.productName }</span>
							<span class="wish-list-Info">${item.price }</span>
						</div>
						<div class="listbox">
							<span class="wish-list-Info">수량 : <input type="number" name="productCount" min="1" value="1" class="product-count"></span>
						</div>
						<div class="listbox list-button-box">
							<button class="buyBtn">구매하기</button>
							<button class="MyCartDelBtn">장바구니 빼기</button>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${empty list }">
	<div class="con">
		<h2><strong>${authEmail }</strong>님의 장바구니 목록</h2>
		<ul>
			<li><div>장바구니 목록이 없습니다.</div></li>
		</ul>
	</div>
</c:if>
	<div class="payment-con">
	<h2><strong>${list[0].email }</strong>님의 구매 목록</h2>
	<ul>
		<c:forEach items="${payList}" var="pmvo">
		      <li class="payment-list-li">
			      <div class="payment-box-li">
				      <div class="payment-listBox">
					  	<div class="listBox-subject">결제번호 :</div><div class="listBox-content">${pmvo.impUid}</div>
				      </div>
				      <div class="payment-listBox">
					  	<div class="listBox-subject">상품이름 :</div><div class="listBox-content">${pmvo.name}</div>
				      </div>
				      <div class="payment-listBox">
					  	<div class="listBox-subject">결제금액 :</div><div class="listBox-content">${pmvo.amount}</div>
				      </div>
				      <div class="payment-listBox">
					  	<div class="listBox-subject">결제일 :</div><div class="listBox-content">${pmvo.regAt }</div>
					  	${pmvo.status }
				      </div>
				      <div class="payment-listBox">
				      <c:if test="${pmvo.status eq 1 }">
					  	<button class="refundBtn" data-merchantUid="${pmvo.impUid}">환불하기</button>
				      </c:if>
				      <c:if test="${pmvo.status eq 2 }">
					  	<div class="refund-2">환불중</div>
				      </c:if>
				      <c:if test="${pmvo.status eq 3 }">
					  	<div class="refund-3">환불완료</div>
				      </c:if>
				      </div>
			      </div>
		      </li>
		 </c:forEach>
	</ul>
	</div>
</div>
<h3><strong>상품과 수량을 확인해주세요.</strong></h3>
<div id="buyCartZone">
</div>
<div id="modalBack">
	<div id="modalCon" style="display: none;">
		<div id="modalInfo">
			<div class="select-box">
				<span class="select">결제 방법 선택하기</span>
			</div>
			<div class="modal-info">
				<div class="modal-totalSum-box">
				</div>
			</div>
			<div class="select-method">
				<img alt="카카페페이.img" src="/resources/image/payment_icon_yellow_medium.png" id="kakaoPay" >
			</div>
			<div class="select-method">
				<img alt="toss" src="/resources/image/toss.png" id="toss" >
			</div>
		</div>
		<div class="modal-btnBox">
			<button type="button" id="cancle">취소</button>
		</div>
	</div>
</div>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript">
let emailVal = `<c:out value="${authEmail}" />`;
</script>
<script type="text/javascript" src="/resources/js/shop/cartList.js"></script>
<script type="text/javascript" src="/resources/js/shop/myPayment.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</body>
</html>