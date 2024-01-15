window.onload = function () {
    showBuyList(emailVal);
};

document.addEventListener('click', function (e) {
    if (e.target.classList.contains('buyBtn')) {
        let li = e.target.closest('li');
        let pno = li.querySelector('.wish-list-Info').dataset.pno;
        let pname = li.querySelector('.wish-list-Info').dataset.pname;
        let price = li.querySelector('.wish-list-Info').dataset.price;
        let productCount = li.querySelector('.product-count').value;
        Data = {
            email: emailVal,
            pno: pno,
            productName: pname,
            price: price,
            productCount: productCount
        }

        editBuyCart(Data).then(result => {
            if(result>0){
                alert("구매카트 담기 성공");
                showBuyList(emailVal);
            }
        })
    }
    else if (e.target.classList.contains('cart-delBtn')) {
        let li = e.target.closest('.buy-cart-li')
        let ctno = li.dataset.ctno;
        deleteCart(ctno).then(result => {
            if (result > 0) {
                alert("장바구니 빼기");
                li.remove();
                showBuyList(emailVal);
            }
        })
    }
})

async function editBuyCart(Data) {
    try {
        const url = "/shop/editBuyCart"
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(Data)
        }
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

let totalSum;
let DataPN = [];
function showBuyList(emailVal) {
    let div = document.getElementById('buyCartZone');
    selectList(emailVal).then(result => {
        if(result){
            totalSum = 0;
            let li = `<ul class="cart-list">`;
            li += `<li class="buy-cart-li"><strong>제품 이름</strong><strong>가격</strong><strong>수량</strong><strong>총 금액</strong></li>`;
            for (let info of result) {
                li += `<li class="buy-cart-li" data-ctno=${info.ctno}>`;
                li += `<span>${info.productName}</span>`;
                li += `<span>${info.price}</span>`;
                li += `<span>${info.productCount}</span>`;
                li += `<span>${info.totalprice}</span>`;
                li += `<button type="button" class="cart-delBtn">X</button>`;
                li += `</li>`;
                totalSum += info.totalprice;
                DataPN.push(info.productName);
            }
            li += `</ul>`;
            li += `<div class="final-box">`;
            li += `<strong>총 결제금액 : <input type="text" value="${totalSum}" readonly="readonly" class="totalSum"></strong>`;
            li += `<div class="buysmalldiv">`;
            li += `<button id="buyBtn"">결제하기</button>`;
            li += `</div>`;
            li += `</div>`;
            div.innerHTML = li;
        }
    })
}
async function selectList(email) {
    try {
        const url = "/shop/selectList"
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({ email: email })
        }
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteCart(num) {
    try {
        const url = "/shop/deleteCart/" + num;
        const config = {
            method: "delete",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            }
        }
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteMyCartList(Data){
    try {
        const url = "/shop/deleteMyCartList/";
        const config = {
            method: "delete",
            headers: {
                'content-type': 'application/json; charset=utf-8'
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
document.addEventListener('click', function (e) {
    if (e.target.id == 'buyBtn') {
        let modalBack = document.getElementById('modalBack');
        let modalCon = document.getElementById('modalCon');
        modalCon.style.display='block';
        modalBack.classList.add('modal_background');

        let div = document.querySelector('.modal-totalSum-box');
        let li = `<span class="totalSpan">결제금액</span>`;
        li += `<input type="type" value="${totalSum}" readonly="readonly" class="totalInput">`;
        div.innerHTML = li;
    }
    else if(e.target.id=='cancle'){
        let modalBack = document.getElementById('modalBack');
        let modalCon = document.getElementById('modalCon');
        modalCon.style.display='none';
        modalBack.classList.remove('modal_background');
    }
    else if(e.target.classList.contains('MyCartDelBtn')){
        let li = e.target.closest('li');
        let pno = li.querySelector('.wish-list-Info').dataset.pno;
        Data={
            pno:pno,
            email:emailVal
        }
        deleteMyCartList(Data).then(result=>{
            if(result>0){
                alert("장바구니 빼기 성공");
                li.remove();
                showBuyList(emailVal);
            }
        })
    }
})

var today = new Date();   
var hours = today.getHours(); // 시
var minutes = today.getMinutes();  // 분
var seconds = today.getSeconds();  // 초
var milliseconds = today.getMilliseconds();
var makeMerchantUid = hours +  minutes + seconds + milliseconds;

let selectPG;
document.addEventListener('click',(e)=>{
    console.log(e.target.id);
    switch (e.target.id) {
        case "kakaoPay":
            selectPG = "kakaopay.TC0ONETIME"
            requestPay();
        break;
        case "toss":
            selectPG = "tosspayments.iamporttest_3"
            requestPay();
        break;
        // case "":
        //     selectPG = ""
        //     requestPay();
        // break;
    }
});



function requestPay() {

    var IMP = window.IMP;
    var itemNames = DataPN.join(', ');
    IMP.init('imp14236486');
    IMP.request_pay(
      {
        pg: selectPG,
        payMethod: "card",
        merchantUid: "IMP"+makeMerchantUid,
        name: itemNames,
        amount: totalSum,
        buyerEmail: emailVal,
        buyerName: emailVal,
        buyerTel: "010-1234-5678",
        buyerAddr: "서울특별시 강남구 삼성동"
      },function(data){
        console.log(data);
		if(data.success){
			var msg = "결제 완료";
            msg += '고유ID : ' + data.imp_uid;                //아임포트 uid는 실제 결제 시 결제 고유번호를 서버와 비교해서 결제처리하는데 필요없긴함.
            msg += '// 상점 거래ID : ' + data.merchant_uid;
            msg += '// 결제 금액 : ' + data.paid_amount;
            msg += '// 카드 승인번호 : ' + data.apply_num;
            
            $.ajax({
            	type : 'post',
            	url : '/payment/kakaopay',
                contentType: 'application/json; charset=utf-8',
            	data : JSON.stringify({
                    pg:"kakaopay.TC0ONETIME",
                    "buyerEmail" : emailVal,
                    "buyerName": emailVal,
                    "buyerTel": "010-1234-5678",
                    "buyerAddr": "서울특별시 강남구 삼성동",
                    "name":itemNames,
                    "amount":data.paid_amount,
                    "payMethod":"card",
                    "impUid": data.imp_uid
                }),
                success: function (data) {
                    console.log("성공");
                },
                error: function (error) {
                    console.log("실패");
                }
            });
        }else{
        	var msg = "결제 실패"
        	msg += "에러 내용" + rsp.error_msg;
        }
		alert(msg);
		document.location.href="/shop/myCart?email="+emailVal;
	});
}
