<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resources/css/member/login.css">
</head>
<body>
   <jsp:include page="../common/jsp/header.jsp"></jsp:include>
<form action="/member/login" method="post" class="loginForm">
<div class="loginBox">
	<div class="emailBox">
	  <span>EMAIL</span>
	  <input type="email" class="form-control" name="email" id="e" placeholder="example@OOO.com">
	</div>
	<div class="passWordBox">
	  <span>PASSWORD</span>
	  <input type="password" class="form-control" name="pwd" id="p" placeholder="password">
	</div>
	
	<c:if test="${not empty param.errMsg }">
		<div class="text-danger mb-3">
			<c:choose>
				<c:when test="${param.errMsg eq 'Bad credentials' }">
					<c:set var="errText" value="이메일 & 비밀번호가 일치하지 않습니다."></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="errText" value="관리자에게 문의해주세요"></c:set>
				</c:otherwise>
			</c:choose>
			
			${errText }
		</div>
	</c:if>
<button type="submit" class="loginBtn" id="login">로그인</button>
</div>	
<div class="searchPwdRegister"><a href="/member/FindMvoInfo" class="searchpass">비밀번호 찾기</a><a href="/member/register" class="register"> 회원가입하기</a></div>

</form>
<div class="otherRegister">

<div id="naver_id_login"></div>
<a href="/oauth/googleLogin" class="googleLogin" ><img alt="" src="/resources/image/web_light_sq_ctn@2x.png"  class="googleLoginBtn"></a>
<img alt="" src="/resources/image/kakao_login_medium_narrow.png" onclick="kakaoLogin();" class="kakoLogin">

</div>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script src="https://accounts.google.com/gsi/client" async></script>
	
		  		
			    <script type="text/javascript">
			        var naver_id_login = new naver_id_login("oK8TxRQic6qm3pRXmpDu", "http://localhost:8088/member/naverLogin");
			        var state = naver_id_login.getUniqState();
			        naver_id_login.setButton("white", 10,44);
			        naver_id_login.setDomain("http://localhost:8088/");
			        naver_id_login.setState(state);
			        naver_id_login.init_naver_id_login();
			    </script>
<!-- 			<ul>
				<li onclick="kakaoLogout();">
			          <span>카카오 로그아웃</span>
				</li>
			</ul> -->
			
			<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
			<script>
			async function getEmail(email){
				  try {
				      const resp = await fetch('/member/getEmail/'+email);
				      const result = await resp.json();
				      return result;
				  } catch (error) {
				      console.log(error);
				  }
				}

			Kakao.init('47a120da454c0c25535d076446daac13'); //발급받은 키 중 javascript키를 사용해준다.
			console.log(Kakao.isInitialized()); // sdk초기화여부판단
			//카카오로그인
			function kakaoLogin() {
			    Kakao.Auth.login({
			      success: function (response) {
			        Kakao.API.request({
			          url: '/v2/user/me',
			          success: function (response) {
			        	  console.log(response)
			        	    let email = response.kakao_account.email;
							console.log(email);
							getEmail(email).then(result=>{
								console.log(result);
								if(result == 1){
									document.getElementById("emailLoin").value = response.kakao_account.email;
									document.getElementById('login').submit();
								}else{
									document.getElementById("emailRegister").value = response.kakao_account.email;
									document.getElementById("nameRegister").value = response.properties.nickname;
									document.getElementById('regiser').submit();
								}
							})
			          },
			          fail: function (error) {
			            console.log(error)
			          },
			        })
			      },
			      fail: function (error) {
			        console.log(error)
			      },
			    })
			  }
			//카카오로그아웃  
			function kakaoLogout() {
			    if (Kakao.Auth.getAccessToken()) {
			      Kakao.API.request({
			        url: '/v1/user/unlink',
			        success: function (response) {
			        	console.log(response)
			        },
			        fail: function (error) {
			          console.log(error)
			        },
			      })
			      Kakao.Auth.setAccessToken(undefined)
			    }
			  }  
			</script>
			
			<form action="/member/login" method="post" id="login">
				<input id="emailLoin" name="email" hidden="true">
				<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
			</form>
	
	
			<form action="/member/KakaoRegister" method="get" id="regiser">
				<input id="emailRegister" name="email" hidden="true">
				<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
				<input id="nameRegister" name="name" hidden="true">
			</form>
			
 <jsp:include page="../common/jsp/footer.jsp"></jsp:include>
 
 <script type="text/javascript">
 const isOk = `<c:out value="${isOk}" />`;
 console.log(isOk);
 if (isOk == 1) {
 	alert('회원정보 수정 완료');
 }
 const modify = `<c:out value="${modify}" />`;
 console.log(isOk);
 if (isOk == 1) {
 	alert('비밀번호 변경 완료');
 }
 </script>
</body>
</html>