document.addEventListener("DOMContentLoaded", function () {
    var mainHeader = document.querySelector(".header ");
    var mainHeaderLiItems = document.querySelectorAll(".mainHeaderLi");
    
    mainHeader.style.transition = "height 0.3s ease";
    
    mainHeaderLiItems.forEach(function (item) {
        var submenu = item.querySelector(".submenu");
        var submenuUl = submenu.querySelector(".submenuUl");
        var submenuLi = submenuUl.querySelector('.submenuLi');

        item.addEventListener("mouseenter", function () {
            if (submenuLi) {
                submenu.style.display = "block";
            }
        });

        item.addEventListener("mouseleave", function () {
            if (submenuLi) {
                submenu.style.display = "none";
            }
        });
    });

    profileImg().then(result=>{
        const profileImage_img =document.getElementById('profileImage_img');
        console.log(profileImage_img);
        console.log(result);
        if (result !=null ) {
            profileImage_img.src = `/upload/${result.saveDir.replace(/\\/g, '/')}/${result.uuid}_th_${result.fileName}`;
        }else{
            profileImage_img.src = `/resources/image/default-imge.png`;
        }
    })
});
async function profileImg(){

    
    try {
        const url = "/member/loginHeader";
        
        const resp = await fetch(url);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}
document.addEventListener('click', function (e) {
    if (e.target.classList.contains('linkmove')) {

    }
})
document.addEventListener('scroll', function () {
    var header = document.querySelector('.header');
    var scrollPosition = window.scrollY;
    header.style.top = scrollPosition + 'px';
    const scrollY = window.scrollY;

  if (scrollY > 100) {
    header.classList.add("header-border-bottom");
  } else {
    header.classList.remove("header-border-bottom");
  }
})
