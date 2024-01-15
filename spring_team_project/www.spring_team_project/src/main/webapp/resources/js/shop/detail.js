let uuidVal = document.getElementById('uuidVal').value;
let saveDirVal = document.getElementById('saveDirVal').value;
let fileNameVal = document.getElementById('fileNameVal').value;

window.onload = function() {
    wishHeart();
};

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

document.addEventListener('change',function(){
    let isOk = 1;
    let div = document.getElementById('fileZone');
    let cmtFile = document.getElementById('cmtFileBtn').files;
    for(let file of cmtFile){
        isOk *= fileValidation(file.name, file.size);
    }
    if(isOk>0){
        let ul = `<ul>`;
        ul += `<li>첨부파일 :</li>`;
        for(let file of cmtFile){
            ul += `<li><span>${file.name}</span></li>`;
        }
        ul+=`</ul>`;
        div.innerHTML=ul;
    }else{
        alert("이미지파일(jpg, jpeg, png, gif, emp)만 올릴 수 있습니다.");
    }
})

function wishHeart(){
    let searchData ={
        email : emailVal,
        pno : pnoVal
    }
    let icon = document.querySelector('.heart')
    searchMyWishFunc(searchData).then(result=>{
        if(result>0){
            icon.classList.replace('bi-heart', 'bi-heart-fill');
        }
    })
}

document.addEventListener('click',function(e){
    let DataInfo={
        price : priceVal,
        productName : productNameVal,
        pno : pnoVal,
        email : emailVal,
        uuid : uuidVal,
        saveDir : saveDirVal,
        fileName : fileNameVal,
    }
    let searchData ={
        email : emailVal,
        pno : pnoVal
    }
    if(e.target.classList.contains('heart')){
        let icon = document.querySelector('.heart')
        searchMyWishFunc(searchData).then(result=>{
            console.log(result);
            if(result==0){
                addMyWishFunc(DataInfo).then(result=>{
                    if(result>0){
                        icon.classList.replace('bi-heart', 'bi-heart-fill');
                        alert("찜하기");
                    }
                })
            }else{
                console.log("1이라 일로옴");
                removeMyWishFunc(searchData).then(result=>{
                    if(result>0){
                        icon.classList.replace('bi-heart-fill', 'bi-heart');
                        alert("찜빼기");
                    }
                })
            }
        })
    }
})

async function searchMyWishFunc(Data){
    try {
        const url = "/shop/searchMyWish"
        const config={
            method:"post",
            headers:{
                'content-type' : 'application/json; charset=utf-8'
            },
            body:JSON.stringify(Data)
        }
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function addMyWishFunc(Data){
    try {
        const url = "/shop/addMyWish"
        const config={
            method:"post",
            headers:{
                'content-type' : 'application/json; charset=utf-8'
            },
            body:JSON.stringify(Data)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function removeMyWishFunc(Data){
    try {
        const url = "/shop/removeMyWish"
        const config={
            method:"delete",
            headers:{
                'content-type' : 'application/json; charset=utf-8'
            },
            body:JSON.stringify(Data)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

  document.getElementById("scrollToTop").addEventListener("click", function () {
    scrollToPosition(0);
  });

  document.getElementById("scrollToBottom").addEventListener("click", function () {
    scrollToPosition(document.body.scrollHeight);
  });

  function scrollToPosition(position) {
    window.scrollTo({
      top: position,
      behavior: 'smooth'
    });
  }
