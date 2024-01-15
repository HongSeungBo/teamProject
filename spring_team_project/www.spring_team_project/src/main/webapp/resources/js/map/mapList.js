console.log("mapList 연결");

const filezone = document.getElementById("filezone")
var positions = [];
var markers = [];

getMarkerList().then(result=>{
    showMarkerList(result);
    for(let mvo of result){
        positions.push(
            {
                latlng: new kakao.maps.LatLng(mvo.lat, mvo.lon),
                mno:mvo.mno,
                name:mvo.shopName
            }
        )
    }
    console.log(positions);  
    for (var i = 0; i < positions.length; i ++) {
        // 마커를 생성합니다
        marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title : positions[i].mno, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        });
        var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].name // 인포윈도우에 표시할 내용
        });
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
        markers.push(marker);
    }

})

document.addEventListener("click",(e)=>{
    if(e.target.title != null && e.target.title != ""){
        var btn = document.getElementById('toggleButton');
        var Detail = document.querySelector('.markaerDetail');
        btn.style.right = "-377px"
        Detail.style.transform = 'translateX(0%)';
        Detail.style.width = '350px';
        Detail.style.color = "#000000";
        document.getElementById("markerURL").style.color = "#000000";

        getClickMarker(e.target.title).then(result=>{
            console.log(result);
            document.getElementById('markershopName').innerText = result.mvo.shopName;
            document.getElementById('markersec').innerText = result.mvo.sec;
            document.getElementById('markeranimalType').innerText = result.mvo.animalType;
            document.getElementById('markerstTime').innerText = result.mvo.stTime;
            document.getElementById('markeredTime').innerText = result.mvo.edTime;
            document.getElementById('markernum').innerText = result.mvo.num;
            document.getElementById('markerdetail').innerText = result.mvo.detail;
            if(result.files[0] == null){
                filezone.innerHTML = "";
            }else{
                viewFile(result.files);
            }
            const markarDetail = document.getElementById("markarDetail").innerHTML =
            `<a href="/kakaomap/markerModify?mno=${result.mvo.mno}"><button>정보수정</button></a>
            <a href="/kakaomap/markerDelete?mno=${result.mvo.mno}"><button>마커삭제</button></a>`
        });
    }
})
function showMarkerDetail(mno){
    var btn = document.getElementById('toggleButton');
    btn.style.right = "-377px"
    var Detail = document.querySelector('.markaerDetail');
    Detail.style.width = '350px';
    Detail.style.color = "#000000";
    document.getElementById("markerURL").style.color = "#000000";

    getClickMarker(mno).then(result=>{
        console.log(result);
        document.getElementById('markershopName').innerText = result.mvo.shopName;
        document.getElementById('markersec').innerText = result.mvo.sec;
        document.getElementById('markeranimalType').innerText = result.mvo.animalType;
        document.getElementById('markerstTime').innerText = result.mvo.stTime;
        document.getElementById('markeredTime').innerText = result.mvo.edTime;
        document.getElementById('markernum').innerText = result.mvo.num;
        document.getElementById('markerdetail').innerText = result.mvo.detail;
        document.getElementById('markerURL').setAttribute('href',result.mvo.url);
        if(result.files[0] == null){
            filezone.innerHTML = "";
        }else{
            viewFile(result.files);
        }
        const markarDetail = document.getElementById("markarDetail").innerHTML =
        `
        <button onclick="openMarkerModifyWindow(${result.mvo.mno})">정보수정</button>
        <a href="/kakaomap/markerDelete?mno=${result.mvo.mno}"><button>마커삭제</button></a>`
        const newCenter = new kakao.maps.LatLng(`${result.mvo.lat}`, `${result.mvo.lon}`); // 변경할 중심 좌표 (예시: 도쿄)
        map.panTo(newCenter);
    })
};

function markaerDetailHidden(){
    var btn = document.getElementById('toggleButton');
    btn.style.right = "-27px"
    var Detail = document.querySelector('.markaerDetail');
    Detail.style.width = '0px';
    Detail.style.color = "#00000000";
    document.getElementById("markerURL").style.color = "#00000000";
    filezone.innerHTML = "";
    markarDetail.innerHTML ="";
}

document.getElementById("search").addEventListener("click",()=>{
    console.log("버튼눌림");
    removeMarker();
    positions = [];

    let shop_Name = document.getElementById("shopName").value;
    console.log("이름 "+shop_Name);
    let marker_sec = document.getElementById("sec").value;
    console.log("카테고리 "+marker_sec);
    const query = 'input[name="animalType"]:checked';
    const selectedEls = document.querySelectorAll(query);

    // 선택된 목록에서 value 찾기
    let result = '';
    selectedEls.forEach((el) => {
    result += el.value + ' ';
    });
    console.log("대응 종 "+result);

    const markerDate={
        shopName:shop_Name,
        sec:marker_sec,
        animalType:result,
    }
    getSearchMarkerList(markerDate).then(result=>{
        console.log(result);
        showMarkerList(result);
        for(let mvo of result){
            console.log(mvo);
            positions.push(
                {
                    title: mvo.mno, 
                    latlng: new kakao.maps.LatLng(mvo.lat, mvo.lon),
                    name:mvo.shopName
                }
            )
        }        
        for (var i = 0; i < positions.length; i ++) {
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title : positions[i].title // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            });
            var infowindow = new kakao.maps.InfoWindow({
                content: positions[i].name // 인포윈도우에 표시할 내용
            });
            kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
            kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
            markers.push(marker);
            
        }
        for (var i = 0; i < markers.length; i ++) {
            markers[i].setMap(map);
        }
    })

});

// document.getElementById("menuBtn").addEventListener('click',()=>{
//     const animalTypeBox = document.getElementById("animalTypeBox")
//     console.log(animalTypeBox);
//     console.log(animalTypeBox.style.display);
//     if(animalTypeBox.style.display == ""){
//         animalTypeBox.style.setProperty("display","block");
//     }else if(animalTypeBox.style.display == "block"){
//         animalTypeBox.style.setProperty("display","");
//     }
// });

async function getMarkerList(){
    try {
        const resp =  await fetch('/kakaomap/list');
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    } 
}

async function getSearchMarkerList(markerDate){
    try {
        const url = "/kakaomap/search";
        const config ={
            method:"post",
            headers:{
                'content-type' : 'application/json; charset=UTF-8'
            },
            body:JSON.stringify(markerDate)
        }
        const resp =  await fetch(url,config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    } 
}

function showMarkerList(result){
    const div = document.getElementById("markerList");
    div.innerHTML = "";
    let li = ``;
    for (let mvo of result) {
        li += `<div class="buy-cart-li" onclick="showMarkerDetail(${mvo.mno})">`;
        li += `<h3>${mvo.shopName}</h3><br>`;
        li += `<span>분류 :${mvo.sec}</span><br>`;
        // li += `<span>이용가능종 :${mvo.animalType}</span><br>`;
        li += `<span>영업시간 :${mvo.stTime}~${mvo.edTime}</span><br>`;
        li += `<span>전화번호 : ${mvo.num}</span><br>`;
        // li += `<span>${mvo.detail}</span>`;
        li += `</div><hr>`;
    }
    div.innerHTML = li;
}


function removeMarker() {
    console.log(markers);
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = []; 
}


async function getClickMarker(mno){
    console.log(mno);
    try {
        const resp = await fetch("/kakaomap/getmarker/"+mno)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function viewFile(fileList){
    let str = "";
    for (const file of fileList) {
        str += `<img alt="오류" src="/upload/${file.saveDir}/${file.uuid}_th_${file.fileName}">`
    }
    filezone.innerHTML = str;
}

function openMarkerModifyWindow(mno) {
    var markerModifyWindow = 
    window.open(`/kakaomap/markerModify?mno=${mno}`, 
    'MarkerModify', 'width=560, height=800');
    markerModifyWindow.onunload = function() {
        // 부모 창을 새로고침합니다.
        location.reload();
    };
}
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

document.getElementById('toggleButton').addEventListener('click', function() {
    var btn = document.getElementById('toggleButton');
    var container = document.getElementById("sidebox");
    var currentTransform = container.style.transform;
    // console.log(btn.innerText);
    if(btn.innerText == "<"){
        container.style.transform = 'translateX(-100%)';
        btn.innerText = ">"
    }else if(btn.innerText == ">"){
        container.style.transform = 'translateX(0%)';
        btn.innerText = "<";
    }

  });