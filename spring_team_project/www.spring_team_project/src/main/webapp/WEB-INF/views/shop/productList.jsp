<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/productList.css">
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
</head>
<body>
<div class="scroll-senser">
	<div class="scroll-smalldiv"><span><i class="bi bi-caret-up scroll" id="scrollToTop"></i></span></div>
	<div class="scroll-smalldiv"><span><i class="bi bi-caret-down scroll" id="scrollToBottom"></i></span></div>
</div>
		<div class="product-list">
			<c:forEach items="${list }" var="shop">
			<div class="product-li">
				<div class="product-header">
					<div class="product-left">
						<div class="left-header">
							<div class="header-info"><a href="/shop/modify?pno=${shop.pno }"><span class="pno">${shop.pno }</span></a></div>
							<div class="header-info"><a href="/shop/modify?pno=${shop.pno }"><span class="productName">${shop.productName }</span></a></div>
						</div>
						<div class="left-body">
							<div class="header-info tagBox">
								<c:forEach items="${shop.tagName }" var="tagName">
										<div class="tagBox-tagName">#${tagName }</div>
								</c:forEach></div>
							<div class="header-info classification">${shop.classification }</div>
							<div class="header-info avgScore"><i class="bi bi-star-fill"></i>${shop.avgScore }</div>
						</div>
					</div>
				</div>
				<div class="productContent">${shop.productContent }</div>
				<div class="product-img">
					<c:forEach items="${shop.list }" var="list">
						<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}">
					</c:forEach>
				</div>
				<div class="product-footer">
					<div class="footerBox">
						<div class="footer-info regAt">${shop.regAt }</div>
						<div class="footer-info cmtQty">리뷰 : ${shop.cmtQty }</div>
					</div>
					<button type="button" class="DelBtn" data-pno="${shop.pno }">상품 삭제</button>
				</div>
			</div>
			</c:forEach>
		</div>
	<div class="paging-div">
		<form action="/shop/list" method="get">
			<c:set value="${ph.ppvo.tagType }" var="tagTyped"></c:set>
			<select name="tagType" id="tagType">
				<option ${typed eq null ? 'selected':'' }>태그 선택</option>
				<option value="p" ${tagTyped eq 'p' ? 'selected':'' }>앵무새</option>
				<option value="l" ${tagTyped eq 'l' ? 'selected':'' }>도마뱀</option>
				<option value="h" ${tagTyped eq 'h' ? 'selected':'' }>고슴도치</option>
				<option value="d" ${tagTyped eq 'd' ? 'selected':'' }>오리너구리</option>
				<option value="s" ${tagTyped eq 's' ? 'selected':'' }>뱀</option>
				<option value="x" ${tagTyped eq 'x' ? 'selected':'' }>여우</option>
				<option value="g" ${tagTyped eq 'g' ? 'selected':'' }>기니피그</option>
				<option value="r" ${tagTyped eq 'r' ? 'selected':'' }>토끼</option>
				<option value="t" ${tagTyped eq 't' ? 'selected':'' }>거북이</option>
				<option value="c" ${tagTyped eq 'c' ? 'selected':'' }>카멜레온</option>
				<option value="m" ${tagTyped eq 'm' ? 'selected':'' }>두더지</option>
				<option value="o" ${tagTyped eq 'o' ? 'selected':'' }>설치류</option>
				<option value="f" ${tagTyped eq 'f' ? 'selected':'' }>어류</option>
			</select>
			<c:set value="${ph.ppvo.type }" var="typed"></c:set>
			<select name="type">
				<option ${typed eq null ? 'selected':'' }>선택</option>
				<option value="p" ${typed eq 'p' ? 'selected':'' }>상품명</option>
				<option value="c" ${typed eq 'c' ? 'selected':'' }>카테고리</option>
				<option value="pc" ${typed eq 'pc' ? 'selected':'' }>모두</option>
			</select>
			<input type="search" name="keyword" value="${ph.ppvo.keyword }">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.ppvo.qty }">
			<button type="submit" class="submitBtn">찾기</button>
		</form>
		<ul class="pagination">
			<li style="${(ph.prev eq false) ? 'display:none;' : '' }">
				<a href="/shop/productList?pageNo=${ph.startPage-1 }&qty=${ph.ppvo.qty }&type=${ph.ppvo.type }&tagType=${ph.ppvo.tagType }&keyword=${ph.ppvo.keyword }">&laquo;</a>
			</li>
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li>
					<a href="/shop/productList?pageNo=${i }&qty=${ph.ppvo.qty }&type=${ph.ppvo.type }&tagType=${ph.ppvo.tagType }&keyword=${ph.ppvo.keyword }" class="paginglist ${(i eq ph.ppvo.pageNo) ? 'active' : ''}">${i }</a>
				</li>
			</c:forEach>
			<li style="${(ph.next eq false) ? 'display:none;' : '' }">
				<a href="/shop/productList?pageNo=${ph.endPage+1 }&qty=${ph.ppvo.qty }&type=${ph.ppvo.type }&tagType=${ph.ppvo.tagType }&keyword=${ph.ppvo.keyword }">&raquo;</a>
			
			</li>
		</ul>
	</div>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/shop/productList.js"></script>
</body>
</html>