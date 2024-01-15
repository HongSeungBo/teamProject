<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보수정</title>
<link rel="stylesheet" type="text/css" href="/resources/css/shop/modify.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
</head>
<body>

	
		<form action="/shop/modify" method="post" enctype="multipart/form-data" id="modiForm">
		<div class="product-register">
		<input type="hidden" name="pno" value="${svo.pno }" readonly="readonly">
			<div class="left-div">
				<div class="product-main-img" id="mainFileZone">
					<div id="mainFileImg"></div>
					<input type="file" name="files" id="mainFile" class="main-img-btn" style="visibility: hidden;" multiple="multiple">
					<button type="button" class="img-btn main-img-btn" id="mainFileBtn">이미지추가</button>
				</div>
				<div class="main-file-div">
					<c:forEach items="${svo.list }" var="list">
						<c:if test="${list.main eq true }">
							<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}" class="prevMainImg">
							<button class="delMainBtn delBtn" data-uuid="${list.uuid }">X</button>
						</c:if>
					</c:forEach>
				</div>
				<div class="product-info">		
					<input type="text" name="productName" class="p-r-input product-name" value="${svo.productName }">
					<select name="classification" class="p-r-select">
						<option value="Food" ${svo.classification eq 'Food'? 'selected':''}>먹이</option>
						<option value="Decorating" ${svo.classification eq 'Decorating'? 'selected':''}>꾸미기</option>
					</select>
					<input type="text" name="price" class="p-r-input product-price" value="${svo.price }">
					<input type="text" name="tag" class="p-r-input product-tag" id="tagName" placeholder="태그">
					<button type="button" id="modTagBtn">태그추가</button>
					<div >
						<div class="tagName-div">
							<ul id="modTagZone">
								<c:forEach items="${svo.tagName }" var="tag">
									<li>#${tag } <button type="button" class="exTagDelBtn">태그삭제</button>
									<input type="hidden" name="tagName" value="${tag }"></li>
								</c:forEach>
							</ul>
							
						</div>
					</div>
				</div>
			</div>
			<div class="product-detail">
				<input type="file" name="files" id="subFiles" style="display: none;" multiple="multiple">
				<div class="detail-more">
					<textarea rows="" cols="" name="productContent" class="product-content">${svo.productContent }</textarea>
					<button type="button" class="img-btn sub-img-btn" id="subFilesBtn">이미지추가</button>
				</div>
				
				<div id="subFileZone"></div>
					<div class="subfiles">
						<c:forEach items="${svo.list }" var="list">
							<c:if test="${list.main eq false }">
								<div class="sub-file-div">
									<img src="/upload/${fn: replace(list.saveDir, '\\', '/')}/${list.uuid}_th_${list.fileName}" alt="${list.fileName}" class="prevSubImg">
									<button class="delSubBtn delBtn" data-uuid="${list.uuid }">X</button>
								</div>
							</c:if>
						</c:forEach>
					</div>
			</div>
			
			<div class="div-reg-btn">
				<button type="submit" id="regBtn" class="reg-btn">수정</button>
				<button type="button" class="reg-btn" id="regregBtn">취소</button>
			</div>
		</div>
	</form>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/shop/modify.js"></script>
	<script type="text/javascript" src="/resources/js/shop/productRegister.js"></script>
</body>
</html>