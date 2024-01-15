<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/member/modify.css">
</head>
<body>
<c:set value="${mvo }" var="mvo"></c:set>
<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<div class="containerBox">
<h2>회원정보 수정</h2>
<form action="/member/modify" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
<div class="e">
	<h3>아이디</h3>
	  <input type="email" class="info-1" name="email" id="email" value="${mvo.email}" readonly="readonly">
	
	</div>
	<div class="p">
	  <h3>비밀번호</h3>
	  <input type="password" class="info" name="pwd" id="pwd" placeholder="password">
	  <div id="error-message-pwd"></div>
	</div>
	
	<div class="nick">
	  <h3>닉네임</h3>
	  <input type="text" class="info" name="nickName" id="nick" value="${NickName}">
	</div>
	
	<div class="name">
	  <h3>이름</h3>
	  <input type="text" class="info-1" name="name" id="name" value="${mvo.name }" placeholder="이름" readonly="readonly">
	</div>
	
	<div class="phone">
	  <h3>전화번호</h3>
	  <input type="tel" class="info" name="phon1" id="phon" value="010" readonly="readonly">
	  - <input type="tel" class="info" name="phon2" value="${phon2}" id="phon">
	  - <input type="tel" class="info" name="phon3" value="${phon3}" id="phon">
	</div>
	<!-- 생년월일 -->
	<div class="age">
    <h3>생년월일</h3>
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
	</div> 
	
	

	
	<div class="country">	
	<h3>주소</h3>
	<div class="address">
    <div class="select-container">
        <select id="regionSelect" name="region">
              <option ${region eq null ? 'selected':'' }>지역</option>
              <option value="서울특별시" ${region eq '서울특별시' ? 'selected':'' }>서울특별시</option>
              <option value="부산광역시" ${region eq '부산광역시' ? 'selected':'' }>부산광역시</option>  
		      <option value="대구광역시" ${region eq '대구광역시' ? 'selected':'' }>대구광역시</option>
		      <option value="인천광역시" ${region eq '인천광역시' ? 'selected':'' }>인천광역시</option>
			  <option value="광주광역시" ${region eq '광주광역시' ? 'selected':'' }>광주광역시 </option>
			  <option value="대전광역시" ${region eq '대전광역시' ? 'selected':'' }>대전광역시 </option>
			  <option value="울산광역시" ${region eq '울산광역시' ? 'selected':'' }>울산광역시 </option>
			  <option value="세종특별자치시" ${region eq '세종특별자치시' ? 'selected':'' }>세종특별자치시 </option>
			  <option value="경기도" ${region eq '경기도' ? 'selected':'' }>경기도</option>
			  <option value="강원도" ${region eq '강원도' ? 'selected':'' }>강원도</option>
		      <option value="충청북도" ${region eq '충청북도' ? 'selected':'' }>충청북도</option>
		      <option value="충청남도" ${region eq '충청남도' ? 'selected':'' }>충청남도</option>
		      <option value="전라북도" ${region eq '전라북도' ? 'selected':'' }>전라북도</option>
		      <option value="전라남도" ${region eq '전라남도' ? 'selected':'' }>전라남도</option>
		      <option value="경상북도" ${region eq '경상북도' ? 'selected':'' }>경상북도</option>
		      <option value="경상남도" ${region eq '경상남도' ? 'selected':'' }>경상남도</option>
		      <option value="제주특별자치도" ${region eq '제주특별자치도' ? 'selected':'' }>제주특별자치도</option>
	  </select>
       
    </div>
	  
     
    <div class="select-container">
    <select id="subRegionSelect" name="subRegion">
        <c:choose>
            <c:when test="${subRegion != null}">
                <option value="<c:out value='${subRegion}'/>" selected><c:out value='${subRegion}'/></option>
            </c:when>
            <c:otherwise>
                <option value="" selected>시/군/구</option>
            </c:otherwise>
        </c:choose>
    </select>
</div>
	</div>
     <div class="select-container">
     	<input type="text" class="infoaddress" name="detailaddress" placeholder="상세주소" value="${detailAddress }">
     </div>
	</div>
	
	 <div class="input-container picture">
        <h3>프로필사진</h3>
        <input type="file" class="info" name="file" id="file" style="display: none;" multiple="multiple">
     	<button type="button" id="trigger" class="uploadBtn">사전첨부</button>
     </div>

      <div class="input-container profile" id="profile">
      	<c:if test="${mfvo ne null }">
			<img id="profileImage" src="/upload/${fn:replace(mfvo.saveDir,'\\','/')  }/${mfvo.uuid}_${mfvo.fileName}">
		</c:if>
		<c:if test="${mfvo eq null }">
			<img id="profileImage" src="/resources/image/default-imge.png">
		</c:if>
      </div>
	
	<button type="submit" class="btn" id="rregBtn">등록</button>
</form>	
</div>
<script type="text/javascript">
const birthyear = `<c:out value="${birthyear}"/>`;
const birthmonth = `<c:out value="${birthmonth}"/>`;
const birthday = `<c:out value="${birthday}"/>`;
</script>
<jsp:include page="../common/jsp/footer.jsp"></jsp:include>

<script type="text/javascript" src="/resources/js/member/modify.js"></script>
</body>
</html>