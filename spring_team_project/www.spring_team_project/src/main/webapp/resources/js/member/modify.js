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
    selectElement.innerHTML = `<option value="" selected>시/군/구</option>`;
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
    let selectedYear = parseInt(birthyear, 10); 
    let selectedMonth = parseInt(birthmonth, 10); 
    let selectedDay = parseInt(birthday, 10); 

   
    for (let year = 1900; year <= currentYear; year++) {
        let optionElement = document.createElement('option');
        optionElement.value = year;
        optionElement.text = year + '년';
        if (year === selectedYear) {
            optionElement.selected = true; 
        }
        birthYearSelect.add(optionElement);
    }

    
    for (let month = 1; month <= 12; month++) {
        let optionElement = document.createElement('option');
        optionElement.value = month;
        optionElement.text = month + '월';
        if (month === selectedMonth) {
            optionElement.selected = true; 
        }
        birthMonthSelect.add(optionElement);
    }

    updateBirthDayOptions();

    
    birthMonthSelect.addEventListener('change', function () {
        updateBirthDayOptions();
    });

    function updateBirthDayOptions() {
        let daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();
        birthDaySelect.innerHTML = `<option value="" selected>일 선택</option>`;
        for (let day = 1; day <= daysInMonth; day++) {
            let optionElement = document.createElement('option');
            optionElement.value = day;
            optionElement.text = day + '일';
            if (day === selectedDay) {
                optionElement.selected = true;
            }
            birthDaySelect.add(optionElement);
        }
    }
});





document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
});


document.addEventListener('change',(e)=>{
    
    let file = e.target.files;
    let profile = document.getElementById('profile');

    if (e.target.id == 'file') {
        handleFileSelect(file[0]).then(result => {
            if (result == 1) {
                spreadProfileFile().then(result => {
                  console.log(result);
                    let existingProfileImage = document.getElementById('profileImage');
                    if (result) {
                        existingProfileImage.src = `/upload/${result.saveDir.replace(/\\/g, '/')}/${result.uuid}_th_${result.fileName}`;
                        document.getElementById('profileImage').src = existingProfileImage.src;
                    }
                    
                });
            }
        });
    }
})
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

function isPasswordValid(password) {
    const pattern = /^(?=.*[!@#$%^&*(),.?":{}|<>]).{8,}$/;
    return pattern.test(password);
}

function validateForm() {
    let pwdValue = document.getElementById('pwd').value;
 
    if (pwdValue === null || pwdValue.trim() === '') {
        return true; 
    }

    if (!isPasswordValid(pwdValue)) {
        displayErrorMessagePwd('비밀번호는 특수문자를 포함하여 8자리 이상이어야 합니다.');
        return false;
    }


    return true;
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