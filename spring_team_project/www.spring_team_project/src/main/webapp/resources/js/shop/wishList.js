document.addEventListener('click',function(e){
    if(e.target.classList.contains('cartBtn')){
        let li = e.target.closest('li');
        let pno = li.querySelector('.wish-list-Info').dataset.pno;
        let pname = li.querySelector('.wish-list-Info').dataset.pname;
        let price = li.querySelector('.wish-list-Info').dataset.price;
        let uuid = li.querySelector('.imgBox-img').dataset.uuid;
        let fileName = li.querySelector('.imgBox-img').dataset.filename;
        let saveDir = li.querySelector('.imgBox-img').dataset.savedir;
        Data={
            email:emailVal,
            pno:pno,
            productName:pname,
            price:price,
            uuid:uuid,
            fileName:fileName,
            saveDir:saveDir
        }

        editMyCart(Data).then(result=>{
            if(result>0){
                alert("장바구니 등록 성공");
            }else{
                alert("장바구니 등록 실패");
            }
        })
    }
    else if(e.target.classList.contains('delMyWish')){
        let li = e.target.closest('li');
        let pno = li.querySelector('.wish-list-Info').dataset.pno;
        let email = emailVal;
        deleteMyWish(pno, email).then(result=>{
            if(result>0){
                alert("찜 빼기 성공");
                li.remove();
            }
        })

    }
})

async function editMyCart(Data){
    try {
        const url = "/shop/editMyCart"
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

async function deleteMyWish(pno, email){
    try {
        const url = "/shop/deleteMyWish/"+pno+"/"+email
        const config={
            method:"delete",
            headers:{
                'content-type' : 'application/json; charset=utf-8'
            }
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
