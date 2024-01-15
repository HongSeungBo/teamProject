<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="/resources/css/common/header.css">
</head>
<body>

<div class="header">
	<div class="subHeader">
		<ul class="subHeaderUl">
		
		<sec:authorize access="isAnonymous()">
			<li class="subHeaderLi"><a href="/member/login" class="subHeaderLiA">로그인</a></li>
			<li class="subHeaderLi"><a href="/member/register" class="subHeaderLiA">회원가입</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.mvo.email" var="authEmail"/>
        	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
        	<sec:authentication property="principal.mvo.authList" var="auths"/>
			
			<c:choose>
				<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
				<li class="subHeaderLi"><a href="/payment/refundList" class="subHeaderLiA">환불목록</a></li>
				<li class="subHeaderLi"><a href="/member/list" class="subHeaderLiA">회원목록</a></li>
				</c:when>
			</c:choose>
		</sec:authorize>
			<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.mvo.email" var="authEmail"/>
			<li class="subHeaderLi"><a href="/member/logout" class="subHeaderLiA">로그아웃</a></li>
			<li class="subHeaderLi"><a href="/shop/mywish?email=${authEmail }" class="subHeaderLiA">찜목록</a></li>
			<li class="subHeaderLi"><a href="/shop/myCart?email=${authEmail }" class="subHeaderLiA">장바구니</a></li>
			<li class="subHeaderLi"><a href="/member/detail?email=${authEmail}" class="subHeaderLiA">회원정보</a></li>
			<li class="subHeaderLi">
				<div class="input-container profile" id="profile_div">
			      	
						<img id="profileImage_img">
					
	     		 </div>
     		 </li>
			</sec:authorize>
		</ul>
		<form action="/member/logout" method="post" id="logoutForm">
	        <input type="hidden" max="email" value="${authEmail }">
        </form>
	</div>
	<div class="mainHeader">
	
    <ul class="mainHeaderUl">
    	<li class="mainHeaderLi">
    		<a href="/" class="mainHeaderLiA"><img src="/resources/image/tinyPet.png" alt="로고" class="tiny-logo"></a>
    		<div class="submenu">
		        <ul class="submenuUl ">
		        </ul>
	        </div>
    	</li>
        <li class="mainHeaderLi">
            <a href="/kakaomap/map" class="mainHeaderLiA"> 지도</a>
            <div class="submenu">

                <ul class="submenuUl">
	                <sec:authorize access="isAuthenticated()">
	                    <li class="submenuLi"><a href="/kakaomap/register" class="submenuLiA">마커추가</a></li>
	                </sec:authorize>
                </ul>
            </div>
        </li>
        <li class="mainHeaderLi">
	        <a href="/shop/list" class="mainHeaderLiA">쇼핑몰</a>
	        <div class="submenu">
	        <ul class="submenuUl">
            <sec:authorize access="isAuthenticated()">
		        <sec:authentication property="principal.mvo.email" var="authEmail"/>
		        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
		        <sec:authentication property="principal.mvo.authList" var="auths"/>
	                <c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
					    <li class="submenuLi"><a href="/shop/register" class="submenuLiA">상품등록</a></li>
					    <li class="submenuLi"><a href="/shop/productList" class="submenuLiA">상품관리</a></li>
	                </c:if>
			</sec:authorize>
				    	<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.mvo.email" var="authEmail"/>
					    	
				    	</sec:authorize>
	                </ul>
	        </div>
        </li>
        <li class="mainHeaderLi">
            <a href="/board/list" class="mainHeaderLiA">게시판</a>
            <div class="submenu">
                <ul class="submenuUl"> 
                <sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.mvo.email" var="authEmail"/>
				 <li class="submenuLi"><a href="/board/register" class="submenuLiA">글 쓰기</a></li>
			</sec:authorize>
                </ul>
            </div>
        </li>
        <li class="mainHeaderLi">
            <a href="/announcement/list" class="mainHeaderLiA">공지사항</a>
            <div class="submenu">
                <ul class="submenuUl">
                 <sec:authorize access="isAuthenticated()">
			        <sec:authentication property="principal.mvo.email" var="authEmail"/>
			        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
			        <sec:authentication property="principal.mvo.authList" var="auths"/>
		        		<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
                 	 	  <li class="submenuLi"><a href="/announcement/register" class="submenuLiA">공지쓰러가기</a></li>
                  		</c:if>
                    </sec:authorize>
                </ul>
            </div>
        </li>
    </ul>
</div>
</div>
<!-- <script>
  $(document).ready(function () {
    $('.mainHeaderLi').each(function () {
      if ($(this).find('.submenu').length > 0) {
        $(this).addClass('submenu');
      }
    });
  });
</script> -->
<script type="text/javascript" src="/resources/js/common/header.js"></script>
</body>
</html>