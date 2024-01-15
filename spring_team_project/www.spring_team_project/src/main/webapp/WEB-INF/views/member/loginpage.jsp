<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
     <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<meta name ="google-signin-client_id" content="317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com">
</head>

<jsp:include page="../common/jsp/header.jsp"></jsp:include>
<body class="boby">
	
	<form action="/member/login" method="post">
		<input type="email" name="email" class="login-input" placeholder="아이디">
		<input type="password" name="pwd" class="login-input" placeholder="비밀번호">
		<button type="submit">로그인</button>
	</form>

	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
	<script src="https://accounts.google.com/gsi/client" async></script>
		   <a href="/oauth/googleLogin">google</a>
		   
		    <div>
		    	<div id="naver_id_login"></div>
			    <script type="text/javascript">
			        var naver_id_login = new naver_id_login("oK8TxRQic6qm3pRXmpDu", "http://localhost:8088/member/naverLogin");
			        var state = naver_id_login.getUniqState();
			        naver_id_login.setButton("white", 2,40);
			        naver_id_login.setDomain("http://localhost:8088/");
			        naver_id_login.setState(state);
			        naver_id_login.init_naver_id_login();
			    </script>
		    </div>
		    
		    
			      <a href="javascript:void(0)">
			      	<img alt="" src="/resources/image/kakao_login_medium_narrow.png">
			      </a>
			<ul>
				<li onclick="kakaoLogin();">
				</li>
				<li onclick="kakaoLogout();">
			      <a href="javascript:void(0)">
			          <span>카카오 로그아웃</span>
			      </a>
				</li>
			</ul>
			
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
				<input id="pwd" name="pwd" value=" " hidden="true">
			</form>
	
	
			<form action="/member/register" method="post" id="regiser">
				<input id="emailRegister" name="email" hidden="true">
				<input id="pwd" name="pwd" value=" " hidden="true">
				<input id="nameRegister" name="name" hidden="true">
			</form>
</body>
</html>