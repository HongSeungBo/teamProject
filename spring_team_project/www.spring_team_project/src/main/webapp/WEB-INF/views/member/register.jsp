<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/member/register.css">
</head>
<body>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<div class="containerBox">
	<h2>회원정보 입력</h2><span class="spt">* 필수입력사항</span>
<form action="/member/register" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
	<div class="e">
	<h3><span class="sptag">* </span>아이디</h3>
	<input type="email" class="info" name="email" id="email" placeholder="example@OOO.com" value="${mvo.email}"> 
	<div id="error-message" style="color: red;"></div>
	<button type="button" onclick="checkEmail()" class="Checkbtn">아이디 중복 확인</button>
	</div>
	<div class="p">
	  <h3><span class="sptag">* </span>비밀번호</h3>
	  <input type="password" class="info" name="pwd" id="pwd" placeholder="password" value="${mvo.pwd}">
	<div id="error-message-pwd" style="color: red;"></div>
	</div>
	<div class="nick">
	  <h3><span class="sptag">* </span>닉네임</h3>
	  <input type="text" class="info" name="nickName" id="nick" placeholder="nick_name" value="${mvo.nickName}">
	<div id="error-message-nick" style="color: red;"></div>
	</div>
	<div class="name">
	  <h3><span class="sptag">* </span>이름</h3>
	  <input type="text" class="info" name="name" id="name" placeholder="이름" value="${mvo.name}">
	<div id="error-message-name" style="color: red;"></div>
	</div>
	<div class="phone">
	  <h3><span class="sptag">* </span>전화번호</h3>
	  <input type="tel" class="info" name="phon1" id="phon" value="010" readonly="readonly">
	  - <input type="tel" class="info" name="phon2" id="phon1">
	  - <input type="tel" class="info" name="phon3" id="phon2">
	<div id="error-message-phon" style="color: red;"></div>
	</div>
	<div class="age">
    <h3><span class="sptag">* </span>생년월일</h3>
    <div class="select-container">
    <select id="birthYearSelect" name="birthYear">
        <option value="">년도 선택</option>
    </select>
	</div>

	<div class="select-container">
	    <select id="birthMonthSelect" name="birthMonth">
	        <option value="">월 선택</option>
	    </select>
	</div>
	
	<div class="select-container">
	    <select id="birthDaySelect" name="birthDay">
	        <option value="">일 선택</option>
	    </select>
	</div>
	<div id="error-message-birth" style="color: red;"></div>
	</div>
	
	<div class="country">	
	<h3><span class="sptag">* </span>주소</h3>
	<div class="address">
    <div class="select-container">
        <select id="regionSelect" name="region">
              <option value="">지역</option>
              <option value="서울특별시">서울특별시 </option>
              <option value="부산광역시">부산광역시 </option>  
		      <option value="대구광역시">대구광역시 </option>
		      <option value="인천광역시">인천광역시 </option>
			  <option value="광주광역시">광주광역시 </option>
			  <option value="대전광역시">대전광역시 </option>
			  <option value="울산광역시">울산광역시 </option>
			  <option value="세종특별자치시">세종특별자치시 </option>
			  <option value="경기도">경기도 </option>
			  <option value="강원도">강원도 </option>
		      <option value="충청북도">충청북도 </option>
		      <option value="충청남도">충청남도 </option>
		      <option value="전라북도">전라북도 </option>
		      <option value="전라남도">전라남도 </option>
		      <option value="경상북도">경상북도 </option>
		      <option value="경상남도">경상남도 </option>
		      <option value="제주특별자치도">제주특별자치도</option>
	  </select>
       
    </div>
	  
     <div class="select-container">
        <select id="subRegionSelect" name="subRegion">
            <option value="">시/군/구</option>  
        </select>
    </div> 
	</div>
     <div class="select-container">
     	<input type="text" class="infoaddress" id="detailAddr" name="detailaddress" placeholder="상세주소">
     </div>
     <div id="error-message-address" style="color: red;"></div>
	</div>
	
	 <div class="input-container picture">
        <h3>프로필사진</h3>
        <input type="file" class="info" name="file" id="file" style="display: none;" multiple="multiple">
     	<button type="button" id="trigger" class="uploadBtn">사전첨부</button>
     </div>

      <div class="input-container profile" id="profile">
			<img id="profileImage" name="file" src="/resources/image/default-imge.png">
      </div>
	<input hidden="true" name="platform" value="${mvo.platform}">
	
	<button type="submit" class="btn" id="rregBtn">등록</button>
</form>	
</div>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/member/register.js"></script>
</body>
</html>