// Custom JS for Accounts Page
$(document).ready(function(){
	// Home Page Script
	$(function() {	
		$('a[href*=\\#]:not([href=\\#])').click(function() {
		    var target = $(this.hash);
	        target = target.length ? target : $('[name=' + this.hash.substr(1) +']');
	        if (target.length) {
				$('html,body').animate({
				scrollTop: target.offset().top
	            }, 1000);
				return false;
			}
		});
	});
	// Function to Trigger Filterizr 
	var filterizd = $('.filtr-container').filterizr({
		 "delay": 1,
		    "filterOutCss": {
		        "opacity": 0,
		        "transform": "scale(0.75)",
		        "transition": "0.3s"
		    },
		    "filterInCss": {
		        "opacity": 1,
		        "transform": "scale(1)",
		        "transition": "0.3s"
		    }
	});
	
	
	$.ajaxPrefilter(function( settings, original, xhr ) {
	    if (['post','put','delete'].includes(settings.type.toLowerCase())
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
	            // Does this cookie string begin with the name we want?
	            if (cookie.substring(0, name.length + 1) == (name + '=')) {
	                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                break;
	            }
	        }
	    }
	    return cookieValue;
	}
	
});	