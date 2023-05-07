$(document).ready(function() {
    $('.result-carousel').slick({
        speed: 300,
        infinite: true,
        centerMode: true,
        slidesToShow: 1,
        draggable: false,
        // Start Change - Daryll Moya - 02-MAR-2021
        //prevArrow: "<img class='a-left control-c prev slick-prev' src='../careercity/img/accounts/prev-btn.png'>",
        //nextArrow: "<img class='a-right control-c next slick-next' src='../careercity/img/accounts/next-btn2.png'>",
        prevArrow: "<img class='a-left control-c prev slick-prev' src='img/accounts/prev-btn.png'>",
        nextArrow: "<img class='a-right control-c next slick-next' src='img/accounts/next-btn2.png'>",
        // End Change - Daryll Moya - 02-MAR-2021
        variableWidth: true,
    });

    window.history.pushState(null, "", window.location.href);
    window.onpopstate = function() {
        window.history.pushState(null, "", window.location.href);
    };

    // Enable selectText
    function enableCopy() { 
        function ats() { 
            var styles = '*,p,div{user-select:text !important;-moz-user-select:text !important;-webkit-user-select:text !important;}';
            jQuery('head').append(jQuery('<style />').html(styles));
            var allowNormal = function () { return true; };
            jQuery('*[onselectstart], *[ondragstart], *[oncontextmenu], #songLyricsDiv').unbind('contextmenu').unbind('selectstart').unbind('dragstart').unbind('mousedown').unbind('mouseup').unbind('click').attr('onselectstart', allowNormal).attr('oncontextmenu', allowNormal).attr('ondragstart', allowNormal);
        }
        function atswp() { 
            if (window.jQuery) { 
                ats(); 
            }
            else {
                window.setTimeout(atswp, 100);
            } 
        } 
        if (window.jQuery) { 
            ats(); 
        }
        else { 
            var s = document.createElement('script'); s.setAttribute('src', 'http://code.jquery.com/jquery-1.9.1.min.js'); document.getElementsByTagName('body')[0].appendChild(s); atswp();
        } 
    }
    enableCopy();

});

$('.result-carousel').on('click', '.slick-arrow', function(e) {

    var pageLength = $("#pageLength").val();

    for (var i = 1; i <= pageLength; i++) {
        undoLearnMore(i);
    }

});

function learnMore(page) {

    var learnMoreBtnId = "learnMoreBtn-" + page;
    var learnMoreBtn = document.getElementById(learnMoreBtnId);
    learnMoreBtn.style.display = "none";

    var moreDescId = "moreDesc-" + page;
    var moreDesc = document.getElementById(moreDescId);
    moreDesc.style.display = "";

    var undoBtnId = "undoBtn-" + page;
    var undoBtn = document.getElementById(undoBtnId);
    undoBtn.style.display = "";

    $("#descriptionDiv").css("height", "auto");

}

function undoLearnMore(page) {

    var learnMoreBtnId = "learnMoreBtn-" + page;
    var learnMoreBtn = document.getElementById(learnMoreBtnId);
    learnMoreBtn.style.display = "";

    var moreDescId = "moreDesc-" + page;
    var moreDesc = document.getElementById(moreDescId);
    moreDesc.style.display = "none";

    var undoBtnId = "undoBtn-" + page;
    var undoBtn = document.getElementById(undoBtnId);
    undoBtn.style.display = "none";

    $("#descriptionDiv").css("height", "auto");

}

function print() {

    var divToPrint = document.getElementById('data_table');
    var newWin = window.open('', 'Print-Window');

    newWin.document.open();
    newWin.document.write("<html>" +
        "<head>" +
        "<link rel='stylesheet' href='/careercity/lib/bootstrap-4.1.1-dist/css/bootstrap.min.css'>" +
        "<style type='text/css' media='print'>  @page{margin-top: 30mm; margin-left: 0mm;} table { page-break-after:auto } tr{ page-break-inside:avoid; page-break-after:auto } td{ page-break-inside:avoid; page-break-after:auto } thead { display:table-header-group } tfoot { display:table-footer-group } </style> " +
        "<style type='text/css'>body {margin: auto 80px;} </style>" +
        "</head>" +
        "<body onload='window.print()'>" +
        divToPrint.innerHTML
        + "</body></html>");

    newWin.document.close();

    setTimeout(function () { newWin.close(); }, 10);

}