<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<style>
	li{
		list-style: none;;
	}
</style>
<meta charset="UTF-8">
<title>상품 상세설명</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/detail.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/jsp/header.jsp"></jsp:include>
	<div class="scroll-senser">
		<div class="scroll-smalldiv"><span><i class="bi bi-heart heart"></i></span></div>
		<div class="scroll-smalldiv"><span><i class="bi bi-caret-up" id="scrollToTop"></i></span></div>
		<div class="scroll-smalldiv"><a href="/shop/list"><button type="button" class="backBtn">back</button></a></div>
		<div class="scroll-smalldiv"><span><i class="bi bi-caret-down" id="scrollToBottom"></i></span></div>
	</div>
	<div class="product-con">
		<div class="left-div">
			<div class="mainImg-div">
				<c:forEach items="${svo.list }" var="list">
					<c:if test="${list.main eq true}">
						<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_${list.fileName}" alt="${list.fileName}">
					</c:if>
				</c:forEach>
			</div>
			<div class="div-info">
				<input type="hidden" name="pno" value="${svo.pno }">
				<div class="info-productName"><span class="product-logo">상품명</span> : <span class="product-name">${svo.productName }</span></div>
				<div class="info-Price"><span class="product-logo">가격</span> : <span class="product-price">${svo.price }</span></div>
				<div class="tagName-div">
					<ul class="tagName-ul">
						<c:forEach items="${svo.tagName }" var="tag">
							<li>#${tag }</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="product-detail">
			<div class="detail-text"><span>${svo.productContent }</span>
			</div>
			<div class="subImg-div">
				<c:forEach items="${svo.list }" var="list">
					<c:if test="${list.main eq false}">
						<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}" class="subfile" data-uuid="${list.uuid }" data-filename="${list.fileName }" data-savedir="${list.saveDir }">
					</c:if>
				</c:forEach>
			</div>
			<div id="detailSubFile"></div>
		</div>
	</div>
	<div class="review-con">
		<sec:authorize access="isAuthenticated()">
		        <sec:authentication property="principal.mvo.email" var="authEmail"/>
		        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
		        <sec:authentication property="principal.mvo.authList" var="auths"/>
			<div class="product-review-div">
				<div class="review-write">
					<div class="ratingCon">
						<i class="bi bi-star star" data-num="1"></i><i class="bi bi-star star" data-num="2"></i><i class="bi bi-star star" data-num="3"></i><i class="bi bi-star star" data-num="4"></i><i class="bi bi-star star" data-num="5"></i>
					</div>
					<div class="review-info">
						<div class="UserID">
							<input type="hidden" name="id" value="${authEmail }" id="email"><span>${authEmail }</span>
						</div>
						<div class="cmt-content">
							<textarea rows="" cols="" id="cmtText" name="content"></textarea>
							<input type="file" name="files" id="cmtFileBtn" style="display: none;" multiple="multiple">
							<button type="button" id="cmtFileTrigger">첨부파일</button>
							<div class="cmt-img-div" id="fileZone"></div>
						</div>
						<div class="cmt-btn-div">
							<button type="button" class="cmt-btn" id="cmtRegBtn">입력</button>
						</div>
					</div>	
				</div>
			</div>
			</sec:authorize>
			<div class="cmtBox">
				<div class="product-commentList" id="commentList"></div>
			</div>
	</div>
			<div class="paging-div">
				<form id="productSearch" method="get">
					<select name="type" id="type">
						<option ${typed eq null? 'selected':'' }>선택</option>
						<option value="i" ${typed eq 'i'? 'selected':'' }>작성자</option>
						<option value="c" ${typed eq 'c'? 'selected':'' }>내용</option>
					</select>
					<input type="text" name="keyword" id="searchContent">
					<button type="button" id="reviewSearch">검색</button>
				</form>
				
				<div id="cmtPaging" data-page="1" style="visibility: hidden;">
					<ul id="pagination">
						<li>
						<a class="link" href=""><span aria-hidden="true">&laquo;</span></a>
					</li>
					<li>
						<a class="link" href="" aria-current="page"></a>
					</li>
					<li>
						<a class="link" href=""><span aria-hidden="true">&raquo;</span></a>
					</li>
					</ul>
				</div>
			</div>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
<script type="text/javascript">
let pnoVal = `<c:out value="${svo.pno}" />`;
let emailVal = `<c:out value="${authEmail}" />`;
let productNameVal = `<c:out value="${svo.productName}" />`;
let priceVal = `<c:out value="${svo.price}" />`;
</script>
<c:forEach items="${svo.list }" var="list">
	<c:if test="${list.main eq true}">
		<input type="hidden" id="uuidVal" value="${list.uuid }">
		<input type="hidden" id="saveDirVal" value="${list.saveDir }">
		<input type="hidden" id="fileNameVal" value="${list.fileName }">
	</c:if>
</c:forEach>
<script type="text/javascript" src="/resources/js/shop/detail.js"></script>
<script type="text/javascript" src="/resources/js/shop/ProductComment.js"></script>
<script type="text/javascript">
showCommentList(pnoVal);
</script>
</body>
</html>