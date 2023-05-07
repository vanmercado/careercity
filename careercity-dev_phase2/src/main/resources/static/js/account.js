$(function(){

    $('#department-menu button').click(function(){
		$(this).parents('.dropdown').find('#department-textbtn span').html($(this).text() + ' <span class="caret"></span>');
   });

});

function addClickCount(id, pillar) {

    $.ajax({
        type: "GET",
        url: ctx + "/careercity-api/user",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            $('#workdayUsername').val(data.userAuthentication.details.preferred_username);
        },
        async: false,
        error: function (error) {
            console.log(error.responseText);
        }
    });

    var email = $('#workdayUsername').val();

    $.ajax({
        type: "POST",
        url: ctx + "/addClickCount",
        data: {
            accountId: id,
            pillar: pillar,
            email: email
        },
        dataType: "JSON"
    }).done(function (data) {
        console.log(data);
    });

}

$(document).ready(function () {

    /*$('.home-link').hover(
        function(){
            $(this).append($("<span></span>"));
        },
        function(){
            $(this).find("span:last").remove();
        }
    );*/

    $('input[name=filtr-search]').focusout(function () {
        var text = $(this).val();
        if ($.trim(text) != '') {
            $.ajax({
                type: "POST",
                url: ctx + "/addSearchedKey",
                data: {
                    searchedText: text,
                    email: email
                },
                dataType: "JSON"
            }).done(function (data) {
                console.log(data);
            });
        }
    });

});