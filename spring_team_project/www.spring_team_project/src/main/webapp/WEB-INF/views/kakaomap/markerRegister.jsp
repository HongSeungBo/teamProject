<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 Marker 추가</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/map/mapRegister.css">
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-straight/css/uicons-regular-straight.css'>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<body class="boby">
	<div class="con">
	
	<!-- 지도 영역 -->
	<div id="markerList"></div>
	<div id="map" style="width:1220px;height:800px;"></div>
	
	<div id="inputBox">
	이름 : <input name="shopName" id="shopName" placeholder="name"><br>
	번호 : <input id="num" placeholder="phonNum"><br>
	영업시작 시간 : <input type="time" id="stTime"><br>
	영업종료 시간 : <input type="time" id="edTime"><br>
	
	<select id="sec">
		<option selected="selected">카테고리</option>
		<option value="병원">병원</option>
		<option value="공원">공원</option>
		<option value="가게">가게</option>
		<option value="카페">카페</option>
		<option value="분양소">분양소</option>
	</select><br>
	
	<div id="menu">
		<button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" id="menuBtn">
		<i class="fi fi-rr-paw"></i>이용 가능 종
		</button>
		<div class="collapse" id="collapseExample">
			<div class="card card-body">
				<ul id="animalTypeBox">
					<li><label><input type="checkbox" class="animalType" name="animalType" value="개"><i class="fi fi-rr-dog"></i>개</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="고양이"><i class="fi fi-rs-cat"></i>고양이</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="거북이"><i class="fi fi-rr-turtle"></i>거북이</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="친질라"><i class="fi fi-rs-squirrel"></i>친질라</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="앵무"><i class="fi fi-rr-bird"></i>앵무</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="햄스터"><i class="fi fi-rs-squirrel"></i>햄스터</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="뱀"><i class="fi fi-rr-snake"></i>뱀</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="양서류"><i class="fi fi-rs-frog"></i>양서류</label></li>
					<li><label><input type="checkbox" class="animalType" name="animalType" value="관상어"><i class="fi fi-rs-fish"></i>관상어</label></li>
				</ul>
			</div>
		</div>
	</div><br>
	<input type="file" name="markerIMG" id="markerIMG" multiple="multiple" >
	<div id="filezone"></div><br>
	<div>
		<textarea rows="4" cols="50" placeholder="상세설명" id="markerDetail"></textarea>
	</div><br>
    <button type="button" id="addBtn">추가</button>
	</div>
		
		
	<!-- 인증키 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbba8da0fe2ce062b6eeace3e4daa44f"></script>
	<!-- 라이브러리 추가 -->
	<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script> -->
	
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.448488308314175, 126.7009818176601),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
	</div>
	<!-- <script type="text/javascript" src="/resources/js/map/mapList.js"></script> -->
	<script type="text/javascript" src="/resources/js/map/mapRegister.js"></script>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
</html>