<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>

<script type="text/javascript">
	async function getEmail(email){
	  try {
	      const resp = await fetch('/member/getEmail/'+email);
	      const result = await resp.json();
	      return result;
	  } catch (error) {
	      console.log(error);
	  }
	}
	
	var naver_id_login = new naver_id_login("oK8TxRQic6qm3pRXmpDu", "http://localhost:8088/member/naverLogin");
	//접근 토큰 값 출력
	/* alert(naver_id_login.oauthParams.access_token); */
	//네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	//네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	
	function naverSignInCallback() {
		let email = naver_id_login.getProfileData('email');
		console.log(email);
		getEmail(email).then(result=>{
			console.log(result);
			if(result == 1){
				document.getElementById("emailLoin").value = naver_id_login.getProfileData('email');
				document.getElementById('login').submit();
			}else{
				document.getElementById("emailRegister").value = naver_id_login.getProfileData('email');
				document.getElementById("nameRegister").value = naver_id_login.getProfileData('name');
				document.getElementById("phoNumRegister").value = naver_id_login.getProfileData('mobile');
				document.getElementById('regiser').submit();
			}
		})
	}
</script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js">

</script>
	<form action="/member/login" method="post" id="login">
		<input id="emailLoin" name="email" hidden="true">
		<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
	</form>
	
	
	<form action="/member/NaverRegister" method="get" id="regiser">
		<input id="emailRegister" name="email" hidden="true">
		<input id="pwd" name="pwd" value="qwertyuiop" hidden="true">
		<input id="nameRegister" name="name" hidden="true">
		<input id="phoNumRegister" name="phoNum" hidden="true">
	</form>
	
	
</body>
</html>