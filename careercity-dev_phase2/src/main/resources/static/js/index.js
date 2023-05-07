var pic1 = document.getElementById("pic1");
var pic2 = document.getElementById("pic2");
var pic3 = document.getElementById("pic3");
var picA = document.getElementById("picA");
var picB = document.getElementById("picB");
var picC = document.getElementById("picC");
var images1 = document.getElementById("images1");
var images2 = document.getElementById("images2");
var images3 = document.getElementById("images3");
var desc = document.getElementById("description");
var title = document.getElementById("account-title1");

pic1.addEventListener("mouseover", changeDesc);
pic2.addEventListener("mouseover", changeDesc);
pic3.addEventListener("mouseover", changeDesc);

pic1.addEventListener("mouseout", changeDesc);
pic2.addEventListener("mouseout", changeDesc);
pic3.addEventListener("mouseout", changeDesc);

function changeDesc() {

    picA.classList.add("picZoom");

    document.onmouseover = function(e) {

        if(e.target.id == "pic1"){
            desc.innerHTML = "Are you passionate about making meaningful and loyal customer connections? Contact Center Operations is the right avenue for you to unleash your skills and talents in delivering efficient and on-brand support to customers.";
            title.innerHTML = "Contact Center Outsourcing";
            picA.classList.add("picsZoom");
            images1.classList.add("imagesZoom");
            title.classList.add("sizeadj1");
        }
        else if(e.target.id == "pic2"){
            desc.innerHTML = "Be part of the next-gen digital experience by exploring a variety of expertise in service desk, infrastructure management, applications development and many more!";
            title.innerHTML = "Digital Solutions";
            picB.classList.add("picsZoom");
            images2.classList.add("imagesZoom");
            title.classList.add("sizeadj2");
        }
        else if(e.target.id == "pic3"){
            desc.innerHTML = "Interested in roles that support the business? Dive and discover various roles that enable TELUS International to provide effortless experience to our customers.";
            title.innerHTML = "Support functions";
            picC.classList.add("picsZoom");
            images3.classList.add("imagesZoom");
        }

    }

    document.onmouseout = function(e) {

        if(e.target.id == "pic1" || e.target.id == "pic2" || e.target.id == "pic3"){

            desc.innerHTML = "Click the pillars and discover roles that fit your interest and skills";
            title.innerHTML = "Say hello to new opportunities";
            
            title.classList.remove("sizeadj1");
            title.classList.remove("sizeadj2");
            
            picA.classList.remove("picsZoom");
            picB.classList.remove("picsZoom");
            picC.classList.remove("picsZoom");
            
            images1.classList.remove("imagesZoom");
            images2.classList.remove("imagesZoom");
            images3.classList.remove("imagesZoom");

        }

    }

}

$(document).ready(function(){
    
    $(window).scroll(function(){
        var positionTop = $(document).scrollTop();
        console.log(positionTop);
        if ($(window).scrollTop() > 1350 && $(window).scrollTop() < 1400){
            $("#scroll-panda").css({
                "right" : "-25vh"
            });
        }
        else if ($(window).scrollTop() > 1401 && $(window).scrollTop() < 1450){
            $("#scroll-panda").css({
                "right" : "-20vh"
            });
        }
        else if ($(window).scrollTop() > 1451 && $(window).scrollTop() < 1500){
            $("#scroll-panda").css({
                "right" : "-15vh"
            });
        }
        else if ($(window).scrollTop() > 1501 && $(window).scrollTop() < 1550){
            $("#scroll-panda").css({
                "right" : "-10vh"
            });
        }
        else if ($(window).scrollTop() > 1551 && $(window).scrollTop() < 1600){
            $("#scroll-panda").css({
                "right" : "-5vh"
            });
        }
        else if ($(window).scrollTop() > 1601){
            $("#scroll-panda").css({
                "right" : "0vh"
            });
        }
        else {
            $("#scroll-panda").css({
                "right" : "-30vh"
            });
        }
     });

     window.onscroll = function() {scrollFunction()};

});

function focusOnQuiz(){
    document.getElementById('section3opaque').scrollIntoView({behavior: "smooth", block:"center"});
}

function focusOnAccounts(){
    document.getElementById('section2').scrollIntoView({behavior: "smooth", block:"center"});
}

// Start Add - Daryll Moya - 23-FEB-2021
function focusOnAbout(){
    document.getElementById('aboutsectioncontainer').scrollIntoView({behavior: "smooth", block:"center"});
}
// End Add - Daryll Moya - 23-FEB-2021

function backToTop() {
    $(document.body).animate({scrollTop:0}, 'slow');
    $(document.documentElement).animate({scrollTop:0}, 'slow');
}

// Handles visibility of backToTop button
function scrollFunction() {

    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("backToTopBtn").style.display = "block";
    }
    else {
        document.getElementById("backToTopBtn").style.display = "none";
    }

}