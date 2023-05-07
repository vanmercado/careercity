/*
 * @Author: A.M.Castor, x164856
 * @Date: April 22, 2020
 */
function init_dataTable(className) {
	$(className).DataTable({
		"emptyTable": "No data available in table",
		"paging": true,
		"searching": false,
		"bLengthChange": false,
		"pageLength": 6,
		"order": [[1, "desc"]],
		language: {
			paginate: {
				next: '&#62;', // or '>'
				previous: '&#60;' // or '<' 
			}
		},
		"info": false
		//"infoCallback": function( settings, start, end, max, total, pre ) {    
		//    var api = this.api();
		//    var pageInfo = api.page.info();          
		//    return 'Page '+ (pageInfo.page+1) +' of '+ pageInfo.pages;
		//}
	});
}

function init_dataTable_ytdModalTable(className) {
	$(className).DataTable({
		"emptyTable": "No data available in table",
		"paging": true,
		"searching": true,
		"bLengthChange": false,
		"pageLength": 10,
		"order": [[1, "desc"]],
		language: {
			paginate: {
				next: '&#62;', // or '>'
				previous: '&#60;' // or '<' 
			}
		},
		"info": false
		//"infoCallback": function( settings, start, end, max, total, pre ) {    
		//    var api = this.api();
		//    var pageInfo = api.page.info();          
		//    return 'Page '+ (pageInfo.page+1) +' of '+ pageInfo.pages;
		//}
	});
	$(className).find('tr.headings').css("height", "32px");

} //init_dataTable_ytdModalTable



function init_customBarGraph(startDate, endDate, site, functionalArea, manager, supervisor) {

	if (typeof (Morris) === 'undefined') { return; }
	console.log('init_morris_charts');

	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/preferredsites",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(error) {
			console.log(error.responseText);
		}
	});

	function getData(data) {
		if (data.length == 0) {
			$('.prefSiteWrapper').append(
				' <div style="text-align: center; margin-top: 60px;"><small style="color:gray;">No data</small></div> '
			);
		} else {
			$('.prefSiteWrapper').append(
				' <div id="graph_bar_prefSite" style="width:100%; height:200px;"></div>  '
			);
			var siteListFormat = [];
			$.each(data, function(k, v) {
				siteListFormat.push({ 'label': v.label, 'counts': v.counts });
			});

			var def = $('#graph_bar_prefSite').empty();
			var bar = Morris.Bar({
				element: 'graph_bar_prefSite',
				data: siteListFormat,
				xkey: 'label',
				ykeys: ['counts'],
				labels: ['Counts'],
				barRatio: 0.4,
				barColors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
				xLabelAngle: 30,
				gridTextSize: 9,
				hideHover: 'auto',
				resize: true
			});

			$('#graph_bar_prefSite').find('svg').css("height", "300px");

			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				var target = $(e.target).attr("href")

				if (target == '#tab_content2') {
					bar.redraw();
					$(window).trigger('resize');
				}
				else if (target == '#tab_content1') {
					//def.redraw();
					//$(window).trigger('resize');
				}
				else {

				}
			});


		}


	} //getData
} //init_customBarGraph


// CAREER SURVEY RESULTS TAB
function load_TableSurveyPrefWorkTids(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/preferredworktids",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (let i = 0; i < data.length; i++) {
				$('.survey_work_tids_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.survey_work_tids_table';
			init_dataTable(className);
		}
		else {
			$('.survey_work_tids_div').empty();
			$('.survey_work_tids_div').append(loadNoDataMessage_table());
		}
	}

} //load_TableSurveyPrefWorkTids

function load_TableSurveyPrefWorkSupport(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/preferredworksupport",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (let i = 0; i < data.length; i++) {
				$('.survey_work_support_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.survey_work_support_table';
			init_dataTable(className);
		}
		else {
			$('.survey_work_support_div').empty();
			$('.survey_work_support_div').append(loadNoDataMessage_table());
		}
	}
} //load_TableSurveyPrefWorkSupport



// CAREER CITY CLICKS TAB
function load_TableClicksPreferredSites(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/preferredsites",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_pref_site_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.clicks_pref_site_table';
			init_dataTable(className);
		} else {
			$('.clicks_pref_site_div').empty();
			$('.clicks_pref_site_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksPreferredSites

function load_TableClicksPreferredPrograms(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/preferredprograms",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_pref_programs_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}

			let className = '.clicks_pref_programs_table';
			init_dataTable(className);
		} else {
			$('.clicks_pref_programs_div').empty();
			$('.clicks_pref_programs_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksPreferredPrograms

function load_TableClicksInDemandBusinessTypes(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/indemandbusinesstypes",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_bus_types_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}

			let className = '.clicks_bus_types_table';
			init_dataTable(className);
		} else {
			$('.clicks_bus_types_div').empty();
			$('.clicks_bus_types_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksInDemandBusinessTypes

function load_TableClicksInDemandWorkTypeCCO(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/indemandworktypecco",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_work_type_cco_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}

			let className = '.clicks_work_type_cco_table';
			init_dataTable(className);
		} else {
			$('.clicks_work_type_cco_div').empty();
			$('.clicks_work_type_cco_div').append(loadNoDataMessage_table_cclicksTab());
		}

	}
} //load_TableClicksInDemandWorkTypeCCO

function load_TableClicksPrefSuppJobs(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/preferredsupportjobs",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_pref_supp_jobs_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}

			let className = '.clicks_pref_supp_jobs_table';
			init_dataTable(className);
		} else {
			$('.clicks_pref_supp_jobs_div').empty();
			$('.clicks_pref_supp_jobs_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksPrefSuppJobs

function load_TableClicksPrefSuppDept(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/preferredsupportdept",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_pref_supp_dept_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.clicks_pref_supp_dept_table';
			init_dataTable(className);
		} else {
			$('.clicks_pref_supp_dept_div').empty();
			$('.clicks_pref_supp_dept_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksPrefSuppDept

function load_TableClicksInDemandJobProfile(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/indemandjobprofile",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_job_profile_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.clicks_job_profile_table';
			init_dataTable(className);
		} else {
			$('.clicks_job_profile_div').empty();
			$('.clicks_job_profile_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksInDemandJobProfile

function load_TableClicksSupportAndTids(startDate, endDate, site, functionalArea, manager, supervisor) {
	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/clicks/supportandtids",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				$('.clicks_supp_and_tids_tbody').append(
					"<tr><td><span style='float:left'>" + data[i].label + "</span></td><td><span style='float:right'>" + data[i].counts + "</span></td></tr"
				);
			}
			let className = '.clicks_supp_and_tids_table';
			init_dataTable(className);
		} else {
			$('.clicks_supp_and_tids_div').empty();
			$('.clicks_supp_and_tids_div').append(loadNoDataMessage_table_cclicksTab());
		}
	}
} //load_TableClicksSupportAndTids

//Analytics depending on daterange
$('#reportrange_right_surveyResult').on('apply.daterangepicker', function(ev, picker) {

	// ===================================== Bar graph =====================================
	//                            alert(formatDate(picker.startDate));
	//                            alert(formatDate(picker.endDate));

	function formatDate(date) {
		let d = new Date(date),
			month = '' + (d.getMonth() + 1),
			day = '' + d.getDate(),
			year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [year, month, day].join('-');
	}

	var startDate = formatDate(picker.startDate);
	var endDate = formatDate(picker.endDate);

	$('.fromDate_surveyResult').val(startDate);
	$('.toDate_surveyResult').val(endDate);

	clearExistingData_surveyResult();
	checkCurrentFilterValuesAndPassToController_surveyResult();

});

// daterangepicker for Career City Clicks Tab
$('#reportrange_right_ccClicksTab').on('apply.daterangepicker', function(ev, picker) {

	clearExistingData_clicks();

	function formatDate(date) {
		var d = new Date(date),
			month = '' + (d.getMonth() + 1),
			day = '' + d.getDate(),
			year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [year, month, day].join('-');
	}

	let startDate = formatDate(picker.startDate);
	let endDate = formatDate(picker.endDate);

	$('.fromDate_clicks').val(startDate);
	$('.toDate_clicks').val(endDate);

	checkCurrentFilterValuesAndPassToController_clicks();


}); //end - daterangepicker for Career City Clicks Tab


// DATARANGE PICKER - USAGE TAB
$('#reportrange_right_usageTab').on('apply.daterangepicker', function(ev, picker) {

	function formatDate(date) {
		var d = new Date(date),
			month = '' + (d.getMonth() + 1),
			day = '' + d.getDate(),
			year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [year, month, day].join('-');
	}

	var startDate = formatDate(picker.startDate);
	var endDate = formatDate(picker.endDate);

	$('.fromDate_usageReport').val(startDate);
	$('.toDate_usageReport').val(endDate);

	$('#usageTraffic').empty();
	$('.canvasContainer_usageByuserGroup').empty();
	$('.labelContainer_usageByuserGroup').empty();
	$('.canvasContainer_usageByGender').empty();
	$('.labelContainer_usageByGender').empty();
	$('.canvasContainer_usageBySite').empty();
	$('.labelContainer_usageBySite').empty();
	$('.canvasContainer_usageByJobLevel').empty();
	$('.labelContainer_usageByJobLevel').empty();

	checkCurrentFilterValuesAndPassToController_usageReport();

	//init_usageTraffic(start, end, func, "all");
	//init_usageReportDougnut(start, end, func, "all");

}); // END - DATARANGE PICKER - USAGE TAB

function init_usageTraffic(startDate, endDate, site, functionalArea, manager, supervisor) {

	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	$.ajax({
		type: "POST",
		url: "../reporting/usage/byusertraffic",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getData(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getData(data) {
		if (typeof ($.plot) === 'undefined') { return; }

		if (data.length != 0) {
			var totalCounts = 0;
			let usageTrafficData = [];
			for (let i = 0; i < data.length; i++) {
				usageTrafficData.push([Date.parse(data[i].label), data[i].counts]);
				totalCounts += data[i].counts;
			}
			// update ytd user counts
			$('.ytdUserCounts_value').text(totalCounts);
			let usageTraffic_settings = {
				grid: {
					show: true,
					aboveData: true,
					color: "#3f3f3f",
					labelMargin: 10,
					axisMargin: 0,
					borderWidth: 0,
					borderColor: null,
					minBorderMargin: 5,
					clickable: true,
					hoverable: true,
					autoHighlight: true,
					mouseActiveRadius: 100
				},
				series: {
					lines: {
						show: true,
						fill: false,
						lineWidth: 1.5,
						steps: false
					},
					points: {
						show: true,
						radius: 1.5,
						symbol: "circle",
						lineWidth: 3.0
					}
				},
				legend: {
					position: "ne",
					margin: [0, -25],
					noColumns: 0,
					labelBoxBorderColor: null,
					labelFormatter: function(label, series) {
						return label + '&nbsp;&nbsp;';
					},
					width: 20,
					height: 1
				},
				colors: ['#96CA59', '#3F97EB', '#72c380', '#6f7a8a', '#f7cb38', '#5a8022', '#2c7282'],
				shadowSize: 0,
				tooltip: true,
				tooltipOpts: {
					content: "%s: %y.0",
					xDateFormat: "%d/%m",
					shifts: {
						x: -30,
						y: -50
					},
					defaultTheme: false
				},
				yaxis: {
					min: 0
				},
				xaxis: {
					mode: "time",
					minTickSize: [1, "day"],
					timeformat: "%d %b",
					min: usageTrafficData[0][0],
					max: usageTrafficData[data.length - 1][0]
				},
			};

			if ($("#usageTraffic").length) {

				$.plot($("#usageTraffic"),
					[{
						label: "Usage Traffic",
						data: usageTrafficData,
						lines: {
							fillColor: "rgba(150, 202, 89, 0.12)"
						},
						points: {
							fillColor: "#fff"
						},
					}], usageTraffic_settings);
			}
		}
		else {
			$('#usageTraffic').append('<div style="text-align: center"><medium style="color:gray">No data</medium></div>');
			$('.demo-placeholder').css('padding-top', '110px');
			$('.ytdUserCounts_value').text("0");
		}
	}
}

function init_defaultValues() {
	// Default date for daterangepicker: last 30 days
	// Parse Date to String in yyyy/mm/dd format
	let c = new Date();
	cMonth = '' + (c.getMonth() + 1),
		cDay = '' + c.getDate(),
		cYear = c.getFullYear();
	if (cMonth.length < 2)
		cMonth = '0' + cMonth;
	if (cDay.length < 2)
		cDay = '0' + cDay;
	let currentDate = [cYear, cMonth, cDay].join('-');

	let p = new Date();
	p.setMonth(p.getMonth() - 12);
	pMonth = '' + (p.getMonth() + 1),
		pDay = '' + p.getDate(),
		pYear = p.getFullYear();
	if (pMonth.length < 2)
		pMonth = '0' + pMonth;
	if (pDay.length < 2)
		pDay = '0' + pDay;
	let oneYearAgo = [pYear, pMonth, pDay].join('-');

	let funcArea = "all";
	let mgrId = "all";
	let defaultValues = {
		"from_date": oneYearAgo,
		"to_date": currentDate,
		"functional_area": funcArea,
		"manager_id": mgrId
	}

	return defaultValues;

}

function init_surveyResultDougnut(startDate, endDate, site, functionalArea, manager, supervisor) {
	$('.canvasContainer_surveyResult_cco').append(
		' <canvas class="canvasDoughnut_surveyResult_cco" height="130" width="130" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_surveyResult_bpoExp').append(
		' <canvas class="canvasDoughnut_surveyResult_bpoExp" height="130" width="130" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_surveyResult_sched').append(
		' <canvas class="canvasDoughnut_surveyResult_sched" height="130" width="130" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_surveyResult_study').append(
		' <canvas class="canvasDoughnut_surveyResult_study" height="130" width="130" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_surveyResult_skills').append(
		' <canvas class="canvasDoughnut_surveyResult_skills" height="130" width="130" style="margin: 15px 10px 10px 0"></canvas> '
	);

	if (typeof (Chart) === 'undefined') { return; }

	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor,
	}

	/*PreferredTypeOfWork-CCO Doughnut */
	if ($('.canvasDoughnut_surveyResult_cco').length) {

		$.ajax({
			type: "POST",
			url: "../reporting/surveyresults/preferredworkcco",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(dateRange),
			success: function(data) {
			getDataFromShowPreferrredWorkCCO(data);
		},
			error: function(err) {
				console.log(err.responseText);
			}
		});

	function getDataFromShowPreferrredWorkCCO(data) {
		if (data.length != 0) {
			var ccoNames = []
			var ccoCounts = [];
			var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
			var ccoColors = [];
			var ccoColorsHover = [];

			for (var i = 0; i < data.length; i++) {
				if (data[i].counts != "0") {
					ccoNames.push(data[i].label);
					ccoCounts.push(data[i].counts);
				}
			}

			for (var i = 0; i < ccoNames.length; i++) {
				ccoColors.push(colors[i]);
				ccoColorsHover.push(colorsHover[i]);
			}

			for (var i = 0; i < ccoNames.length; i++) {
				$('.labelContainer_surveyResult_cco').append(
					' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + ccoNames[i] + '</div></div> '
				);
			}

			var ccoNamesData = JSON.stringify(ccoNames);
			var ccoCountsData = JSON.stringify(ccoCounts);

			var countList = [];
			for (var i = 0; i < ccoCounts.length; i++) {
				countList.push(parseInt(ccoCounts[i]));
			}

			var total = 0;
			for (var i = 0; i < countList.length; i++) {
				total = total + countList[i];
			}

			var percentageList = [];
			for (var i = 0; i < ccoCounts.length; i++) {
				percentageList.push(((countList[i] / total) * 100).toFixed(2));
			}

			var percentageData = JSON.stringify(percentageList);
			var ccoColorsData = JSON.stringify(ccoColors);
			var ccoColorsHoverData = JSON.stringify(ccoColorsHover);

			var chart_doughnut_settings = {
				type: 'doughnut',
				tooltipFillColor: "rgba(51, 51, 51, 0.55)",
				data: {
					labels: jQuery.parseJSON(ccoNamesData),
					datasets: [{
						data: jQuery.parseJSON(ccoCountsData),
						backgroundColor: jQuery.parseJSON(ccoColorsData),
						hoverBackgroundColor: jQuery.parseJSON(ccoColorsHoverData),
						percentage: jQuery.parseJSON(percentageData)
					}]
				},
				options: {
					legend: false,
					responsive: false,
					tooltips: {
						titleFontSize: 11,
						bodyFontSize: 11,
						callbacks: {
							label: function(tooltipItem, data) {
								return [
									data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
								];
							}
						}
					}
				}
			}

			$('.canvasDoughnut_surveyResult_cco').each(function() {
				var chart_element = $(this);
				var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
			});
		} else {
			$('.canvasContainer_surveyResult_cco').empty();
			$('.canvasContainer_surveyResult_cco').append(loadNoDataMessage());
		}
	} //getDataFromShowPreferrredWorkCCO
}
/* End PreferredTypeOfWork-CCO Doughnut */

/* BPOExperience Doughnut */
if ($('.canvasDoughnut_surveyResult_bpoExp').length) {

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/bpoexperience",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getDataFromShowBPOExperience(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromShowBPOExperience(data) {

		if (data.length != 0) {
			var bpoExpNames = []
			var bpoExpCounts = [];
			var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
			var bpoExpColors = [];
			var bpoExpColorsHover = [];

			for (var i = 0; i < data.length; i++) {
				if (data[i].counts != "0") {
					bpoExpNames.push(data[i].label);
					bpoExpCounts.push(data[i].counts);
				}
			}

			for (var i = 0; i < bpoExpNames.length; i++) {
				bpoExpColors.push(colors[i]);
				bpoExpColorsHover.push(colorsHover[i]);
			}

			for (var i = 0; i < bpoExpNames.length; i++) {
				$('.labelContainer_surveyResult_bpoExp').append(
					' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + bpoExpNames[i] + '</div></div> '
				);
			}

			var bpoExpNamesData = JSON.stringify(bpoExpNames);
			var bpoExpCountsData = JSON.stringify(bpoExpCounts);

			var countList = [];
			for (var i = 0; i < bpoExpCounts.length; i++) {
				countList.push(parseInt(bpoExpCounts[i]));
			}

			var total = 0;
			for (var i = 0; i < countList.length; i++) {
				total = total + countList[i];
			}

			var percentageList = [];
			for (var i = 0; i < bpoExpCounts.length; i++) {
				percentageList.push(((countList[i] / total) * 100).toFixed(2));
			}

			var percentageData = JSON.stringify(percentageList);
			var bpoExpColorsData = JSON.stringify(bpoExpColors);
			var bpoExpColorsHoverData = JSON.stringify(bpoExpColorsHover);

			var chart_doughnut_settings = {
				type: 'doughnut',
				tooltipFillColor: "rgba(51, 51, 51, 0.55)",
				data: {
					labels: jQuery.parseJSON(bpoExpNamesData),
					datasets: [{
						data: jQuery.parseJSON(bpoExpCountsData),
						backgroundColor: jQuery.parseJSON(bpoExpColorsData),
						hoverBackgroundColor: jQuery.parseJSON(bpoExpColorsHoverData),
						percentage: jQuery.parseJSON(percentageData)
					}]
				},
				options: {
					legend: false,
					responsive: false,
					tooltips: {
						titleFontSize: 11,
						bodyFontSize: 11,
						callbacks: {
							label: function(tooltipItem, data) {
								return [
									data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
								];
							}
						}
					}
				}
			}

			$('.canvasDoughnut_surveyResult_bpoExp').each(function() {
				var chart_element = $(this);
				var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
			});
		}
		else {
			$('.canvasContainer_surveyResult_bpoExp').empty();
			$('.canvasContainer_surveyResult_bpoExp').append(loadNoDataMessage());
		}
	} //getDataFromShowBPOExperience
}
/* End BPOExperience Doughnut */

/* PreferredWorkSchedule Doughnut */
if ($('.canvasDoughnut_surveyResult_sched').length) {

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/preferredworkschedule",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
		getDataFromShowPreferredWorkSchedule(data);
	},
		error: function(err) {
		console.log(err.responseText);
	}
		});

function getDataFromShowPreferredWorkSchedule(data) {
	if (data.length != 0) {
		var schedNames = []
		var schedCounts = [];
		var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
		var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
		var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
		var schedColors = [];
		var schedColorsHover = [];

		for (var i = 0; i < data.length; i++) {
			if (data[i].counts != "0") {
				schedNames.push(data[i].label);
				schedCounts.push(data[i].counts);
			}
		}

		for (var i = 0; i < schedNames.length; i++) {
			schedColors.push(colors[i]);
			schedColorsHover.push(colorsHover[i]);
		}

		for (var i = 0; i < schedNames.length; i++) {
			$('.labelContainer_surveyResult_sched').append(
				' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + schedNames[i] + '</div></div> '
			);
		}

		var schedNamesData = JSON.stringify(schedNames);
		var schedCountsData = JSON.stringify(schedCounts);

		var countList = [];
		for (var i = 0; i < schedCounts.length; i++) {
			countList.push(parseInt(schedCounts[i]));
		}

		var total = 0;
		for (var i = 0; i < countList.length; i++) {
			total = total + countList[i];
		}

		var percentageList = [];
		for (var i = 0; i < schedCounts.length; i++) {
			percentageList.push(((countList[i] / total) * 100).toFixed(2));
		}

		var percentageData = JSON.stringify(percentageList);
		var schedColorsData = JSON.stringify(schedColors);
		var schedColorsHoverData = JSON.stringify(schedColorsHover);

		var chart_doughnut_settings = {
			type: 'doughnut',
			tooltipFillColor: "rgba(51, 51, 51, 0.55)",
			data: {
				labels: jQuery.parseJSON(schedNamesData),
				datasets: [{
					data: jQuery.parseJSON(schedCountsData),
					backgroundColor: jQuery.parseJSON(schedColorsData),
					hoverBackgroundColor: jQuery.parseJSON(schedColorsHoverData),
					percentage: jQuery.parseJSON(percentageData)
				}]
			},
			options: {
				legend: false,
				responsive: false,
				tooltips: {
					titleFontSize: 11,
					bodyFontSize: 11,
					callbacks: {
						label: function(tooltipItem, data) {
							return [
								data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
							];
						}
					}
				}
			}
		}

		$('.canvasDoughnut_surveyResult_sched').each(function() {
			var chart_element = $(this);
			var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
		});
	} else {
		$('.canvasContainer_surveyResult_sched').empty();
		$('.canvasContainer_surveyResult_sched').append(loadNoDataMessage());
	}
} //getDataFromShowPreferredWorkSchedule
	}
/* End PreferredWorkSchedule Doughnut */

/* FieldOfStudy Doughnut */
if ($('.canvasDoughnut_surveyResult_study').length) {

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/fieldofstudy",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			getDataFromShowAllFieldOfStudy(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromShowAllFieldOfStudy(data) {
		if (data.length != 0) {
			var studyNames = []
			var studyCounts = [];
			var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
			var studyColors = [];
			var studyColorsHover = [];

			for (var i = 0; i < data.length; i++) {
				if (data[i].counts != "0") {
					studyNames.push(data[i].label);
					studyCounts.push(data[i].counts);
				}
			}

			for (var i = 0; i < studyNames.length; i++) {
				studyColors.push(colors[i]);
				studyColorsHover.push(colorsHover[i]);
			}

			for (var i = 0; i < studyNames.length; i++) {
				$('.labelContainer_surveyResult_study').append(
					' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + studyNames[i] + '</div></div> '
				);
			}

			var studyNamesData = JSON.stringify(studyNames);
			var studyCountsData = JSON.stringify(studyCounts);

			var countList = [];
			for (var i = 0; i < studyCounts.length; i++) {
				countList.push(parseInt(studyCounts[i]));
			}

			var total = 0;
			for (var i = 0; i < countList.length; i++) {
				total = total + countList[i];
			}

			var percentageList = [];
			for (var i = 0; i < studyCounts.length; i++) {
				percentageList.push(((countList[i] / total) * 100).toFixed(2));
			}

			var percentageData = JSON.stringify(percentageList);
			var studyColorsData = JSON.stringify(studyColors);
			var studyColorsHoverData = JSON.stringify(studyColorsHover);

			var chart_doughnut_settings = {
				type: 'doughnut',
				tooltipFillColor: "rgba(51, 51, 51, 0.55)",
				data: {
					labels: jQuery.parseJSON(studyNamesData),
					datasets: [{
						data: jQuery.parseJSON(studyCountsData),
						backgroundColor: jQuery.parseJSON(studyColorsData),
						hoverBackgroundColor: jQuery.parseJSON(studyColorsHoverData),
						percentage: jQuery.parseJSON(percentageData)
					}]
				},
				options: {
					legend: false,
					responsive: false,
					tooltips: {
						titleFontSize: 11,
						bodyFontSize: 11,
						callbacks: {
							label: function(tooltipItem, data) {
									return [
									data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
									];
		}
	}
}
					}
				}

$('.canvasDoughnut_surveyResult_study').each(function() {
	var chart_element = $(this);
	var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
});
			} else {
	$('.canvasContainer_surveyResult_study').empty();
	$('.canvasContainer_surveyResult_study').append(loadNoDataMessage());
}
		} //getDataFromShowAllFieldOfStudy
	}
/* End FieldOfStudy Doughnut */

/* AdditionalSkills Doughnut */
if ($('.canvasDoughnut_surveyResult_skills').length) {

	$.ajax({
		type: "POST",
		url: "../reporting/surveyresults/additionalskills",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
		getDataFromShowAllAdditionalSkills(data);
	},
		error: function(err) {
			console.log(err.responseText);
		}
		});

function getDataFromShowAllAdditionalSkills(data) {
	if (data.length != 0) {
		var skillNames = []
		var skillCounts = [];
		var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
		var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
		var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
		var skillColors = [];
		var skillColorsHover = [];

		for (var i = 0; i < data.length; i++) {
			if (data[i].counts != "0") {
				skillNames.push(data[i].label);
				skillCounts.push(data[i].counts);
			}
		}

		for (var i = 0; i < skillNames.length; i++) {
			skillColors.push(colors[i]);
			skillColorsHover.push(colorsHover[i]);
		}

		for (var i = 0; i < skillNames.length; i++) {
			$('.labelContainer_surveyResult_skills').append(
				' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + skillNames[i] + '</div></div> '
			);
		}

		var skillNamesData = JSON.stringify(skillNames);
		var skillCountsData = JSON.stringify(skillCounts);

		var countList = [];
		for (var i = 0; i < skillCounts.length; i++) {
			countList.push(parseInt(skillCounts[i]));
		}

		var total = 0;
		for (var i = 0; i < countList.length; i++) {
			total = total + countList[i];
		}

		var percentageList = [];
		for (var i = 0; i < skillCounts.length; i++) {
			percentageList.push(((countList[i] / total) * 100).toFixed(2));
		}

		var percentageData = JSON.stringify(percentageList);
		var skillColorsData = JSON.stringify(skillColors);
		var skillColorsHoverData = JSON.stringify(skillColorsHover);

		var chart_doughnut_settings = {
			type: 'doughnut',
			tooltipFillColor: "rgba(51, 51, 51, 0.55)",
			data: {
				labels: jQuery.parseJSON(skillNamesData),
				datasets: [{
					data: jQuery.parseJSON(skillCountsData),
					backgroundColor: jQuery.parseJSON(skillColorsData),
					hoverBackgroundColor: jQuery.parseJSON(skillColorsHoverData),
					percentage: jQuery.parseJSON(percentageData)
				}]
			},
			options: {
				legend: false,
				responsive: false,
				tooltips: {
					titleFontSize: 11,
					bodyFontSize: 11,
					callbacks: {
						label: function(tooltipItem, data) {
							return [
								data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
							];
						}
					}
				}
			}
		}

		$('.canvasDoughnut_surveyResult_skills').each(function() {
			var chart_element = $(this);
			var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
		});
	} else {
		$('.canvasContainer_surveyResult_skills').empty();
		$('.canvasContainer_surveyResult_skills').append(loadNoDataMessage());
	}
} //getDataFromShowAllAdditionalSkills
	}
	/* End AdditionalSkills Doughnut */

} //init_surveyResultDougnut

function init_usageReportDougnut(startDate, endDate, site, functionalArea, manager, supervisor) {
	$('.canvasContainer_usageByuserGroup').append(
		' <canvas class="canvasDoughnut_usage_userGroup" height="200" width="auto" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_usageByGender').append(
		' <canvas class="canvasDoughnut_usage_gender" height="200" width="auto" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_usageBySite').append(
		' <canvas class="canvasDoughnut_usage_site" height="200" width="auto" style="margin: 15px 10px 10px 0"></canvas> '
	);
	$('.canvasContainer_usageByJobLevel').append(
		' <canvas class="canvasDoughnut_usage_jobLevel" height="200" width="auto" style="margin: 15px 10px 10px 0"></canvas> '
	);

	if (typeof (Chart) === 'undefined') { return; }

	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor
	}

	//----------------------- UsageByAgeGroup Doughnut -----------------------
	if ($('.canvasDoughnut_usage_userGroup').length) {

		$.ajax({
			type: "POST",
			url: "../reporting/usage/byagegroup",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(dateRange),
			success: function(data) {
				getDataFromUsageByAgeGroup(data);
			},
			error: function(err) {
				console.log(err.responseText);
			}
		});


		function getDataFromUsageByAgeGroup(data) {
			if (data.length != 0) {
				var userGroupNames = []
				var userGroupCounts = [];
				var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
				var userGroupColors = [];
				var userGroupColorsHover = [];

				for (var i = 0; i < data.length; i++) {
					if (data[i].counts != "0") {
						userGroupNames.push(data[i].label);
						userGroupCounts.push(data[i].counts);
					}
				}

				for (var i = 0; i < userGroupNames.length; i++) {
					userGroupColors.push(colors[i]);
					userGroupColorsHover.push(colorsHover[i]);
				}

				for (var i = 0; i < userGroupNames.length; i++) {
					$('.labelContainer_usageByuserGroup').append(
						' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + userGroupNames[i] + '</div></div> '
					);
				}

				var userGroupNamesData = JSON.stringify(userGroupNames);
				var userGroupCountsData = JSON.stringify(userGroupCounts);

				var countList = [];
				for (var i = 0; i < userGroupCounts.length; i++) {
					countList.push(parseInt(userGroupCounts[i]));
				}

				var total = 0;
				for (var i = 0; i < countList.length; i++) {
					total = total + countList[i];
				}

				var percentageList = [];
				for (var i = 0; i < userGroupCounts.length; i++) {
					percentageList.push(((countList[i] / total) * 100).toFixed(2));
				}

				var percentageData = JSON.stringify(percentageList);
				var userGroupColorsData = JSON.stringify(userGroupColors);
				var userGroupColorsHoverData = JSON.stringify(userGroupColorsHover);

				var chart_doughnut_settings = {
					type: 'doughnut',
					tooltipFillColor: "rgba(51, 51, 51, 0.55)",
					data: {
						labels: jQuery.parseJSON(userGroupNamesData),
						datasets: [{
							data: jQuery.parseJSON(userGroupCountsData),
							backgroundColor: jQuery.parseJSON(userGroupColorsData),
							hoverBackgroundColor: jQuery.parseJSON(userGroupColorsHoverData),
							percentage: jQuery.parseJSON(percentageData)
						}]
					},
					options: {
						legend: false,
						responsive: false,
						tooltips: {
							titleFontSize: 11,
							bodyFontSize: 11,
							callbacks: {
								label: function(tooltipItem, data) {
									return [
										data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
									];
								}
							}
						}
					}
				}

				$('.canvasDoughnut_usage_userGroup').each(function() {
					var chart_element = $(this);
					var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
				});
			} else {
				$('.canvasContainer_usageByuserGroup').empty();
				$('.canvasContainer_usageByuserGroup').append(loadNoDataMessage());
			}

		}
	}
	//----------------------- END OF UsageByAgeGroup Doughnut -----------------------

	//----------------------- UsageByGender Doughnut -----------------------
	if ($('.canvasDoughnut_usage_gender').length) {
		$.ajax({
			type: "POST",
			url: "../reporting/usage/bygender",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(dateRange),
			success: function(data) {
				getDataFromUsageByGender(data);
			},
			error: function(err) {
				console.log(err.responseText);
			}
		});

		function getDataFromUsageByGender(data) {
			if (data.length != 0) {
				var genderNames = []
				var genderCounts = [];
				var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
				var genderColors = [];
				var genderColorsHover = [];

				for (var i = 0; i < data.length; i++) {
					if (data[i].counts != "0") {
						genderNames.push(data[i].label);
						genderCounts.push(data[i].counts);
					}
				}

				for (var i = 0; i < genderNames.length; i++) {
					genderColors.push(colors[i]);
					genderColorsHover.push(colorsHover[i]);
				}

				for (var i = 0; i < genderNames.length; i++) {
					$('.labelContainer_usageByGender').append(
						' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + genderNames[i] + '</div></div> '
					);
				}

				var genderNamesData = JSON.stringify(genderNames);
				var genderCountsData = JSON.stringify(genderCounts);

				var countList = [];
				for (var i = 0; i < genderCounts.length; i++) {
					countList.push(parseInt(genderCounts[i]));
				}

				var total = 0;
				for (var i = 0; i < countList.length; i++) {
					total = total + countList[i];
				}

				var percentageList = [];
				for (var i = 0; i < genderCounts.length; i++) {
					percentageList.push(((countList[i] / total) * 100).toFixed(2));
				}

				var percentageData = JSON.stringify(percentageList);
				var genderColorsData = JSON.stringify(genderColors);
				var genderColorsHoverData = JSON.stringify(genderColorsHover);

				var chart_doughnut_settings = {
					type: 'doughnut',
					tooltipFillColor: "rgba(51, 51, 51, 0.55)",
					data: {
						labels: jQuery.parseJSON(genderNamesData),
						datasets: [{
							data: jQuery.parseJSON(genderCountsData),
							backgroundColor: jQuery.parseJSON(genderColorsData),
							hoverBackgroundColor: jQuery.parseJSON(genderColorsHoverData),
							percentage: jQuery.parseJSON(percentageData)
						}]
					},
					options: {
						legend: false,
						responsive: false,
						tooltips: {
							titleFontSize: 11,
							bodyFontSize: 11,
							callbacks: {
								label: function(tooltipItem, data) {
									return [
										data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
									];
								}
							}
						}
					}
				}

				$('.canvasDoughnut_usage_gender').each(function() {
					var chart_element = $(this);
					var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
				});
			} else {
				$('.canvasContainer_usageByGender').empty();
				$('.canvasContainer_usageByGender').append(loadNoDataMessage());
			}
		} //getDataFromUsageByGender
	}
	//----------------------- END OF UsageByGender Doughnut -----------------------

	//----------------------- UsageBySite Doughnut -----------------------
	if ($('.canvasDoughnut_usage_site').length) {

		$.ajax({
			type: "POST",
			url: "../reporting/usage/bysite",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(dateRange),
			success: function(data) {
				getDataFromUsageBySite(data);
			},
			error: function(err) {
				console.log(err.responseText);
			}
		});


		function getDataFromUsageBySite(data) {
			if (data.length != 0) {
				var siteNames = []
				var siteCounts = [];
				var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
				var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
				var siteColors = [];
				var siteColorsHover = [];

				for (var i = 0; i < data.length; i++) {
					if (data[i].counts != "0") {
						siteNames.push(data[i].label);
						siteCounts.push(data[i].counts);
					}
				}

				for (var i = 0; i < siteNames.length; i++) {
					siteColors.push(colors[i]);
					siteColorsHover.push(colorsHover[i]);
				}

				for (var i = 0; i < siteNames.length; i++) {
					$('.labelContainer_usageBySite').append(
						' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + siteNames[i] + '</div></div> '
					);
				}

				var siteNamesData = JSON.stringify(siteNames);
				var siteCountsData = JSON.stringify(siteCounts);

				var countList = [];
				for (var i = 0; i < siteCounts.length; i++) {
					countList.push(parseInt(siteCounts[i]));
				}

				var total = 0;
				for (var i = 0; i < countList.length; i++) {
					total = total + countList[i];
				}

				var percentageList = [];
				for (var i = 0; i < siteCounts.length; i++) {
					percentageList.push(((countList[i] / total) * 100).toFixed(2));
				}

				var percentageData = JSON.stringify(percentageList);
				var siteColorsData = JSON.stringify(siteColors);
				var siteColorsHoverData = JSON.stringify(siteColorsHover);

				var chart_doughnut_settings = {
					type: 'doughnut',
					tooltipFillColor: "rgba(51, 51, 51, 0.55)",
					data: {
						labels: jQuery.parseJSON(siteNamesData),
						datasets: [{
							data: jQuery.parseJSON(siteCountsData),
							backgroundColor: jQuery.parseJSON(siteColorsData),
							hoverBackgroundColor: jQuery.parseJSON(siteColorsHoverData),
							percentage: jQuery.parseJSON(percentageData)
						}]
					},
					options: {
						legend: false,
						responsive: false,
						tooltips: {
							titleFontSize: 11,
							bodyFontSize: 11,
							callbacks: {
								label: function(tooltipItem, data) {
									return [
										data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
									];
								}
							}
						}
					}
				}

				$('.canvasDoughnut_usage_site').each(function() {
					var chart_element = $(this);
					var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
				});
			} else {
				$('.canvasContainer_usageBySite').empty();
				$('.canvasContainer_usageBySite').append(loadNoDataMessage());
			}
		} //getDataFromUsageBySite
	}
	//----------------------- END OF UsageBySite Doughnut -----------------------



	//----------------------- UsageByJobLevel Doughnut  -----------------------
	if ($('.canvasDoughnut_usage_jobLevel').length) {
		$.ajax({
			type: "POST",
			url: "../reporting/usage/byjoblevel",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(dateRange),
			success: function(data) {
			getDataFromUsageByJobLevel(data);
				console.log(data)
		},
			error: function(err) {
				console.log(err.responseText);
			}
		});



	function getDataFromUsageByJobLevel(data) {
		if (data.length != 0) {
			var jobLevelNames = []
			var jobLevelCounts = [];
			var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
			var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
			var jobLevelColors = [];
			var jobLevelColorsHover = [];

			for (var i = 0; i < data.length; i++) {
				if (data[i].counts != "0") {
					jobLevelNames.push(data[i].label);
					jobLevelCounts.push(data[i].counts);
				}
			}

			for (var i = 0; i < jobLevelNames.length; i++) {
				jobLevelColors.push(colors[i]);
				jobLevelColorsHover.push(colorsHover[i]);
			}

			for (var i = 0; i < jobLevelNames.length; i++) {
				$('.labelContainer_usageByJobLevel').append(
					' <div class="reportItem_label_body"><div class="reportItem_label_colorValue"><i class="fa fa-square ' + colorNames[i] + '"></i></div><div class="reportItem_label_itemValue">' + jobLevelNames[i] + '</div></div> '
				);
			}

			var jobLevelNamesData = JSON.stringify(jobLevelNames);
			var jobLevelCountsData = JSON.stringify(jobLevelCounts);

			var countList = [];
			for (var i = 0; i < jobLevelCounts.length; i++) {
				countList.push(parseInt(jobLevelCounts[i]));
			}

			var total = 0;
			for (var i = 0; i < countList.length; i++) {
				total = total + countList[i];
			}

			var percentageList = [];
			for (var i = 0; i < jobLevelCounts.length; i++) {
				percentageList.push(((countList[i] / total) * 100).toFixed(2));
			}

			var percentageData = JSON.stringify(percentageList);
			var jobLevelColorsData = JSON.stringify(jobLevelColors);
			var jobLevelColorsHoverData = JSON.stringify(jobLevelColorsHover);

			var chart_doughnut_settings = {
				type: 'doughnut',
				tooltipFillColor: "rgba(51, 51, 51, 0.55)",
				data: {
					labels: jQuery.parseJSON(jobLevelNamesData),
					datasets: [{
						data: jQuery.parseJSON(jobLevelCountsData),
						backgroundColor: jQuery.parseJSON(jobLevelColorsData),
						hoverBackgroundColor: jQuery.parseJSON(jobLevelColorsHoverData),
						percentage: jQuery.parseJSON(percentageData)
					}]
				},
				options: {
					legend: false,
					responsive: false,
					events: ['click'],
					tooltips: {
						titleFontSize: 11,
						bodyFontSize: 11,
						callbacks: {
							label: function(tooltipItem, data) {
								return [
									data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
								];
							}
						}
					}
				}
			}

			$('.canvasDoughnut_usage_jobLevel').each(function() {
				var chart_element = $(this);
				var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
			});
		} else {
			$('.canvasContainer_usageByJobLevel').empty();
			$('.canvasContainer_usageByJobLevel').append(loadNoDataMessage());
		}
	} //getDataFromUsageByJobLevel
}
//----------------------- END OF UsageByJobLevel Doughnut -----------------------



}//init_usageReportDougnut


// Export CSV

function downloadExcel(csv, filename) {
	var csvFile;
	var downloadLink;

	csvFile = new Blob([csv], { type: "text/csv" });

	downloadLink = document.createElement("a");
	downloadLink.download = filename;
	downloadLink.href = window.URL.createObjectURL(csvFile);
	downloadLink.style.display = "none";

	document.body.appendChild(downloadLink);

	downloadLink.click();
}

// test export button
function exportCSV_usageBySite(startDate, endDate, site, functionalArea, manager, supervisor) {

	var dateRange = {
		"from_date": startDate,
		"to_date": endDate,
		"site": site,
		"functional_area": functionalArea,
		"manager": manager,
		"supervisor": supervisor
	}

	$.ajax({
		type: "POST",
		url: "../exporting/usage/byusertraffic",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(dateRange),
		success: function(data) {
			//getUsageTrafficData(data);
			console.log(data)
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

}

//initial download
function exportCSV_usageByJobLevel(filename) {
	var csv = [];
	var rows = document.querySelectorAll(".test1, .test2");

	for (var i = 1; i < rows.length; i++) {
		var row = [],
			cols = rows[i].querySelectorAll(".test4, .test3");
		for (var j = 0; j < cols.length; j++) row.push(cols[j].innerText);

		csv.push(row.join(","));
	}
	downloadExcel(csv.join("\n"), filename);
}

// End Export CSV


function usageFilter_site() {
	$.ajax({
		type: "GET",
		url: "../reporting/sites",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			getDataFromFilterService_findAllSites(data);
		},
		error: function(err) {
		console.log(err.responseText);
	}
	});
	
function getDataFromFilterService_findAllSites(data) {
	for (var i = 0; i < data.length; i++) {
		$('.dropdown_selection_site').append(
			' <option value="' + data[i].site + '" selected>' + data[i].site + '</option> '
		);
	}

	$('select.dropdown_selection_site').multipleSelect({
		filter: true,
		filterPlaceholder: "Type to search"
	});

	$('.dropdown_selection_site button.ms-choice').addClass('ms-choice_site');
	$('.dropdown_selection_site div.ms-drop').addClass('ms-drop_site');
	var isAllSelected = $(".dropdown_selection_site button.ms-choice_site span").text() == 'All selected';
	if (isAllSelected == true) {
		$('button.ms-choice_site span').text("Site: All selected");
	}

	$('select.dropdown_selection_site').change(function() {
		$('.dropdown_selection_site button.ms-choice').addClass('ms-choice_site');
		$('.dropdown_selection_site div.ms-drop').addClass('ms-drop_site');
		var isAllSelected = $(".dropdown_selection_site button.ms-choice_site span").text() == 'All selected';
		if(isAllSelected == true) {
		$('button.ms-choice_site span').text("Site: All selected");
	}
	$('.ms-drop_site.bottom').css("left", "");
	$('.ms-drop_site.bottom').css("right", "0px");

	var userInput_site = $('select.dropdown_selection_site').multipleSelect('getSelects', 'text');
	if (userInput_site != "") {
		$('#usageTraffic').empty();
		$('.canvasContainer_usageByuserGroup').empty();
		$('.labelContainer_usageByuserGroup').empty();
		$('.canvasContainer_usageByGender').empty();
		$('.labelContainer_usageByGender').empty();
		$('.canvasContainer_usageBySite').empty();
		$('.labelContainer_usageBySite').empty();
		$('.canvasContainer_usageByJobLevel').empty();
		$('.labelContainer_usageByJobLevel').empty();
		checkCurrentFilterValuesAndPassToController_usageReport();

		var siteList = userInput_site.toString();
		$('.siteList-usageReport').val(siteList);

		usageFilter_functionalArea(siteList);
		usageFilter_manager("all");
		usageFilter_supervisor("all");
	}
});
	} //getDataFromFilterService_findAllSites
} //usageFilter_site

function usageFilter_functionalArea(siteList) {
	$('.dropdown_container_functionalArea').empty();
	$('.dropdown_container_functionalArea').append(
		' <select class="dropdown_selection_functionalArea" multiple="multiple" placeholder="Functional Area"></select> '
	);

	var functionalArea = {
		"site": siteList, "functional_area": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/functionalareabysite",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getFunctionalAreaBysite(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getFunctionalAreaBysite(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_functionalArea').append(
				' <option value="' + data[i].functional_area + '" selected>' + data[i].functional_area + '</option> '
			);
		}

		$('select.dropdown_selection_functionalArea').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_functionalArea button.ms-choice').addClass('ms-choice_functionalArea');
		$('.dropdown_selection_functionalArea div.ms-drop').addClass('ms-drop_functionalArea');
		var isAllSelected = $(".dropdown_selection_functionalArea button.ms-choice_functionalArea span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_functionalArea span').text("Functional Area: All selected");
		}

		$('select.dropdown_selection_functionalArea').change(function() {
			$('.dropdown_selection_functionalArea button.ms-choice').addClass('ms-choice_functionalArea');
			$('.dropdown_selection_functionalArea div.ms-drop').addClass('ms-drop_functionalArea');
			var isAllSelected = $(".dropdown_selection_functionalArea button.ms-choice_functionalArea span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_functionalArea span').text("Functional Area: All selected");
			}
			$('.ms-drop_functionalArea.bottom').css("left", "");
			$('.ms-drop_functionalArea.bottom').css("right", "0px");

			var userInput_functionalArea = $('select.dropdown_selection_functionalArea').multipleSelect('getSelects', 'text');
			if (userInput_functionalArea != "") {
				$('#usageTraffic').empty();
				$('.canvasContainer_usageByuserGroup').empty();
				$('.labelContainer_usageByuserGroup').empty();
				$('.canvasContainer_usageByGender').empty();
				$('.labelContainer_usageByGender').empty();
				$('.canvasContainer_usageBySite').empty();
				$('.labelContainer_usageBySite').empty();
				$('.canvasContainer_usageByJobLevel').empty();
				$('.labelContainer_usageByJobLevel').empty();
				checkCurrentFilterValuesAndPassToController_usageReport();
				var functionalAreaList = userInput_functionalArea.toString();
				$('.functionalArea_usageReport').val(functionalAreaList);
				usageFilter_manager(functionalAreaList);

			}
		});
	} //getDataFromController_getFunctionalAreaBysite
} //usageFilter_functionalArea

function usageFilter_manager(functionalAreaList) {
	$('.dropdown_container_manager').empty();
	$('.dropdown_container_manager').append(
		' <select class="dropdown_selection_manager" multiple="multiple" placeholder="Manager"></select> '
	);

	var functionalArea = {
		"site": "", "functional_area": functionalAreaList
	}

	$.ajax({
		type: "POST",
		url: "../reporting/managerlistbyfunctionalarea",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getManagerListByFunctionalArea(data);
		},
			error: function(err) {
				console.log(err.responseText);
			}
	});

	function getDataFromController_getManagerListByFunctionalArea(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_manager').append(
				' <option value="' + data[i].mgr_id + '" selected>' + data[i].mgr_name + '</option> '
			);
		}

		$('select.dropdown_selection_manager').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_manager button.ms-choice').addClass('ms-choice_manager');
		$('.dropdown_selection_manager div.ms-drop').addClass('ms-drop_manager');
		var isAllSelected = $(".dropdown_selection_manager button.ms-choice_manager span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_manager span').text("Manager of Immediate Manager: All selected");
		}

		$('select.dropdown_selection_manager').change(function() {
			$('.dropdown_selection_manager button.ms-choice').addClass('ms-choice_manager');
			$('.dropdown_selection_manager div.ms-drop').addClass('ms-drop_manager');
			var isAllSelected = $(".dropdown_selection_manager button.ms-choice_manager span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_manager span').text("Manager of Immediate Manager: All selected");
			}
			$('.ms-drop_manager.bottom').css("left", "");
			$('.ms-drop_manager.bottom').css("right", "0px");

			var userInput_manager = $('select.dropdown_selection_manager').multipleSelect('getSelects');
			if (userInput_manager != "") {
				$('#usageTraffic').empty();
				$('.canvasContainer_usageByuserGroup').empty();
				$('.labelContainer_usageByuserGroup').empty();
				$('.canvasContainer_usageByGender').empty();
				$('.labelContainer_usageByGender').empty();
				$('.canvasContainer_usageBySite').empty();
				$('.labelContainer_usageBySite').empty();
				$('.canvasContainer_usageByJobLevel').empty();
				$('.labelContainer_usageByJobLevel').empty();
				checkCurrentFilterValuesAndPassToController_usageReport();
				var managerList = userInput_manager.toString();
				$('.manager_usageReport').val(managerList);
				usageFilter_supervisor(managerList);

			}
		});

	} //getDataFromController_getFunctionalAreaBysite
} //usageFilter_manager

function usageFilter_supervisor(managerList) {

	$('.dropdown_container_supervisor').empty();
	$('.dropdown_container_supervisor').append(
		' <select class="dropdown_selection_supervisor" multiple="multiple" placeholder="Supervisor"></select> '
	);

	var manager = {
		"mgr_id": managerList, "mgr_name": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/suplistbymanager",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(manager),
		success: function(data) {
			getDataFromController_getSupervisorListByManager(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getSupervisorListByManager(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_supervisor').append(
				' <option value="' + data[i].sup_id + '" selected>' + data[i].sup_name + '</option> '
			);
		}

		$('select.dropdown_selection_supervisor').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_supervisor button.ms-choice').addClass('ms-choice_supervisor');
		$('.dropdown_selection_supervisor div.ms-drop').addClass('ms-drop_supervisor');
		var isAllSelected = $(".dropdown_selection_supervisor button.ms-choice_supervisor span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_supervisor span').text("Immediate Manager: All selected");
		}

		$('select.dropdown_selection_supervisor').change(function() {
			$('.dropdown_selection_supervisor button.ms-choice').addClass('ms-choice_supervisor');
			$('.dropdown_selection_supervisor div.ms-drop').addClass('ms-drop_supervisor');
			var isAllSelected = $(".dropdown_selection_supervisor button.ms-choice_supervisor span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_supervisor span').text("Immediate Manager: All selected");
			}
			$('.ms-drop_supervisor.bottom').css("left", "");
			$('.ms-drop_supervisor.bottom').css("right", "0px");

			var userInput_supervisor = $('select.dropdown_selection_supervisor').multipleSelect('getSelects');
			if (userInput_supervisor != "") {
				$('#usageTraffic').empty();
				$('.canvasContainer_usageByuserGroup').empty();
				$('.labelContainer_usageByuserGroup').empty();
				$('.canvasContainer_usageByGender').empty();
				$('.labelContainer_usageByGender').empty();
				$('.canvasContainer_usageBySite').empty();
				$('.labelContainer_usageBySite').empty();
				$('.canvasContainer_usageByJobLevel').empty();
				$('.labelContainer_usageByJobLevel').empty();
				checkCurrentFilterValuesAndPassToController_usageReport();
				var supervisorList = userInput_supervisor.toString();
			}
		});
	} //getDataFromController_getSupervisorListByManager
} //usageFilter_supervisor

function init_dropDownSettings_functionalArea() {
	$('.dropdown_selection_funcArea button.ms-choice').addClass('ms-choice_funcArea');
	$('.dropdown_selection_funcArea div.ms-drop').addClass('ms-drop_funcArea');
	var isAllSelected = $(".dropdown_selection_funcArea button.ms-choice_funcArea span").text() == 'All selected';
	if (isAllSelected == true) {
		$('button.ms-choice_funcArea span').text("Functional Area: All selected");
	}

	$('.ms-drop_funcArea.bottom').css("left", "");
	$('.ms-drop_funcArea.bottom').css("right", "0px");

}

function init_dropDownSettings_managerId() {
	$('.dropdown_selection_managerId button.ms-choice').addClass('ms-choice_managerId');
	$('.dropdown_selection_managerId div.ms-drop').addClass('ms-drop_managerId');
	var isAllSelected = $(".dropdown_selection_managerId button.ms-choice_managerId span").text() == 'All selected';
	if (isAllSelected == true) {
		$('button.ms-choice_managerId span').text("Manager: All selected");
	}
	$('.ms-drop_managerId.bottom').css("left", "");
	$('.ms-drop_managerId.bottom').css("right", "0px");
}

function init_dropDown_functionalArea() {
	var $select = $('select.dropdown_selection_funcArea');

	$select.multipleSelect({
		filter: true,
		filterPlaceholder: "Type to search"
	});

	$('select.dropdown_selection_funcArea').change(function() {

		$('.dropdown_selection_funcArea button.ms-choice').addClass('ms-choice_funcArea');
		$('.dropdown_selection_funcArea div.ms-drop').addClass('ms-drop_funcArea');
		var isAllSelected = $(".dropdown_selection_funcArea button.ms-choice_funcArea span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_funcArea span').text("Functional Area: All selected");
		}

		var userInput_functionalArea = $select.multipleSelect('getSelects', 'text');

		// Run query to db unless user unselected all
		if (userInput_functionalArea != "") {
			var functionalAreas = userInput_functionalArea.toString();
			$('.functionalArea').val(functionalAreas);

			var start = $('.fromDate').val();
			var end = $('.toDate').val();
			var func = $('.functionalArea').val();

			var userInput_managerId = $('select.dropdown_selection_managerId').multipleSelect('getSelects');
			var mgr = userInput_managerId.toString();

			console.log("From funcArea_dropdown: ");
			console.log("Current startDate: " + start);
			console.log("Current endDate: " + end);
			console.log("Current functionalArea: " + func);
			console.log("Current managerId: " + mgr);


			$('.functionalArea').val(userInput_functionalArea);

			$('#usageTraffic').empty();

			$('.canvasContainer_usageByuserGroup').empty();
			$('.labelContainer_usageByuserGroup').empty();

			$('.canvasContainer_usageByGender').empty();
			$('.labelContainer_usageByGender').empty();

			$('.canvasContainer_usageBySite').empty();
			$('.labelContainer_usageBySite').empty();

			$('.canvasContainer_usageByJobLevel').empty();
			$('.labelContainer_usageByJobLevel').empty();

			//init_usageTraffic(start, end, func, "all");
			init_usageReportDougnut(start, end, func, "all");
		}
	});
}

function init_dropDown_managerId() {
	var $select = $('select.dropdown_selection_managerId');

	$select.multipleSelect({
		filter: true,
		filterPlaceholder: "Type to search"
	});

	$('select.dropdown_selection_managerId').change(function() {

		$('.dropdown_selection_managerId button.ms-choice').addClass('ms-choice_managerId');
		$('.dropdown_selection_managerId div.ms-drop').addClass('ms-drop_managerId');
		var isAllSelected = $(".dropdown_selection_managerId button.ms-choice_managerId span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_managerId span').text("Manager: All selected");
		}

		var userInput_managerId = $select.multipleSelect('getSelects');

		// Run query to db unless user unselected all
		if (userInput_managerId != "") {
			var mgrId = userInput_managerId.toString();
			$('.managerId').val(mgrId);

			var start = $('.fromDate').val();
			var end = $('.toDate').val();
			var func = $('.functionalArea').val();
			var mgr = $('.managerId').val();
			console.log("From mgrid_dropdown: ");
			console.log("Current startDate: " + start);
			console.log("Current endDate: " + end);
			console.log("Current functionalArea: " + func);
			console.log("Current managerId: " + mgr);


			$('.managerId').val(userInput_managerId);

			$('#usageTraffic').empty();

			$('.canvasContainer_usageByuserGroup').empty();
			$('.labelContainer_usageByuserGroup').empty();

			$('.canvasContainer_usageByGender').empty();
			$('.labelContainer_usageByGender').empty();

			$('.canvasContainer_usageBySite').empty();
			$('.labelContainer_usageBySite').empty();

			$('.canvasContainer_usageByJobLevel').empty();
			$('.labelContainer_usageByJobLevel').empty();

			//init_usageTraffic(start, end, func, "all");
			init_usageReportDougnut(start, end, func, mgr);
		}
	});
}

function storeToTempRepo(start, end, functional_area, manager_id) {
	$('.fromDate').val(start);
	$('.toDate').val(end);
	$('.functionalArea').val(functional_area);
	$('.managerId').val(manager_id);
}

function dropDownDefaultAllSelectedPlaceHolder() {
	var allSelected = $(".dropdown_selection option:not(:selected)").length > 0
	if (allSelected == false) {
		$('button.ms-choice span').text("Functional Area: All selected");
	}
}

function checkCurrentFilterValuesAndPassToController_usageReport() {

	var start = $('.fromDate_usageReport').val();
	var end = $('.toDate_usageReport').val();
	var site = "";
	var functionalArea = "";
	var manager = "";
	var supervisor = "";

	var isAllSelected_siteList = $(".dropdown_selection_site button.ms-choice_site span").text() == 'Site: All selected';
	if (isAllSelected_siteList == true) {
		site = "all";
	} else {
		site = $('select.dropdown_selection_site').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_funcArea = $(".dropdown_selection_functionalArea button.ms-choice_functionalArea span").text() == 'Functional Area: All selected';
	if (isAllSelected_funcArea == true) {
		functionalArea = "all";
	} else {
		functionalArea = $('select.dropdown_selection_functionalArea').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_manager = $(".dropdown_selection_manager button.ms-choice_manager span").text() == 'Manager of Immediate Manager: All selected';
	if (isAllSelected_manager == true) {
		manager = "all";
	} else {
		manager = $('select.dropdown_selection_manager').multipleSelect('getSelects').toString();
	}

	var isAllSelected_supervisor = $(".dropdown_selection_supervisor button.ms-choice_supervisor span").text() == 'Immediate Manager: All selected';
	if (isAllSelected_supervisor == true) {
		supervisor = "all";
	} else {
		supervisor = $('select.dropdown_selection_supervisor').multipleSelect('getSelects').toString();
	}

	console.log('checkCurrentFilterValuesAndPassToController_usageReport:');
	console.log(start);
	console.log(end);
	console.log(site);
	console.log(functionalArea);
	console.log(manager);
	console.log(supervisor);

	init_usageReportDougnut(start, end, site, functionalArea, manager, supervisor);
	init_usageTraffic(start, end, site, functionalArea, manager, supervisor);
} //checkCurrentFilterValuesAndPassToController_usageReport

function checkCurrentFilterValuesAndPassToController_surveyResult() {

	var start = $('.fromDate_surveyResult').val();
	var end = $('.toDate_surveyResult').val();
	var site = "";
	var functionalArea = "";
	var manager = "";
	var supervisor = "";

	var isAllSelected_siteList = $(".dropdown_selection_site-surveyResult button.ms-choice_site-surveyResult span").text() == 'Site: All selected';
	if (isAllSelected_siteList == true) {
		site = "all";
	} else {
		site = $('select.dropdown_selection_site-surveyResult').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_funcArea = $(".dropdown_selection_functionalArea-surveyResult button.ms-choice_functionalArea-surveyResult span").text() == 'Functional Area: All selected';
	if (isAllSelected_funcArea == true) {
		functionalArea = "all";
	} else {
		functionalArea = $('select.dropdown_selection_functionalArea-surveyResult').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_manager = $(".dropdown_selection_manager-surveyResult button.ms-choice_manager-surveyResult span").text() == 'Manager of Immediate Manager: All selected';
	if (isAllSelected_manager == true) {
		manager = "all";
	} else {
		manager = $('select.dropdown_selection_manager-surveyResult').multipleSelect('getSelects').toString();
	}

	var isAllSelected_supervisor = $(".dropdown_selection_supervisor-surveyResult button.ms-choice_supervisor-surveyResult span").text() == 'Immediate Manager: All selected';
	if (isAllSelected_supervisor == true) {
		supervisor = "all";
	} else {
		supervisor = $('select.dropdown_selection_supervisor-surveyResult').multipleSelect('getSelects').toString();
	}

	console.log('checkCurrentFilterValuesAndPassToController_surveyResult:');
	console.log(start);
	console.log(end);
	console.log(site);
	console.log(functionalArea);
	console.log(manager);
	console.log(supervisor);

	init_customBarGraph(start, end, site, functionalArea, manager, supervisor);
	init_surveyResultDougnut(start, end, site, functionalArea, manager, supervisor);
	load_TableSurveyPrefWorkTids(start, end, site, functionalArea, manager, supervisor);
	load_TableSurveyPrefWorkSupport(start, end, site, functionalArea, manager, supervisor);
	updateCCOHireCount(start, end);
	updateTidsSuppHireCount(start, end);
} //checkCurrentFilterValuesAndPassToController_usageReport

function checkCurrentFilterValuesAndPassToController_clicks() {
	var start = $('.fromDate_clicks').val();
	var end = $('.toDate_clicks').val();
	var site = "";
	var functionalArea = "";
	var manager = "";
	var supervisor = "";

	var isAllSelected_siteList = $(".dropdown_selection_site-clicks button.ms-choice_site-clicks span").text() == 'Site: All selected';
	if (isAllSelected_siteList == true) {
		site = "all";
	} else {
		site = $('select.dropdown_selection_site-clicks').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_funcArea = $(".dropdown_selection_functionalArea-clicks button.ms-choice_functionalArea-clicks span").text() == 'Functional Area: All selected';
	if (isAllSelected_funcArea == true) {
		functionalArea = "all";
	} else {
		functionalArea = $('select.dropdown_selection_functionalArea-clicks').multipleSelect('getSelects', 'text').toString();
	}

	var isAllSelected_manager = $(".dropdown_selection_manager-clicks button.ms-choice_manager-clicks span").text() == 'Manager of Immediate Manager: All selected';
	if (isAllSelected_manager == true) {
		manager = "all";
	} else {
		manager = $('select.dropdown_selection_manager-clicks').multipleSelect('getSelects').toString();
	}

	var isAllSelected_supervisor = $(".dropdown_selection_supervisor-clicks button.ms-choice_supervisor-clicks span").text() == 'Immediate Manager: All selected';
	if (isAllSelected_supervisor == true) {
		supervisor = "all";
	} else {
		supervisor = $('select.dropdown_selection_supervisor-clicks').multipleSelect('getSelects').toString();
	}

	console.log('checkCurrentFilterValuesAndPassToController_clicks:');
	console.log(start);
	console.log(end);
	console.log(site);
	console.log(functionalArea);
	console.log(manager);
	console.log(supervisor);

	load_TableClicksPreferredSites(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksPreferredPrograms(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksInDemandBusinessTypes(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksInDemandWorkTypeCCO(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksPrefSuppJobs(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksPrefSuppDept(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksInDemandJobProfile(start, end, site, functionalArea, manager, supervisor);
	load_TableClicksSupportAndTids(start, end, site, functionalArea, manager, supervisor);
} //checkCurrentFilterValuesAndPassToController_clicks            

function surveyResultFilter_site() {

	$.ajax({
		type: "GET",
		url: "../reporting/sites",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			getDataFromFilterService_findAllSites(data);
		},
			error: function(err) {
				console.log(err.responseText);
			}
	});

	function getDataFromFilterService_findAllSites(data) {

		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_site-surveyResult').append(
				' <option value="' + data[i].site + '" selected>' + data[i].site + '</option> '
			);
		}

		$('select.dropdown_selection_site-surveyResult').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_site-surveyResult button.ms-choice').addClass('ms-choice_site-surveyResult');
		$('.dropdown_selection_site-surveyResult div.ms-drop').addClass('ms-drop_site-surveyResult');
		var isAllSelected = $(".dropdown_selection_site-surveyResult button.ms-choice_site-surveyResult span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_site-surveyResult span').text("Site: All selected");
		}

		$('select.dropdown_selection_site-surveyResult').change(function() {
			$('.dropdown_selection_site-surveyResult button.ms-choice').addClass('ms-choice_site-surveyResult');
			$('.dropdown_selection_site-surveyResult div.ms-drop').addClass('ms-drop_site-surveyResult');
			var isAllSelected = $(".dropdown_selection_site-surveyResult button.ms-choice_site-surveyResult span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_site-surveyResult span').text("Site: All selected");
			}
			$('.ms-drop_site-surveyResult.bottom').css("left", "");
			$('.ms-drop_site-surveyResult.bottom').css("right", "0px");

			var userInput_site = $('select.dropdown_selection_site-surveyResult').multipleSelect('getSelects', 'text');
			if (userInput_site != "") {
				var siteList = userInput_site.toString();
				$('.siteList_surveyResult').val(siteList);
				clearExistingData_surveyResult();
				checkCurrentFilterValuesAndPassToController_surveyResult();
				surveyResultFilter_functionalArea(siteList);
				surveyResultFilter_manager("all");
				surveyResultFilter_supervisor("all");
			}
		});
	} //getDataFromFilterService_findAllSites
} //surveyResultFilter_site

function surveyResultFilter_functionalArea(siteList) {
	$('.dropdown_container_functionalArea-surveyResult').empty();
	$('.dropdown_container_functionalArea-surveyResult').append(
		' <select class="dropdown_selection_functionalArea-surveyResult" multiple="multiple" placeholder="Functional Area"></select> '
	);

	var functionalArea = {
		"site": siteList, "functional_area": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/functionalareabysite",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getFunctionalAreaBysite(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getFunctionalAreaBysite(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_functionalArea-surveyResult').append(
				' <option value="' + data[i].functional_area + '" selected>' + data[i].functional_area + '</option> '
			);
		}

		$('select.dropdown_selection_functionalArea-surveyResult').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_functionalArea-surveyResult button.ms-choice').addClass('ms-choice_functionalArea-surveyResult');
		$('.dropdown_selection_functionalArea-surveyResult div.ms-drop').addClass('ms-drop_functionalArea-surveyResult');
		var isAllSelected = $(".dropdown_selection_functionalArea-surveyResult button.ms-choice_functionalArea-surveyResult span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_functionalArea-surveyResult span').text("Functional Area: All selected");
		}

		$('select.dropdown_selection_functionalArea-surveyResult').change(function() {
			$('.dropdown_selection_functionalArea-surveyResult button.ms-choice').addClass('ms-choice_functionalArea-surveyResult');
			$('.dropdown_selection_functionalArea-surveyResult div.ms-drop').addClass('ms-drop_functionalArea-surveyResult');
			var isAllSelected = $(".dropdown_selection_functionalArea-surveyResult button.ms-choice_functionalArea-surveyResult span").text() == 'All selected';
			if(isAllSelected == true) {
			$('button.ms-choice_functionalArea-surveyResult span').text("Functional Area: All selected");
		}
		$('.ms-drop_functionalArea-surveyResult.bottom').css("left", "");
		$('.ms-drop_functionalArea-surveyResult.bottom').css("right", "0px");

		var userInput_functionalArea = $('select.dropdown_selection_functionalArea-surveyResult').multipleSelect('getSelects', 'text');
		if (userInput_functionalArea != "") {
			var functionalAreaList = userInput_functionalArea.toString();
			$('.functionalArea_surveyResult').val(functionalAreaList);
			clearExistingData_surveyResult();
			checkCurrentFilterValuesAndPassToController_surveyResult();
			surveyResultFilter_manager(functionalAreaList);
		}
	});
} //getDataFromController_getFunctionalAreaBysite
} //surveyResultFilter_functionalArea

function surveyResultFilter_manager(functionalAreaList) {

	$('.dropdown_container_manager-surveyResult').empty();
	$('.dropdown_container_manager-surveyResult').append(
		' <select class="dropdown_selection_manager-surveyResult" multiple="multiple" placeholder="Manager"></select> '
	);

	var functionalArea = {
		"site": "", "functional_area": functionalAreaList
	}

	$.ajax({
		type: "POST",
		url: "../reporting/managerlistbyfunctionalarea",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getManagerListByFunctionalArea(data);
		},
		error: function(err) {
		console.log(err.responseText);
	}
	});

function getDataFromController_getManagerListByFunctionalArea(data) {
	for (var i = 0; i < data.length; i++) {
		$('.dropdown_selection_manager-surveyResult').append(
			' <option value="' + data[i].mgr_id + '" selected>' + data[i].mgr_name + '</option> '
		);
	}

	$('select.dropdown_selection_manager-surveyResult').multipleSelect({
		filter: true,
		filterPlaceholder: "Type to search"
	});

	$('.dropdown_selection_manager-surveyResult button.ms-choice').addClass('ms-choice_manager-surveyResult');
	$('.dropdown_selection_manager-surveyResult div.ms-drop').addClass('ms-drop_manager-surveyResult');
	var isAllSelected = $(".dropdown_selection_manager-surveyResult button.ms-choice_manager-surveyResult span").text() == 'All selected';
	if (isAllSelected == true) {
		$('button.ms-choice_manager-surveyResult span').text("Manager of Immediate Manager: All selected");
	}

	$('select.dropdown_selection_manager-surveyResult').change(function() {
		$('.dropdown_selection_manager-surveyResult button.ms-choice').addClass('ms-choice_manager-surveyResult');
		$('.dropdown_selection_manager-surveyResult div.ms-drop').addClass('ms-drop_manager-surveyResult');
		var isAllSelected = $(".dropdown_selection_manager-surveyResult button.ms-choice_manager-surveyResult span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_manager-surveyResult span').text("Manager of Immediate Manager: All selected");
		}
		$('.ms-drop_manager-surveyResult.bottom').css("left", "");
		$('.ms-drop_manager-surveyResult.bottom').css("right", "0px");

		var userInput_manager = $('select.dropdown_selection_manager-surveyResult').multipleSelect('getSelects');
		if (userInput_manager != "") {
			var managerList = userInput_manager.toString();
			$('.manager_surveyResult').val(managerList);
			clearExistingData_surveyResult();
			checkCurrentFilterValuesAndPassToController_surveyResult();
			surveyResultFilter_supervisor(managerList);
		}
	});

} //getDataFromController_getFunctionalAreaBysite
} //surveyResultFilter_manager

function surveyResultFilter_supervisor(managerList) {

	$('.dropdown_container_supervisor-surveyResult').empty();
	$('.dropdown_container_supervisor-surveyResult').append(
		' <select class="dropdown_selection_supervisor-surveyResult" multiple="multiple" placeholder="Supervisor"></select> '
	);

	var manager = {
		"mgr_id": managerList, "mgr_name": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/suplistbymanager",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(manager),
		success: function(data) {
			getDataFromController_getSupervisorListByManager(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getSupervisorListByManager(data) {

		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_supervisor-surveyResult').append(
				' <option value="' + data[i].sup_id + '" selected>' + data[i].sup_name + '</option> '
			);
		}

		$('select.dropdown_selection_supervisor-surveyResult').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_supervisor-surveyResult button.ms-choice').addClass('ms-choice_supervisor-surveyResult');
		$('.dropdown_selection_supervisor-surveyResult div.ms-drop').addClass('ms-drop_supervisor-surveyResult');
		var isAllSelected = $(".dropdown_selection_supervisor-surveyResult button.ms-choice_supervisor-surveyResult span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_supervisor-surveyResult span').text("Immediate Manager: All selected");
		}

		$('select.dropdown_selection_supervisor-surveyResult').change(function() {
			$('.dropdown_selection_supervisor-surveyResult button.ms-choice').addClass('ms-choice_supervisor-surveyResult');
			$('.dropdown_selection_supervisor-surveyResult div.ms-drop').addClass('ms-drop_supervisor-surveyResult');
			var isAllSelected = $(".dropdown_selection_supervisor-surveyResult button.ms-choice_supervisor-surveyResult span").text() == 'All selected';
			if(isAllSelected == true) {
			$('button.ms-choice_supervisor-surveyResult span').text("Immediate Manager: All selected");
		}
		$('.ms-drop_supervisor-surveyResult.bottom').css("left", "");
		$('.ms-drop_supervisor-surveyResult.bottom').css("right", "0px");

		var userInput_supervisor = $('select.dropdown_selection_supervisor-surveyResult').multipleSelect('getSelects');
		if (userInput_supervisor != "") {
			clearExistingData_surveyResult();
			checkCurrentFilterValuesAndPassToController_surveyResult();
			var supervisorList = userInput_supervisor.toString();
			$('.supervisor_surveyResult').val(supervisorList);
		}
	});

} //getDataFromController_getSupervisorListByManager
} //surveyResultFilter_supervisor

function clicksFilter_site() {

	$.ajax({
		type: "GET",
		url: "../reporting/sites",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			getDataFromFilterService_findAllSites(data);
		},
			error: function(err) {
				console.log(err.responseText);
			}
	});

	function getDataFromFilterService_findAllSites(data) {

		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_site-clicks').append(
				' <option value="' + data[i].site + '" selected>' + data[i].site + '</option> '
			);
		}

		$('select.dropdown_selection_site-clicks').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_site-clicks button.ms-choice').addClass('ms-choice_site-clicks');
		$('.dropdown_selection_site-clicks div.ms-drop').addClass('ms-drop_site-clicks');
		var isAllSelected = $(".dropdown_selection_site-clicks button.ms-choice_site-clicks span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_site-clicks span').text("Site: All selected");
		}

		$('select.dropdown_selection_site-clicks').change(function() {
			$('.dropdown_selection_site-clicks button.ms-choice').addClass('ms-choice_site-clicks');
			$('.dropdown_selection_site-clicks div.ms-drop').addClass('ms-drop_site-clicks');
			var isAllSelected = $(".dropdown_selection_site-clicks button.ms-choice_site-clicks span").text() == 'All selected';
			if(isAllSelected == true) {
			$('button.ms-choice_site-clicks span').text("Site: All selected");
		}
		$('.ms-drop_site-clicks.bottom').css("left", "");
		$('.ms-drop_site-clicks.bottom').css("right", "0px");

		var userInput_site = $('select.dropdown_selection_site-clicks').multipleSelect('getSelects', 'text');
		if (userInput_site != "") {
			var siteList = userInput_site.toString();
			$('.siteList_clicks').val(siteList);
			clearExistingData_clicks();
			checkCurrentFilterValuesAndPassToController_clicks();
			clicksFilter_functionalArea(siteList);
			clicksFilter_manager("all");
			clicksFilter_supervisor("all");
		}
	});
} //getDataFromFilterService_findAllSites
} //clicksFilter_site

function clicksFilter_functionalArea(siteList) {

	$('.dropdown_container_functionalArea-clicks').empty();
	$('.dropdown_container_functionalArea-clicks').append(
		' <select class="dropdown_selection_functionalArea-clicks" multiple="multiple" placeholder="Functional Area"></select> '
	);

	var functionalArea = {
		"site": siteList, "functional_area": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/functionalareabysite",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getFunctionalAreaBysite(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getFunctionalAreaBysite(data) {

		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_functionalArea-clicks').append(
				' <option value="' + data[i].functional_area + '" selected>' + data[i].functional_area + '</option> '
			);
		}

		$('select.dropdown_selection_functionalArea-clicks').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_functionalArea-clicks button.ms-choice').addClass('ms-choice_functionalArea-clicks');
		$('.dropdown_selection_functionalArea-clicks div.ms-drop').addClass('ms-drop_functionalArea-clicks');
		var isAllSelected = $(".dropdown_selection_functionalArea-clicks button.ms-choice_functionalArea-clicks span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_functionalArea-clicks span').text("Functional Area: All selected");
		}

		$('select.dropdown_selection_functionalArea-clicks').change(function() {
			$('.dropdown_selection_functionalArea-clicks button.ms-choice').addClass('ms-choice_functionalArea-clicks');
			$('.dropdown_selection_functionalArea-clicks div.ms-drop').addClass('ms-drop_functionalArea-clicks');
			var isAllSelected = $(".dropdown_selection_functionalArea-clicks button.ms-choice_functionalArea-clicks span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_functionalArea-clicks span').text("Functional Area: All selected");
			}
			$('.ms-drop_functionalArea-clicks.bottom').css("left", "");
			$('.ms-drop_functionalArea-clicks.bottom').css("right", "0px");

			var userInput_functionalArea = $('select.dropdown_selection_functionalArea-clicks').multipleSelect('getSelects', 'text');
			if (userInput_functionalArea != "") {
				var functionalAreaList = userInput_functionalArea.toString();
				$('.functionalArea_clicks').val(functionalAreaList);
				clearExistingData_clicks();
				checkCurrentFilterValuesAndPassToController_clicks();
				clicksFilter_manager(functionalAreaList);
			}
		});
	} //getDataFromController_getFunctionalAreaBysite
} //clicksFilter_functionalArea

function clicksFilter_manager(functionalAreaList) {
	$('.dropdown_container_manager-clicks').empty();
	$('.dropdown_container_manager-clicks').append(
		' <select class="dropdown_selection_manager-clicks" multiple="multiple" placeholder="Manager"></select> '
	);

	var functionalArea = {
		"site": "", "functional_area": functionalAreaList
	}

	$.ajax({
		type: "POST",
		url: "../reporting/managerlistbyfunctionalarea",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(functionalArea),
		success: function(data) {
			getDataFromController_getManagerListByFunctionalArea(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getManagerListByFunctionalArea(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_manager-clicks').append(
				' <option value="' + data[i].mgr_id + '" selected>' + data[i].mgr_name + '</option> '
			);
		}

		$('select.dropdown_selection_manager-clicks').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_manager-clicks button.ms-choice').addClass('ms-choice_manager-clicks');
		$('.dropdown_selection_manager-clicks div.ms-drop').addClass('ms-drop_manager-clicks');
		var isAllSelected = $(".dropdown_selection_manager-clicks button.ms-choice_manager-clicks span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_manager-clicks span').text("Manager of Immediate Manager: All selected");
		}

		$('select.dropdown_selection_manager-clicks').change(function() {
			$('.dropdown_selection_manager-clicks button.ms-choice').addClass('ms-choice_manager-clicks');
			$('.dropdown_selection_manager-clicks div.ms-drop').addClass('ms-drop_manager-clicks');
			var isAllSelected = $(".dropdown_selection_manager-clicks button.ms-choice_manager-clicks span").text() == 'All selected';
			if(isAllSelected == true) {
			$('button.ms-choice_manager-clicks span').text("Manager of Immediate Manager: All selected");
		}
		$('.ms-drop_manager-clicks.bottom').css("left", "");
		$('.ms-drop_manager-clicks.bottom').css("right", "0px");

		var userInput_manager = $('select.dropdown_selection_manager-clicks').multipleSelect('getSelects');
		if (userInput_manager != "") {
			var managerList = userInput_manager.toString();
			$('.manager_clicks').val(managerList);
			clearExistingData_clicks();
			checkCurrentFilterValuesAndPassToController_clicks();
			clicksFilter_supervisor(managerList);

		}
	});

} //getDataFromController_getFunctionalAreaBysite
} //clicksFilter_manager

function clicksFilter_supervisor(managerList) {

	$('.dropdown_container_supervisor-clicks').empty();
	$('.dropdown_container_supervisor-clicks').append(
		' <select class="dropdown_selection_supervisor-clicks" multiple="multiple" placeholder="Supervisor"></select> '
	);

	var manager = {
		"mgr_id": managerList, "mgr_name": ""
	}

	$.ajax({
		type: "POST",
		url: "../reporting/suplistbymanager",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(manager),
		success: function(data) {
			getDataFromController_getSupervisorListByManager(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromController_getSupervisorListByManager(data) {
		for (var i = 0; i < data.length; i++) {
			$('.dropdown_selection_supervisor-clicks').append(
				' <option value="' + data[i].sup_id + '" selected>' + data[i].sup_name + '</option> '
			);
		}

		$('select.dropdown_selection_supervisor-clicks').multipleSelect({
			filter: true,
			filterPlaceholder: "Type to search"
		});

		$('.dropdown_selection_supervisor-clicks button.ms-choice').addClass('ms-choice_supervisor-clicks');
		$('.dropdown_selection_supervisor-clicks div.ms-drop').addClass('ms-drop_supervisor-clicks');
		var isAllSelected = $(".dropdown_selection_supervisor-clicks button.ms-choice_supervisor-clicks span").text() == 'All selected';
		if (isAllSelected == true) {
			$('button.ms-choice_supervisor-clicks span').text("Immediate Manager: All selected");
		}

		$('select.dropdown_selection_supervisor-clicks').change(function() {
			$('.dropdown_selection_supervisor-clicks button.ms-choice').addClass('ms-choice_supervisor-clicks');
			$('.dropdown_selection_supervisor-clicks div.ms-drop').addClass('ms-drop_supervisor-clicks');
			var isAllSelected = $(".dropdown_selection_supervisor-clicks button.ms-choice_supervisor-clicks span").text() == 'All selected';
			if (isAllSelected == true) {
				$('button.ms-choice_supervisor-clicks span').text("Immediate Manager: All selected");
			}
			$('.ms-drop_supervisor-clicks.bottom').css("left", "");
			$('.ms-drop_supervisor-clicks.bottom').css("right", "0px");

			var userInput_supervisor = $('select.dropdown_selection_supervisor-clicks').multipleSelect('getSelects');
			if (userInput_supervisor != "") {
				clearExistingData_clicks();
				checkCurrentFilterValuesAndPassToController_clicks();
				var supervisorList = userInput_supervisor.toString();
				$('.supervisor_clicks').val(supervisorList);
			}
		});

	} //getDataFromController_getSupervisorListByManager
} //clicksFilter_supervisor

function loadNoDataMessage() {
	var html = '<div style="text-align: end; margin-top: 70px;""><small style="color:gray; margin-right: -20px">No data</small></div>'
	return html;
}

function loadNoDataMessage_table() {
	var html = '<div style="margin-left: 115px; margin-top: 46px;""><small style="color:gray; margin-right: -20px">No data</small></div>'
	return html;
}

function loadNoDataMessage_table_cclicksTab() {
	var html = '<div style="margin: 46px 0 60px -15px"><small style="color:gray; margin-right: -20px">No data</small></div>'
	return html;
}

function clearExistingData_surveyResult() {

	$('.prefSiteWrapper').empty();
	$('.canvasContainer_surveyResult_cco').empty();
	$('.labelContainer_surveyResult_cco').empty();
	$('.canvasContainer_surveyResult_bpoExp').empty();
	$('.labelContainer_surveyResult_bpoExp').empty();
	$('.canvasContainer_surveyResult_sched').empty();
	$('.labelContainer_surveyResult_sched').empty();
	$('.canvasContainer_surveyResult_study').empty();
	$('.labelContainer_surveyResult_study').empty();
	$('.canvasContainer_surveyResult_skills').empty();
	$('.labelContainer_surveyResult_skills').empty();

	// PREFERRED WORK - TIDS
	$('.survey_work_tids_div').empty();
	$('.survey_work_tids_div').append(
		'<table class="table survey_work_tids_table"><thead><tr><th>ROLE</th><th>COUNT</th></tr></thead><tbody class="survey_work_tids_tbody"></tbody></table>'
	);

	// PREFERRED WORK - SUPPORT
	$('.survey_work_support_div').empty();
	$('.survey_work_support_div').append(
		'<table class="table survey_work_support_table"><thead><tr><th>ROLE</th><th>COUNT</th></tr></thead><tbody class="survey_work_support_tbody"></tbody></table>'
	);

} //clearExistingData_surveyResult

function clearExistingData_clicks() {

	$('.clicks_pref_site_div').empty();
	$('.clicks_pref_site_div').append(
		'<table class="table clicks_pref_site_table"><thead><tr><th>SITE</th><th>COUNT</th></tr></thead><tbody class="clicks_pref_site_tbody"></tbody></table>'
	);

	$('.clicks_pref_programs_div').empty();
	$('.clicks_pref_programs_div').append(
		'<table class="table clicks_pref_programs_table"><thead><tr><th>ACCOUNT NAME</th><th>COUNT</th></tr></thead><tbody class="clicks_pref_programs_tbody"></tbody></table>'
	);

	$('.clicks_bus_types_div').empty();
	$('.clicks_bus_types_div').append(
		'<table class="table clicks_bus_types_table"><thead><tr><th>BUSINESS TYPE</th><th>COUNT</th></tr></thead><tbody class="clicks_bus_types_tbody"></tbody></table>'
	);

	$('.clicks_work_type_cco_div').empty();
	$('.clicks_work_type_cco_div').append(
		'<table class="table clicks_work_type_cco_table"><thead><tr><th>WORK TYPE</th><th>COUNT</th></tr></thead><tbody class="clicks_work_type_cco_tbody"></tbody></table>'
	);

	$('.clicks_pref_supp_jobs_div').empty();
	$('.clicks_pref_supp_jobs_div').append(
		'<table class="table clicks_pref_supp_jobs_table"><thead><tr><th>WORK TYPE</th><th>COUNT</th></tr></thead><tbody class="clicks_pref_supp_jobs_tbody"></tbody></table>'
	);

	$('.clicks_pref_supp_dept_div').empty();
	$('.clicks_pref_supp_dept_div').append(
		'<table class="table clicks_pref_supp_dept_table"><thead><tr><th>WORK TYPE</th><th>COUNT</th></tr></thead><tbody class="clicks_pref_supp_dept_tbody"></tbody></table>'
	);

	$('.clicks_job_profile_div').empty();
	$('.clicks_job_profile_div').append(
		'<table class="table clicks_job_profile_table"><thead><tr><th>JOB PROFILE</th><th>COUNT</th></tr></thead><tbody class="clicks_job_profile_tbody"></tbody></table>'
	);

	$('.clicks_supp_and_tids_div').empty();
	$('.clicks_supp_and_tids_div').append(
		'<table class="table clicks_supp_and_tids_table"><thead><tr><th>CATEGORY</th><th>COUNT</th></tr></thead><tbody class="clicks_supp_and_tids_tbody"></tbody></table>'
	);

} //clearExistingData_clicks

function updateCCOHireCount(startDate, endDate) {

	var HireDate = {
		"from_date": startDate, "to_date": endDate
	}

	$.ajax({
		type: "POST",
		url: "../reporting/validhires",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(HireDate),
		success: function(data) {
			getDataFromGetCCOHireByDateController(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getDataFromGetCCOHireByDateController(data) {

		var ccoHireList = data.filter(i => i.workType == "CCO");
		var ccoHireCounts = ccoHireList.length;
		$('.ytdCcoCounts_value').text(ccoHireCounts);

		$('.ccoModalBody').empty();
		var table = '<table  class="ccoYtdTable table-striped table-bordered" style="width: 100%"><thead></thead><tbody></tbody></table>'
		$('.ccoModalBody').append(table);
		var thead = '<tr class="headings"><th class="columnhidden">Workday ID</th><th class="column-title">Name</th><th class="column-title">Previous Job Profile</th><th class="column-title">New Job Profile</th><th class="column-title">Method</th><th class="column-title">Date of use</th></tr>';
		$('.ccoModalBody').find('thead').append(thead);

		for (var i = 0; i < ccoHireList.length; i++) {

			var row = '<tr><td>' + ccoHireList[i].emp_id + '</td><td>' + ccoHireList[i].first_name + ' ' + ccoHireList[i].last_name + '</td><td>' + ccoHireList[i].previousJobProfile + '</td><td>' + ccoHireList[i].newJobProfile + '</td><td>' + ccoHireList[i].method + '</td><td>' + ccoHireList[i].dateUsed + '</td></tr>'
			$('.ccoModalBody').find('tbody').append(row);
		}

		init_dataTable_ytdModalTable('.ccoYtdTable');

	} //getDataFromGetCCOHireByDateController
} //updateCCOHireCount

function updateTidsSuppHireCount(startDate, endDate) {

	var HireDate = {
		"from_date": startDate, "to_date": endDate
	}

	$.ajax({
		type: "POST",
		url: "../reporting/validhires",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify(HireDate),
		success: function(data) {
			console.log(data);
			getTidsSuppHireByDate(data);
		},
		error: function(err) {
			console.log(err.responseText);
		}
	});

	function getTidsSuppHireByDate(data) {

		var tidsHirelist = data.filter(i => i.workType == "DS");
		var suppHirelist = data.filter(i => i.workType == "ST");
		var tidsHireCounts = tidsHirelist.length;
		var suppHireCounts = suppHirelist.length;

		$('.ytdTidsCounts_value').text(tidsHireCounts);
		$('.ytdSupportCounts_value').text(suppHireCounts);

		$('.tidsModalBody').empty();
		var table = '<table  class="tidsYtdTable table-striped table-bordered" style="width: 100%"><thead></thead><tbody></tbody></table>'
		$('.tidsModalBody').append(table);
		var thead = '<tr class="headings"><th class="columnhidden">Workday ID</th><th class="column-title">Name</th><th class="column-title">Previous Job Profile</th><th class="column-title">New Job Profile</th><th class="column-title">Method</th><th class="column-title">Date of use</th></tr>';
		$('.tidsModalBody').find('thead').append(thead);

		$('.supportModalBody').empty();
		var table = '<table  class="supportYtdTable table-striped table-bordered" style="width: 100%"><thead></thead><tbody></tbody></table>'
		$('.supportModalBody').append(table);
		var thead = '<tr class="headings"><th class="columnhidden">Workday ID</th><th class="column-title">Name</th><th class="column-title">Previous Job Profile</th><th class="column-title">New Job Profile</th><th class="column-title">Method</th><th class="column-title">Date of use</th></tr>';
		$('.supportModalBody').find('thead').append(thead);

		for (var i = 0; i < tidsHirelist.length; i++) {

			var row = '<tr><td>' + tidsHirelist[i].emp_id + '</td><td>' + tidsHirelist[i].first_name + ' ' + tidsHirelist[i].last_name + '</td><td>' + tidsHirelist[i].previousJobProfile + '</td><td>' + tidsHirelist[i].newJobProfile + '</td><td>' + tidsHirelist[i].method + '</td><td>' + tidsHirelist[i].dateUsed + '</td></tr>'
			$('.tidsYtdTable').find('tbody').append(row);
		}
		init_dataTable_ytdModalTable('.tidsYtdTable');

		for (var i = 0; i < suppHirelist.length; i++) {
			var row = '<tr><td>' + suppHirelist[i].emp_id + '</td><td>' + suppHirelist[i].first_name + ' ' + suppHirelist[i].last_name + '</td><td>' + suppHirelist[i].previousJobProfile + '</td><td>' + suppHirelist[i].newJobProfile + '</td><td>' + suppHirelist[i].method + '</td><td>' + suppHirelist[i].dateUsed + '</td></tr>'
			$('.supportYtdTable').find('tbody').append(row);
		}
		init_dataTable_ytdModalTable('.supportYtdTable');

	} //getTidsSuppHireByDate
} //updateTidsSuppHireCount


function load_ytdCards() {

	var pillarList = ['.ytdCcoCountsMainContainer', '.ytdTidsCountsMainContainer', '.ytdSupportCountsMainContainer'];
	pillarList.forEach(ytdCardsOnHover);

	function ytdCardsOnHover(item, index) {
		$(item).css("cursor", "pointer");
		$(item).mouseenter(function() {
			$(this).css("box-shadow", "rgba(83, 16, 132, 0.76) 0px 2px 2px 0px, rgba(57, 20, 97, 0.62) 0px 1px 1px 0px, rgba(81, 14, 150, 0.84) 0px 1px 5px 0px");
		});
		$(item).mouseleave(function() {
			$(this).css("box-shadow", "rgba(0, 0, 0, 0.14) 0px 2px 2px 0px, rgba(0, 0, 0, 0.2) 0px 1px 1px 0px, rgba(0, 0, 0, 0.12) 0px 1px 5px 0px");
		});
	}

	$('.ytdCcoCountsMainContainer').click(() => $('#ccoModal').modal('show'));
	$('.ytdTidsCountsMainContainer').click(() => $('#tidsModal').modal('show'));
	$('.ytdSupportCountsMainContainer').click(() => $('#supportModal').modal('show'));

} //load_ytdCards

$(document).ajaxStart(function() {
	$(".loading").show();
}).ajaxStop(function() {
	$(".loading").hide();
});

$(document).ready(function() {

	var defaultValues = init_defaultValues();
	var defaultStartDate = defaultValues.from_date;
	var defaultEndDate = defaultValues.to_date;

	//initialize data
	$('.fromDate_usageReport').val(defaultStartDate);
	$('.toDate_usageReport').val(defaultEndDate);
	$('.fromDate_surveyResult').val(defaultStartDate);
	$('.toDate_surveyResult').val(defaultEndDate);

	/*Usage Tab*/
	usageFilter_site();
	usageFilter_functionalArea("all");
	usageFilter_manager("all");
	usageFilter_supervisor("all");
	init_usageTraffic(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	init_usageReportDougnut(defaultStartDate, defaultEndDate, "all", "all", "all", "all");

	/*SurveyResults Tab*/
	surveyResultFilter_site();
	surveyResultFilter_functionalArea("all");
	surveyResultFilter_manager("all");
	surveyResultFilter_supervisor("all");
	init_customBarGraph(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	init_surveyResultDougnut(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableSurveyPrefWorkTids(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableSurveyPrefWorkSupport(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	updateCCOHireCount(defaultStartDate, defaultEndDate);
	updateTidsSuppHireCount(defaultStartDate, defaultEndDate);

	/*CareerCityCLicks Tab*/
	clicksFilter_site();
	clicksFilter_functionalArea("all");
	clicksFilter_manager("all");
	clicksFilter_supervisor("all");
	load_TableClicksPreferredSites(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksPreferredPrograms(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksInDemandBusinessTypes(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksInDemandWorkTypeCCO(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksPrefSuppJobs(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksPrefSuppDept(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksInDemandJobProfile(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_TableClicksSupportAndTids(defaultStartDate, defaultEndDate, "all", "all", "all", "all");
	load_ytdCards();

});