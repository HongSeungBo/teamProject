<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>마커 수정</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/map/mapModify.css">
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>
	<link rel='stylesheet' href='https://cdn-uicons.flaticon.com/2.0.0/uicons-regular-straight/css/uicons-regular-straight.css'>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<%-- <jsp:include page="../common/jsp/header.jsp"></jsp:include> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<body class="boby">
	<div class="con">
	
	<!-- 지도 영역 -->
	<div id="map" style="width:540px;height:400px;"></div>
	
	
	<div id="formBox">
	<form action="/kakaomap/markerModify" method="post" enctype="multipart/form-data" id="form">
		<input name="mno" hidden="true" value="${mdto.getMvo().getMno()}">
		<input name="lat" id="lat" hidden="true" value="${mdto.getMvo().getLat()}">
		<input name="lon" id="lon" hidden="true" value="${mdto.getMvo().getLon()}">
		
		
		<%-- <input id="markermno" name="mno" value="${mdto.getMvo().getMno()}" readonly="readonly"> --%>
		이름 : <input id="markershopName" name="shopName" value="${mdto.getMvo().getShopName()}">
		<select id="sec" name="sec">
			<option selected="selected" value="">카테고리</option>
			<option value="병원"  ${mdto.getMvo().getSec() eq '병원'? 'selected': ''}>병원</option>
			<option value="공원" ${mdto.getMvo().getSec() eq '공원'? 'selected': ''}>공원</option>
			<option value="가계" ${mdto.getMvo().getSec() eq '가계'? 'selected': ''}>가계</option>
			<option value="카페" ${mdto.getMvo().getSec() eq '카페'? 'selected': ''}>카페</option>
			<option value="분양소" ${mdto.getMvo().getSec() eq '분양소'? 'selected': ''}>분양소</option>
		</select>
			<div id="menu">
					<button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" id="menuBtn">
					<i class="fi fi-rr-paw"></i>이용 가능 종
					</button>
					<div class="collapse" id="collapseExample">
				      <div class="card card-body">
					  	<ul id="animalTypeBox">
						    <li><label><input type="checkbox" id="개" class="animalType" name="animalType" value="개"><i class="fi fi-rr-dog"></i>개</label></li>
						    <li><label><input type="checkbox" id="고양이" class="animalType" name="animalType" value="고양이"><i class="fi fi-rs-cat"></i>고양이</label></li>
						    <li><label><input type="checkbox" id="거북이" class="animalType" name="animalType" value="거북이"><i class="fi fi-rr-turtle"></i>거북이</label></li>
						    <li><label><input type="checkbox" id="친질라" class="animalType" name="animalType" value="친질라"><i class="fi fi-rs-squirrel"></i>친질라</label></li>
						    <li><label><input type="checkbox" id="앵무" class="animalType" name="animalType" value="앵무"><i class="fi fi-rr-bird"></i>앵무</label></li>
						    <li><label><input type="checkbox" id="햄스터" class="animalType" name="animalType" value="햄스터"><i class="fi fi-rs-squirrel"></i>햄스터</label></li>
						    
						    <li><label><input type="checkbox" id="뱀" class="animalType" name="animalType" value="뱀"><i class="fi fi-rr-snake"></i>뱀</label></li>
						    <li><label><input type="checkbox" id="양서류" class="animalType" name="animalType" value="양서류"><i class="fi fi-rs-frog"></i>양서류</label></li>
						    <li><label><input type="checkbox" id="관상어" class="animalType" name="animalType" value="관상어"><i class="fi fi-rs-fish"></i>관상어</label></li>
					  	</ul>
				      </div>
				    </div>
				</div><br>
		영업시간 : <input id="markerstTime" name="stTime" value="${mdto.getMvo().getStTime()}" type="time"><input id="markeredTime" name="edTime" value="${mdto.getMvo().getEdTime()}" type="time">
		<br>
		전화번호 : <input id="markernum" name="num" value="${mdto.getMvo().getNum()}"><br>
		상세정보<br>
		<textarea rows="4" cols="50" id="markerdetail" name="detail">${mdto.getMvo().getDetail()}</textarea>
		<br>
		url : <input type="text" name="url" id="markerURL" value="${mdto.getMvo().getUrl()}">
		<br>
		<input type="file" name="files" id="markerIMG" multiple="multiple" >
			<c:forEach items="${mdto.getFiles()}" var="file">
				<div id="${file.uuid}" <%-- data-uuid="${file.uuid}" --%>>
					<input hidden="true" value="${file.uuid}" name="uuid">
					<img alt="오류" src="/upload/${file.saveDir}/${file.uuid}_th_${file.fileName}">
					<button class="imgDelete" type="button">X</button>
				</div>
			</c:forEach>
		<div id="filezone">
		</div>
	<button type="button" onclick="submitFormAndClose()">수정</button>
	</form>
	</div>
		
		
	<!-- 인증키 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbba8da0fe2ce062b6eeace3e4daa44f"></script>
	<!-- 라이브러리 추가 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bbba8da0fe2ce062b6eeace3e4daa44f&libraries=services,clusterer,drawing"></script>
	
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(${mdto.getMvo().getLat()}, ${mdto.getMvo().getLon()}),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
	</script>
	<script type="text/javascript">
		let animalType = `<c:out value="${mdto.getMvo().getAnimalType()}"></c:out>`
	</script>
	<script type="text/javascript" src="/resources/js/map/markerModify.js"></script>
	</div>
</body>
</html>