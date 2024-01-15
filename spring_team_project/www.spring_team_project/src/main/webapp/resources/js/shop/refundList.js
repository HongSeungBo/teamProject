document.addEventListener('DOMContentLoaded', function() {
    // 모든 refundBtn 클래스를 가진 버튼을 선택
    var refundButtons = document.querySelectorAll('.refundBtn');

    // 각 버튼에 대한 클릭 이벤트 리스너를 등록
    refundButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            // 클릭된 버튼의 data-impUid 속성 값 추출
            let Uid = button.dataset.merchantuid;

            // 추출한 값 사용 예시 (여기서는 콘솔에 출력)
            console.log('data-Uid:', Uid);

            // 여기에서 해당 데이터(impUid)를 서버로 전송하는 등의 작업을 수행
            cancelPay(Uid)
        });
    });
});
function cancelPay(Uid) {
    setPayment(Uid).then(result=>{
        console.log(result);
        if (result == 1) {
            alert("환불 처리 완료")
        }else{
            alert("환불 처리 실패")
        }
        location.reload();
    })
}
async function setPayment(Uid){

    try {
        const url = "/payment/setPayment/"+Uid
        const config ={
            method:"post",
            headers:{
                'content-type' : 'application/json; charset=UTF-8'
            }
        }
        const resp =  await fetch(url,config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}