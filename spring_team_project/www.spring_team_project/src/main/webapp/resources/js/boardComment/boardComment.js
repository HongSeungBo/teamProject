console.log('comment in~!!');
console.log(bnoVal);
async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method:'post',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
   const cmtText = document.getElementById('cmtText').value; 
   const cmtWriter = document.getElementById('cmtWriter').innerText;
   if (cmtText == "" || cmtText == null) {
    alert('댓글을 입력해주세요');
    document.getElementById('cmtText').focus();
    return false;
   }else{
    let cmtData={
        bno : bnoVal,
        writer : cmtWriter,
        content : cmtText
    };
    console.log(cmtData);
    postCommentToServer(cmtData).then(result =>{
        console.log(result);
        if (result == 1) {
            alert('댓글 등록 성공');
            document.getElementById('cmtText').value='';
        }
        getCommentList(bnoVal);
    })
   }
})
async function spreadCommentListFromServer(bno,page){
    try {
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno, page=1){
    spreadCommentListFromServer(bno, page).then(result=>{
        console.log(result);
        let ul = document.getElementById('cmtListArea');
        if (result.cmtList.length > 0) {
            if (page ==1) {
                ul.innerText="";
            }
            for(let cvo of result.cmtList){
                let li = `<li class="cmtli" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="cmtd"><div class="fw-bole">${cvo.writer}</div>`;
                li += `<input type="text" id="inputtt" class="inputt" value="${cvo.content}" readonly="readonly"><br>`;
                li +=`<span">${cvo.regAt}</span>`;
                if (cvo.writer == authEmail) {
                    li += `<button type="button" class="modBtn">수정</button>`;
                    li += `<button type="button" class="delBtn">삭제</button></div></li>`;
                }
                ul.innerHTML += li;
            }
            let moreBtn = document.getElementById('moreBtn');
            
            
            if (result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page = page + 1;
            }else{
                moreBtn.style.visibility = 'hidden';
            }
        }else{
            let li = `<li>댓글이 없습니다</li>`;
            ul.innerHTML = li;
        }

    })
}
async function removeCommentToServer(cno){
    try {
        const url = '/comment/'+cno;
        const config={
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function editCommentToServer(cmtDataMod){
    try {
        const url = '/comment/'+cmtDataMod.cno;
        const config = {
            method:'put',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e)=>{
    if (e.target.id == 'moreBtn') {
        getCommentList(bnoVal, parseInt(e.target.dataset.page));
    }else if(e.target.classList.contains('delBtn')){
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        
        removeCommentToServer(cnoVal).then(result =>{
            if (result == 1) {
                alert('댓글 삭제 성공~!!');
            }
            getCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('modBtn')){
        
        let li = e.target.closest('li');

        let cmtText = li.querySelector('.fw-bole').nextSibling;
        let input = li.querySelector('.inputt');
        input.removeAttribute('readonly');
        input.focus();

        // 숨김 처리
        e.target.style.display = 'none';
        li.querySelector('.delBtn').style.display = 'none';

        // 등록 버튼 생성
        let registerBtn = document.createElement('button');
        registerBtn.type = 'button';
        registerBtn.className = 'postBtn';
        registerBtn.innerText = '등록';

        

        registerBtn.onclick = function () {
            let cmtDataMod = {
                cno: li.dataset.cno,
                content: input.value
            };
            editCommentToServer(cmtDataMod).then(result => {
                if (parseInt(result)) {
                    if (result == "1") {
                        alert('댓글 수정 완료');
                    }
                    getCommentList(bnoVal);
                }
            });
        };

        let cancelBtn = document.createElement('button');
        cancelBtn.type = 'button';
        cancelBtn.className = 'cancel';
        cancelBtn.innerText = '취소';
        cancelBtn.onclick = function(){
            input.setAttribute('readonly', 'readonly');
            e.target.style.display = 'inline-block';
            li.querySelector('.delBtn').style.display = 'inline-block';
            li.removeChild(registerBtn);
            li.removeChild(cancelBtn);
        }


        li.appendChild(registerBtn);
        li.appendChild(cancelBtn);
    }
    })

 

  
