// async function getLogin(){
//   try {
//     const resp = await fetch('/member//member/googleLogin/');
//     const result = await resp.text();
//     return result;
// } catch (error) {
//     console.log(error);
// }
// }

// getLogin().then(result=>{
//   console.log(result);
//   document.getElementById("login").setAttribute("href",`href="${result}"`)
  
//   `href="${result}"`
// })

// import qs from "qs";
// 				const CLIENT_ID = "317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com";
// 				const AUTHORIZE_URI = "https://accounts.google.com/o/oauth2/v2/auth";
	
// 				const queryStr = qs.stringify({
// 				  client_id: CLIENT_ID,
// 				  redirect_uri: "https://localhost:8088/member/googleCallback",
// 				  response_type: "token",
// 				  scope: "https://www.googleapis.com/auth/contacts.readonly",
// 				});
	
// 				const loginUrl = AUTHORIZE_URI + "?" + queryStr;