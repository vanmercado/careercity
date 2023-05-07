// To toggle the sidebar, just switch the CSS classes
$(document).ready(function () {

    $(".toggle-sidebar").click(function () {

        console.log("ello");
        $("#sidebar").toggleClass("collapsed");
        $("#content").toggleClass("col-md-12 col-md-9");

        return false;

    });

});