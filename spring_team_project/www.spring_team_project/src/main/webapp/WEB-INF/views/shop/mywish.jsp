<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/resources/css/shop/mywishList.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.mvo.email" var="authEmail"/>
</sec:authorize>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<c:if test="${not empty list }">
	<div class="con">
		<h2><strong>${list[0].email }</strong>님의 찜목록</h2>
		<ul>
			<c:forEach items="${list }" var="item">
				<li class="wish-list-li">
					<div class="box-li">
						<span class="wish-list-Info" data-pno="${item.pno }" data-pname="${item.productName }" data-price="${item.price }">${item.pno }</span>
						<div class="wish-list-imgBox">
							<img class="imgBox-img" src="/upload/${fn: replace(item.saveDir, '\\', '/')}/${item.uuid}_th_${item.fileName}" alt="${item.fileName}" data-uuid="${item.uuid }" data-filename="${item.fileName }" data-savedir="${item.saveDir }">
						</div>
						<div class="li-info-box">
							<div><span class="info-name">상품명 : </span><span class="wish-list-Info" >${item.productName }</span></div>
							<div><span class="info-name">가격 : </span><span class="wish-list-Info">${item.price }</span></div>
						</div>
						<div class="list-button-box">
							<button class="cartBtn">카트담기</button>
							<button class="delMyWish">찜 취소</button>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div id="buyCart">
		<ul class="buyCart-ul">
			
		</ul>
	</div>
</c:if>
<c:if test="${empty list }">
	<div class="con">
		<h2><strong>${authEmail }</strong>님의 찜목록</h2>
		<ul>
			<li><div>찜 목록이 없습니다.</div></li>
		</ul>
	</div>
</c:if>
	<div class="button-box">
		<a href="/shop/myCart?email=${authEmail }"><button class="moveCart">장바구니로 이동</button></a>
	</div>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript">
	let emailVal = `<c:out value="${authEmail}" />`;
</script>
<script type="text/javascript" src="/resources/js/shop/wishList.js"></script>
</body>
</html>