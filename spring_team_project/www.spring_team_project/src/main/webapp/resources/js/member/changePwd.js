document.querySelector('.newPwdForm').addEventListener('submit', function (event) {
    let pwdValue = document.querySelector('.newPwd1').value;
    

   
    if (!isPasswordValid(pwdValue)) {
        event.preventDefault(); 
        displayErrorMessagePwd('비밀번호는 특수문자를 포함하여 8자리 이상이어야 합니다.');
    }

});

function isPasswordValid(password) {
    const pattern = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$/;
    return pattern.test(password);
}

function displayErrorMessagePwd(message) {
    let errorMessageElementPWD = document.getElementById('error-message-pwd');

    if (!errorMessageElementPWD) {
        errorMessageElementPWD = document.createElement('div');
        errorMessageElementPWD.id = 'error-message-pwd';

        let formElement = document.querySelector('form'); 
        formElement.appendChild(errorMessageElementPWD);
    }
    errorMessageElementPWD.innerText = message;
}