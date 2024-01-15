<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<link rel="stylesheet" type="text/css" href="/resources/css/shop/register.css">
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">

<body>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
	<form action="/shop/register" method="post" enctype="multipart/form-data">
		<div class="product-register">
			<div class="left-div">
				<div class="product-main-img" id="mainFileZone">
					<div id="mainFileImg"></div>
					<input type="file" name="files" id="mainFile" class="main-img-btn" style="visibility: hidden;" multiple="multiple">
					<button type="button" class="img-btn main-img-btn" id="mainFileBtn">이미지추가</button>
				</div>
				
				<div class="product-info">		
					<input type="text" name="productName" class="p-r-input product-name" placeholder="상품명">
					<select name="classification" class="p-r-select">
						<option value="">분류</option>
						<option value="Food">먹이</option>
						<option value="Supplement">영양제</option>
						<option value="Decorating">꾸미기</option>
						<option value="beauty">미용</option>
						<option value="washing">샴푸/비누</option>
					</select>
					<input type="text" name="price" class="p-r-input product-price" placeholder="상품가격">
					<input type="text" name="tag" class="p-r-input product-tag" id="tagName" placeholder="태그">
					<button type="button" id="tagBtn">태그추가</button>
					<div id="tagZone">
						
					</div>
				</div>
			</div>
			<div class="product-detail">
				<div class="detail-more">
					<textarea rows="" cols="" name="productContent" class="product-content" placeholder="상품 설명란"></textarea>
					<button type="button" class="img-btn sub-img-btn" id="subFilesBtn">이미지추가</button>
				</div>
				
				<div id="subFileZone"></div>
			</div>
			<input type="file" name="files" id="subFiles" style="visibility: hidden;" multiple="multiple">
			
			<div class="div-reg-btn">
				<button type="submit" id="regBtn" class="reg-btn">상품등록</button>
				<a href="/"><button type="button" class="reg-btn">취소</button></a>
			</div>
		</div>
	</form>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="/resources/js/shop/productRegister.js"></script>
</html>