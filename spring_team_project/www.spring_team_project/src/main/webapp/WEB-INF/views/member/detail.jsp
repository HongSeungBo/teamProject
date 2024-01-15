<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/member/detail.css">
</head>
<body>
        <jsp:include page="../common/jsp/header.jsp"></jsp:include>
    <div class="container">
        <c:set value="${mvo}" var="mvo"></c:set>
        <div class="modiBox">
        <a href="/member/modify?email=${mvo.email}">
            <button type="button" class="modiBtn">회원정보수정</button>
        </a>
       </div>
        <div class="detailBox">
            <ul class="detailUl">
                <li class="detailLi"><strong>아이디:</strong> ${mvo.email}</li>
                <li class="detailLi"><strong>닉네임:</strong> ${mvo.nickName}</li>                
                <li class="detailLi"><strong>이름:</strong> ${mvo.name}</li>
                <li class="detailLi"><strong>주소:</strong> ${mvo.address}</li>
                <li class="detailLi"><strong>전화번호:</strong> ${mvo.phonNum}</li>
                <li class="detailLi"><strong>생년월일:</strong> ${mvo.birth}</li>
                <li class="detailLi"><strong>계정 생성일:</strong> ${mvo.regAt}</li>
                <li class="detailLi"><strong>마지막 로그인:</strong> ${mvo.lastLogin}</li>
                <li class="detailLi"><strong>프로필 사진</strong> <div class="input-container profile" id="profile">
      	<c:if test="${mfvo ne null }">
			<img id="profileImage" src="/upload/${fn:replace(mfvo.saveDir,'\\','/')  }/${mfvo.uuid}_${mfvo.fileName}">
		</c:if>
		<c:if test="${mfvo eq null }">
			<img id="profileImage" src="/resources/image/default-imge.png">
		</c:if>
      </div></li>
            </ul>
        </div>
        <div class="delBox">
         <a href="/member/remove?email=${mvo.email}">
            <button type="button" class="delBtn">회원탈퇴</button>
        </a>
        </div>
    </div>
        <jsp:include page="../common/jsp/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    let isDelValue = "${mvo.isDel}";
  
    if (isDelValue !== 'N') {
    	let modBox = document.querySelector('.modiBox');
        let delBox = document.querySelector('.delBox');
        if (delBox) {
            delBox.style.display = 'none';
            modBox.style.display = 'none';
        }
    }
});
</script>
</html>