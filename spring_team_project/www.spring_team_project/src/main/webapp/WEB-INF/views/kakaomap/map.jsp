<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 시작하기</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/map/map.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-straight/css/uicons-regular-straight.css'>
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>

<!-- <i class="fi fi-rr-paw"></i>

<i class="fi fi-rs-fox"></i>
<i class="fi fi-rr-bird"></i>
<i class="fi fi-rs-fish"></i>
<i class="fi fi-rr-snake"></i>
<i class="fi fi-rs-cat"></i>
<i class="fi fi-rs-crow"></i>
<i class="fi fi-rr-dog"></i>
<i class="fi fi-rs-frog"></i>
<i class="fi fi-rs-squirrel"></i>
<i class="fi fi-rs-turtle"></i>

<i class="fi fi-rs-shop"></i>
<i class="fi fi-rr-hospital"></i>
<i class="fi fi-rr-bench-tree"></i>
<i class="fi fi-rs-usd-circle"></i>
<i class="fi fi-rs-following"></i> -->

<body class="boby">
	<div class="con">

	
	<!-- <a href="/kakaomap/markerModify">markerModify</a>
	<a href="/kakaomap/register">장소추가</a>
	<br> -->
	

	<!-- 지도 영역 -->
	
	<div id="map" style="width:100%;height:835px;"></div>
	<div id="sidebox">
	<div>
		<div class="seachBox">
		<button id="toggleButton"><</button>
			<div id="searchdiv">
				<button id="search"><i class="bi bi-search"></i></button>
				<input name="shopName" id="shopName" placeholder="검색">
				<select id="sec">
					<option selected="selected" value="">...</option>
					<option value="병원">병원</option>
					<option value="공원">공원</option>
					<option value="가계">가계</option>
					<option value="카페">카페</option>
					<option value="분양소">분양소</option>
				</select>
			</div>
			
			<div id="searchdiv2">
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
				</div>
			</div>
		</div>
		<div id="markerList">
		
		</div>
	</div>
		<div class="markaerDetail">
			<div id="filezone"></div>
			<br>
			<h2 id="markershopName">1</h2><br>
			<div>분류 : <label id="markersec">2</label></div><br>
			<div><i class="fi fi-rr-paw"></i>이용 가능 종 : <label id="markeranimalType">3</label></div><br>
			<div><i class="fi fi-rs-clock-five"></i>영업시간 : <label id="markerstTime">4</label> ~ <label id="markeredTime">5</label></div><br>
			<div><i class="fi fi-rs-phone-rotary"></i>연락처 : <label id="markernum">6</label></div><br>
			<div><label id="markerdetail">7</label></div><br>
			<a href="" id="markerURL"></a><br>
			<div id="markarDetail"></div>
			<button id="markaerDetailHidden" onclick="markaerDetailHidden()">X</button>
		</div>
	</div>
		
		
	<!-- 인증키 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbba8da0fe2ce062b6eeace3e4daa44f"></script>
	<!-- 라이브러리 추가 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbba8da0fe2ce062b6eeace3e4daa44f&libraries=services,clusterer,drawing"></script>
	
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.448488308314175, 126.7009818176601),
			level: 3
		};
		var map = new kakao.maps.Map(container, options);
	</script>
	</div>
	<script type="text/javascript" src="/resources/js/map/mapList.js"></script>
	<jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
</html>