document.addEventListener('click',function(e){
    if(e.target.classList.contains('DelBtn')){
        console.log("삭제버튼 클릭");
        let div = e.target.closest('.product-list');
        let pno = e.target.dataset.pno;
        if(div){
            deleteProduct(pno).then(result=>{
                if(result>0){
                    alert("삭제 성공");
                    div.remove();
                    location.reload();
                }else{
                    alert("삭제 실패");
                }
            })
        }
    }
})

async function deleteProduct(pno){
    try {
        const resp = await fetch("/shop/deleteProduct/"+pno);
        const result = await resp.text();
        return result;
    } catch (error) {
        console(error)
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