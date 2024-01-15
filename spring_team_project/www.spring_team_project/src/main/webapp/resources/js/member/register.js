document.getElementById('regionSelect').addEventListener('change', function () {
        let subRegionSelect = document.getElementById('subRegionSelect');
        let region = this.value;

       
        switch (region) {
            case '서울특별시':
                addOptions(subRegionSelect, ['종로구', '중구', '용산구', '성동구', '광진구', '동대문구', '중랑구', '성북구', '강북구', '도봉구', '노원구', '은평구', '서대문구', '마포구', '양천구', '강서구', '구로구', '금천구', '영등포구', '동작구', '관악구', '서초구', '강남구', '송파구', '강동구']);
                break;
            case '부산광역시':
                addOptions(subRegionSelect, ['중구', '서구', '동구', '영도구', '부산진구', '동래구', '남구', '북구', '해운대구', '사하구', '금정구', '강서구', '연제구', '수영구', '사상구', '기장군']);
                break;
            case '대구광역시':
                addOptions(subRegionSelect, ['중구', '동구', '서구', '남구', '북구', '수성구', '달서구', '달성군']);
                break;
             case '인천광역시':
                addOptions(subRegionSelect, ['중구', '동구', '미추홀구', '연수구', '남동구', '부평구', '계양구', '서구', '강화군', '옹진군']);
                break;
            case '광주광역시':
                addOptions(subRegionSelect, ['동구', '서구', '남구', '북구', '광산구']);
                break;
            case '대전광역시':
                addOptions(subRegionSelect, ['동구', '중구', '서구', '유성구', '대덕구']);
                break;
            case '울산광역시':
                addOptions(subRegionSelect, ['중구', '남구', '동구', '북구', '울주군']);
                break;
            case '세종특별자치시':
                addOptions(subRegionSelect, ['세종시']);
                break;
            case '경기도':
                addOptions(subRegionSelect, ['수원시', '성남시', '고양시', '용인시', '부천시', '안산시', '안양시', '남양주시', '화성시', '평택시', '의정부시', '시흥시', '파주시', '광명시', '김포시', '군포시', '광주시', '이천시', '양주시', '오산시', '구리시', '안성시', '포천시', '의왕시', '하남시', '여주시', '양평군', '동두천시', '과천시', '가평군']);
                break;
             case '강원도':
                addOptions(subRegionSelect, ['춘천시', '원주시', '강릉시', '동해시', '태백시', '속초시', '삼척시', '홍천군', '횡성군', '영월군', '평창군', '정선군', '철원군', '화천군', '양구군', '인제군', '고성군', '양양군']);
                break;
            case '충청북도':
                addOptions(subRegionSelect, ['청주시', '충주시', '제천시', '보은군', '옥천군', '영동군', '증평군', '진천군', '괴산군', '음성군', '단양군']);
                break;
            case '충청남도':
                addOptions(subRegionSelect, ['천안시', '공주시', '보령시', '아산시', '서산시', '논산시', '계룡시', '당진시', '금산군', '부여군', '서천군', '청양군', '홍성군', '예산군', '태안군']);
                break;
            case '전라북도':
                addOptions(subRegionSelect, ['전주시', '군산시', '익산시', '정읍시', '남원시', '김제시', '완주군', '진안군', '무주군', '장수군', '임실군', '순창군', '고창군', '부안군']);
                break;
            case '전라남도':
                addOptions(subRegionSelect, ['목포시', '여수시', '순천시', '나주시', '광양시', '담양군', '곡성군', '구례군', '고흥군', '보성군', '화순군', '장흥군', '강진군', '해남군', '영암군', '무안군', '함평군', '영광군', '장성군', '완도군', '진도군', '신안군']);
                break;
            case '경상북도':
                addOptions(subRegionSelect, ['포항시', '경주시', '김천시', '안동시', '구미시', '영주시', '영천시', '상주시', '문경시', '경산시', '군위군', '의성군', '청송군', '영양군', '영덕군', '청도군', '고령군', '성주군', '칠곡군', '예천군', '봉화군', '울진군', '울릉군']);
                break;
            case '경상남도':
                addOptions(subRegionSelect, ['창원시', '진주시', '통영시', '사천시', '김해시', '밀양시', '거제시', '양산시', '의령군', '함안군', '창녕군', '고성군', '남해군', '하동군', '산청군', '함양군', '거창군', '합천군']);
                break;
            case '제주특별자치도':
                addOptions(subRegionSelect, ['제주시', '서귀포시']);
                break;
            
        }

    });

   
    function addOptions(selectElement, options) {
        options.forEach(function (option) {
            let optionElement = document.createElement('option');
            optionElement.value = option;
            optionElement.text = option;
            selectElement.add(optionElement);
        });
    }
    
    
    
    document.getElementById('regionSelect').addEventListener('change', function () {
        let subRegionSelect = document.getElementById('subRegionSelect');
        let region = this.value;

        
        subRegionSelect.style.display = 'block';
    });


    document.addEventListener('DOMContentLoaded', function () {
        let birthYearSelect = document.getElementById('birthYearSelect');
        let birthMonthSelect = document.getElementById('birthMonthSelect');
        let birthDaySelect = document.getElementById('birthDaySelect');

        
        let currentYear = new Date().getFullYear();

        
        for (let year = 1900; year <= currentYear; year++) {
            let optionElement = document.createElement('option');
            optionElement.value = year+'-';
            optionElement.text = year + '년';
            birthYearSelect.add(optionElement);
        }

        
        for (let month = 1; month <= 12; month++) {
            let optionElement = document.createElement('option');
            optionElement.value = month+'-';
            optionElement.text = month + '월';
            birthMonthSelect.add(optionElement);
        }

       
        birthMonthSelect.addEventListener('change', function () {
            let selectedMonth = parseInt(this.value, 10);
            let birthYear = parseInt((birthYearSelect.value).substring(0,4));
            
            let daysInMonth = new Date(birthYear, selectedMonth, 0).getDate();
            
            
            
            for (let day = 1; day <= daysInMonth; day++) {
                let optionElement = document.createElement('option');
                optionElement.value = day;
                optionElement.text = day + '일';
                birthDaySelect.add(optionElement);
            }
        });

    });


    
    document.addEventListener('DOMContentLoaded', () => {
        const fileInput = document.getElementById('file');
        const triggerButton = document.getElementById('trigger');
        const profileImage = document.getElementById('profileImage');
    
        triggerButton.addEventListener('click', () => {
            fileInput.click();
        });
    
        fileInput.addEventListener('change', async (e) => {
            const file = e.target.files[0];
    
            if (file) {
                try {
                    const result = await handleFileSelect(file);
                    console.log(result);
                    console.log(file);
                    if (result == 1) {
                        const profileResult = await spreadProfileFile();
                        console.log(profileResult);
                        
                        if (profileResult) {
                            profileImage.src = `/upload/${profileResult.saveDir.replace(/\\/g, '/')}/${profileResult.uuid}_th_${profileResult.fileName}`;
                        }
                    }
                } catch (error) {
                    console.error(error);
                }
            }
        });
    });
    

    async function handleFileSelect(file) {
        
        let formData = new FormData();
        formData.append('file',file);
        
        try {
            const url = "/member/image";
            const config = {
                method:'post',
                body: formData
            }
            const resp = await fetch(url,config);
            const result = await resp.text();
            return result;
        } catch (error) {
            console.log(error);
        }
    }

    async function spreadProfileFile(){
        try {
            const resp = await fetch('/member/check');
            const result = await resp.json();
            return result;
        } catch (error) {
            console.log(error);
        }
    }
    


    function validateForm() {
        let emailValue = document.getElementById('email').value;
        let pwdValue = document.getElementById('pwd').value;
        let nickValue = document.getElementById('nick').value;
        let nameValue = document.getElementById('name').value;
        let phon1Value = document.getElementById('phon1').value;
        let phon2Value = document.getElementById('phon2').value;
        let regionSelectValue = document.getElementById('detailAddr').value;
        let birthYearSelectValue = document.getElementById('birthYearSelect').value;


        if (emailValue.trim() === '') {
            displayErrorMessage('아이디를 작성해 주세요.');
            return false;
        }

        if (pwdValue.trim() === '') {
            displayErrorMessagePwd('비밀번호를 작성해 주세요.');
            return false;
        }
        if (!isPasswordValid(pwdValue)) {
            displayErrorMessagePwd('비밀번호는 특수문자를 포함하여 8자리 이상이어야 합니다.');
            return false;
        }
        if (nickValue.trim() === '') {
            displayErrorMessageNick('닉네임을 작성해 주세요.');
            return false;
        }
        if (nameValue.trim() === '') {
            displayErrorMessagename('이름을 작성해 주세요.');
            return false;
        }
        if (phon1Value.trim() === '' || phon2Value.trim() === '') {
            displayErrorMessagePhon('전화번호를 작성해 주세요.');
            return false;
        }
        if (regionSelectValue.trim() === '') {
            displayErrorMessageAddress('주소를 작성해 주세요.');
            return false;
        }
        
        if (birthYearSelectValue.trim() === '') {
            displayErrorMessagebirth('생년월일 작성해 주세요.');
            return false;
        }
        return true;
    }

    function displayErrorMessage(message) {
        
        let errorMessageElement = document.getElementById('error-message');

        
        if (!errorMessageElement) {
            errorMessageElement = document.createElement('div');
            errorMessageElement.id = 'error-message';
            errorMessageElement.style.color = 'red';
            errorMessageElement.style.fontWeight = 'bold';
            

            
            let formElement = document.querySelector('form'); 
            formElement.appendChild(errorMessageElement);
        }

        errorMessageElement.innerText = message;

        
    }
    function displayErrorMessagePwd(message){
    let errorMessageElementPWD = document.getElementById('error-message-pwd');

        
        if (!errorMessageElementPWD) {
            errorMessageElementPWD = document.createElement('div');
            errorMessageElementPWD.id = 'error-message-pwd';
            errorMessageElementPWD.style.color = 'red';
            errorMessageElementPWD.style.fontWeight = 'bold';
            

            
            let formElement = document.querySelector('form'); 
            formElement.appendChild(errorMessageElementPWD);
        }
        errorMessageElementPWD.innerText = message;
    }

    function isPasswordValid(password) {
       
        let pattern = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$/;
        return pattern.test(password);
    }

    

    function displayErrorMessageNick(message){
        let errorMessageElementNick = document.getElementById('error-message-nick');
    
            
            if (!errorMessageElementNick) {
                errorMessageElementNick = document.createElement('div');
                errorMessageElementNick.id = 'error-message-nick';
                errorMessageElementNick.style.color = 'red';
                errorMessageElementNick.style.fontWeight = 'bold';
                
    
                
                let formElement = document.querySelector('form'); 
                formElement.appendChild(errorMessageElementNick);
            }
            errorMessageElementNick.innerText = message;
        }

        function displayErrorMessagename(message){
            let errorMessageElementName = document.getElementById('error-message-name');
        
                
                if (!errorMessageElementName) {
                    errorMessageElementName = document.createElement('div');
                    errorMessageElementName.id = 'error-message-name';
                    errorMessageElementName.style.color = 'red';
                    errorMessageElementName.style.fontWeight = 'bold';
                    
        
                    
                    let formElement = document.querySelector('form'); 
                    formElement.appendChild(errorMessageElementName);
                }
                errorMessageElementName.innerText = message;
            }

            function displayErrorMessagePhon(message){
                let errorMessageElementName = document.getElementById('error-message-phon');
            
                    
                    if (!errorMessageElementName) {
                        errorMessageElementName = document.createElement('div');
                        errorMessageElementName.id = 'error-message-phon';
                        errorMessageElementName.style.color = 'red';
                        errorMessageElementName.style.fontWeight = 'bold';
                        
            
                        
                        let formElement = document.querySelector('form'); 
                        formElement.appendChild(errorMessageElementName);
                    }
                    errorMessageElementName.innerText = message;
                }
                function displayErrorMessagePhon(message){
                let errorMessageElementName = document.getElementById('error-message-phon');
            
                    
                    if (!errorMessageElementName) {
                        errorMessageElementName = document.createElement('div');
                        errorMessageElementName.id = 'error-message-phon';
                        errorMessageElementName.style.color = 'red';
                        errorMessageElementName.style.fontWeight = 'bold';
                        
            
                        
                        let formElement = document.querySelector('form'); 
                        formElement.appendChild(errorMessageElementName);
                    }
                    errorMessageElementName.innerText = message;
                }
                function displayErrorMessageAddress(message){
                    let errorMessageElementName = document.getElementById('error-message-address');
                
                        
                        if (!errorMessageElementName) {
                            errorMessageElementName = document.createElement('div');
                            errorMessageElementName.id = 'error-message-address';
                            errorMessageElementName.style.color = 'red';
                            errorMessageElementName.style.fontWeight = 'bold';
                            
                
                            
                            let formElement = document.querySelector('form'); 
                            formElement.appendChild(errorMessageElementName);
                        }
                        errorMessageElementName.innerText = message;
                    }
                function displayErrorMessagebirth(message){
                    let errorMessageElementName = document.getElementById('error-message-birth');
                
                        
                        if (!errorMessageElementName) {
                            errorMessageElementName = document.createElement('div');
                            errorMessageElementName.id = 'error-message-birth';
                            errorMessageElementName.style.color = 'red';
                            errorMessageElementName.style.fontWeight = 'bold';
                            
                
                            
                            let formElement = document.querySelector('form'); 
                            formElement.appendChild(errorMessageElementName);
                        }
                        errorMessageElementName.innerText = message;
                    }
                
                    
                    function checkEmail() {
                        let emailValue = document.getElementById('email').value;
                        sendEmail(emailValue).then(result=>{
                            console.log(result);
                            try {
                                if (result == 2 ) {
                                    document.getElementById('error-message').innerHTML = '이미 있는 이메일입니다.';
                                    document.getElementById('rregBtn').disabled = true;
                                }else {
                                    document.getElementById('error-message').innerHTML = '사용 가능한 이메일입니다.';
                                    document.getElementById('rregBtn').disabled = false;
                                }
                                
                            } catch (error) {
                                console.log(error);
                            }
                        });
                        

                    }
        
                    async function sendEmail(email){
                        try {
                            const resp = await fetch('/member/checkEmail/'+email);
                            const result = await resp.text();
                            return result;
                        } catch (error) {
                            console.log(error);
                        }
                    }
                    
 
 function validatePassword() {
    let password = document.getElementById("pwd").value;
    let pattern = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$/;
    return pattern.test(password);
}

