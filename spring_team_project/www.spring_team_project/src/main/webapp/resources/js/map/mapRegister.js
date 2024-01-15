console.log("연결체크 KaoKao");

var latlng

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


var newMarker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
var markerImage = new kakao.maps.MarkerImage(
    'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
    new kakao.maps.Size(35, 50), new kakao.maps.Point(13, 34));

newMarker.setImage(markerImage);
// 지도에 마커를 표시합니다
newMarker.setMap(map);
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    newMarker.setPosition(latlng);
    
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    console.log(message);
    
});

const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|emp|jfif)$");
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

function viewFile(fileList){
    const filezone = document.getElementById("filezone")
    let str = "";
    for (const file of fileList) {
        str += `<img alt="오류" src="/upload/${file.saveDir}/${file.uuid}_th_${file.fileName}">`
    }
    filezone.innerHTML = str;
}



async function insertMarker(markerDate,){
    try {
        const url = "/kakaomap/post";
        const config ={
            method:"post",
            headers:{
                'content-type' : 'application/json; charset=UTF-8'
            },
            body:JSON.stringify(markerDate),
        }
        const resp =  await fetch(url,config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function markerFileUpload(files){
    try {
        const url = "/kakaomap/markerFileUpload";
        const formData = new FormData();
        for (const file of files) {
            formData.append('files', file);
        }
        const config = {
            method: "post",
            body: formData
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function insertMarkerFile(insertFile){
    try {
        const url = "/kakaomap/insertMarkerFile";
        const fileData = new FormData();
        for (const filed of insertFile) {
            fileData.append('files', filed);
        }
        const config = {
            method: "post",
            body: fileData
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }

}

document.addEventListener('change',(e)=>{
    
    if(e.target.id == "markerIMG"){
        let isOk=1;
        const markerFile = document.getElementById('markerIMG').files;
        for(let file of markerFile){
            let vaildResult = fileValidation(file.name, file.size);
            isOk *= vaildResult;
        }
        if(isOk==1){
            alert("업로드가능")
            markerFileUpload(markerFile).then(result=>{
                console.log(result);
                viewFile(result);
            })
        }
    }
});

document.getElementById("addBtn").addEventListener('click',()=>{

    console.log("위도 "+latlng.getLat());
    console.log("경도 "+latlng.getLng());
    let shop_Name = document.getElementById("shopName").value;
    console.log("이름 "+shop_Name);
    let marker_sec = document.getElementById("sec").value;
    console.log("카테고리 "+marker_sec);
    const query = 'input[name="animalType"]:checked';
    const selectedEls = document.querySelectorAll(query);

    // 선택된 목록에서 value 찾기
    let result = '';
    selectedEls.forEach((el) => {
    result += el.value + ',';
    });
    console.log("대응 종 "+result);
        const markerDate={
            lat:latlng.getLat(),
            lon:latlng.getLng(),
            shopName:shop_Name,
            sec:marker_sec,
            animalType:result,
            stTime:document.getElementById("stTime").value,
            edTime:document.getElementById("edTime").value,
            num:document.getElementById("num").value,
            detail:document.getElementById("markerDetail").value
        }
        const insertFile = document.getElementById('markerIMG').files;
        insertMarker(markerDate).then(result=>{
            console.log(result);
            if(result == 1){
                insertMarkerFile(insertFile).then(result=>{
                    if(result == 1){
                        alert("등록완료");
                        location.reload(true);
                    }
                });
            }
        })

});

async function getMarkerList(){
    try {
        const resp =  await fetch('/kakaomap/list');
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
        li += `<span>이용가능종 :${mvo.animalType}</span><br>`;
        li += `<span>영업시간 :${mvo.stTime}~${mvo.edTime}</span><br>`;
        li += `<span>전화번호 : ${mvo.num}</span><br>`;
        li += `<div>`;
        li += `<button onclick="openMarkerModifyWindow(${mvo.mno})">정보수정</button>
        <br>
        <a href="/kakaomap/DeleteM?mno=${mvo.mno}"><button>마커삭제</button></a></div>`;
        // li += `<span>${mvo.detail}</span>`;
        li += `</div><hr>`;
    }
    div.innerHTML = li;
}


function showMarkerDetail(mno){
    getClickMarker(mno).then(result=>{
        const newCenter = new kakao.maps.LatLng(`${result.mvo.lat}`, `${result.mvo.lon}`); // 변경할 중심 좌표 (예시: 도쿄)
        map.panTo(newCenter);
    })
};
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


document.getElementById("menuBtn").addEventListener('click',()=>{
    const animalTypeBox = document.getElementById("animalTypeBox")
    console.log(animalTypeBox);
    console.log(animalTypeBox.style.display);
    if(animalTypeBox.style.display == ""){
        animalTypeBox.style.setProperty("display","block");
    }else if(animalTypeBox.style.display == "block"){
        animalTypeBox.style.setProperty("display","");
    }
});
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