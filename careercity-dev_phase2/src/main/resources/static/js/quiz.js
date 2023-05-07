$(function() {
    "use strict";
    $('.info').first().show().animate({
        // Start Change - Daryll Moya - 27-FEB-2021
        //width: '76%',
        width: '84%',
        // End Change - Daryll Moya - 27-FEB-2021
        height: $("#quizSection").css("height")
    });

    $(".item").css("height", $("#quizSection").css("height"));

    $(document).ready(function() {
        document.getElementById('quizSection').scrollIntoView();
    });

	/*
	$('.nextBtn').click(function() {
		
        if ($("#quizName").val().length === 0) {
            $('#quizName_helper').attr('hidden', false);
            if ($("#quizWorkdayId").val().length === 8) {
                $('#quizWorkdayId_helper').attr('hidden', true);
                $('#quizName').focus();
                return;
            }
        } else {
            $('#quizName_helper').attr('hidden', true);
        }

        if ($("#quizWorkdayId").val().length !== 8) {
            $('#quizWorkdayId_helper').attr('hidden', false);
            $('#quizWorkdayId').focus();
            return;
        } else {
            $('#quizWorkdayId_helper').attr('hidden', true);
        }
    });
	*/

    $('.backBtn').click(function() {
        var prevbar = $(this).closest('.info').prev();
        $(".content").hide();
        var sectionHeight = $(".item").css("height");
        prevbar.prev('.info').show().animate({
            // Start Change - Daryll Moya - 27-FEB-2021
            //width: '76%',
            width: '84%',
            // End Change - Daryll Moya - 27-FEB-2021
            'padding': '2%',
            'height': sectionHeight
        }, 500).siblings(".info").animate({
            "width": "0", "height": sectionHeight
        }, 500, function() {
            prevbar.prev('.info').siblings(".info").css({ "display": "none" });
            $(".content").show();
        });
    });
	

	// Next button event toggles to next form/page when clicked.
	$.fn.nextBtnToggle = function() {
		var nextbar = $(this).closest('.info').next();
        $(".content").hide();
        var sectionHeight = $(".item").css("height");
        nextbar.next('.info').show().animate({
            // Start Change - Daryll Moya - 27-FEB-2021
            //width: '76%',
            width: '84%',
            // End Change - Daryll Moya - 27-FEB-2021
            'padding': '2%',
            'height': sectionHeight
        }, 500).siblings(".info").animate({
            "width": "0", "height": sectionHeight
        }, 500, function() {
            nextbar.next('.info').siblings(".info").css({ "display": "none" });
            $(".content").show();
        });
	}

	// Modal validation for Questionnaire
	$('.nextBtn').click(function() {
		if (this.id == 'quizmainbtn') {
			$(this).nextBtnToggle();
		}
	
		if (this.id == 'question1-next') {
			var checkbox1Count = (document.querySelectorAll('#question1 input[type="checkbox"]:checked')).length;
			if (checkbox1Count > 0) {
				$(this).removeAttr('data-toggle');
				$(this).removeAttr('data-target');
				$(this).nextBtnToggle();
			} else {
				$('#quizModalText').text('Please select your preferred site location to proceed.\nYou may select a maximum of 2.');
				$(this).attr('data-toggle', 'modal');
				$(this).attr('data-target', '#quizModal');
			}
		}
		
		if (this.id == 'question2-next') {
			var checkbox2Count = (document.querySelectorAll('#question2 input[type="checkbox"]:checked')).length;
			var checkbox3Count = (document.querySelectorAll('#question3 input[type="checkbox"]:checked')).length;
			var checkbox4Count = (document.querySelectorAll('#question4 input[type="checkbox"]:checked')).length;
			if (checkbox2Count > 0 || checkbox3Count > 0 || checkbox4Count > 0) {
				$(this).removeAttr('data-toggle');
				$(this).removeAttr('data-target');
				$(this).nextBtnToggle();
			} else {
				$('#quizModalText').text('Please select your prefered type of work to proceed.\nYou may select a maximum of 3.');
				$(this).attr('data-toggle', 'modal');
				$(this).attr('data-target', '#quizModal');
			}
		}
		
		if (this.id == 'question8-next') {
			var radiobutton8 = document.querySelector('#question8 input[type="radio"]:checked');
			if (radiobutton8 !== null) {
				console.log('NOT NULL '+radiobutton8);
				$(this).removeAttr('data-toggle');
				$(this).removeAttr('data-target');
				$(this).nextBtnToggle()
			} else {
				console.log(radiobutton8);
				$('#quizModalText').text('Please select your years of BPO Experience to proceed.');
				$(this).attr('data-toggle', 'modal');
				$(this).attr('data-target', '#quizModal');
			}
		}
	
	});
	
	
	/*
    $("#q6div input[type='radio']").click(function() {
        $('#q7divSub input[type="radio"]').each(function() {
            $('#q7divSub input[type="radio"]').prop('checked', false);
        })
    });


    $("#q8div input[type='radio']").click(function() {
        $('#question8 input[type="checkbox"]').each(function() {
            $('#question8 input[type="checkbox"]').prop('checked', false);
        })
    });
	*/
	
	
    $(".form-check-input").click(function() {
        var questionElement = $(this).attr("name");

        if (questionElement == "q1") {
            var checkboxes = document.querySelectorAll('#question1 input[type="checkbox"]:checked');
            validateCheckbox('#question1', checkboxes, 2);
        }
        else if (questionElement == "q2" || questionElement == "q3" || questionElement == "q4") {
            var checkboxes = document.querySelectorAll('#question2 input[type="checkbox"]:checked');
            validateCheckbox('#question2', checkboxes, 3);
        }

		else if (questionElement == "q8") {
            var radiobutton = document.querySelector('#question8 input[type="radio"]:checked');
        }

		/*
        else if(questionElement == "q5"){
            var checkboxes = document.querySelectorAll('#question5 input[type="checkbox"]:checked');
            // Initiate a variable for max input to restrict the buttons if "Any" item chosen.
            var max = 2;
            // Change the value of max if "Any" is selected.
            max = isChoosedAny(checkboxes);
            validateCheckbox('#question5',checkboxes,max);
        }
		
        else if (questionElement == "q5") {
            var radiobutton = document.querySelector('#question5 input[type="radio"]:checked');
            validateRadioButton('#question5', radiobutton);
        }
        else if (questionElement == "q6") {
            var q6Radiobutton = document.querySelector('#q6div input[type="radio"]:checked');
            // Hide Question 7 if Educational Attaintment does not apply Field of Study
            if (q6Radiobutton.id == "q64") {
                $('#q7divSub').hide();
            }
            else {
                $('#q7divSub').show();
            }
        }
        else if (questionElement == "q6" || questionElement == "q7") {
            var q6Radiobutton = document.querySelector('#q6div input[type="radio"]:checked');
            var q7Radiobutton = document.querySelector('#q7divSub input[type="radio"]:checked');
            //validateQ6AndQ7('#question6', q6Radiobutton, q7Radiobutton);
        }

        else if (questionElement == "q8" || questionElement == "q9") {
            var radiobutton = document.querySelector('#question8 input[type="radio"]:checked');
            var checkboxes = document.querySelectorAll('#question8 input[type="checkbox"]:checked');
            //validateQ8AndQ9('#question8', radiobutton, checkboxes);
        }
		*/
	})
	

    $(document).keypress(function(e) {
        if (e.which == 13) {
            e.preventDefault();
            e.stopPropagation();
        }
    });


    /*$("#agreeCheckbox").click(function(){
        var status = $("#agreeCheckbox").is(":checked");
        if(status == true){
            $("#submitBtn").removeAttr('disabled');
        }
        else {
            $("#submitBtn").attr("disabled", "disabled");
        }
    });*/
    
});

// Temporarily removed due to not being required to provide input to proceed to other questions
/*
function validateQ6AndQ7(questionNumber, radioQ6, radioQ7) {
    if (radioQ6 === null || radioQ7 === null) {
        $(questionNumber + "-next").attr("disabled", "disabled");
    }
    else {
        $(questionNumber + "-next").removeAttr('disabled');
    }
}

function validateQ8AndQ9(questionNumber, radiobutton, checkboxes) {
    if (checkboxes.length <= 0 || radiobutton === null) {
        $(questionNumber + "-next").attr("disabled", "disabled");
    }
    else {
        $(questionNumber + "-next").removeAttr('disabled');
    }
}
*/

function validateCheckbox(questionNumber, checkbox, max) {

    var questionElement = questionNumber + " input[type='checkbox']";

    if (checkbox.length > 0) {
        //$(questionNumber + "-next").removeAttr('disabled');
    }

    if (checkbox.length == max) {
        var checkboxes = document.querySelectorAll(questionElement);
        checkboxes.forEach(function(e) {
            if (e.checked == false) {
                e.disabled = true;
                // $(e).parent().css('text-decoration', 'line-through');
				$(e).parent().css('opacity', '0.3');
            }
        });
    }

    if (checkbox.length < max) {
        var checkboxes = document.querySelectorAll(questionElement);
        checkboxes.forEach(function(e) {
            e.disabled = false;
            // $(e).parent().css('text-decoration', 'none');
			$(e).parent().css('opacity', '1');
        });
    }


	/*
    // Algorithm for disabling the "ANY" item if it's not the first pick.
    if (questionNumber == "#question5" && checkbox.length == 1 && checkbox[0].value != 28) {
        var checkboxes = document.querySelectorAll(questionElement);
        checkboxes.forEach(function(e) {
            if (e.value == 28) {
                e.disabled = true;
                $(e).parent().css('text-decoration', 'line-through');
            }
        });
    }

    // Temporarily removed due to not being required to provide input to proceed to other questions
    
    if (checkbox.length < 1) {
        $(questionNumber + "-next").attr("disabled", "disabled");
    }
    */

}

function validateRadioButton(questionNumber, radioButton) {
    if (radioButton.value !== null) {
        $(questionNumber + "-next").removeAttr('disabled');
        return;
    }
    $(questionNumber + "-next").attr("disabled", "disabled");
}

// Function that disable any choices if "ANY" item is chosen.
function isChoosedAny(checkbox) {
    if (checkbox.length == 1) {
        checkbox.forEach(function(e) {
            if (e.value == 28) {
                max = 1;
            } else {
                max = 2;
            }
        });
    }
    return max;
}

$('#quizWorkdayId').on('keypress', function(e) {
    var inputLength = $('#quizWorkdayId').val().length;

    if (inputLength >= 8) {
        e.preventDefault();
    }

    return e.metaKey || // cmd/ctrl
        e.which <= 0 || // arrow keys
        e.which == 8 || // delete key
        /[0-9]/.test(String.fromCharCode(e.which)); // numbers
});

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: ctx + "/careercity-api/user",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
            $('#quizWorkdayEmail').val(data.userAuthentication.details.preferred_username);
            $('#quizName').val(data.userAuthentication.details.name);
        },
        async: false,
        error: function(error) {
            console.log(error.responseText);
        }
    });

    var email = $('#quizWorkdayEmail').val();

    var user = {
        "email": email
    }

    $.ajaxPrefilter(function(settings, original, xhr) {
        if (['post', 'put', 'delete'].includes(settings.type.toLowerCase())
            && !settings.crossDomain) {
            xhr.setRequestHeader("X-XSRF-TOKEN", getCookie('XSRF-TOKEN'));
        }
    });

    function getCookie(name) {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }

    $.ajax({
        type: "POST",
        url: ctx + "/careercity-api/authenticated-user",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user),
        success: function (data) {
            $('#quizWorkdayId').val(data.username);
        },
        error: function (error) {
            console.log(error.responseText);
        }
    });

});