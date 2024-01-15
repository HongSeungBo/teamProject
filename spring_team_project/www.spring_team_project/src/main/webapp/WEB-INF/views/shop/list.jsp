<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 홈</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/list.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body>
		<ul class="product-con">
			<c:forEach items="${list }" var="shop">
			<a href="/shop/detail?pno=${shop.pno }">
			<li class="shop-li">
				<div class="shop-list">
					<div class="product-img">
						<c:forEach items="${shop.list }" var="list">
							<div class="img-box">
								<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}">
							</div>
						</c:forEach>
					</div>
					<div class="product-info">
						<span class="avgScore"><i class="bi bi-star-fill"></i>${shop.avgScore }<span> ( <strong>${shop.cmtQty}</strong> ) <i class="bi bi-heart-fill"></i>${shop.wishCnt }</span></span><br>
						<input type="hidden" value="${shop.pno }">
						<span class="product-logo">상품명</span> : <span class="product-name">${shop.productName }</span><br>
						<span class="product-logo">가격</span> : <span class="product-price">${shop.price }</span>
						<div class="tagName-div">
							<ul>
								<c:forEach items="${shop.tagName }" var="tagName">
									<li>#${tagName }</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</li>
			</a>
			</c:forEach>
		</ul>
		<!-- 검색 -->
		<div class="paging-div">
			<form action="/shop/list" method="get">
				<c:set value="${ph.spvo.tagType }" var="tagTyped"></c:set>
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
				<c:set value="${ph.spvo.type }" var="typed"></c:set>
				<select name="type">
					<option ${typed eq null ? 'selected':'' }>선택</option>
					<option value="p" ${typed eq 'p' ? 'selected':'' }>상품명</option>
					<option value="c" ${typed eq 'c' ? 'selected':'' }>카테고리</option>
					<option value="pc" ${typed eq 'pc' ? 'selected':'' }>모두</option>
				</select>
				<input type="search" name="keyword" value="${ph.spvo.keyword }">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.spvo	.qty }">
				<button type="submit" class="submitBtn">찾기</button>
			</form>
			<!-- 페이징 -->
			<ul class="pagination">
				<li style="${(ph.prev eq false) ? 'display:none;' : '' }">
					<a href="/shop/list?pageNo=${ph.startPage-1 }&qty=${ph.spvo.qty }&type=${ph.spvo.type }&tagType=${ph.spvo.tagType }&keyword=${ph.spvo.keyword }">&laquo;</a>
				</li>
				<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
					<li>
						<a href="/shop/list?pageNo=${i }&qty=${ph.spvo.qty }&type=${ph.spvo.type }&tagType=${ph.spvo.tagType }&keyword=${ph.spvo.keyword }" class="paginglist ${(i eq ph.spvo.pageNo) ? 'active' : ''}">${i }</a>
					</li>
				</c:forEach>
				<li style="${(ph.next eq false) ? 'display:none;' : '' }">
					<a href="/shop/list?pageNo=${ph.endPage+1 }&qty=${ph.spvo.qty }&type=${ph.spvo.type }&tagType=${ph.spvo.tagType }&keyword=${ph.spvo.keyword }">&raquo;</a>
				</li>
			</ul>
		</div>
	
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
</html>