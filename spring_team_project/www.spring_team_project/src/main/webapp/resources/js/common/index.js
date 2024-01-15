document.addEventListener("DOMContentLoaded", function () {
  const itemUl = document.querySelector(".itemUl");
  const itemLiList = document.querySelectorAll(".itemLi");
  let currentIndex = 0;

    function showNextItem() {
      currentIndex = (currentIndex + 1) % (itemLiList.length - 5);

      itemUl.style.transform = `translateX(-${currentIndex * 200}px)`; 
    }

    setInterval(showNextItem, 4000); 
});

function moveItems(direction) {
  const itemUl = document.querySelector('.itemUl');
  const itemWidth = document.querySelector('.itemLi').offsetWidth;

  const currentTransform = window.getComputedStyle(itemUl).getPropertyValue('transform');
  const currentTranslateX = parseInt(currentTransform.split(',')[4]);
  
  const numItems = 1;
  const firstItemPosition = 0;
  const lastItemPosition = (itemUl.childElementCount - numItems) * itemWidth;

  if (direction === 'left' && currentTransform === firstItemPosition) {
    newTranslateX = currentTranslateX;
  } else if (direction === 'right' && currentTransform >= lastItemPosition) {
    newTranslateX = currentTranslateX;
  } else {
    itemUl.style.transform = `translateX(${currentTranslateX}px)`;

    requestAnimationFrame(() => {
      newTranslateX = direction === 'left' ? currentTranslateX + numItems * itemWidth : currentTranslateX - numItems * itemWidth;
      itemUl.style.transform = `translateX(${newTranslateX}px)`;
      console.log(newTranslateX);
    });
  }
  
}







// ==================================================================
async function indexMarker(){
  console.log("indexMarker");
  let cnt = 4;
  try {
      const resp = await fetch("/kakaomap/indexMarker/"+cnt)
      const result = await resp.json();
      return result;
  } catch (error) {
      console.log(error);
  }
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
async function animalTypeIcon(animalType){
  let str = "";
  const csvData = animalType;
  const csvArray = csvData.split(',');
  for(let type of csvArray){
    switch(type){
      case "개" : str += '<i class="fi fi-rr-dog"></i> 개 '; break;
      case "고양이" : str += '<i class="fi fi-rs-cat"></i> 고양이 '; break;
      case "거북이" : str += '<i class="fi fi-rr-turtle"></i> 거북이 '; break;
      case "친질라" : str += '<i class="fi fi-rs-squirrel"></i> 친질라 '; break;
      case "앵무" : str += '<i class="fi fi-rr-bird"></i> 앵무 '; break;
      case "햄스터" : str += '<i class="fi fi-rs-squirrel"></i> 햄스터 '; break;
      case "뱀" : str += '<i class="fi fi-rr-snake"></i> 뱀 '; break;
      case "양서류" : str += '<i class="fi fi-rs-frog"></i> 양서류 '; break;
      case "관상어" : str += '<i class="fi fi-rs-fish"></i> 관상어 '; break;
    }
  }
  return str;
}

indexMarker().then(result=>{
  const div = document.getElementById("mapBox");
  div.innerHTML = "";
  let li = ``;
  for (let mvo of result) {
    getClickMarker(mvo.mno).then(result=>{
        li += `<div class="mapDetail">`;
        for (const file of result.files) {
          li+=`<div class="map-img-box">`;
          li += `<img class="filezone" alt="오류" src="/upload/${file.saveDir}/${file.uuid}_th_${file.fileName}">`;
          li+=`</div>`;
        }
        li += `<div>`;
        li += `<h3>${result.mvo.shopName}</h3><br>`;
        li += `<span>분류 :${result.mvo.sec}</span><br>`;
        li += `<span><i class="fi fi-rr-paw"></i>이용가능종 :`
        animalTypeIcon(mvo.animalType).then(type=>{
          li += type;
          li += `</span><br>`;
          li += `<span><i class="fi fi-rs-clock-five"></i>영업시간 :${result.mvo.stTime}~${result.mvo.edTime}</span><br>`;
          li += `<span><i class="fi fi-rs-phone-rotary"></i>전화번호 : ${result.mvo.num}</span><br>`;
          li += `</div>`;
          li += `</div>`;
          div.innerHTML = li;
        })
    });
  }

});

document.getElementById("scrollToTop").addEventListener("click", function () {
  scrollToPosition(0);
});

function scrollToPosition(position) {
  window.scrollTo({
    top: position,
    behavior: 'smooth'
  });
}