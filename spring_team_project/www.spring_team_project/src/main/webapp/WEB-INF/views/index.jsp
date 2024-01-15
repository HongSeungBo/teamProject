<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
	<title>main</title>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

	<meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id" content="317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com">
	<meta name="google-signin-callback" content="http://localhost:8088/member/googleCallback">
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-straight/css/uicons-regular-straight.css'>
</head>

<link rel="stylesheet" type="text/css" href="/resources/css/common/index.css">
<jsp:include page="common/jsp/header.jsp"></jsp:include>
<body>
<div class="scroll-senser">
	<div class="scroll-smalldiv"><span class="scroll" id="scrollToTop">top</span></div>
</div>
	<div id="mapBox"></div>
	

	<div class="contentBox">
	<h2 class="subject">인기상품 TOP 10</h2>
		<div class="itemBox">
			<div class="item">
				<ul class="itemUl">
					<c:forEach items="${list }" var="shop">
						<li class="itemLi">
							<c:forEach items="${shop.list }" var="list">
								<c:if test="${list.main eq true }">
									<a href="/shop/detail?pno=${shop.pno }"><img class="itemImg" src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}"></a>
								</c:if>
							</c:forEach>
							<span class="product-logo">상품명</span> : <span class="product-name">${shop.productName }</span><br>
							<span class="product-logo">가격</span> : <span class="product-price">${shop.price }</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		<div class="spanBox">
		<i class="bi bi-caret-left sp" onclick="moveItems('left')"></i>
       <i class="bi bi-caret-right sp" onclick="moveItems('right')"></i>
		</div>
		</div>
	</div>
	<div class="underBox">
		<div class="rankList">
			<div class="rank">
			<p class="rankp">인기글</p>
			<c:forEach items="${topList}" var="bvo">
				<ul class="rankul">
					<li class="rankli"><a href="/board/detail?bno=${bvo.bno }" class="ranklia">${bvo.title } <c:if test="${bvo.cmtQty ne 0}"><span class="cmtcount">[${bvo.cmtQty}]</span></c:if></a></li>
				</ul>
			</c:forEach>	
			</div>
		</div>
		<div class="rankList">
			<div class="rank">
			<p class="rankp">공지사항</p>
			<c:forEach items="${noticeList}" var="avo">
				<ul class="rankul">
					<li class="rankli"><a href="/announcement/list?ano=${avo.ano }" class="ranklia">${avo.title }</a></li>
				</ul>
			</c:forEach>	
			</div>
		</div>
	</div>
	<jsp:include page="common/jsp/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/common/index.js"></script>
<script type="text/javascript">
const isOk = `<c:out value="${isOk}" />`;
if (isOk == 1) {
	alert('회원 탈퇴 완료');
}
</script>
</body>	
</html>
