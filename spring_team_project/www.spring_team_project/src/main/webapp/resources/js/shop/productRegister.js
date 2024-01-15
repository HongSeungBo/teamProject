document.getElementById('mainFileBtn').addEventListener('click',function(){
    document.getElementById('mainFile').click();
})

document.getElementById('subFilesBtn').addEventListener('click',function(){
    document.getElementById('subFiles').click();
})

const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|emp)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){
        console.log("이미지파일아님");
        return 0;
    }else if(regExp.test(fileName)){
        console.log("실행파일");
        return 0;
    }else if(fileSize>maxSize){
        console.log("사이즈 오버");
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change',function(e){
    console.log(e.target);
    if(e.target.id == 'mainFile'){
        document.getElementById('regBtn').disabled=false;
        let isOk=1;
        const mainFileObj = document.getElementById('mainFile').files;
        const div = document.getElementById('mainFileImg');
        isOk = fileValidation(mainFileObj[0].name, mainFileObj[0].size);
        const fileUpload = mainFileObj[0];
        if(isOk==1){
            TemFile(fileUpload).then(result=>{
                alert(`${result>0? '업로드 가능':'업로드 불가능'}`);
                loadTmtFile().then(imagePath=>{
                    loadTmtUuid().then(uuid=>{
                        loadtmtFileName().then(fileName=>{
                            div.innerHTML =`<img src="/upload/${imagePath.replace(/\\/g, '/')}/${uuid}_${fileName}" alt="${fileName}" data-uuid="${uuid}" data-filename="${fileName}">`;
                        })
                    })
                })
            }).catch(error=>{
                console.log("비동기함수 실행 완료" + error);
            })
        }else{
            document.getElementById('regBtn').disabled=true;
        }
    }
    else if(e.target.id=='subFiles'){
        document.getElementById('regBtn').disabled=false;
        const subFileObj = document.getElementById('subFiles').files;
        let div = document.getElementById('subFileZone');
        div.innerHTML='';
        let isOk=1;
        for(let file of subFileObj){
            let vaildResult = fileValidation(file.name, file.size);
            isOk *= vaildResult;
        }
        if(isOk>0){
            alert("서브 이미지 등록 가능");
            const subFiles = subFileObj;
            subFileUpload(subFiles).then(result=>{
                alert(`${result>0? '업로드 가능':'업로드 불가능'}`);
                loadTmtSubFile(parseInt(result)).then(imagePath=>{
                    loadTmtSubUuid(parseInt(result)).then(uuid=>{
                        loadtmtSubFileName(parseInt(result)).then(fileName=>{
                            for(let i=0;i<parseInt(result);i++){
                                div.innerHTML += `<img src="/upload/${imagePath[i].replace(/\\/g, '/')}/${uuid[i]}_th_${fileName[i]}" alt="${fileName[i]}">`;
                            }
                        })
                    })
                })
            })
        }else{
            document.getElementById('regBtn').disabled=true;
        }
    }
    
})
document.getElementById('tagBtn').addEventListener('click',function(){
        let tagName = document.getElementById('tagName').value;
        let div = document.getElementById('tagZone');
        let span = `<span><input type="hidden" name="tagName" value="${tagName}"> # ${tagName} <button type="button" class="tagDelBtn">x</button></span>`;
        div.innerHTML += span;
        document.getElementById('tagName').value='';
})

document.addEventListener('click',function(e){
    if(e.target.classList.contains('tagDelBtn')){
        let span = e.target.closest('span');
        span.remove();
    }
})

async function TemFile(fileUpload){
    try {
        const url = "/shop/tmtfile";
        const formData = new FormData();
        formData.append('file',fileUpload);
        const config = {
            method: "post",
            body: formData
        };
        console.log(formData);
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log("비동기 처리 이미지 저장 중 에러" + error);
    }
}

async function loadTmtFile(){
    try {
        const resp = await fetch("/shop/getImagePath");
        const imagePath = await resp.text();
        return imagePath;
    } catch (error) {
        console.log("비동기 처리 경로 받아오는 중 에러" + error);
    }
}
async function loadTmtUuid(){
    try {
        const resp = await fetch("/shop/loadTmtUuid");
        const uuid = await resp.text();
        return uuid;
    } catch (error) {
        console.log("비동기 처리 uuid 받아오는 중 에러" + error);
    }
}
async function loadtmtFileName(){
    try {
        const resp = await fetch("/shop/loadtmtFileName");
        const fileName = await resp.text();
        return fileName;
    } catch (error) {
        console.log("비동기 처리 파일이름 받아오는 중 에러" + error);
    }
}

async function subFileUpload(files){
    try {
        const url = "/shop/subFileUpload";
        const formData = new FormData();
        for (const file of files) {
            formData.append('files', file);
        }
        const config = {
            method: "post",
            body: formData
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log("비동기 처리 하단 이미지 저장 중 에러" + error);
    }
}

async function loadTmtSubFile(num){
    try {
        const resp = await fetch("/shop/loadTmtSubFile/"+num);
        const imagePath = await resp.json();
        return imagePath;
    } catch (error) {
        console.log("비동기 처리 경로 받아오는 중 에러2" + error);
    }
}
async function loadTmtSubUuid(num){
    try {
        const resp = await fetch("/shop/loadTmtSubUuid/"+num);
        const uuid = await resp.json();
        return uuid;
    } catch (error) {
        console.log("비동기 처리 uuid 받아오는 중 에러2" + error);
    }
}
async function loadtmtSubFileName(num){
    try {
        const resp = await fetch("/shop/loadtmtSubFileName/"+num);
        const fileName = await resp.json();
        return fileName;
    } catch (error) {
        console.log("비동기 처리 파일이름 받아오는 중 에러2" + error);
    }
}
