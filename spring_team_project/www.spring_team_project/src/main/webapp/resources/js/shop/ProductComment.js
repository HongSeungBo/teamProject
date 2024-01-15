let scoreNum=1;
let div = document.getElementById('detailSubFile');
let startDiv = document.querySelector('.ratingCon');
let keyword = null;
let type = null;
document.addEventListener('click',function(e){
    if(e.target.classList.contains('subfile')){
        let uuid = e.target.dataset.uuid;
        let fileName = e.target.dataset.filename;
        let saveDir = e.target.dataset.savedir;
        div.innerHTML=`<img src="/upload/${saveDir.replace(/\\/g, '/')}/${uuid}_${fileName}" alt="${fileName}">`;
    }
    else if(e.target.dataset.num){
        let num = e.target.dataset.num;
        if(num<5){
            for(let i=1;i<=5;i++){
                let star = document.querySelector(`.ratingCon [data-num="${i}"]`);
                star.classList.add('bi-star');
                star.classList.remove('bi-star-fill');
            }
            for(let i=1;i<=num;i++){
                let star = document.querySelector(`.ratingCon [data-num="${i}"]`);
                star.classList.add('bi-star-fill');
                star.classList.remove('bi-star');
            }
        }else if(num==5){
            for(let i=1;i<=num;i++){
                let star = document.querySelector(`.ratingCon [data-num="${i}"]`);
                star.classList.add('bi-star-fill');
                star.classList.remove('bi-star');
            }
        }
        scoreNum = num;
    }
    else if(e.target.classList.contains('link')){
        e.preventDefault();
        console.log(e.target.getAttribute('href'));
        // 6
        showCommentList(pnoVal, e.target.getAttribute('href'));
    }
    else if(e.target.classList.contains('s-link')){
        e.preventDefault();
        console.log(e.target.getAttribute('href'));
        const hrefParts = e.target.getAttribute('href').split('/');
        const page = parseInt(hrefParts[0]);
        const keyword = hrefParts[1];
        const type = hrefParts[2];
        const formData = new FormData();
        formData.append('keyword', keyword);
        formData.append('type', type);
        CommentSearchFun(formData, pnoVal, page);
    }
    else if(e.target.classList.contains('cmtDelBtn')){
        let li = e.target.closest('li');
        let pcno = li.dataset.pcno;
        console.log("pcno >> " + pcno);
        DeleteCmt(pcno).then(result=>{
            if(result>0){
                alert("삭제 성공");
                showCommentList(pnoVal);
            }else{
                alert("삭제 실패");
            }
        })
    }
    else if(e.target.classList.contains('recommend')){
        let pcnoVal = e.target.dataset.pcno;
        console.log("pcno >>>>>> " + pcnoVal);
        let Data = {
            pno : pnoVal,
            pcno : pcnoVal,
            email : emailVal
        }
        console.log(Data);
        selectRecommendID(Data).then(result=>{
            if(result==0){
                addRecommendId(Data).then(result=>{
                    if(result>0){
                        alert("추천 성공");
                        e.target.classList.replace('bi-hand-thumbs-up', 'bi-hand-thumbs-up-fill');
                        showCommentList(pnoVal);
                    }
                })
            }else{
                removeRecommendId(Data).then(result=>{
                    if(result>0){
                        alert("추천 취소");
                        e.target.classList.replace('bi-hand-thumbs-up-fill', 'bi-hand-thumbs-up');
                        showCommentList(pnoVal);
                    }
                })
            }
        })
    }
    // 3
    else if(e.target.id=='reviewSearch'){
        const formData = new FormData(document.getElementById('productSearch'));
        let page=1;
        let pno = pnoVal;
        
        CommentSearchFun(formData, pno, page);
    }
    else if(e.target.classList.contains('review-img')){
        let uuid = e.target.dataset.cmtuuid;
        let fileName = e.target.dataset.cmtfilename;
        let saveDir = e.target.dataset.cmtsavedir;
        let li = e.target.closest('.comment-li');
        let div = li.querySelector('.cmtBoxImg');
        console.log(li);
        console.log(div);
        div.innerHTML=`<img src="/upload/${saveDir.replace(/\\/g, '/')}/${uuid}_${fileName}" alt="${fileName}">`
    }
})

async function CommentSearchFun(formData, pno, page) {
    try {
        for (const [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }
        const result = await commentSearch(formData, pno, page);
        const div = document.getElementById('commentList');
        console.log(result);
        if (result.cmtList.length > 0) {
            if (page == 1) {
                div.innerHTML = '';
            }
            div.innerHTML = '';
            for (let pcvo of result.cmtList) {
                Data = {
                    pno: pnoVal,
                    pcno: pcvo.pcno,
                    email: emailVal
                };
                let ul = `<li class="comment-li" data-pcno="${pcvo.pcno}">`;
                ul+=`<div class="cmtbox-header">`;
                ul += `<div class="writer-div" data-writer="${pcvo.email}">${pcvo.email}</div>`;
                ul += `<span class="cmtbox-reg">${pcvo.regAt}</span>`;
                ul+=`</div>`;
                ul+=`<div class="cmtbox-body">`;
                ul+=`<div class="cmtbox-leftbox">`;
                ul += `<div class="content-div">${pcvo.content}`;
                
                if (pcvo.cmtFileList != null) {
                    for (let i = 0; i < pcvo.cmtFileList.length; i++) {
                        ul += `<img src="/upload/${pcvo.cmtFileList[i].saveDir.replace(/\\/g, '/')}/${pcvo.cmtFileList[i].uuid}_th_${pcvo.cmtFileList[i].fileName}" alt="${pcvo.cmtFileList[i].fileName}" class="review-img" data-cmtsavedir="${pcvo.cmtFileList[i].saveDir}" data-cmtfilename="${pcvo.cmtFileList[i].fileName}" data-cmtuuid="${pcvo.cmtFileList[i].uuid}">`;
                    }
                }
                ul+=`</div>`;

                const selectResult = await selectRecommendID(Data);

                if (selectResult == 1) {
                    if(emailVal){
                        ul += `<i class="bi bi-hand-thumbs-up-fill recommend" data-pcno="${pcvo.pcno}"></i>`
                    }
                    ul+=`<span>( ${pcvo.recommend} )</span>`;
                } else if (selectResult == 0) {
                    if(emailVal){
                        ul += `<i class="bi bi-hand-thumbs-up recommend" data-pcno="${pcvo.pcno}"></i>`
                    }
                    ul+=`<span>( ${pcvo.recommend} )</span>`;
                }
                ul+=`</div>`;
                if (pcvo.email == emailVal) {
                    ul += `<button type="button" class="comment-btn cmtDelBtn">삭제</button>`;
                }
                ul+=`</div>`;
                ul+=`<div class="cmtBoxImg"></div>`
                ul += `</li>`;
                div.innerHTML += ul;
            }

            let cmtPaging = document.getElementById('cmtPaging');

            if (result.pcpvo.pageNo < result.endPage || result.next) {
                cmtPaging.dataset.page = page + 1;
                cmtPaging.style.visibility = "visible";
            }

            // 4
            CommentSearchList(result.prev, result.startPage, result.pcpvo, result.endPage, result.next);
        }
    } catch (error) {
        console.error(error);
    }
}
function CommentSearchList(prev, startPage, pcpvo, endPage, next){
    let cmtPaging = document.getElementById('cmtPaging');
    cmtPaging.innerHTML=``;

    let ul = `<ul class="pagination">`;
    if(prev){
        ul+=`<li><a class="s-link" href="${startPage-1}"><span>&laquo;</span></a></li>`;
    }
    for(let i=1;i<=endPage;i++){
        // 5
        ul+=`<li class="${pcpvo.pageNo==i? 'active':''}" aria-current="page">`;
        ul+=`<a class="s-link" href="${i}/${pcpvo.keyword}/${pcpvo.type}">${i}</a></li>`;
    }
    if(next){
        ul+=`<li><a class="s-link" href="${startPage+1}"><span>&raquo;</span></a></li>`;
    }
    ul+=`</ul>`;
    cmtPaging.innerHTML=ul;
}

// 1
// 7
async function showCommentList(pnoVal, page = 1) {
    const result = await getCommentList(pnoVal, page);
    console.log(result);
    console.log(result.cmtList.length);
    console.log(result.prev);
    console.log(result.next);
    const div = document.getElementById('commentList');
    if (result.cmtList.length > 0) {
        if (page == 1) {
            div.innerHTML = '';
        }
        div.innerHTML = '';
        for (let pcvo of result.cmtList) {
            Data = {
                pno: pnoVal,
                pcno: pcvo.pcno,
                email: emailVal
            };
            let ul = `<li class="comment-li" data-pcno="${pcvo.pcno}">`;
            ul+=`<div class="cmtbox-header">`;
            ul += `<div class="writer-div" data-writer="${pcvo.email}">${pcvo.email}</div>`;
            ul += `<span class="cmtbox-reg">${pcvo.regAt}</span>`;
            ul+=`</div>`;
            ul+=`<div class="cmtbox-body">`;
            ul+=`<div class="cmtbox-leftbox">`;
            ul += `<div class="content-div">${pcvo.content}`;
            
            if (pcvo.cmtFileList != null) {
                for (let i = 0; i < pcvo.cmtFileList.length; i++) {
                    ul += `<img src="/upload/${pcvo.cmtFileList[i].saveDir.replace(/\\/g, '/')}/${pcvo.cmtFileList[i].uuid}_th_${pcvo.cmtFileList[i].fileName}" alt="${pcvo.cmtFileList[i].fileName}" data-cmtsavedir="${pcvo.cmtFileList[i].saveDir}" data-cmtfilename="${pcvo.cmtFileList[i].fileName}" data-cmtuuid="${pcvo.cmtFileList[i].uuid}" class="review-img">`;
                }
            }

            ul += `</div>`;

            const recommendResult = await selectRecommendID(Data);

            if (recommendResult == 1) {
                if(emailVal){
                    ul += `<i class="bi bi-hand-thumbs-up-fill recommend" data-pcno="${pcvo.pcno}"></i>`
                }
                ul+=`<span>( ${pcvo.recommend} )</span>`;
            } else if (recommendResult == 0) {
                if(emailVal){
                    ul += `<i class="bi bi-hand-thumbs-up recommend" data-pcno="${pcvo.pcno}"></i>`
                }
                ul+=`<span>( ${pcvo.recommend} )</span>`;
            }
            ul+=`</div>`;
            if (pcvo.email == emailVal) {
                ul += `<button type="button" class="comment-btn cmtDelBtn">삭제</button>`;
            }
            ul+=`</div>`;
            ul+=`<div class="cmtBoxImg"></div>`
            ul += `</li>`;
            div.innerHTML += ul;
        }

        let cmtPaging = document.getElementById('cmtPaging');
        if (result.pcpvo.pageNo < result.endPage || result.next) {
            cmtPaging.dataset.page = page + 1;
            cmtPaging.style.visibility = "visible";
        }

        CommentPaging(result.prev, result.startPage, result.pcpvo, result.endPage, result.next);
    }
}

function CommentPaging(prev, startPage, pcpvo, endPage, next){
    let cmtPaging = document.getElementById('cmtPaging');
    cmtPaging.innerHTML=``;

    let ul = `<ul class="pagination">`;
    if(prev){
        ul+=`<li><a class="link" href="${startPage-1}"><span>&laquo;</span></a></li>`;
    }

    for(let i=1;i<=endPage;i++){
        ul+=`<li class="${pcpvo.pageNo==i? 'active':''}" aria-current="page">`;
        ul+=`<a class="link" href="${i}/">${i}</a></li>`;
    }

    if(next){
        ul+=`<li><a class="link" href="${startPage+1}"><span>&raquo;</span></a></li>`;
    }
    ul+=`</ul>`;
    cmtPaging.innerHTML=ul;
}

document.getElementById('cmtFileTrigger').addEventListener('click',function(){
    document.getElementById('cmtFileBtn').click();
})

document.getElementById('cmtRegBtn').addEventListener('click',function(){
    let cmtFile = document.getElementById('cmtFileBtn').files;
    let cmtText = document.getElementById('cmtText').value;
    let emailVal = document.getElementById('email').value;
    Data = {
        pno : pnoVal,
        email : emailVal,
        content : cmtText,
        score : scoreNum
    }
    commentEditText(Data).then(result=>{
        if(result>0){
            commentEditFile(cmtFile).then(fileNum=>{
                if(fileNum>0){
                    alert("등록 성공");
                    let div = document.getElementById('fileZone');
                    div.innerHTML='';
                    document.getElementById('cmtText').value='';
                    showCommentList(pnoVal);
                }else{
                    alert("등록 실패");
                    showCommentList(pnoVal);
                }
            })
        }else{
            alert("댓글 등록 실패");
        }
    })
})

async function commentEditFile(cmtFile){
    try {
        const url = "/product/registerFile"
        const formData = new FormData();
        for (const file of cmtFile) {
            formData.append('files', file);
        }
        const config = {
            method: "post",
            body: formData
        };
        const resp = await fetch(url, config);
        const fileNum = await resp.text();
        return fileNum;
    } catch (error) {
        console.log(error);
    }
}

async function commentEditText(Data){
    try {
        const url = "/product/registerText"
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

// 2
async function getCommentList(pno, page){
    try {
        const resp = await fetch("/product/list/"+pno+"/"+page);
        const result = resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function DeleteCmt(pcno){
    try{
        const resp = await fetch("/product/delete/"+pcno);
        const result = resp.text();
        return result;
    }catch(error){
        console.log(error);
    }
}

async function addRecommendId(Data){
    try {
        const url = "/product/addRecommend"
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

async function removeRecommendId(Data){
    try {
        const url = "/product/removeRecommend"
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

async function selectRecommendID(Data){
    try {
        const url = "/product/selectRecommend"
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

async function commentSearch(formData, pno, page){
    try {
        const url = "/product/commentSearch/"+pno+"/"+page;
        const config={
            method:"post",
            body:formData
        }
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    }catch(error){
        console.log(error);
    }
}

function recommendColorFunc(){

}