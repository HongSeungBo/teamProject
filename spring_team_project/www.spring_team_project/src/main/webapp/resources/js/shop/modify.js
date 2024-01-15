document.getElementById('mainFileBtn').disabled = true;
document.addEventListener('click',function(e){
    if(e.target.classList.contains('delSubBtn')){
        let div = e.target.closest('.sub-file-div');
        let uuid = e.target.dataset.uuid;
        if(div){
            let img = div.querySelector('.prevSubImg');
            if(img && img.classList.contains('prevSubImg')){
                DelFileFunc(uuid).then(result=>{
                    if(result>0){
                        alert("삭제 성공");
                    }
                })
                img.remove();
                div.remove();
            }
        }
    }
    else if(e.target.classList.contains('delMainBtn')){
        let div = e.target.closest('.main-file-div');
        if(div){
            let img = div.querySelector('.prevMainImg');
            let uuid = e.target.dataset.uuid;
            if(img && img.classList.contains('prevMainImg')){
                img.remove();
                div.remove();
                DelFileFunc(uuid).then(result=>{
                    if(result>0){
                        alert("삭제 성공");
                    }
                })
                document.getElementById('mainFileBtn').disabled = false;
            }
        }
    }
    else if(e.target.classList.contains('reg-btn')){
        // /shop/productList
    }
})

async function DelFileFunc(Data){
    try {
        const url = "/shop/prevFilemodify"
        const config={
            method:"put",
            headers:{
                'Content-Type' : 'application/json; charset=utf-8'
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

document.getElementById('modTagBtn').addEventListener('click',function(){
    let tagName = document.getElementById('tagName').value;
    let div = document.getElementById('modTagZone');
    console.log(div);
    let span = `<li><input type="hidden" name="tagName" value="${tagName}">#${tagName} <button type="button" class="tagDelBtn">태그삭제</button></li>`;
    div.innerHTML += span;
    document.getElementById('tagName').value='';
})

document.addEventListener('click',function(e){
    if(e.target.classList.contains('tagDelBtn')){
        let span = e.target.closest('span');
        span.remove();
    }else if(e.target.classList.contains('exTagDelBtn')){
        let li = e.target.closest('li');
        li.remove();
    }
})

async function cancleDelFile(){
    try {
        const url = "/shop/cancleFileDel"
        const config={
            method:"get",
            headers:{
                'Content-Type' : 'application/json; charset=utf-8'
            }
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('regregBtn').addEventListener('click',  function(){
     cancleDelFile().then(result=>{
        if(result>0){
            window.location.href = '/shop/productList';
        }
    })
})
window.onload = function(){
    cancleDelFile().then(result=>{
    });
};