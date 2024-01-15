console.log("연결체크 KaoKao");

animalTypeChecked();
// viewFile(fileList);

var latlng

var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);
// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    console.log(message);

    document.getElementById("lat").value = latlng.getLat();
    document.getElementById("lon").value = latlng.getLng();

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

const filezone = document.getElementById("filezone")
function viewFile(files){
    let str ="";
    for (const file of files) {
        console.log(file);
        str += `<img alt="오류" src="/upload/${file.saveDir}/${file.uuid}_th_${file.fileName}">`
    }
    filezone.innerHTML = str;
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

async function markerFileDelete(img){
    try {
        const url = "/kakaomap/markerFileDelete";
        const config ={
            method: "post",
            body:img
        }
        const resp = await fetch(url, config);
        const result = await resp.text;
        return result;
    } catch (error) {
        console.log(error);
    }
}

function animalTypeChecked(){
    console.log(animalType);
    var Type =[];
    Type = animalType.split(",");
    for(let animal of Type){
        if(animal != "" && animal != ","){
            console.log(animal);
            document.getElementById(`${animal}`).setAttribute('checked',true);
        }
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

document.getElementById("menuBtn").addEventListener('click',()=>{
    const animalTypeBox = document.getElementById("animalTypeBox")
    if(animalTypeBox.style.display == ""){
        animalTypeBox.style.setProperty("display","block");
    }else if(animalTypeBox.style.display == "block"){
        animalTypeBox.style.setProperty("display","");
    }
});

document.addEventListener('click',(e)=>{
    console.log(e.target);
    if (e.target.classList.contains('imgDelete')) {
        const DeleteImg = e.target.closest("div");
        if(confirm("이미지를 삭제하시겠습니까?")){
            // const img = DeleteImg.dataset.uuid;
            // console.log(img);
            // markerFileDelete(img).then(result=>{
            // })
            DeleteImg.innerHTML ="";
        }
    }
});

async function closeWindow() {
    await document.getElementById("form").submit();
    window.close();
}

function submitFormAndClose() {
    // 폼 데이터를 서버로 전송
    $.ajax({
      type: "POST",
      url: "/kakaomap/markerModify",
      data: $("#form").serialize(), // 폼 데이터 직렬화
      success: function(response) {
        // 서버 응답 처리 (선택사항)
        console.log(response);

        // 창 닫기
        window.close();
      },
      error: function(error) {
        console.error("Error:", error);
        // 에러 처리 (선택사항)
      }
    });
  }
