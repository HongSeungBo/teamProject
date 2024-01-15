console.log("연결확인 googleLogin");
console.log(emailVal);
console.log(nameVal);


async function getEmail(email){
    try {
        const resp = await fetch('/member/getEmail/'+email);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


let email = emailVal;
console.log(email);

function tmp() {
    getEmail(email).then(result=>{
        console.log(result);
        if(result == 1){
            document.getElementById("emailLoin").value = emailVal;
            document.getElementById('login').submit();
        }else{
            document.getElementById("emailRegister").value = emailVal;
            document.getElementById("nameRegister").value = nameVal;
            document.getElementById('regiser').submit();
        }
    })
}


tmp();
document.getElementById("").click();